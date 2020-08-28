package day07;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;
import utility.LibraryUtil;

import java.time.LocalDate;

public class LibraryApp_API_DB_Test {

    private static String libraryToken ;

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";
        //added a utility class that contains below method
        libraryToken = LibraryUtil.loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username")
                                                  , ConfigurationReader.getProperty("library1.librarian_password"));
        DB_Utility.createConnection("library1");

    }
    @DisplayName("Validating the /dashboard_stats endpoint data against Database")
    @Test
    public void test(){
        System.out.println("libraryToken = " + libraryToken);
        // We will make a call to /Dashboard_stats endpoint and validate the data against database data
        DB_Utility.runQuery("SELECT count(*) from books"); // it return the book count as single row and col
        String bookCount  =  DB_Utility.getColumnDataAtRow(1,1);

        DB_Utility.runQuery("SELECT count(*) from users");
        String userCount  =  DB_Utility.getColumnDataAtRow(1,1);

        DB_Utility.runQuery("SELECT count(*) from book_borrow where is_returned=false");
        String borrowedBookCount  =  DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("bookCount = " + bookCount);
        System.out.println("userCount = " + userCount);
        System.out.println("borrowedBookCount = " + borrowedBookCount);



    }


    @AfterAll
    public static void destroy(){

        DB_Utility.destroy();
        RestAssured.reset(); // this is for resetting all the values we set for RestAssured itself

    }




}
