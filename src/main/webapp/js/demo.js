/**
 * 
 */

 Nav.extend(View);



function Nav(config) {
	Nav.prototype.parent.call(this, config);

}

//Nav.prototype = Object.create(View.prototype);
//Nav.prototype.constructor=View;

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