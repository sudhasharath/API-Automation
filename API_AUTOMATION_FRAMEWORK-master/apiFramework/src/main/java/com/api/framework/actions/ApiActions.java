package com.api.framework.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.api.framework.dataobjects.GitCommitUserDataObject;
import com.api.framework.dataobjects.GitRepoUserDataObject;
import com.api.framework.utils.ApiUtils;
import com.api.framework.utils.ConfigReader;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiActions {
	private ApiUtils apiUtils;
	private ConfigReader configReader;
	private HttpGet getRequest;
	private HttpResponse getResponse;
	private String apiEndpoint;
	private String endpointQueryParam;
	private JsonNode responseJson;
	private String responseString;

	public ApiActions() {
		apiUtils = new ApiUtils();
		configReader = new ConfigReader();
	}

	public void setApiEndpoint(String url) {
		apiEndpoint = getGitApiUrlFor(url);
	}

	public void setApiEndpointWithQueryParam() {
		apiEndpoint = apiEndpoint + endpointQueryParam;
	}

	public String getHeaderValueFromConfig(String headerKey) {
		switch (headerKey) {
		case "headerAcceptRepoSearch":
			return configReader.getHeaderAcceptForRepoSearch();
		case "headerAcceptCommitSearch":
			return configReader.getHeaderAcceptForCommitSearch();
		default:
			return "";
		}
	}

	public String getHeaderString(String headerKey) {
		switch (headerKey) {
		case "headerAcceptRepoSearch":
			return "Accept";
		case "headerAcceptCommitSearch":
			return "Accept";
		default:
			return "";
		}
	}

	public void setApiHeader(String headerKey) {
		getRequest = apiUtils.createHttpGetRequest(apiEndpoint);
		getRequest = apiUtils.setGetRequestHeader(getRequest, getHeaderString(headerKey),
				getHeaderValueFromConfig(headerKey));
	}

	public void getEndpointQueryParamForLangSearch(String progLang) {
		if (!progLang.contains(";")) {
			endpointQueryParam = "?q=language:" + progLang;
		} else {
			String[] progLangs = progLang.split(";");
			List<String> progLangsList = Arrays.asList(progLangs);
			StringBuilder sb = new StringBuilder();
			for (String lang : progLangsList) {
				sb.append("language:");
				sb.append(lang);
				sb.append("+");
			}
			endpointQueryParam = "?q=" + sb.toString().substring(0, sb.toString().length() - 1);
		}
	}

	public void getEndpointQueryParamForTopRecordSearchForUser(String username) {
		endpointQueryParam = "?q=" + username + "&sort=stars&order=desc";
	}

	public void getEndpointQueryParamForRepoCreatedOnADay(String date, String username) {
		endpointQueryParam = "?q=" + username + "+created:" + date;
	}

	public void getEndpointQueryParamForCommitCreatedOnADay(String date, String username) {
		endpointQueryParam = "?q=committer-name:" + username + "+committer-date:" + date;
	}

	public GitRepoUserDataObject setGitRepoDataObject(JSONObject json) {
		GitRepoUserDataObject newObj = new GitRepoUserDataObject();
		newObj.setFull_name(json.getString("full_name"));
		newObj.setCreated_at(json.getString("created_at"));
		newObj.setScore(Integer.toString(json.getInt("score")));
		newObj.setLanguage(json.get("language").toString());
		return newObj;
	}

	public GitCommitUserDataObject setGitCommitDataObject(JSONObject json) {
		GitCommitUserDataObject newObj = new GitCommitUserDataObject();
		newObj.setUrl(json.get("url").toString());
		newObj.setMessage(json.get("message").toString());
		return newObj;
	}

	public void getTopRecordsFromRepoSearchResponse() {
		try {
			JSONObject dataInJson = new JSONObject(responseString);
			List<GitRepoUserDataObject> repoUserData = new ArrayList<>();
			JSONArray totalUsers = dataInJson.getJSONArray("items");
			for (int i = 0; i < totalUsers.length(); i++) {
				GitRepoUserDataObject localObj = new GitRepoUserDataObject();
				localObj = setGitRepoDataObject(totalUsers.getJSONObject(i));
				repoUserData.add(localObj);
			}
			for (GitRepoUserDataObject object : repoUserData) {
				System.out.println("Data for Object:");
				System.out.println("Name: "+object.getFull_name());
				System.out.println("Language: "+object.getLanguage());
				System.out.println("Score: "+object.getScore());
				System.out.println("Created On: "+object.getCreated_at());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRecordsFromCommitSearchResponse() {
		try {
			JSONObject dataInJson = new JSONObject(responseString);
			List<GitCommitUserDataObject> commitUserData = new ArrayList<>();
			JSONArray totalUsers = dataInJson.getJSONArray("items");
			for (int i = 0; i < totalUsers.length(); i++) {
				GitCommitUserDataObject localObj = new GitCommitUserDataObject();
				localObj = setGitCommitDataObject(totalUsers.getJSONObject(i).getJSONObject("commit"));
				commitUserData.add(localObj);
			}
			for (GitCommitUserDataObject object : commitUserData) {
				System.out.println("Data of Object:");
				System.out.println("Commit Url: "+object.getUrl());
				System.out.println("Commit Message: "+object.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getEndpointQueryParamForSingleUser(String username) {
		endpointQueryParam = "?q=" + username;
	}

	public void getHttpResponse() throws ParseException, IOException {
		getResponse = apiUtils.getDataFromGitApiForGetRequest(getRequest);
		responseString = EntityUtils.toString(getResponse.getEntity());
	}

	public void verifyResponseStatusCode(String statusCode) {
		try {
			int statusCodeFromResponse = apiUtils.getHttpResponseStatusCode(getResponse);
			System.out.println("API Response Code: "+statusCodeFromResponse);
		} catch (Exception e) {

		}
	}

	public void getResponseToJsonNode() {
		try {
			getHttpResponse();
			responseJson = apiUtils.getJsonNode(getResponse);
		} catch (Exception e) {
		}
	}

	public void getResponseToJsonNodeFromString() {
		try {
			getHttpResponse();
			responseJson = apiUtils.getJsonNode(responseString);
		} catch (Exception e) {
		}
	}
	public void compareResultsFromProgLangSearch(String progLang) {
		// Fetching the total number of Github repositories for a given programming
		// language
		try {
				System.out.println("Total " + progLang + " repositories in git: "
						+ responseJson.get("total_count"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compareResultsFromUserSearch(String username) {
		// Fetching the total number of Github repositories for a given programming
		// language
		try {
			System.out.println("Total " + username + " repositories in git: " + responseJson.get("total_count"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getGitApiUrlFor(String searchCriteria) {
		String apiUrl = "";
		switch (searchCriteria) {
		case "repositories":
			apiUrl = "https://api.github.com/search/repositories";
			break;

		case "commits":
			apiUrl = "https://api.github.com/search/commits";
			break;

		case "code":
			apiUrl = "https://api.github.com/search/code";
			break;

		case "issues":
			apiUrl = "https://api.github.com/search/issues";
			break;

		case "users":
			apiUrl = "https://api.github.com/search/users";
			break;

		case "topics":
			apiUrl = "https://api.github.com/search/topics";
			break;

		case "labels":
			apiUrl = "https://api.github.com/search/labels";
			break;

		default:
			apiUrl = "https://api.github.com/search/repositories";
			break;
		}

		return apiUrl;
	}

}
