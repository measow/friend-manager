(function (angular) {
    'use strict';
    angular.module('app').config(['$routeProvider', getRoutes]);

    function getRoutes($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : 'app/home.html'
            })
            .when('/home', {
                templateUrl : 'app/home.html'
            })
            .when('/user-profile', {
                templateUrl : 'app/user-profile.html'
            })
            .when('/login', {
                templateUrl : 'app/login.html'
            });
    }
})(window.angular);