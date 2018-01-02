(function (angular) {
    'use strict';
    angular.module('app', ['ngRoute'])
        .run(function (sessionStorageApi, currentUser) {
            var SESSION_KEY = 'FM_SESSION_KEY';
            var storedUser = sessionStorageApi.getObject(SESSION_KEY);
            if(currentUser) {
                currentUser.changeUser(storedUser);
            }
        });
})(window.angular);