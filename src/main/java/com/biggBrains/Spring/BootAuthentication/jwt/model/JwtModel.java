package com.biggBrains.Spring.BootAuthentication.jwt.model;

public class JwtModel {
	
	String username;
	String password;
	
	public JwtModel() {
		
		
	}

	public JwtModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUsername(String userName) {
		this.username = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtModel [userName=" + username + ", password=" + password + "]";
	}
	
	

}
