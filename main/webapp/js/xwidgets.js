/**
 * 
 */
function Model(config) {
	this.url = config.url;
	this.type=(config.type!=null)?config.type:{};
	this.views = new Array();
	this.updateView = function(data) {
		
		for(var i = 0;i <this.views.length;i++) {
			this.views[i].render(data);
		}
	}
	
	this.addView = function(view) {
		this.views.push(view);
	}
	
	this.connect = function() {
		var ajax = new Ajax();
		ajax.post(this.url, this.type, this, this.updateView, false);
	}
	
	this.onTimer = function(activate) {
		if(activate) {
			window.setInterval(function() {this.connect()}, config.frequency);
		}
	}
}

function View(config) {
	this.impl = (config.impl!=null)?config.impl:"remote";
	this.placeholder = config.placeholder;
}

View.prototype.render=function(data) {
	if(this.impl == "remote") {
		var ph = document.getElementById(this.placeholder);
		ph.innerHtml = data;
	}	
}

function Nav(config) {
	View.call(this, config);
}

Nav.prototype = Object.create(View.prototype);
Nav.prototype.constructor=View;

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


function Ajax() {
	this.x = function() {
	    if (typeof XMLHttpRequest !== 'undefined') {
	        return new XMLHttpRequest();  
	    }
	    var versions = [
	        "MSXML2.XmlHttp.5.0",   
	        "MSXML2.XmlHttp.4.0",  
	        "MSXML2.XmlHttp.3.0",   
	        "MSXML2.XmlHttp.2.0",  
	        "Microsoft.XmlHttp"
	    ];

	    var xhr;
	    for(var i = 0; i < versions.length; i++) {  
	        try {  
	            xhr = new ActiveXObject(versions[i]);  
	            break;  
	        } catch (e) {
	        }  
	    }
	    return xhr;
	};
	
	this.send = function(url, callBackHandler, callback, method, data, sync) {
	    var x = this.x();
	    x.open(method, url, sync);
	    x.onreadystatechange = function() {
	        if (x.readyState == 4) {
	            callback.call(callBackHandler, x.responseText);
	        }
	    };
	    if (method == 'POST') {
	        x.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	    }
	    x.send(data)
	};
	
	this.get = function(url, data, callBackHandler, callback, sync) {
	    var query = [];
	    for (var key in data) {
	        query.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]));
	    }
	    this.send(url + '?' + query.join('&'), callBackHandler, callback, 'GET', null, sync)
	};

	this.post = function(url, data, callBackHandler, callback, sync) {
	    var query = [];
	    for (var key in data) {
	        query.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]));
	    }
	    this.send(url, callBackHandler, callback, 'POST', query.join('&'), sync)
	};	
		
}