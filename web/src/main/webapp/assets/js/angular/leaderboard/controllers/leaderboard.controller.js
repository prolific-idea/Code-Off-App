<<<<<<< HEAD
(function () {
	'use strict';

	angular.module("codeOffApp").controller("leaderboardController", leaderboardCtrl);

	leaderboardCtrl.$inject = ["$http", "$log", "Leaderboards", "Leaderboard"];

	function leaderboardCtrl($http, $log, Leaderboards, Leaderboard) {
		var ctrl = this;

		ctrl.maxSize = 10; //links in pagination shown
		ctrl.bigTotalItems = 35; //total number of items
		ctrl.bigCurrentPage = 1;
		ctrl.leaderboard = [];
		ctrl.persons = [];

		ctrl.refresh = function() {
			Leaderboards.getPersons().$promise.then(
				function getPersonsArray(response) {
					var responseData = response;
					console.log(responseData);

					ctrl.persons = [];
					for (var i = 0; i < responseData.length; i++) {
						ctrl.persons.push(responseData[i]);
					}
				}, $log.error
			);

			Leaderboard.getLeaderboard().$promise.then(
				function getLeaderboardArray(response) {
					var responseData = response;
					console.log(responseData);

					ctrl.leaderboard = [];
					for (var i = 0; i < responseData.length; i++) {
						ctrl.leaderboard.push(responseData[i]);
					}
				}, $log.error
			);

			//Leaderboards.getPageOfLeaderboard({pageSize: ctrl.bigTotalItems, pageNum: ctrl.bigCurrentPage}).$promise.then(
			//	function resetLeaderboard(response) {
			//		var responseData = response;
			//		console.log(responseData);
			//
			//		ctrl.bigTotalItems = responseData.Total / 2;
			//		ctrl.bigCurrentPage = responseData.Page;
			//		ctrl.leaderboard = [];
			//
			//		for (var i = 0; i < responseData.length; i++) {
			//			ctrl.leaderboard.push(responseData[i]);
			//		}
			//	}, $log.error
			//);
		}

		ctrl.refresh();
	}
}());
=======
(function () {
    var app = angular.module("codeOffLeaderboard");
    app.controller("viewLeaderboardController", function ($scope, $http, $log, Coders) {
        var ctrl = $scope;
        var pageSize = 6;
        ctrl.pageNum = 1;
        ctrl.codersTemp = Coders.getPagedLeaderboard({ pageNum: ctrl.pageNum, pageSize: pageSize });
        ctrl.codersTemp.$promise.then(function () {
            ctrl.coders = ctrl.codersTemp;
            if (ctrl.coders.length <= pageSize)
                ctrl.CanNotNext = true;
        }, $log.error);

        ctrl.ToNextPage = function () {
            ctrl.pageNum += 1;
            ctrl.codersTemp = Coders.getPagedLeaderboard({ pageNum: ctrl.pageNum, pageSize: pageSize });
            ctrl.codersTemp.$promise.then(function () {
                ctrl.coders = ctrl.codersTemp;
                if (ctrl.coders.length <= pageSize)
                    ctrl.CanNotNext = true;
            }, $log.error);
        }

        ctrl.ToPrevPage = function () {
            if (ctrl.pageNum != 1) {
                ctrl.pageNum -= 1;
                ctrl.codersTemp = Coders.getPagedLeaderboard({ pageNum: ctrl.pageNum, pageSize: pageSize });
                ctrl.codersTemp.$promise.then(function () {
                    ctrl.coders = ctrl.codersTemp;
                    if (ctrl.coders.length <= pageSize)
                        ctrl.CanNotNext = true;
                }, $log.error);
            }
        }
    });
})();
>>>>>>> c3752b40dac656fab4628161f1482fecaeb6c295
