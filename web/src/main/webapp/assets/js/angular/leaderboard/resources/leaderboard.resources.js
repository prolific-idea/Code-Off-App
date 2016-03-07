(function () {
	'use strict';

	angular.module("codeOffApp")
		.factory("Leaderboards", function ($resource) {
			return $resource("/api/leaderboard", null, {
				getPersons: {
					method:  "GET",
					isArray: true
				},

				getPageOfLeaderboard: {
					method: "GET",
					params: {
						pageSize: "@pageSize",
						pageNum:  "@pageNum"
					}
				}
			});
		});
}());
