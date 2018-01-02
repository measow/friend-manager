(function (angular) {
    'use strict';
    angular.module('app').config(['$routeProvider', getRoutes]);

    function getRoutes($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'app/dashboard.html'
            })
            .when('/home', {
                template: '<fm-dashboard></fm-dashboard>'
            })
            .when('/user-profile/:userId', {
                template: '<fm-user-profile></fm-user-profile>'
            })
            .when('/login', {
                template: '<fm-login></fm-login>'
            });
    }
})(window.angular);