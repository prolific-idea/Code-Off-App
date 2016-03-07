(function () {
    'use strict';

    angular.module('codeOffChallengeAdmin.customNgChange', []);

    angular.module('codeOffChallengeAdmin.customNgChange')
        .directive('customNgChange', function () {
            return {
                restrict: 'A',
                link: function (scope, element, attrs) {
                    var onChangeHandler = scope.$eval(attrs.customNgChange);
                    element.bind('change', onChangeHandler);
                }
            };
        });
})();
