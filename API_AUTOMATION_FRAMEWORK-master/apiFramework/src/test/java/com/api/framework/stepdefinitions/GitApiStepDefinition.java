package com.api.framework.stepdefinitions;

import com.api.framework.actions.ApiActions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GitApiStepDefinition {
	ApiActions action;
	public GitApiStepDefinition() {
		action = new ApiActions();
	}
	@Given("^user set the API endpoint url to fetch \"([^\"]*)\"$")
	public void user_set_the_API_endpoint_url_to_fetch(String url) throws Throwable {
		action.setApiEndpoint(url);
	}

	@Given("^user set the API endpoint query parameters to fetch records for \"([^\"]*)\" programming language$")
	public void user_set_the_API_endpoint_query_parameters_to_fetch_records_for_programming_language(String progLang) throws Throwable {
	   action.getEndpointQueryParamForLangSearch(progLang);
	   action.setApiEndpointWithQueryParam();
	}

	@Given("^user set the GET request header \"([^\"]*)\"$")
	public void user_set_the_GET_request_header(String headerKey) throws Throwable {
	    action.setApiHeader(headerKey);
	}

	@When("^user execute the GET request on GitHub$")
	public void user_execute_the_GET_request_on_GitHub_for_() throws Throwable {
//		action.getResponseToJsonNode();
		action.getResponseToJsonNodeFromString();
	}

	@Then("^user recieves response code \"([^\"]*)\"$")
	public void user_recieves_response_code(String statusCode) throws Throwable {
	    action.verifyResponseStatusCode(statusCode);
	}

	@Then("^user recieves total number of repositories count as \"([^\"]*)\" for language \"([^\"]*)\"$")
	public void user_recieves_total_number_of_repositories_count_as_for_language(String arg1, String progLang) throws Throwable {
		action.compareResultsFromProgLangSearch(progLang);
	}	
	
	@Given("^user set the API endpoint query parameters to fetch records for \"([^\"]*)\" username$")
	public void user_set_the_API_endpoint_query_parameters_to_fetch_records_for_username(String username) throws Throwable {
		 action.getEndpointQueryParamForSingleUser(username);
		 action.setApiEndpointWithQueryParam();
	}

	@Then("^user recieves total number of repositories count as \"([^\"]*)\" for username \"([^\"]*)\"$")
	public void user_recieves_total_number_of_repositories_count_as_for_username(String count, String username) throws Throwable {
		action.compareResultsFromUserSearch(username);
	}
	
	@Given("^user set the API endpoint query parameters to fetch top (\\d+) starred records for \"([^\"]*)\" username$")
	public void user_set_the_API_endpoint_query_parameters_to_fetch_top_starred_records_for_username(int arg1, String username) throws Throwable {
		 action.getEndpointQueryParamForTopRecordSearchForUser(username);
		 action.setApiEndpointWithQueryParam();
	}

	@Then("^user verifies top (\\d+) record details$")
	public void user_recieves_top_record_details(int arg1) throws Throwable {
	    action.getTopRecordsFromRepoSearchResponse();
	}
	
	@Given("^user set the API endpoint query parameters to fetch repositories created on \"([^\"]*)\" by \"([^\"]*)\" username$")
	public void user_set_the_API_endpoint_query_parameters_to_fetch_repositories_created_on_by_username(String date, String username) throws Throwable {
		action.getEndpointQueryParamForRepoCreatedOnADay(date, username);
		 action.setApiEndpointWithQueryParam();
	}

	@Then("^user verifies record details of repositories created on a particular day$")
	public void user_verifies_record_details_of_repositories_created_on_a_particular_day() throws Throwable {
		action.getTopRecordsFromRepoSearchResponse();
	}
	
	@Given("^user set the API endpoint query parameters to fetch commits on \"([^\"]*)\" created by \"([^\"]*)\" username$")
	public void user_set_the_API_endpoint_query_parameters_to_fetch_commits_on_created_by_username(String date, String username) throws Throwable {
		action.getEndpointQueryParamForCommitCreatedOnADay(date, username);
		action.setApiEndpointWithQueryParam();   
	}

	@Then("^user verifies record details of commits of a user on a particular day$")
	public void user_verifies_record_details_of_commits_of_a_user_on_a_particular_day() throws Throwable {
		action.getRecordsFromCommitSearchResponse();
	}
}
