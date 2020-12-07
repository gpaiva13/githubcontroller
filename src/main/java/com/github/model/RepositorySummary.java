package com.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositorySummary {
	
	private String _private;
	private String created_at;
	private String description;
	private String full_name;
	private String language;
	private String name;
	private Owner owner;
	private String updated_at;
	
	public RepositorySummary(@JsonProperty("_private") String _private, 
			@JsonProperty("created_at") String created_at, 
			@JsonProperty("description") String description, 
			@JsonProperty("full_name") String full_name, 
			@JsonProperty("language") String language,
			@JsonProperty("name") String name, 
			@JsonProperty("owner") Owner owner, 
			@JsonProperty("updated_at") String updated_at) {
		super();
		this._private = _private;
		this.created_at = created_at;
		this.description = description;
		this.full_name = full_name;
		this.language = language;
		this.name = name;
		this.owner = owner;
		this.updated_at = updated_at;
	}

	public String get_private() {
		return _private;
	}

	public void set_private(String _private) {
		this._private = _private;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOwner() {
		return (owner != null ? owner.getLogin() : null);
	}

	@JsonProperty("owner")
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	
}
