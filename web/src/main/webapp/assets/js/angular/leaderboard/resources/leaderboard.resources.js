(function () {
	'use strict';

	angular.module("codeOffLeaderboard")
		.factory("Coders", function ($resource) {
			return $resource("/api/leaderboard/board", null, {
				getLeaderboard: {
					method: "GET",
					isArray: true
				},
				getPagedLeaderboard: {
					method: "GET",
					params: {
						pageNum: "@page",
						pageSize: "@size"
					},
					isArray: true
				}
			});
		})
		.factory("PersonCount", function ($resource) {
			return $resource("/api/person/count", null, {
				count: {
					method: "GET"
				}
			});
		})
		.factory("Challenges", function ($resource) {
			return $resource("/api/leaderboard/challenges", null, {
				getChallenges: {
					method: "GET",
					params: {
						id: "@id",
						pageNum: "@page",
						pageSize: "@size"
					},
					isArray: true
				}
			});
		})
		.factory("Technologies", function ($resource) {
			return $resource("/api/leaderboard/tech", null, {
				getTechnologies: {
					method: "GET",
					params: {
						techName: "@techName",
						pageNum: "@page",
						pageSize: "@size"
					},
					isArray: true
				}
			});
		});
}());
