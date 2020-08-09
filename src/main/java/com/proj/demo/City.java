package com.proj.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="citydetails")
public class City {
	@Column(name="name")
	public String name;
	
	@Column(name="coord")
	public String coord;
	
	@Column(name="state")
	public String state;
	
	
	@Column(name="country")
	public String country;
	
	@Id
	@Column(name="id")
	public int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoord() {
		return coord;
	}

	public void setCoord(String coord) {
		this.coord = coord;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", state=" + state + ", country=" + country + "]";
	}
	
	
}
