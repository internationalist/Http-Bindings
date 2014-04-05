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
Object.prototype.extend = function(parent) {
Object.protoype=Object.create(parent.prototype);
Object.prototype.constructor=parent;
Object.prototype.parent=parent;
};



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
	this.id;
	this.values=[];
	this.name="default";
	this.type;	
}

View.prototype.render=function(data) {
	if(this.impl == "remote") {
		var ph = document.getElementById(this.placeholder);
		ph.innerHtml = data;
	}	
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