/**
 * Definition of factory module.
 * These correspond to @Repository in Spring
 */

/**
 * Rest --> {status:'OK|FAILED', comment:'null|failureReason', result:[{},{},{}]}
 */


angular.module('forum.factories',[]);

angular.module('forum.factories').factory('channelSrv',
    ['$http', function ($http) {
        var channels = [];
        var selCha = {};    //cid, name, pass
        var chSrv = {};
        var rootUrl = ROOTURL;

        //-->promise; must chain per .success(...)
        chSrv.getChannels = function () {
            var self = this;
            return $http.get(rootUrl + 'channel/list').success(
                function (data) {
                    self.channels = data.result;
                    return data;
                }
            );
        };
        chSrv.addChannel = function (ch) {
            $http.get(rootUrl + 'channel/add?name='+ch.name+'&password='+ch.pass).success(function(){
                console.log('Created.');
            });
        };
        //updates password of selected channel in channels[]
        chSrv.updatePassword = function () {
            this.channels.forEach(function(ch){
                if (ch.cid==selCha.cid) ch.pass=selCha.pass;
            })
        };

        chSrv.setSelCha = function (selectedChannel) {
            selCha = selectedChannel;
        };
        chSrv.getSelCha = function () {
            if (!selCha.cid) selCha = {cid: 1, name: 'wall', pass: ''};
            return selCha;
        };
        return chSrv;
    }]);


angular.module('forum.factories').factory('postSrv',
    ['$http', function ($http) {
        var postSRV = {};
        var rootUrl = ROOTURL;

        //-->promise; must chain per .success(...)
        postSRV.loadAll = function (ch) {
            var self = this;
            return $http.get(rootUrl + 'post/list?channelname=' + ch.name + '&password=' + ch.pass);
        };

        postSRV.submitPost = function (ch, content) {
            $http.get(rootUrl + 'post/add?channelname='+ch.name+'&password='+ch.pass+'&content='+content);
        };

        postSRV.delPost = function (ch, po) {
            $http.get(rootUrl + 'post/delete?channelname='+ch.name+'&password='+ch.pass+'&postid='+po.postid);
        };

        return postSRV;
    }]);



angular.module('forum.factories').factory('userSrv',
    ['$rootScope', '$http', function ($rootScope, $http) {
        console.log("User service starting...");
        var user = 'Gall';
        var userSrv = {};
        userSrv.getUser = function () {
            return user;
        };
        userSrv.setUser = function (newUser) {
            user = newUser;
        };
        return userSrv;
    }]);
