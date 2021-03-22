package apitest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class PrimeiroTest {

    public static void main(String[] args) {
        Response response =  RestAssured.request(Method.GET, "http://localhost:3000/");
        System.out.println(response.getBody().asString().equals("{\"message\":\"Funcionando!\"}") );
        System.out.println(response.statusCode() == 200);

        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

    }

}
