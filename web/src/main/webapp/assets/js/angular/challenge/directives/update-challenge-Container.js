(function () {
    'use strict';

    angular.module('codeOffChallengeAdmin.updateChallenge', []);

    angular.module('codeOffChallengeAdmin.updateChallenge')
        .directive('updateChallenge', function () {
            return {
                restrict: 'E',
                templateUrl: 'templates/updateChallenge.html'
            }
        });
}());