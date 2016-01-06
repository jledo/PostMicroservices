package es.paradigma.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import es.paradigma.service.product.ProductApplication;
import es.paradigma.service.product.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductApplication.class)
@WebIntegrationTest({ "server.port=8081" })
public class ProductTest {
	public String baseUrl = "http://localhost:8081/api/v1";
	public RestTemplate restTemplate = new TestRestTemplate();


	@Test
	public void listProductTest() {
		String token = login("Javier", "abcd123");
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		headers.put("X-AUTH-TOKEN", tokenList);
		HttpEntity<String> request = new HttpEntity<>(headers);

		@SuppressWarnings("rawtypes")
		ResponseEntity<List> resultado = restTemplate.exchange(baseUrl+ "/product", HttpMethod.GET, request, List.class);
		Product model = new Product("5","1", "denny");
		Assert.assertTrue(!resultado.getBody().contains(model));
		Assert.assertTrue(resultado.getBody().size() == 2);
	}
	
	@Test
	public void listProductPageTest() {
		String token = login("Javier", "abcd123");
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		headers.put("X-AUTH-TOKEN", tokenList);
		HttpEntity<String> request = new HttpEntity<>(headers);

		@SuppressWarnings("rawtypes")
		ResponseEntity<String> resultado = restTemplate.exchange(baseUrl+ "/product/search?filter=id:1", HttpMethod.GET, request, String.class);
		Assert.assertNotNull(resultado);
	}

	@Test
	public void listOneProductTest() {
		String token = login("Javier", "abcd123");
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		List<String> tokenList = new ArrayList<>();
		tokenList.add(token);
		headers.put("X-AUTH-TOKEN", tokenList);
		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<Product> resultado1 = restTemplate.exchange(baseUrl + "/product/1", HttpMethod.GET, request,
				Product.class);

		ResponseEntity<Product> resultado5 = restTemplate.exchange(baseUrl + "/product/5", HttpMethod.GET, request,
				Product.class);
		Assert.assertNotNull(resultado1.getBody());
		Assert.assertEquals(resultado5.getStatusCode(), HttpStatus.FORBIDDEN);

	}


	private String login(String username, String password) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");

		Map<String, Object> user = new HashMap<>();
		user.put("username", username);
		user.put("password", password);
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(user,headers);


		ResponseEntity<String> resultado = restTemplate.exchange(baseUrl + "/login", HttpMethod.POST, entity,
				String.class);
		String token = resultado.getHeaders().getFirst("X-AUTH-TOKEN");
		return token;
	}
}