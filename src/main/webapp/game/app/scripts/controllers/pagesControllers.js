// Contrôleur de la page d'accueil
routeAppControllers.controller('homeCtrl', ['$scope', '$location','$routeParams','$http',
	function($scope, $location, $routeParams, $http){
        $scope.message = "Welcome";
        $scope.need_players_name = true;
		
		$scope.getPlayersName = function(player1, player2){
            alert(player1, player2);
			//$location.path("/search");//+ $scope.id);
        }
		
		if($routeParams.query)
			alert("yes");

		//Meilleures ventes :		
		$scope.datasBest = [];
		$http.get('client/app/json/bestSales.php').success(function(data){ //get meilleures ventes
			$scope.datasBest = data;
			for(i=0;i<data.length;i++){
				if($scope.datasBest[i].poster == "N/A"){
					$scope.datasBest[i].poster = "client/app/images/logos/no-image.jpg";
				}
			}
		});  
		
		$scope.master = {firstName: "John", lastName: "Doe"};
    }
]);

// Contrôleur de la page de search
routeAppControllers.controller('searchCtrl', ['$scope', '$location', '$routeParams', '$http', 
    function($scope, $location, $routeParams, $http){
		//$scope.query = $routeParams.query;		
		$scope.datas = [];

		$scope.detailAction = function(){
            $location.path("/detail/rrr");//+ $scope.id);
        }
    }
]);
routeAppControllers.controller('searchCtrl2', ['$scope', '$location', '$routeParams', '$http', 'servicesSearch', 
    function($scope, $location, $routeParams, $http, servicesSearch){
		$scope.query = $routeParams.query;		
		$scope.datas = [];
		
		/*servicesSearch.searchFilm($scope.query).success(function(data){	
            
			// $scope.datas = data;
			$scope.datas = data.movies;

			for(i=0;i<data.movies.length;i++){
				if($scope.datas[i].poster == "N/A"){
					$scope.datas[i].poster = "client/app/images/logos/no-image.jpg";
				}
				if($scope.datas[i].year == "N/A"){
					$scope.datas[i].year = "(date inconnue)";
				}
			}
		});*/	

		$scope.detailAction = function(){
            $location.path("/detail/rrr");//+ $scope.id);
        }
    }
]);


// Contrôleur de la page detail
routeAppControllers.controller('detailCtrl', ['$scope', '$location','$rootScope', '$routeParams', '$http', '$timeout','servicesSearch','PanierService',
    function($scope, $location, $rootScope, $routeParams, $http, $timeout, servicesSearch,PanierService){
		$scope.id = $routeParams.id;	


		$scope.details = [];
                
                servicesSearch.detailsFilm($scope.id).success(function(data){	
            		/*$http.get('client/app/json/jsonUnSeulFilm2.php').success(function(data){
            			//alert(data.movies[0].title);
            			$scope.details = data[0];
            		});*/
                    // $scope.details = data[0];
					$scope.details = data;
                    if($scope.details.trailer == "N/A"){
                        $scope.bd=false;
                    } else{
                        $scope.bd=true;
                    }

                });
        
        /** fonction ajout dans panier  **/

        //$scope.addPanier = function (idC, idD, idP , titre, annee, support, quantite, pu) {
        $scope.addPanier = function (produit, support, quantite) {
            //panierFunction(idC,titre, annee, support, quantite, pu);
            if(support=="DVD"){
                panierFunction(produit.idProductDvd, produit.title, produit.year, support, 1, produit.dvdPrice);
            }else if(support=="BluRay"){
                panierFunction(produit.idProductBR, produit.title, produit.year, support, 1, produit.blurayPrice);
            }else if(support=="Copie Numerique"){
                panierFunction(produit.idProductNumeric, produit.title, produit.year, support, 1, produit.numericPrice);
            }
            
			
            function panierFunction(id, titre, annee, support, quantite, pu){

                var user={"idProduct":id,
                    "mail":$rootScope.globals.currentUser.email,
                    "title":titre,
                    "year":annee,
                    "support":support,
                    "unitPrice":pu};
                PanierService.panierManage().addProduct(user).then(function(response) { 
                    //alert("yess add"+response.data.cart[0].title);
                    $scope.userIn.datasPanier = response.data.cart;
                    //$scope.dansPanier[0]=true;
                });
				$timeout(function() {$scope.addInfo = false;}, 1000);

            }
        };
        
        $scope.supportPrice=function(index, prix){
            if(index=="2"){
                $scope.txtDtlPu = prix;
            }else if(index=="3"){
                $scope.txtDtlPu = prix;
            }else{
                $scope.txtDtlPu = prix;
            }                
        }		
	}
]);


// Contrôleur de la page achat
routeAppControllers.controller('achatCtrl', ['$scope','$location', '$routeParams', '$http', '$rootScope', 'ngDialog', '$timeout','$interval','PanierService',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, $interval,PanierService){

        $scope.datasOrders = [];
		
		if(!$rootScope.globals.currentUser){
			$location.path("/home");
		}
                
        if($rootScope.globals.currentUser.email){               
            var user={"mail":$rootScope.globals.currentUser.email}; 
            PanierService.panierManage().getOrder(user).then(function(response){ 
                $scope.datasOrders = response.data.order;
            });
        }                
		
		$scope.toto=0;						
        $scope.download = function () {
            //ngDialog.open({ template: 'dialogDownload' });  
			$interval(function() {$scope.toto= $scope.toto+1;}, 2000, 100);
        };
        
        $rootScope.$on('ngDialog.setPadding', function (event, padding){
            angular.element( document.querySelector('.paddingHeader') ).css('padding-right', padding + 'px');
        });

    }
]);


// Contrôleur de la page achat
routeAppControllers.controller('settingCtrl', ['$scope','$location', '$http', '$rootScope', '$timeout','$interval','UserService',
    function($scope, $location, $routeParams, $http, $rootScope, ngDialog, $timeout, $interval, UserService){
		var logID={'mail':'user1@gmail.com'};
									UserService.userManage().InfoUser(logID).then(function (info) {
										//alert(info.data.mail);
										$rootScope.userIn.firstName = info.data.firstName;
										$rootScope.userIn.lastName = info.data.lastName;
										$rootScope.userIn.address = info.data.address;
										//$rootScope.userIn.zipcode = info.data.zipcode;
										$rootScope.userIn.phone = info.data.phone;
										//$rootScope.userIn.city = info.data.city;
										//$rootScope.userIn.country = info.data.country;
										$rootScope.userIn.mail = info.data.mail;									
										
									});
		
		//$scope.userIn.firstName = $rootScope.userIn.firstName;
		$scope.userIn.lastName = $rootScope.userIn.lastName;
		$scope.userIn.address = $rootScope.userIn.address;
		//$rootScope.userIn.zipcode = info.data.zipcode;
		$scope.userIn.phone = $rootScope.userIn.phone;
		/*$rootScope.userIn.city = info.data.city;
		$rootScope.userIn.country = info.data.country;*/
		$scope.userIn.mail = $rootScope.userIn.mail;
		
		alert($rootScope.userIn.mail);
    }
]);