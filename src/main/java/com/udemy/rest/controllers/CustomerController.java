package com.udemy.rest.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.udemy.rest.entities.Customer;
import com.udemy.rest.entities.Post;
import com.udemy.rest.exceptions.CustomerNotFoundException;
import com.udemy.rest.repos.CustomerRepository;
import com.udemy.rest.repos.PostRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private PostRepository repo1;

	@Autowired
	private ResourceBundleMessageSource source;

	@GetMapping("/welcome")
	public String welcomeMessage() {
		return source.getMessage("Welcome", null, Locale.FRANCE);
	}

	@GetMapping("/customers")
	public MappingJacksonValue getAllTheCustomers() {
		List<Customer> listOfCustomers = repo.findAll();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(listOfCustomers);
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("name",
				"phone");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("simpleBean", simpleBeanPropertyFilter);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}

	@GetMapping("/customers/{id}")
	public MappingJacksonValue getCustomer(@PathVariable("id") Long id) {
		Optional<Customer> customer = repo.findById(id);
		if (customer.isPresent()) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customer.get());

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");

			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("simpleBean", filter);

			mappingJacksonValue.setFilters(filterProvider);

			return mappingJacksonValue;
		} else {
			throw new CustomerNotFoundException("customer not found with id :" + id);

			// ServletUriComponentsBuilder.fromCurrentRequest().
		}
	}

	@PostMapping("/customers")
	public void saveCustomer(@RequestBody @Valid Customer customer, BindingResult result) {
		if (!result.hasErrors()) {
			repo.save(customer);
		} else {
			throw new RuntimeException();
		}
	}

	@PostMapping("/customers/{id}/posts")
	public void saveCustomer(@PathVariable("id") long customerId, @RequestBody @Valid Post post, BindingResult result) {
		Optional<Customer> customer = repo.findById(customerId);
		if (customer.isPresent()) {
           post.setCustomer(customer.get());
           post.setPost("This is My First Post");
           repo1.save(post);
		}
	}
	
	@GetMapping("/customers/{id}/posts")
	public MappingJacksonValue getPost(@PathVariable("id") long customerId) {
		Optional<Customer> customer = repo.findById(customerId);
		if (customer.isPresent()) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customer.get().getPosts());

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");

			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("simpleBean", filter);

			mappingJacksonValue.setFilters(filterProvider);

			return mappingJacksonValue;
		}else {
			return null;
		}
	}

}
