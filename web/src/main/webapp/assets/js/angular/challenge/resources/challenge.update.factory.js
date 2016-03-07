(function () {
    angular.
    module("codeOffChallengeAdmin.updateFactory", []).
    factory("SharedUpdateChallenge", function () {

        var service = {
            challengeToBeUpdated : null,

            setChallengeData: function (updateChallengeDataRequirement) {
                this.challengeToBeUpdated = updateChallengeDataRequirement;
            },
            getChallengeData: function () {
                return this.challengeToBeUpdated;
            }
        };

        return service;
    });
})();