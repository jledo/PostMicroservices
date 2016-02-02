
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

db.user.insert(user);