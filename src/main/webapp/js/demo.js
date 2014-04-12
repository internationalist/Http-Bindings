/**
 * 
 */

 Nav.extend(View);

function Nav(config) {
	Nav.prototype.parent.call(this, config);
}

Nav.prototype.preProcess=function(data) {
	var ret = new String(data);
	ret = ret.replace("[", "");
	ret = ret.replace(/\"/g, "");
	ret = ret.replace("]", "");
	console.log(ret);
	return ret.split(",");	
}

Nav.prototype.render=function(data) {
	var ph = document.getElementById(this.placeholder);
	var list = document.createElement("ul");
	var datArr = this.preProcess(data);
	
	for(var i = 0; i <datArr.length; i++) {
		var listElement = document.createElement("li");
		var a = document.createElement("a");
		var listContent = document.createTextNode(datArr[i]);
		a.appendChild(listContent);
		listElement.appendChild(a);
		list.appendChild(listElement);
	}
	ph.appendChild(list);
}

PagedView.extend(View);

function PagedView(config) {
	PagedView.prototype.parent.call(this, config);
	this.name="pageNumber";
	this.values.push(1);
	this.modelkeyname=config.modelkeyname;
	this.currentPage;
	this.totalPages;
}

PagedView.prototype.onUpdateResponse=function(data) {
	this.pagepane.innerHTML=data.pageText;
	if(data.currentPage==1) {
		this.inputButton1.disabled=true;
	} else {
		this.inputButton1.disabled=false;
	}
	if(data.currentPage==data.numPages) {
		this.inputButton2.disabled=true;
	} else {
		this.inputButton2.disabled=false;
	}
	this.currentPage=data.currentPage;
	this.totalPages=data.numPages;
	this.span2.innerHTML = "Page " + data.currentPage + " of " + data.numPages;
}

PagedView.prototype.render=function(data) {
	var ph = document.getElementById(this.placeholder);
	this.pagepane = document.createElement("div");
	this.pagepane.className="pagedpanecontent";
	this.pagepane.innerHTML=data.pageText;
	ph.appendChild(this.pagepane);	
	this.inputButton1 = this.createToolBarButton(ph, "Previous", "prev");
	var me=this;
	this.currentPage=data.currentPage;
	this.totalPages=data.numPages;
	this.inputButton1.onclick=function() {
		if(me.currentPage > 1) {
			me.values=[];
			me.values.push(me.currentPage - 1);
			//get the model from the queue.
			var model = modelEventQueue[me.modelkeyname];
			model.update();
		}
	};
	
	if(data.currentPage==1) {
		this.inputButton1.disabled=true;
	}
	
	this.inputButton2 = this.createToolBarButton(ph, "Next", "nxt");
	
	if(data.currentPage==data.numPages) {
		this.inputButton2.disabled=true;
	}
	
	this.inputButton2.onclick=function() {
		if(me.currentPage < data.numPages) {
			me.values=[];
			me.values.push(me.currentPage + 1);
			//get the model from the queue.
			var model = modelEventQueue[me.modelkeyname];
			model.update();
		}
	};

	
	var toolbarDiv3 = document.createElement("div");
	toolbarDiv3.className="toolbar";
	var span = document.createElement("span");
	span.innerHTML="Go To:";
	span.className="ctrl";
	var inputButton3 = document.createElement("input");
	inputButton3.type="button";
	inputButton3.className="button_small";
	inputButton3.value="Go";
	var inputText = document.createElement("input");
	inputText.className="pager";
	inputText.type="text";
	toolbarDiv3.appendChild(span);
	toolbarDiv3.appendChild(inputText);	
	toolbarDiv3.appendChild(inputButton3);
	ph.appendChild(toolbarDiv3);
	
	inputButton3.onclick=function() {
		if(inputText.value >= 1 && inputText.value <= data.numPages) {
			me.values=[];
			me.values.push(inputText.value);
			//get the model from the queue.
			var model = modelEventQueue[me.modelkeyname];
			model.update();			
		}
	}
	
	var toolbarDiv4 = document.createElement("div");
	toolbarDiv4.className="toolbar";
	this.span2 = document.createElement("span");
	this.span2.innerHTML = "Page " + data.currentPage + " of " + data.numPages;
	toolbarDiv4.appendChild(this.span2);
	ph.appendChild(toolbarDiv4);
}

PagedView.prototype.createToolBarButton=function(ph, buttonName, id) {
	var inputButton = document.createElement("input");
	inputButton.type="button";
	inputButton.className="button";
	inputButton.value=buttonName;
	inputButton.id=id;
	var toolbarDiv = document.createElement("div");
	toolbarDiv.className="toolbar";
	toolbarDiv.appendChild(inputButton);
	ph.appendChild(toolbarDiv);
	return inputButton;
}