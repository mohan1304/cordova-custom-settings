/*global cordova, module*/

module.exports = {
    settings: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SettingsPlugin", "settings", [name]);
    }
	getPreference: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SettingsPlugin", "getPreference", [name]);
    }
};
