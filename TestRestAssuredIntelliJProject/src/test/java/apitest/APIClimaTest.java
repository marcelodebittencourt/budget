package apitest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers.*;

public class APIClimaTest {

    @Test
    public void testPesquisaCidade() {
        String nomeDaCidade = "Porto Alegre";
        given() //Pré-condição específica do teste
                .log().all()
                .queryParam("q", nomeDaCidade) //q={city name}
                .queryParam("appid", "b8a0822bec5ecb00f4f317d431a407ba")//appid={API key}
                .queryParam("units", "metric")
                .queryParam("lang", "pt_br")
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
        .body("name", Matchers.is(nomeDaCidade))
        .body("main.temp", Matchers.greaterThan(-2F))
        ;
    }

    @Test
    public void testPesquisaCidadeXML() {
        String nomeDaCidade = "Porto Alegre";
        given() //Pré-condição específica do teste
                .log().all()
                .queryParam("q", nomeDaCidade) //q={city name}
                .queryParam("appid", "b8a0822bec5ecb00f4f317d431a407ba")//appid={API key}
                .queryParam("units", "metric")
                .queryParam("lang", "pt_br")
                .queryParam("mode", "xml")
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
        //.rootPath("city")
        .rootPath("<city name=Porto Alegre")

        //.body("@name", Matchers.is(nomeDaCidade))
//        .body("country", Matchers.is("BR"))

//                .body("name", Matchers.is(nomeDaCidade))
//                .body("main.temp", Matchers.greaterThan(-2F))
        ;
    }


}
