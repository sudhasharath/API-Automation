package com.api.framework.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepoUserDataObject {

	@JsonProperty("id")
	private int id;

	@JsonProperty("node_id")
	private int node_id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("full_name")
	private String full_name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("private")
	private String privateFlag;

	@JsonProperty("html_url")
	private String html_url;

	@JsonProperty("fork")
	private String fork;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("created_at")
	private String created_at;

	@JsonProperty("updated-at")
	private String updated_at;

	@JsonProperty("pushed_at")
	private String pushed_at;

	@JsonProperty("url")
	private String url;

	@JsonProperty("size")
	private String size;

	@JsonProperty("stargazers_count")
	private String stargazers_count;

	@JsonProperty("watchers_count")
	private String watchers_count;

	@JsonProperty("language")
	private String language;

	@JsonProperty("master_branch")
	private String master_branch;

	@JsonProperty("default_branch")
	private String default_branch;

	@JsonProperty("score")
	private String score;

	@JsonProperty("open_issues_count")
	private String open_issues_count;

	@JsonProperty("forks_count")
	private String forks_count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNode_id() {
		return node_id;
	}

	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrivateFlag() {
		return privateFlag;
	}

	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getFork() {
		return fork;
	}

	public void setFork(String fork) {
		this.fork = fork;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(String stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public String getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(String watchers_count) {
		this.watchers_count = watchers_count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMaster_branch() {
		return master_branch;
	}

	public void setMaster_branch(String master_branch) {
		this.master_branch = master_branch;
	}

	public String getDefault_branch() {
		return default_branch;
	}

	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOpen_issues_count() {
		return open_issues_count;
	}

	public void setOpen_issues_count(String open_issues_count) {
		this.open_issues_count = open_issues_count;
	}

	public String getForks_count() {
		return forks_count;
	}

	public void setForks_count(String forks_count) {
		this.forks_count = forks_count;
	}

}

