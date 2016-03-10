//(function () {
//	'use strict';
//
//	angular.module("codeOffApp").controller("leaderboardController", leaderboardCtrl);
//
//	leaderboardCtrl.$inject = ["$http", "$log", "Leaderboards", "Leaderboard"];
//
//	function leaderboardCtrl($http, $log, Leaderboards, Leaderboard) {
//		var ctrl = this;
//
//		ctrl.maxSize = 10; //links in pagination shown
//		ctrl.bigTotalItems = 35; //total number of items
//		ctrl.bigCurrentPage = 1;
//		ctrl.leaderboard = [];
//		ctrl.persons = [];
//
//		ctrl.refresh = function() {
//			Leaderboards.getPersons().$promise.then(
//				function getPersonsArray(response) {
//					var responseData = response;
//					console.log(responseData);
//
//					ctrl.persons = [];
//					for (var i = 0; i < responseData.length; i++) {
//						ctrl.persons.push(responseData[i]);
//					}
//				}, $log.error
//			);
//
//			Leaderboard.getLeaderboard().$promise.then(
//				function getLeaderboardArray(response) {
//					var responseData = response;
//					console.log(responseData);
//
//					ctrl.leaderboard = [];
//					for (var i = 0; i < responseData.length; i++) {
//						ctrl.leaderboard.push(responseData[i]);
//					}
//				}, $log.error
//			);
//
//			//Leaderboards.getPageOfLeaderboard({pageSize: ctrl.bigTotalItems, pageNum: ctrl.bigCurrentPage}).$promise.then(
//			//	function resetLeaderboard(response) {
//			//		var responseData = response;
//			//		console.log(responseData);
//			//
//			//		ctrl.bigTotalItems = responseData.Total / 2;
//			//		ctrl.bigCurrentPage = responseData.Page;
//			//		ctrl.leaderboard = [];
//			//
//			//		for (var i = 0; i < responseData.length; i++) {
//			//			ctrl.leaderboard.push(responseData[i]);
//			//		}
//			//	}, $log.error
//			//);
//		}
//
//		ctrl.refresh();
//	}
//}());

(function () {
	var app = angular.module("codeOffLeaderboard");

	app.controller("viewLeaderboardController",
		function ($scope, $http, $log, Coders, PersonCount, Challenges) {
			var ctrl = $scope;
			var pageSize = 20;
			ctrl.pageNum = 1;
			ctrl.coders = [];

			ctrl.searchTerm = "";
			ctrl.searchActions = [
				{id: 'filter', name: 'Filter'},
				{id: 'all', name: 'All'},
				{id: 'challenge', name: 'Challenge'},
				{id: 'technology', name: 'Technology'}
			];
			ctrl.selectedSearch = ctrl.searchActions[0];

			ctrl.setSearchAction = function (action) {
				console.log(action);
				ctrl.selectedSearch = action;
				ctrl.searchFor(action);
			}

			ctrl.searchFor = function (actionId) {
				console.log(ctrl.selectedSearch.id);
				console.log("Searching for: " + actionId.id + " and search term: " + ctrl.searchTerm);
				if (actionId.id === "filter") {
					console.log(actionId.id);
				}
				else if (actionId.id === "all") {
					console.log(actionId.id + "not supported");
				}
				else if (actionId.id === "challenge") {
					console.log(actionId.id + " found");
					ctrl.codersTemp = Challenges.getChallenges({id: ctrl.searchTerm, pageNum: ctrl.pageNum, pageSize: pageSize});
					ctrl.personCountTemp = PersonCount.count();
					ctrl.personCountTemp.$promise.then(function () {
						ctrl.countOfCoders = ctrl.personCountTemp;
						ctrl.countOfCoders = ctrl.countOfCoders.countOfPerson;
					}, $log.error);
					ctrl.codersTemp.$promise.then(function () {
						ctrl.coders = ctrl.codersTemp;
						ctrl.ChangeTechDescription();
						console.log(ctrl.coders);
						var lastPage = Math.ceil(ctrl.countOfCoders / pageSize);
						if (lastPage === ctrl.pageNum)
							ctrl.CanNotNext = true;
					}, $log.error);
				} else {
					console.log(actionId + " unknown");
				}
			}

			ctrl.codersTemp = Coders.getPagedLeaderboard({pageNum: ctrl.pageNum, pageSize: pageSize});
			ctrl.personCountTemp = PersonCount.count();
			ctrl.personCountTemp.$promise.then(function () {
				ctrl.countOfCoders = ctrl.personCountTemp;
				ctrl.countOfCoders = ctrl.countOfCoders.countOfPerson;
			}, $log.error);
			ctrl.codersTemp.$promise.then(function () {
				ctrl.coders = ctrl.codersTemp;
				ctrl.ChangeTechDescription();
				console.log(ctrl.coders);
				var lastPage = Math.ceil(ctrl.countOfCoders / pageSize);
				if (lastPage === ctrl.pageNum)
					ctrl.CanNotNext = true;
			}, $log.error);

			ctrl.ToNextPage = function () {
				ctrl.pageNum += 1;
				ctrl.codersTemp = Coders.getPagedLeaderboard({pageNum: ctrl.pageNum, pageSize: pageSize});
				ctrl.codersTemp.$promise.then(function () {
					ctrl.coders = ctrl.codersTemp;
					ctrl.ChangeTechDescription();
					var lastPage = Math.ceil(ctrl.countOfCoders / pageSize);
					ctrl.CanNotNext = lastPage === ctrl.pageNum;
				}, $log.error);
			};

			ctrl.ToPrevPage = function () {
				if (ctrl.pageNum != 1) {
					ctrl.pageNum -= 1;
					ctrl.codersTemp = Coders.getPagedLeaderboard({pageNum: ctrl.pageNum, pageSize: pageSize});
					ctrl.codersTemp.$promise.then(function () {
						ctrl.coders = ctrl.codersTemp;
						ctrl.ChangeTechDescription();
						var lastPage = Math.ceil(ctrl.countOfCoders / pageSize);
						ctrl.CanNotNext = lastPage === ctrl.pageNum;
					}, $log.error);
				}
			};

			ctrl.ChangeTechDescription = function () {
				for (var i = 0; i < ctrl.coders.length; i++) {
					console.log(ctrl.coders[i].techs);
					for (var j = 0; j < ctrl.coders[i].techs.length; j++) {
						console.log(ctrl.coders[i].techs[j]);

						if (ctrl.coders[i].techs[j].description === "C#") {
							console.log(true);
							ctrl.coders[i].techs[j].description = "Csharp";
						}
						if (ctrl.coders[i].techs[j].description === "C++") {
							console.log(true);
							ctrl.coders[i].techs[j].description = "Cplusplus";
						}
						if (ctrl.coders[i].techs[j].description === "Objective-C") {
							console.log(true);
							ctrl.coders[i].techs[j].description = "objc";
						}

						ctrl.coders[i].techs[j].description = ctrl.coders[i].techs[j].description.toLowerCase();
					}
				}
			}
		});
})();
