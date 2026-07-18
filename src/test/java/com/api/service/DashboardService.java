package com.api.service;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class DashboardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";

	public Response count(Role role) {

		Response response = given().spec(requestSpecWithAuth(role)).when().get(COUNT_ENDPOINT);

		return response;
	}

	public Response countWithNoAuth() {

		Response response = given().spec(requestSpec()).when().get(COUNT_ENDPOINT);

		return response;
	}

	// Details Endpoint method
	public Response details(Role role,Object detailsObject) {

		Response response = given().spec(requestSpecWithAuth(role)).body(detailsObject).when().post(DETAIL_ENDPOINT);

		return response;
	}

}
