(function(angular) {
	angular.module('app', []);
	String.prototype.contains = function(it) { return this.indexOf(it) != -1; };
})(angular);