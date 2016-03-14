(function (){
    "use strict";

    var codeOffApp = angular.module("codeOffApp", [
        "ui.bootstrap",
        "ngResource",
        "ngSanitize",
        "codeOffNavigation",
        "codeOffFooter",
        "codeOffApp.leaderboard",
        "codeOffLeaderboard"
    ]);

    angular.module("codeOffLeaderboard", [
        "ngResource",
		"ngSanitize"
    ]);
})();
