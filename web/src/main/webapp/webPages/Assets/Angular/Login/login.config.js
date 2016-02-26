(function () {

    angular
        .module("codeOff.login")
        .config(routeConfig);

    routeConfig.$inject = ["$stateProvider"];

    function routeConfig($stateProvider) {

        $stateProvider
            .state("login", {
                url: "/login",
                views: {
                    'main': {
                        templateUrl: "Angular/Login/Templates/login.html"
                    }
                }
            });

    }

})();