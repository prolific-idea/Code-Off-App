(function () {
    'use strict';

    angular.module('codeOffChallengeAdmin.createChallenge',[]);

    angular.module('codeOffChallengeAdmin.createChallenge')
        .directive('createChallenge', function () {
            return {
                restrict: 'E',
                templateUrl: 'templates/createChallenge.html'
            }
        });
}());