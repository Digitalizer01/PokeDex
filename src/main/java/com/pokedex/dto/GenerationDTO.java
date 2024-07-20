package com.pokedex.dto;

import java.util.Objects;

public class GenerationDTO {
	private int id;
	private int number;
	private String region;
	private int year;

	public GenerationDTO(int number, String region, int year) {
		this.number = number;
		this.region = region;
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenerationDTO other = (GenerationDTO) obj;
		return id == other.id && number == other.number && Objects.equals(region, other.region) && year == other.year;
	}

	@Override
	public String toString() {
		return "GenerationDTO [id=" + id + ", number=" + number + ", region=" + region + ", year=" + year + "]";
	}

}
