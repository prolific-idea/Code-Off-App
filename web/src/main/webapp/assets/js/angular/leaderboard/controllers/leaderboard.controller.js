(function () {
	var app = angular.module("codeOffLeaderboard");

	app.controller("viewLeaderboardController",
		function ($scope, $http, $log, $sce, $window, Coders, PersonCount, Challenges, Technologies) {
			var ctrl = $scope;
			var pageSize = 5;
			ctrl.pageNum = 1;
			ctrl.coders = [];

			ctrl.getPersonsCount = function () {
				ctrl.personCountTemp = PersonCount.count();
				ctrl.personCountTemp.$promise.then(function () {
					ctrl.countOfCoders = ctrl.personCountTemp;
					ctrl.countOfCoders = ctrl.countOfCoders.countOfPerson;
				}, $log.error);
			};
			ctrl.getLeaderboard = function () {
				ctrl.codersTemp.$promise.then(function () {
					ctrl.coders = ctrl.codersTemp;
					ctrl.ChangeTechDescription();
					console.log(ctrl.coders);
					var lastPage = Math.ceil(ctrl.countOfCoders / pageSize);
					if (lastPage === ctrl.pageNum || ctrl.coders.length < pageSize) {
						ctrl.CanNotNext = true;
					}
					else {
						ctrl.CanNotNext = false;
					}
				}, $log.error);
			};
			ctrl.getDefaultLeaderboard = function () {
				ctrl.codersTemp = Coders.getPagedLeaderboard({
					pageNum:  ctrl.pageNum,
					pageSize: pageSize
				});

				ctrl.getPersonsCount();
				ctrl.getLeaderboard();
			};
			ctrl.getDefaultLeaderboard();

			ctrl.getChallengeLeaderoard = function () {
				ctrl.codersTemp = Challenges.getChallenges({
					id:       ctrl.searchTerm,
					pageNum:  ctrl.pageNum,
					pageSize: pageSize
				});

				ctrl.getPersonsCount();
				ctrl.getLeaderboard();
			};

			ctrl.getTechnologyLeaderboard = function () {
				ctrl.codersTemp = Technologies.getTechnologies({
					id:       ctrl.searchTerm,
					pageNum:  ctrl.pageNum,
					pageSize: pageSize
				});

				ctrl.getPersonsCount();
				ctrl.getLeaderboard();
			};

			ctrl.searchTerm = "";
			ctrl.searchActions = [
				{id: 'filter', name: 'Filter'},
				{id: 'challenge', name: 'Challenge'},
				{id: 'technology', name: 'Technology'}
			];
			ctrl.selectedSearch = ctrl.searchActions[0];

			ctrl.setSearchAction = function (action) {
				ctrl.selectedSearch = action;
				ctrl.searchFor(action);
			};

			ctrl.searchFor = function (action) {
				ctrl.pageNum = 1;
				console.log("[searchFor] Page Number is : " + ctrl.pageNum);
				if (action == null) {
					action = ctrl.selectedSearch;
				}
				if (action.name === "Filter") {
					ctrl.searchTerm = null;
					ctrl.getDefaultLeaderboard();
				} else if (action.name === "Challenge") {
					ctrl.getChallengeLeaderoard();
				} else if (action.name === "Technology") {
					ctrl.getTechnologyLeaderboard();
				} else {
					console.log(actionId + " unknown");
				}
			};

			ctrl.toPaginate = function () {
				if (ctrl.selectedSearch.name === "Filter")
					ctrl.getDefaultLeaderboard();
				else if (ctrl.selectedSearch.name === "Challenge")
					ctrl.getChallengeLeaderoard();
				else if (ctrl.selectedSearch.name === "Technology")
					ctrl.getTechnologyLeaderboard();
				else
					ctrl.getDefaultLeaderboard();
			};

			ctrl.ToNextPage = function () {
				ctrl.pageNum += 1;
				ctrl.toPaginate();
			};

			ctrl.ToPrevPage = function () {
				if (ctrl.pageNum != 1) {
					ctrl.pageNum -= 1;
					ctrl.toPaginate();
				}
			};

			ctrl.ChangeTechDescription = function () {
				for (var i = 0; i < ctrl.coders.length; i++) {
					for (var j = 0; j < ctrl.coders[i].techs.length; j++) {
						if (ctrl.coders[i].techs[j].description === "C#") {
							ctrl.coders[i].techs[j].description = "csharp";
						}
						if (ctrl.coders[i].techs[j].description === "C++") {
							ctrl.coders[i].techs[j].description = "cplusplus";
						}
						if (ctrl.coders[i].techs[j].description === "Objective-C") {
							ctrl.coders[i].techs[j].description = "objc";
						}

						ctrl.coders[i].techs[j].description = ctrl.coders[i].techs[j].description.toLowerCase();
					}
				}
			};

			$(window).resize(function(){
				console.log(window.innerWidth);

				$scope.$apply(function(){
					//do something to update current scope based on the new innerWidth and let angular update the view.
				});
			});

			ctrl.iconDisplay = function (techs) {
				var html = "";
				var techLength = techs.length;

				for (var i = 0; i < techLength; i++) {
					html += '<i class="icon-' + techs[i].description + '" style="padding: 0px 3px 0px 4px;"></i>';
				}

				return $sce.trustAsHtml(html);
			};
		});
})();
