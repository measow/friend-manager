(function (angular) {
    'use strict';
    angular.module('app').directive('fmNavigation', getNavigation);

    getNavigation.$inject = ['$location', 'currentUser', 'sessionStorageApi'];

    function getNavigation($location, currentUser, sessionStorageApi) {
        return {
            restrict: 'E',
            controller: NavigationController,
            controllerAs: 'vm',
            templateUrl: 'app/navigation.html'
        };

        function NavigationController() {
            var vm = this;
            vm.logout = logout;
            vm.currentUser = currentUser;
            var SESSION_KEY = 'FM_SESSION_KEY';

            function logout() {
                //TODO: destroy from local storage
                sessionStorageApi.remove(SESSION_KEY);
                currentUser.destroy();
                $location.path('/login');
            }
        }
    }
})(window.angular);