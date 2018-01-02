(function (angular) {
    'use strict';
    angular.module('app').directive('fmLogin', getLogin);

    getLogin.$inject = ['$location', 'securityApi', 'usersApi', 'currentUser', 'sessionStorageApi'];

    function getLogin($location, securityApi, usersApi, currentUser, sessionStorageApi) {
        return {
            restrict: 'E',
            controller: LoginController,
            controllerAs: 'vm',
            templateUrl: 'app/login.html'
        };

        function LoginController() {
            var vm = this;
            vm.loginUser = loginUser;
            vm.registerUser = registerUser;
            var SESSION_KEY = 'FM_SESSION_KEY';

            function loginUser(credentials) {
                // TODO: handle invalid login
                if (credentials) {
                    securityApi.validateUser(credentials)
                        .then(function (existingUser) {
                            currentUser.changeUser(existingUser);
                            sessionStorageApi.setObject(SESSION_KEY, existingUser);
                            $location.path('/home');
                        });
                }
            }

            function registerUser(registration) {
                // TODO: handle invalid registration
                if (registration) {
                    usersApi.createUser(registration)
                        .then(function (newUser) {
                            // TODO: store user somewhere
                            currentUser.changeUser(newUser);
                            sessionStorageApi.setObject(SESSION_KEY, newUser);
                            $location.path('/home');
                        });
                }
            }
        }
    }
})(window.angular);