var exec = require('cordova/exec');

function ThumbSignIn () {

}



function callback(fn) {
    return function(str) {
	try {
        	fn && fn(JSON.parse(str));
        } catch(e) {
        	fn && fn(str);
        }
    };
}

ThumbSignIn.prototype.isAvailable = function (successCallback, errorCallback) {
    exec(callback(successCallback), callback(errorCallback), 'ThumbSignInService', 'isAvailable', []);
};

ThumbSignIn.prototype.register = function (userId, successCallback, errorCallback) {
    exec(callback(successCallback), callback(errorCallback), 'ThumbSignInService', 'register', [userId + ""]);
};


ThumbSignIn.prototype.login = function (successCallback, errorCallback) {
    exec(callback(successCallback), callback(errorCallback), 'ThumbSignInService', 'login', []);
};

ThumbSignIn.prototype.deregister = function (successCallback, errorCallback) {
    exec(callback(successCallback), callback(errorCallback), 'ThumbSignInService', 'deregister', []);
};


module.exports = new ThumbSignIn();
