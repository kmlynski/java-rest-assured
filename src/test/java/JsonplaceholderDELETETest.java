import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class JsonplaceholderDELETETest {

    @Test
    void deleteUsers() {
        given()
                .when()
                .delete("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}



