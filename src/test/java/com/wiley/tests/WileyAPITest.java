package com.wiley.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class WileyAPITest {

    //1. Check that:
    //GET method for https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J?term=Java
    @Test
    public void WileyAPITest() {

        RestAssured.baseURI = "https://www.wiley.com/en-us";
        RestAssured.basePath = "/search/autocomplete/";

        //4 suggestions contain attribute “term” : value starting with the preformatted highlighted word java inside
        //like <span class=\"search-highlight\">java</span>
        RestAssured.when().get("comp_00001H9J?term=Java")
                .then().assertThat().body("suggestions.term.size()", equalTo(4))
                .and().body("suggestions.findAll {it.term.startsWith('<span class=\\\"search-highlight\\\">java</span>')}.size()", equalTo(4));

        //4 products contain attribute “name”: value includes the preformatted highlighted word Java inside
        //like <span class=\"search-highlight\">Java</span>
        RestAssured.when().get("comp_00001H9J?term=Java")
                .then().assertThat().body("products.name.size()", equalTo(4))
                .and().body("products.findAll {it.name.contains('<span class=\\\'search-highlight\\\'>Java</span>')}.size()", equalTo(4));

        //4 pages with attribute “title”: value includes word Wiley
        RestAssured.when().get("comp_00001H9J?term=Java")
                .then().assertThat().body("pages.title.size()", equalTo(4))
                .and().body("pages.findAll {it.title.contains('Wiley')}.size()", equalTo(4));

        //Get from previous response any image url from products -> images
        //Make GET request and check if image has got width 300 px
        RestAssured.when().get("comp_00001H9J?term=Java")
                .then().assertThat().body("products.images[0][0].url.contains('coverImage300')", equalTo(true));
    }

    //2. There is a simple HTTP Request & Response Service https://httpbin.org
    //https://httpbin.org/#/Dynamic_data/post_delay__delay_
    //POST/delay/{delay} 
    //Returns a delayed response (max of 10 seconds). 
    @Test
    public void HTTPRequestTest() {
        RestAssured.baseURI = "https://httpbin.org";
        for (int i = 1; i <= 10; i++) {
            RestAssured.when().post("/delay/" + i).
                    then().statusCode(200).and().assertThat()
                    .body("url", equalTo("https://httpbin.org/delay/" + i));
        }
    }

}
