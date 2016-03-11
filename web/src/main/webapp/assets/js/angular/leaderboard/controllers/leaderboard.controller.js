(function () {
	var app = angular.module("codeOffLeaderboard");

	app.controller("viewLeaderboardController",
		function ($scope, $http, $log, Coders, PersonCount, Challenges, Technologies) {
			var ctrl = $scope;
			var pageSize = 10;
			ctrl.pageNum = 1;
			ctrl.coders = [];

			ctrl.searchTerm = "";
			ctrl.searchActions = [
				{id: 'filter', name: 'Filter'},
				{id: 'challenge', name: 'Challenge'},
				{id: 'technology', name: 'Technology'}
			];
			ctrl.selectedSearch = ctrl.searchActions[0];

			ctrl.setSearchAction = function (action) {
				console.log(action);
				ctrl.selectedSearch = action;
				ctrl.searchFor(action);
			};

			ctrl.searchFor = function (action) {
				if (action == null) {
					console.log("action is null");
					action = ctrl.selectedSearch;
					console.log("searching for: " + action.name + "with search id: " + ctrl.searchTerm);
				}
				console.log("searching for: " + action.name + "with id: " + ctrl.searchTerm);

				console.log(ctrl.selectedSearch.id);
				console.log("Searching for: " + action.name + " and search term: " + ctrl.searchTerm);
				if (action.name === "Filter") {
					console.log(action.name);
					ctrl.refresh();
				}
				else if (action.name === "Challenge") {
					console.log(action.name + " found");
					console.log("Getting challenges");
					ctrl.codersTemp = Challenges.getChallenges({
						id:       ctrl.searchTerm,
						pageNum:  ctrl.pageNum,
						pageSize: pageSize
					});

					console.log("Getting persons");
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
				} else if (action.name === "Technology") {
					console.log(action.name + " found");
					ctrl.codersTemp = Technologies.getTechnologies({
						id:       ctrl.searchTerm,
						pageNum:  ctrl.pageNum,
						pageSize: pageSize
					});

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
			};

			ctrl.codersTemp = Coders.getPagedLeaderboard({
				pageNum:  ctrl.pageNum,
				pageSize: pageSize
			});

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
				ctrl.codersTemp = Coders.getPagedLeaderboard({
					pageNum:  ctrl.pageNum,
					pageSize: pageSize
				});
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
