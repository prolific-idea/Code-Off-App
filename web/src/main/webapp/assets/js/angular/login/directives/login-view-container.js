(function () {
    'use strict';

    angular.module('codeOffLogin.loginView',[]);

    angular.module('codeOffLogin.loginView')
        .directive('loginView', function () {
            return {
                restrict: 'E',
                templateUrl: 'templates/loginView.html'
            }
        });
}());