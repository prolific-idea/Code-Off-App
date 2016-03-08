(function () {
    'use strict';

    angular.module('codeOffChallengeAdmin.navBar',[]);

    angular.module('codeOffChallengeAdmin.navBar')
        .directive('adminNavBar', function () {
            return {
                restrict: 'E',
                templateUrl: 'templates/navigation.html'
            }
        });
}());