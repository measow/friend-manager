(function (angular) {
    'use strict';
    angular.module('app').directive('fmUserProfile', getUserProfile);

    getUserProfile.$inject = ['$routeParams', 'usersApi'];

    function getUserProfile($routeParams, usersApi) {
        return {
            restrict: 'E',
            controller: UserProfileController,
            controllerAs: 'vm',
            templateUrl: 'app/user-profile.html'
        };

        function UserProfileController() {
            var vm = this;
            usersApi.getUserProfile($routeParams.userId)
                .then(function(userProfile) {
                    vm.userProfile = userProfile;
                });
        }
    }
})(window.angular);