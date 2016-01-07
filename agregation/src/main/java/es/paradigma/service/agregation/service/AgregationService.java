package es.paradigma.service.agregation.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AgregationService {


	@Autowired
	@LoadBalanced
	private RestTemplate loadBalanced;
	


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedHashMap findUserById(String id, String token) {
	  
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		headers.put("X-AUTH-TOKEN", tokenList);
		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<LinkedHashMap> result = loadBalanced.exchange("http://userService/api/v1/user/" + id,
				HttpMethod.GET, request, LinkedHashMap.class);

		ResponseEntity<List> projects = loadBalanced.exchange(
				"http://productService/api/v1/product/search?filter=userId:" + id, HttpMethod.GET, request,
				List.class);

		LinkedHashMap user = result.getBody();
		user.put("products", projects.getBody());

		return user;

	}
	
	public List findProductByParameters(Map<String,Object> param, String token) {
		  
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		headers.put("X-AUTH-TOKEN", tokenList);
		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<List> products = loadBalanced.exchange(
				"http://productService/api/v1/product", HttpMethod.GET, request,
				List.class);

		return products.getBody();

	}
	
	public void deleteUser(String id, String token) {
		  
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		headers.put("X-AUTH-TOKEN", tokenList);
		HttpEntity<String> request = new HttpEntity<>(headers);



		loadBalanced.exchange("http://userService/api/v1/user/" + id,
				HttpMethod.DELETE, request, String.class);


	}
}