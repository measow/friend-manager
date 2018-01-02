(function (angular) {
    'use strict';
    angular.module('app').directive('fmLogin', getLogin);

    getLogin.$inject = ['$location', 'securityApi', 'usersApi', 'currentUser'];

    function getLogin($location, securityApi, usersApi, currentUser) {
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

            function loginUser(credentials) {
                // TODO: handle invalid login
                if (credentials) {
                    securityApi.validateUser(credentials)
                        .then(function (user) {
                            // TODO: store user somewhere
                            currentUser.changeUser(user);
                            $location.path('/home');
                        });
                }
            }

            function registerUser(registration) {
                // TODO: handle invalid registration
                if (registration) {
                    usersApi.createUser(registration)
                        .then(function () {
                            // TODO: store user somewhere
                            currentUser.changeUser(registration);
                            $location.path('/home');
                        });
                }
            }
        }
    }
})(window.angular);