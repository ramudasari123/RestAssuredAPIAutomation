package com.automation.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RegUserPOJO {

	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = RandomStringUtils.randomAlphabetic(6).toLowerCase();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName + CommonMethod.randomGen();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName + CommonMethod.randomGen();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email + RandomStringUtils.randomAlphanumeric(5) + "@test.com";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}