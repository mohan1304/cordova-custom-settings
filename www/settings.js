/*global cordova, module*/

module.exports = {
    settings: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SettingsPlugin", "settings", [name]);
    },
    getPreferences: function (names, successCallback, errorCallback) {
            cordova.exec(successCallback, errorCallback, "SettingsPlugin", "getPreferences", names);
    }
};
