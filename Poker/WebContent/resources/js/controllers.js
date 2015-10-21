(function(angular) {

	var app = angular.module('app', []);
	app.controller('controller', function($scope, $http) {
		$scope.userId = '';
		$scope.data = null;

		$scope.$watch('userId', function(newValue, oldValue) {
			$http.get('http://localhost:8080/Poker/v1/users/' + $scope.userId)
					.success(function(data) {

						$scope.data = data;
					});
		})
	});

})(angular);