(function(){
	'use strict';

	angular.module('codeOffNavigation').directive('codeOffNav', function() {
		return {
			restrict: 'E',
			templateUrl: 'js/angular/core/templates/navigation.html'
		}
	});
}());