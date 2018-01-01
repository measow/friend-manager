(function (angular) {
    'use strict';
    angular.module('app').directive('fmDashboard', getDashboard);

    getDashboard.$inject = ['$routeParams', 'usersApi'];

    function getDashboard($routeParams, usersApi) {
        return {
            restrict: 'E',
            controller: DashboardController,
            controllerAs: 'vm',
            templateUrl: 'app/dashboard.html'
        };

        function DashboardController() {
            var vm = this;
            vm.addFriend = addFriend;
            vm.removeFriend = removeFriend;
            vm.friends = null;
            vm.others = null;

            usersApi.getUserProfile($routeParams.userId)
                .then(function (userProfile) {
                    vm.userProfile = userProfile;
                });

            usersApi.getFriendsFor($routeParams.userId)
                .then(function (friends) {
                    vm.friends = friends;
                });

            usersApi.getOthersFor($routeParams.userId)
                .then(function (others) {
                    vm.others = others;
                });

            function addFriend(other) {
                if (other) {
                    usersApi.addFriend(vm.userProfile.userId, other.userId)
                        .then(function (newFriend) {
                            vm.others.splice(other, 1);
                            vm.friends.push(newFriend);
                        });
                }
            }

            function removeFriend(friend) {
                if (friend) {
                    usersApi.removeFriend(friend.friendshipId)
                        .then(function () {
                            vm.friends.splice(friend, 1);
                            vm.others.push(friend);
                        });
                }
            }
        }
    }
})(window.angular);