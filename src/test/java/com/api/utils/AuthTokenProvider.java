package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role, String>();
	private static Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);

	private AuthTokenProvider() {
		// Private cosntructor
	}

	public static String gettoken(Role role) {

		LOGGER.info("checking if the token availabe for the role {}", role);

		if (tokenCache.containsKey(role)) {
			LOGGER.info("token found for the role {}", role);
			return tokenCache.get(role);
		}

		LOGGER.info("Token not available making request for role {}", role);
		UserCredentials userCredentials = null;

		if (role == FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		} else if (role == SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		} else if (role == ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		} else if (role == QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(getProperty("BASE_URI")).and().contentType(ContentType.JSON).and()
				.accept(ContentType.JSON).and().body(userCredentials).log().uri().log().method().log().headers().log()
				.body().when().post("login").then().log().ifValidationFails().statusCode(200).and()
				.body("message", equalTo("Success"))

				.extract().body().jsonPath().getString("data.token");

		tokenCache.put(role, token);

		LOGGER.info("token cached for the role {}", role);

		return token;
	}
}
