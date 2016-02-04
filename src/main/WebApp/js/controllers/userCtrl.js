/**
 * Controller for user view.
 */

angular.module('forum.controllers').controller('userCtrl', ['$scope', 'userSrv',
    function ($scope,userSrv) {
        $scope.usr = userSrv.getUser();
        $scope.getUser = function () {
            console.log(userSrv.getUser());
        };
        $scope.saveUser = function() {
            userSrv.setUser($scope.usr);

        }
}]);
