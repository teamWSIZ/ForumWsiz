
//List modules which it uses
var app = angular.module('forumClient2', [
    'ngRoute',
    'forum.controllers',
    'forum.factories',
    'forum.directives'
]);

app.config(['$routeProvider', function ($routeProvider) {
    var urlBase='partials/';

    $routeProvider.when('/user', {
        templateUrl: urlBase + 'userView.html',
        controller: 'userCtrl'
    }).when('/forum', {
        templateUrl: urlBase + 'forumView.html',
        controller: 'forumCtrl'
    }).when('/newchannel', {
        templateUrl: urlBase + 'nchannelView.html',
        controller: 'nchannelCtrl'
    });
}]);

app.run(function ($rootScope) {
    //Model object --- main data
    $rootScope.M = {};
    $rootScope.M.url = 'http://localhost:8090/forum-0.1.1/';
});