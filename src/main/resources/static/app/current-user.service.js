(function (angular) {
    'use strict';
    angular.module('app').service('currentUser', CurrentUser);

    CurrentUser.$inject = [];

    function CurrentUser() {

        this.userId = null;
        this.name = null;
        this.alias = null;
        this.email = null;
        this.dateOfBirth = null;

        this.isLoggedIn = function isLoggedIn() {
            return (this.userId > 0);
        };

        this.changeUser = function changeUser(data) {
            if(data) {
                this.userId = data.userId;
                this.name = data.name;
                this.alias = data.alias;
                this.email = data.email;
                this.dateOfBirth = data.dateOfBirth;
            }
        };

        this.destroy = function destroy() {
            this.userId = null;
            this.name = null;
            this.alias = null;
            this.email = null;
            this.dateOfBirth = null;
        }
    }
})(window.angular);