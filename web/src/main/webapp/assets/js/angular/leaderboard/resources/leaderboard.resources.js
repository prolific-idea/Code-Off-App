(function() {
    'use strict';

    angular.module("codeOffLeaderboard")
        .factory("Coders", function($resource) {
            return $resource("/api/person/:id", null, {
                getPersons: {
                    method: "GET",
                    isArray: true
                }
            });
        });
}());