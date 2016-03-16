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
					ctrl.changeTechDescription();
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

			ctrl.toNextPage = function () {
				ctrl.pageNum += 1;
				ctrl.toPaginate();
			};

			ctrl.toPrevPage = function () {
				if (ctrl.pageNum != 1) {
					ctrl.pageNum -= 1;
					ctrl.toPaginate();
				}
			};

			ctrl.changeTechDescription = function () {
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

			ctrl.iconDisplay = function (techs) {
				var html = "";
				var w = window.innerWidth;
				var numberOfTech = techs.length;
				var numIcons = Math.floor(5 * ((w / 17) * Math.sqrt(3)) / 100) - 1;
				numIcons = clamp(numIcons, numberOfTech);
				for (var i = 0; i < numIcons; i++) {
					html += '<i class=" icon-spacing icon-' + techs[i].description + '"></i>';
				}
				if (isEllipsesNeeded(numIcons, numberOfTech)) {
					var diff = techs.length - numIcons;
					html += '<i class="fa fa-ellipsis-h field-tip icon-spacing"><span class="tip-content" ' +
						'style="font-family: Montserrat !important;">+' + diff
						+ ' technologies</span></i>';
				}

				if (hideAllTechnologies(numIcons)) {
					html = '<i class="fa fa-ellipsis-h field-tip icon-spacing"><span class="tip-content" ' +
						'style="font-family: Montserrat !important;">+' + numberOfTech
						+ ' technologies</span></i>';
				}

				return $sce.trustAsHtml(html);
			};

			function isEllipsesNeeded(numIcons, nTechs) {
				if (nTechs > numIcons) {
					console.log("nTechs > numIcons true");
					return true;
				}
			}

			function hideAllTechnologies(numIcons) {
				if (numIcons < 1)
					return true;
			}

			function clamp(numIcons, nTechs) {
				if (numIcons > nTechs)
					return nTechs;
				else if (numIcons < 1)
					return 1;
				else
					return numIcons;
			}

			$(window).resize(function () {
				console.log(window.innerWidth);

				$scope.$apply(function () {

				});
			});
		});
})();
