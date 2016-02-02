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