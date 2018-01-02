(function (angular) {
    'use strict';
    angular.module('app').factory('usersApi', getUsersApi);

    getUsersApi.$inject = ['$http'];

    function getUsersApi($http) {

        return {
            createUser: createUser,
            getUserProfile: getUserProfile,
            getFriendsFor: getFriendsFor,
            getOthersFor: getOthersFor,
            addFriend: addFriend,
            removeFriend: removeFriend
        };

        function createUser(registration) {
            return $http.post('/friend-manager/api/users', registration)
                .then(function (response) {
                    return response.data;
                });
        }

        function getUserProfile(userId) {
            return $http.get('/friend-manager/api/users/' + userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function getFriendsFor(userId) {
            return $http.get('/friend-manager/api/users/' + userId + '/friends')
                .then(function (response) {
                    return response.data;
                });
        }

        function getOthersFor(userId) {
            return $http.get('/friend-manager/api/users/' + userId + '/others')
                .then(function (response) {
                    return response.data;
                });
        }

        function addFriend(userId, friendId) {
            return $http.post('/friend-manager/api/users/' + userId + '/friends/' + friendId)
                .then(function (response) {
                    return response.data;
                });
        }

        function removeFriend(friendShipId) {
            return $http.delete('/friend-manager/api/friendships/' + friendShipId)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})(window.angular);