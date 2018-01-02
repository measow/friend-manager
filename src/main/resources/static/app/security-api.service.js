(function (angular) {
    'use strict';
    angular.module('app').factory('securityApi', getSecurityApi);

    getSecurityApi.$inject = ['$http'];

    function getSecurityApi($http) {

        return {
            validateUser: validateUser
        };

        function validateUser(credentials) {
            return $http.post('/friend-manager/api/security/validate-user', credentials)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})(window.angular);