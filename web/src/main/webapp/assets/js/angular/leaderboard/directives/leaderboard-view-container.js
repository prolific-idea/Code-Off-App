(function () {
	'use strict';

	angular.module('codeOffApp.leaderboard', []);

	angular.module('codeOffApp.leaderboard')
		.directive('leaderboardView', function () {
			return {
				restrict: 'E',
				templateUrl: 'assets/js/angular/leaderboard/templates/leaderboard.html'
			}
		});
})();
