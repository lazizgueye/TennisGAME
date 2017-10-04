
//configRoute.js
'use strict';

/**
 * Déclaration de l'application routeApp
 */
var routeApp = angular.module('routeApp', [
    // Dépendances du "module"
    'ngRoute',
    'routeAppControllers',
    'ngCookies'
]);

/**
 * Configuration du module principal : routeApp
 */
routeApp.config(['$routeProvider',
    function($routeProvider) { 
        
        // Système de routage
        $routeProvider
        .when('/home', {
            templateUrl: 'game/app/views/home.html',
            controller: 'homeCtrl'
        })
        .when('/search/:query', {
            templateUrl: 'game/app/views/search.html',
            controller: 'searchCtrl'
        })
		
        

		.otherwise({
            redirectTo: '/home'
        });
	
    }
]);


/**
 * Définition des contrôleurs
 */
var routeAppControllers = angular.module('routeAppControllers', ['ngDialog', 'ngCookies']);
var need_players_name = false;
var dataPanierTotal=[0];
var dansPanier = [false];
var totalPanier = [0];

//var usert = [false];

var dataAchat=[];
var dataAvis=[];

var ListMenu=false;
var GenreFilm = false;
var GenreSerie = false;
var BtnSearch = true;

routeAppControllers.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
            });
}]);


routeAppControllers.run(['$rootScope', '$location', '$cookieStore', '$http', function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
		$rootScope.userIn={'firstName':'','lastName':'','mail':'','zipCode':'','country':'','city':'','address':'','phone':'','datasPanier':'0', 'totalPrice':'0'};
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
		
    }]);
