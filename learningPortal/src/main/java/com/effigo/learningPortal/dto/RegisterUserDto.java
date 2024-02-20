package com.effigo.learningPortal.dto;

import java.util.Set;

import com.effigo.learningPortal.entity.Courses;
import com.effigo.learningPortal.entity.Role;

import lombok.Data;

@Data
public class RegisterUserDto {

	private String name;

	private String email;

	private String password;

	private Set<Role> roles;

	private Set<Courses> courses;
}