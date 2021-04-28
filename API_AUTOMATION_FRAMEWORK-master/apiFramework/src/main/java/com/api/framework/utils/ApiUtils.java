package com.api.framework.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtils {

	public HttpGet createHttpGetRequest(String apiEndpoint) {
		return new HttpGet(apiEndpoint);
	}

	public HttpGet setGetRequestHeader(HttpGet getRequest, String headerKey, String headerValue) {
		getRequest.setHeader(headerKey, headerValue);
		return getRequest;
	}

	public HttpResponse getDataFromGitApiForGetRequest(HttpGet getRequest) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(getRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public JsonNode getJsonNode(HttpResponse response) {
		JsonNode responseJson = null;
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			try {
//				InputStream apiResponseStream=response.getEntity().getContent();
//				System.out.println(apiResponseStream.readAllBytes());
//				System.out.println(response.getEntity());
				responseJson = mapper.readTree(response.getEntity().getContent());
				
				return responseJson;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseJson;
	}
	public JsonNode getJsonNode(String responseString) {
		JsonNode responseJson = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			try {
				responseJson = mapper.readValue(responseString, JsonNode.class);
				return responseJson;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseJson;
	}

	public int getHttpResponseStatusCode(HttpResponse response) {
		int statusCode = 0;
		try {
			statusCode = response.getStatusLine().getStatusCode();
		} catch (Exception e) {

		}
		return statusCode;
	}

}
