/**
 * Controller for new Channel
 */
angular.module('forum.controllers').controller('nchannelCtrl',
    ['$scope','channelSrv', function ($scope, channelSrv) {
        $scope.selCha = channelSrv.getSelCha();
        $scope.newCha = {name:'', pass:''};

        $scope.createChannel = function () {
            channelSrv.addChannel($scope.newCha);
        }

    }]);

