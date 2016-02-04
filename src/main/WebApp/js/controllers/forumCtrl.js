/**
 * Controller for forum View
 */
angular.module('forum.controllers').controller('forumCtrl',
    ['$scope','$interval','channelSrv', 'postSrv', function ($scope, $interval, channelSrv, postSrv) {
        //Data
        $scope.channels = [];
        $scope.posts = [];
        $scope.selCha = {};
        $scope.newPost = '';

        //API
        $scope.loadChannels = function () {
            //fill $scope.channels[], select appropriate
            channelSrv.getChannels().success(function () {
                $scope.channels = channelSrv.channels;
                $scope.loadSelCha();
            });
        };

        $scope.selectChannel = function () {
            //store in the service; also if input-password changed [will be remembered]
            channelSrv.setSelCha($scope.selCha);
            channelSrv.updatePassword();
            $interval($scope.loadPosts, 200, 1);
        };

        $scope.loadSelCha = function () {
            //sets correct selected channel from service (good on view-reload)
            $scope.selCha = channelSrv.getSelCha();
        };

        $scope.loadPosts = function () {
            //fill posts[] for channel `selCha`
            postSrv.loadAll($scope.selCha).success(function (data) {
                $scope.posts = data.result;
            });
        };

        $scope.delPost = function (po) {
            postSrv.delPost($scope.selCha, po);
            $interval($scope.loadPosts, 200, 1);
        };

        $scope.submitPost = function () {
            if ($scope.newPost != '') {
                postSrv.submitPost($scope.selCha, $scope.newPost);
                $scope.newPost = '';
            }
            $interval($scope.loadPosts, 200, 1);
        };

        //INIT
        $scope.loadChannels();
        $interval($scope.loadPosts, 200, 1);

    }]
);
