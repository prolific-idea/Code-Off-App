(function () {
    'use strict';

    angular.module('codeOffNavigation')
        .directive('codeOffNav', function () {
            return {
                restrict: 'E',
                templateUrl: 'assets/js/angular/core/templates/navigation.html'
            }
        });
}());