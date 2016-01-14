
var myapp = angular.module('forumClient', []);



myapp.controller('mainCtrl', function ($scope, $http, $filter) {
    $scope.M = {

        //cats: [],
        //dataSets: [],
        //engines: [],
        //data: [],
        //selCat: {},     //selected Category
        //selDS: {},      //selected DataSet
        //result: [[]],   //indexed by engineid, dataid;  --> .value, .status, .exectime
        //nFile:          //new file
        url: 'http://localhost:8081/',
        leftCh : {},
        rightCh : {},
        channels : [],
        L : {
            name: 'wall',
            pass: ''
        },
        R : {
            name: '',
            pass: ''
        }
    };


    ////////  CHANNEL

    $scope.loadChannels = function () {
        $http.get($scope.M.url + 'channel/list').success(
            function (data) {
                $scope.M.channels = data.result;
            }
        );
    };

    $scope.newChannel = function (ch) {
        $http.get($scope.M.url + 'channel/add?name='+ch.name+'&password='+ch.pass);
    };

    /////// POSTS

    $scope.loadPosts = function (ch) {
        $http.get($scope.M.url + 'post/list?channelname='+ch.name+'&password='+ch.pass).success(
            function(res){
                if (res.status='OK') {
                    ch.posts = res.result;
                }
            }
        );
    };

    $scope.submitPost = function (ch) {
        $http.get($scope.M.url + 'post/add?channelname='+ch.name+'&content='+ch.text).success(
            function(data){
                $scope.loadPosts(ch);
                ch.text = '';
            }
        );
    };

    $scope.delPost = function (ch, post) {
        $http.get($scope.M.url + 'post/delete?channelname='+ch.name+'&password='+ch.pass+
            '&postid='+post.postid).success(
            function(data){
                $scope.loadPosts(ch);
            }
        );
    };

    $scope.loadPosts($scope.M.L);





    ///Initial action
    //$scope.loadChannles();





});
