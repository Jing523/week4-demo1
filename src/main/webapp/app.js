angular.module('myApp', [
    'ngResource'
]).factory('UserResource', ['$resource',
    function ($resource) {
        return $resource('rest/user/:id', {}, {});
    }])
    .controller('myController', ['$scope', 'UserResource', function ($scope, resource) {
        $scope.users = resource.query();
    }])
    .controller('DemoController', ['$scope', 'UserResource', function ($scope, resource) {
        $scope.user = resource.get({
            "id": 1
        });
    }]);