package org.ui.aguntuk.demo;

public class Person {
	
	public Person() {
		
	}
	private String firstName;
	private String lastName;
	private String streetName1;
	private String streetName2;
	private String city;
	private String state;
	private String country;
	private String phoneNumber;

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreetName1() {
		return streetName1;
	}
	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}
	public String getStreetName2() {
		return streetName2;
	}
	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append("First Name ").append(firstName).append("\n")
		.append("Last Name: ").append(lastName).append("\n").append("Address line 1").append(streetName1).append("\n")
		.append("Address line 2").append(streetName2).append("\n").append("City ").append(city).append("\n")
		.append("State ").append(state).append("\n").append("Country ").append(country);
		return stringBuffer.toString();
	}

}
