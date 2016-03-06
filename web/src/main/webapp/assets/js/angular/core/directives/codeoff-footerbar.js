(function () {
    'use strict';

    angular.module('codeOffFooter')
        .directive('codeOffFoot', function () {
            return {
                restrict: 'E',
                templateUrl: 'assets/js/angular/core/templates/footer.html'
            }
        });
}());