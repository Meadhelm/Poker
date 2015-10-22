(function(angular) {
	angular.module('app').controller(
			'registration_controller',
			function($scope, $http, $window) {
				$scope.user = {
					firstName : '',
					lastName : '',
					emailAddress : '',
					password : '',
					type : 'flyingUser'
				};

				$scope.register = function() {
					$http.post('http://localhost:8080/Poker/v1/users',
							$scope.user).success(
							function(data) {
								$scope.data = data;
								$window.location.href = '/Poker/#/home.html?session='
										+ data.emailAddress;
							});
				};
			});
})(angular);