var app = angular.module("topInformationApp", []);
app.controller("topInformationCtrl", function($scope, $http) {	
	$http({
		method: 'GET',
		url: 'http://localhost:8080/topInformation/services/gitRepositories/getStarredRepositories'
	}).then(function successCallback(response) {
		$scope.rows = angular.fromJson(response.data);
		console.log($scope.rows);
	});
});