package com.udemy.rest.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@JsonFilter("simpleBean")
@ApiModel(description = "Customer Bean")
public class Customer {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "its id of customer")
	private Long id;
	
	@Size(min=2, max =20)
	@NotNull
	@ApiModelProperty(notes = "name must have min 2 and max 20 char")
	private String name;
	
	@NotNull
	@Size(min=10, max=10)
	@Pattern(regexp = "^[0-9]+$",message = "must have only numeric")
	@ApiModelProperty(notes = "phone must have 10 digits")
	private String phone;
	
	@OneToMany(mappedBy = "customer")
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getPhone()=" + getPhone() + "]";
	}

}
