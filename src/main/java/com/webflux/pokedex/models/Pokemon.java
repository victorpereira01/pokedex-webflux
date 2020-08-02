package com.webflux.pokedex.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pokemon {
	
	@Id
	private String id;
	private String name;
	private String category;
	private String skill;
	private Double weight;
	
	public Pokemon() {
		super();
	}

	public Pokemon(String id, String name, String category, String skill, Double weight) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.skill = skill;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pokemon [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", category=");
		builder.append(category);
		builder.append(", skill=");
		builder.append(skill);
		builder.append(", weight=");
		builder.append(weight);
		builder.append("]");
		return builder.toString();
	}
	
	
}
