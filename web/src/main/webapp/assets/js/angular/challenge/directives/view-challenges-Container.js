(function () {
    'use strict';

    angular.module('codeOffChallengeAdmin.viewChallenges', []);

    angular.module('codeOffChallengeAdmin.viewChallenges')
        .directive('viewChallenges', function () {
            return {
                restrict: 'E',
                templateUrl: 'templates/viewChallenges.html'
            }
        });
}());