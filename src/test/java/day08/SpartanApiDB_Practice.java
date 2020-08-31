package day08;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanApiDB_Practice {

    /**
     * The dev just implemented the search endpoint
     * and want it to be tested to make sure it's actually
     * returning the correct result from the database
     *
     *    GET /spartans/search?gender=Female&nameContains=a
     *  we want to test the count of result is correct
     *  for numberOfElements field.
     *
     *  The Database query to get the count is :
     *  // all the females that have a in their name , case insensitive
     *   -- This is how we get the data with case insensitive manner
     *      SELECT * FROM SPARTANS
     *       WHERE LOWER(gender) = 'female'
     *       and LOWER(name) LIKE '%a%' ;
     *
     */
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://100.25.192.231" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;

    }






}
