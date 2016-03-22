(function () {
	var app = angular.module("codeOffLeaderboard");

	app.controller("viewLeaderboardController",
				   function ($scope, $http, $log, $sce, $window, Coders, PersonCount, Challenges, Technologies) {
					   var ctrl = $scope;
					   var pageSize = 10;
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
							   if (ctrl.coders.length === 0) {
								   ctrl.codersIsNull = true;
							   } else {
								   ctrl.codersIsNull = false;
							   }

							   ctrl.changeTechDescription();
															 console.log(ctrl.coders);
															 var lastPage = Math.ceil(ctrl.countOfCoders / pageSize);
															 if (lastPage === ctrl.pageNum || ctrl.coders.length < pageSize) {
																 ctrl.CanNotNext = true;
															 }
															 else {
																 ctrl.CanNotNext = false;
															 }
														 }, $log.error
						   );
					   };
					   ctrl.getDefaultLeaderboard = function () {
						   ctrl.codersTemp = Coders.getPagedLeaderboard({
																			pageNum: ctrl.pageNum,
																			pageSize: pageSize
																		});

						   ctrl.getPersonsCount();
						   ctrl.getLeaderboard();
					   };
					   ctrl.getDefaultLeaderboard();

					   ctrl.getChallengeLeaderoard = function () {
						   ctrl.codersTemp = Challenges.getChallenges({
																		  id: ctrl.searchTerm,
																		  pageNum: ctrl.pageNum,
																		  pageSize: pageSize
																	  });

						   ctrl.getPersonsCount();
						   ctrl.getLeaderboard();
					   };

					   ctrl.getTechnologyLeaderboard = function () {
						   ctrl.codersTemp = Technologies.getTechnologies({
																			  techName: ctrl.searchTerm,
																			  pageNum: ctrl.pageNum,
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

					   ctrl.$watch("searchTerm",
								   function (newVal, oldVal) {
									   if (ctrl.selectedSearch.name === "Filter") {
										   ctrl.searchDisabled = true;
									   }
									   if (ctrl.selectedSearch.name === "Challenge") {
										   validateChallengeSearch(newVal);
									   }
									   if (ctrl.selectedSearch.name === "Technology") {
										   validateTechnologySearch(newVal);
									   }
								   }, true
					   );

					   function validateChallengeSearch(searchTerm) {
						   var reg = new RegExp("^[0-9]+$");
						   if (reg.test(searchTerm)) {
							   ctrl.searchDisabled = false;
						   } else {
							   ctrl.searchDisabled = true;
						   }
					   }

					   function validateTechnologySearch(searchTerm) {
						   var reg = new RegExp("^[a-zA-Z]+$");
						   if (reg.test(searchTerm)) {
							   ctrl.searchDisabled = false;
						   } else if (searchTerm.length > 16) {
							   ctrl.searchDisabled = true;
						   } else if (!reg.test(searchTerm) || searchTerm.length > 16) {
							   ctrl.searchDisabled = true;
						   } else {
							   ctrl.searchDisabled = true;
						   }
					   }

					   ctrl.toPaginate = function () {
						   if (ctrl.selectedSearch.name === "Filter") {
							   ctrl.getDefaultLeaderboard();
						   } else if (ctrl.selectedSearch.name === "Challenge") {
							   ctrl.getChallengeLeaderoard();
						   } else if (ctrl.selectedSearch.name === "Technology") {
							   ctrl.getTechnologyLeaderboard();
						   } else {
							   ctrl.getDefaultLeaderboard();
						   }
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
						   var numIcons = 0;
						   if (w < 420) {
							   numIcons = Math.floor(w / (16 * 17)) - 1;
						   } else if (w >= 420 && w < 450) {
							   numIcons = Math.floor(w / (16 * 12)) - 1;
						   } else {
							   numIcons = Math.floor(w / (16 * 10));
						   }

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
								   'style="font-family:\'Montserrat Light\', arial !important;">+' + numberOfTech
								   + ' technologies</span></i>';
						   }

						   if (numberOfTech === 0) {
							   html = '';
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
						   if (numIcons < 1) {
							   return true;
						   }
					   }

					   function clamp(numIcons, nTechs) {
						   if (numIcons > nTechs) {
							   return nTechs;
						   } else if (numIcons < 1 && nTechs === 1) {
							   return 1;
						   } else {
							   return numIcons;
						   }
					   }

					   $(window).resize(function () {
						   console.log(window.innerWidth);

						   $scope.$apply(function () {

						   });
					   });
				   });
})();
