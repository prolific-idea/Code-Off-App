(function (){
    "use strict";

    var codeOffApp = angular.module("codeOffApp", [
        "ui.bootstrap",
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
