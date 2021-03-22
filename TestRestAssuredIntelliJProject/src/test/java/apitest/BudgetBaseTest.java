package apitest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BudgetBaseTest {
    @BeforeAll
    public static void preCondicao() {
        RestAssured.baseURI = "http://localhost:3000/";
    }
}
