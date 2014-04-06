/**The MIT License (MIT)

Copyright (c) <year> <copyright holders>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * 
 */

	 
/*
 * Initialization code to setup subclassing.
 */
<<<<<<< HEAD
Function.prototype.extend = function(parent) {
	if ( parent.constructor == Function ) {	
		this.prototype=Object.create(parent.prototype);
		this.prototype.constructor=this;
		this.prototype.parent=parent;
	} else{
		//Pure Virtual Inheritance 
		this.prototype = parent;
		this.prototype.constructor = this;
		this.prototype.parent = parent;		
	}
};

//global static object model event queue.
var modelEventQueue = new Object();

=======
Object.prototype.extend = function(parent) {
Object.protoype=Object.create(parent.prototype);
Object.prototype.constructor=parent;
Object.prototype.parent=parent;
};

>>>>>>> 417cf62f2f203bca0e404df112055f6ee0ca5063


function Model(config) {
 
	this.url = config.url;
	this.type=(config.type!=null)?config.type:{};
	this.views = new Array();
	this.submitData = new Object();
	if(config.keyname!=null) {
		modelEventQueue[config.keyname]=this;
	}
	
	this.updateView = function(data) {
		for(var i = 0;i <this.views.length;i++) {
			this.views[i].render(data);
		}
	}
	
	this.updateResponse = function(data) {
		for(var i = 0;i <this.views.length;i++) {
			this.views[i].onUpdateResponse(data);
		}
	}
	
	this.update = function() {
		for(var i = 0, tot = this.views.length; i <tot;i++){
			this.submitData[this.views[i].name]=this.views[i].values;
		}
		var ajax = new Ajax();
		ajax.post(this.url, this.submitData, this, this.updateResponse, false);
	}	
	
	
	this.addView = function(view) {
		this.views.push(view);
	}
	
	this.init = function() {
		var ajax = new Ajax();
		ajax.post(this.url, this.type, this, this.updateView, false);
	}
	
/*	this.onTimer = function(activate) {
		if(activate) {
			window.setInterval(function() {this.connect()}, config.frequency);
		}
	}*/
}




function View(config) {
	this.impl = (config.impl!=null)?config.impl:"remote";
	this.placeholder = config.placeholder;
<<<<<<< HEAD
	this.values=[];
	this.name="default";
	this.type;
	this.modelkeyname;
=======
	this.id;
	this.values=[];
	this.name="default";
	this.type;	
>>>>>>> 417cf62f2f203bca0e404df112055f6ee0ca5063
}

View.prototype.render=function(data) {
	if(this.impl == "remote") {
		var ph = document.getElementById(this.placeholder);
		ph.innerHtml = data;
	}	
};

<<<<<<< HEAD
View.prototype.onUpdateResponse=function(data) {
	console.log(data);
};


/*function Form(url) {
	this.formObjects = new Array();
	this.addFormObject(formObject) {
		formObjects.push(formObject);
	}
	this.url=url;
	this.type="POST";
	this.submit = function() {
		var data = new Object();
		for(var i = 0, var tot = formObjects.length; i <tot;i++){
			data[formObjects.name]=formObjects.values;
		}
		var ajax = new Ajax();
		ajax.post(this.url, this.type, this, this.handleResponse, false);
	}
	
	this.handleResponse = function(data) {
		console.log(data);
	}
}*/
=======





>>>>>>> 417cf62f2f203bca0e404df112055f6ee0ca5063

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
	            callback.call(callBackHandler, JSON.parse(x.responseText));
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