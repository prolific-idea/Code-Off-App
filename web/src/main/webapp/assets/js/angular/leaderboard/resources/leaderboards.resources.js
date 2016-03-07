(function () {
	'use strict';

	angular.module("codeOffApp")
		.factory("Leaderboard", function ($resource) {
			return $resource("/api/leaderboard/le", null, {
				getLeaderboard: {
					method:  "GET",
					isArray: true
				}
			});
		});
}());



