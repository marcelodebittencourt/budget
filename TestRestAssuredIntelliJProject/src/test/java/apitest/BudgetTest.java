package apitest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BudgetTest extends BudgetBaseTest {
    @Test
    public void testObtemMensagemInicial() {

        given()
                .log().all()

                .when()
                .get()

                .then()
                .log().all()
                .statusCode(200)
        .body("message", is("Funcionando!"));
    }

    @Test
    public void testObtemEndpointEntries() {

        given()
                .log().all()

                .when()
                .get("/entries")

                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(16))
//                .body("ID", hasItem(11))
                //.body("ID", hasItem(5))
//                .body("ID", hasItems(7,17))
        //pendente verificação se campos existem no JSON - validacao da estrutura
                ;
    }

    @Test
    public void testExclusaoRegistro() {

        String idParaExcluir = "5";

        given()
                .log().all()

                .when()
                .delete("/entries/" + idParaExcluir )

                .then()
                .log().all()
                .statusCode(200)
                .body("affectedRows", is(1));
    }

    //Incluir teste para o endpoint /entries/{id} no verbo GET
    @Test
    public void testConsultaRegistroPorId() {

        Integer idParaConsultar = 6;

        given()
                .log().all()

                .when()
                .get("/entries/" + idParaConsultar )

                .then()
                .log().all()
                .statusCode(200)
                .body("ID", hasItem(idParaConsultar))
                .body("Name", hasItem("Despesas Pessoais"))
                .body("size()", is(1))
                .body("[0]", Matchers.hasKey("ID"))
        ;
    }




}
