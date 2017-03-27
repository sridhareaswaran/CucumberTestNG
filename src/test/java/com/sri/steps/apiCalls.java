package com.sri.steps;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.assertEquals;

public class apiCalls {

    private RequestSpecification spec;
    String Resp;
    String Director_Name;
    String Movie_Name;
    String Genre_Name;
    int HTTP_STATUS_CODE;
    JsonPath response;


    public apiCalls() {
        RestAssured.baseURI = "http://www.omdbapi.com/";
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        // RestAssured.proxy("127.0.0.1", 8888);
        spec = RestAssured.with();
    }


    public void initialize_and_check_api() {
        int httpStatus = spec.given().get().andReturn().getStatusCode();
        assertEquals(httpStatus, 200);
    }


    public void search_title(String title) {
        spec.given().param("t", title)
                .param("r", "json");
    }


    public void search_withID(String ID) {
        spec.given().param("i", ID)
                .param("r", "json");
    }


    public void verify_dirName(String dir_name) {
        assertEquals(dir_name, Director_Name);
    }


    public void verify_genre(String genre) {
        assertEquals(genre, Genre_Name);
    }


    public void getData() {
        Resp = spec.when().get().asString();
        spec.when().get().body(); //.prettyPrint();

        response = new JsonPath(Resp);
        Director_Name = response.get("Director");
        Movie_Name = response.get("Title");
        Genre_Name = response.get("Genre");
        HTTP_STATUS_CODE = spec.when().get().andReturn().getStatusCode();
    }


    public void verify_HttpStatus(String HttpStatus) {
        int statusCode = Integer.parseInt(HttpStatus);
        assertEquals(statusCode, HTTP_STATUS_CODE);
    }


    public void set_releaseData(String year) {
        spec.given().param("y", year);
    }

    public void verify_error(String error) {
        assertThat(Resp, containsString(error));
    }

    public void verify_movieName(String title) {
        assertEquals(title, Movie_Name);
    }


}
