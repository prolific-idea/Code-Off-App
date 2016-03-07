(function (){
    "use strict";

    var codeOffApp = angular.module("codeOffApp", [
        "ngResource",
        "codeOffNavigation",
        "codeOffFooter",
        "codeOffApp.leaderboard",
        "codeOffLeaderboard"
    ]);

    angular.module("codeOffLeaderboard", [
        "ngResource"
    ]);
})();