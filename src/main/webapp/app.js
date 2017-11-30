angular.module('myApp', [
    'ngResource'
]).factory('UserResource', ['$resource',
    function ($resource) {
        return $resource('rest/user', {}, {});
    }])
    .controller('myController', ['$scope', 'UserResource', function ($scope, resource) {
        $scope.users = resource.query();
    }]);