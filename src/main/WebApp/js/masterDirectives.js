angular.module('forum.directives', [])

    //Pokazuje modal aktualnego channel-u
    .directive('newChannelModal', function () {
        return {
            restrict: 'EA',
            scope: {
                //parameters to pass (objects and callbacks)
                channel:  '=',
                //passing callback function, taking one argument, called 'temp'
                savefunction: '&'
            },
            link: function (scope, element, attrs) {
                //local controller
            },
            templateUrl: 'partials/newChannelModal.html'
        };
    })

    .directive('zeroModal', function () {
        return {
            restrict: 'AE',
            transclude: true,
            scope: {
                channel:  '='
            },
            link: function (scope, element, attrs) {
                //local controller
                scope.alpha = function () {
                    console.log('Executed...');
                };

                element.bind('click', function() {
                    $('#zeroModal').modal('show');
                });
                //element.bind('click', scope.alpha);
            },
            template: '<p> Hiiiii </p>'

            //templateUrl: 'partials/zeroModal.html'
        };
    })

//onclick="$('#newChannelModal').modal({keyboard: true})

    //Dyrektywa produkująca edit :  UI-component
    .directive('myedit', function () {
        return {
            restrict: 'E',
            //declared as directive is used
            scope: {
                model:  '=',
                name: '=',
                foredit: '='
            },
            //`attrs` --> pure _string_ values
            link: function (scope, element, attrs) {
                //local controller of this directive
                scope.logit = function(){
                    console.log('Local modal function');
                }
            },
            template: '<div data-ng-click="logit()"><b >Edit this ({{model.url}}):</b><input type="text" data-ng-model="model.url"></div>'
        };
    })





