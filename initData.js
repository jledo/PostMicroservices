db.dropUser("arquitectureapp");

db.createUser(
   {
     user: "arquitectureapp",
     pwd: "arquitectureapp",
     roles: [ "readWrite", "dbAdmin" ]
   }
);

var user = {"_id" : "11",
	    "username" : "Javier",
	    "password" : "$2a$10$Hapocgd6APJAu5Aq8YlN1e986xkJwfZ1u062G8Lo9KYWzawzMMJV.",
	    "roles" : [ {
	    	"name": "admin",
	    	"permissions": [
	    	       {
	    	    	    "resource":"hello",
	    	    	    "method": "read",
	    	    	    "allowAll": true
	    	       },
	    	       {
	    	    	    "resource":"model",
	    	    	    "method": "read",
	    	    	    "allowIds": ["1"],
	    	    	    "denyIds": ["5"],
	    	    	    "allowAll": true,
	    	    	    "ownEntities": true
	    	       },   
	    	       {
	                    "resource" : "user",
	                    "method" : "read",
	                    "allowIds" : [ 
	                        "1"
	                    ],
	                    "denyIds" : [ 
	                        "5"
	                    ],
	                    "allowAll" : true,
	                    "ownEntities" : true
	                },
	                {
	                    "resource" : "product",
	                    "method" : "read",
	                    "allowIds" : [ 
	                        "1"
	                    ],
	                    "denyIds" : [ 
	                        "5"
	                    ],
	                    "allowAll" : true,
	                    "ownEntities" : true
	                }
	    	 ]
	    } ]
};

var user1 = {
		"_id": "1",
		"name": "Usuario1"
}

var user2 = {
		"_id": "2",
		"name": "Usuario2"
}

var user5 = {
		"_id": "5",
		"name": "Usuario3"
}

db.user.insert(user1);
db.user.insert(user2);
db.user.insert(user5);
db.user.insert(user);


var product1 = {
		"_id": "1",
		"userId": "1",
		"name": "producto1"
}

var product2 = {
		"_id": "2",
		"userId": "1",
		"name": "producto2"
}

var product5 = {
		"_id": "5",
		"userId": "1",
		"name": "producto5"
}

db.product.insert(product1);
db.product.insert(product2);
db.product.insert(product5);