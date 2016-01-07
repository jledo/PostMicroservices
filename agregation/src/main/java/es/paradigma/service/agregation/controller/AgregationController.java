package es.paradigma.service.agregation.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.paradigma.service.agregation.service.AgregationService;

@RestController
@RequestMapping("/api/v1/user")
public class AgregationController {
	
	@Autowired
	private AgregationService agregationService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("@permissions.allow(#id, 'user', 'read')")
	public LinkedHashMap findById(@PathVariable("id") String id, @RequestHeader("X-AUTH-TOKEN") String token) {
		return this.agregationService.findUserById(id, token);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("@permissions.allow(#id, 'user', 'delete')")
	public void delete(String id, @RequestHeader("X-AUTH-TOKEN") String token) {
		agregationService.deleteUser(id, token);
	}

	@RequestMapping(value = "/product/search",method = RequestMethod.GET)
	@PreAuthorize("@permissions.allowAny('product', 'read')")
	@PostFilter("@permissions.allow(filterObject.id, 'product', 'read')")
	public List findByParameters(@RequestParam Map<String,Object> parameters, @RequestHeader("X-AUTH-TOKEN") String token) {
		return agregationService.findProductByParameters(parameters, token);
	}

}
