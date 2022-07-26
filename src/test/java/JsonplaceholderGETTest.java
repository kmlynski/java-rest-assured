import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

class JsonplaceholderGETTest {

    @Test
    void readAllUsers() {
        Response response = given().when().get("https://jsonplaceholder.typicode.com/users");


        JsonPath json = response.jsonPath();

        List<String> names = json.getList("name");
        names.forEach(System.out::println);
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(10, names.size());
    }

    @Test
    void readOneUser() {
        Response response = given().when().get("https://jsonplaceholder.typicode.com/users/1");

        JsonPath json = response.jsonPath();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));
    }

    @Test
    void readUserWithPathVariable() {
        Response response = given().pathParam("userId", 1).when().get("https://jsonplaceholder.typicode.com/users/{userId}");

        Assertions.assertEquals(200, response.statusCode());
        JsonPath json = response.jsonPath();
        Assertions.assertEquals("Leanne Graham", json.get("name"));
        Assertions.assertEquals("Bret", json.get("username"));
        Assertions.assertEquals("Kulas Light", json.get("address.street"));
    }

    @Test
    void readUsersWithQueryParams() {
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get("https://jsonplaceholder.typicode.com/users");

        Assertions.assertEquals(200, response.statusCode());
        JsonPath json = response.jsonPath();
        Assertions.assertEquals("Leanne Graham", json.getList("name").get(0));
        Assertions.assertEquals("Bret", json.getList("username").get(0));
        Assertions.assertEquals("Kulas Light", json.getList("address.street").get(0));
    }
}
