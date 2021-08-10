package com.yara;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.persist;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
class TagMetadataResourceTest {

    @BeforeEach
    @Transactional
    void setUp() {
        persist(new TagDetails("h1", "React1A", "test1A", "C", Type.ANALOG),
                new TagDetails("h1", "React1B", "test1B", "D", Type.STRING),
                new TagDetails("h2", "React2A", "test2A", "C", Type.ANALOG),
                new TagDetails("h2", "React2B", "test2B", "D", Type.STRING));
        /*
        TagDetails mock = Mockito.mock(TagDetails.class);
        Mockito.when(mock.getTagMetadataByHistorianName("h1"))
                .thenReturn(Arrays.stream(new TagDetails[] {
                        new TagDetails("h1", "React1_phas", "test1", "C", Type.ANALOG),
                        new TagDetails("h2", "React2_phas", "test2", "D", Type.STRING)}));
        QuarkusMock.installMockForType(mock, TagDetails.class);
         */
    }

    @Test
    void whenGetAllTagDetailsByHName_thenTagsShouldBeFound() {
        given().contentType(ContentType.JSON).when().get("/api/v2/tags/allTagDetails")
                .then().statusCode(200);
    }

    @Test
    void whenGetTagDetailsByHistorianNameH1_thenTagsShouldBeFound() {
        given().contentType(ContentType.JSON)
                .when().get("/api/v2/tags/h1")
                .then().statusCode(200)
                .body("name", hasItems("React1A", "React1B"))
                .body("description", hasItems("test1A", "test1B"))
                .body("units", hasItems("C"))
                .body("type", hasItems("ANALOG", "STRING"));
    }

    @Test
    void whenGetTagDetailsByHistorianNameH2_thenTagsShouldBeFound() {
        given().contentType(ContentType.JSON)
                .when().get("/api/v2/tags/h2")
                .then().statusCode(200)
                .body("name", hasItems("React2A", "React2B"))
                .body("description", hasItems("test2A", "test2B"))
                .body("units", hasItems("C"))
                .body("type", hasItems("ANALOG", "STRING"));
    }

    @Test
    void whenGetTagDetailsByHistorianNameH4_thenTagsShouldNotBeFound() {
        given().contentType(ContentType.JSON)
                .when().get("/api/v2/tags/h4")
                .then().statusCode(500);
    }
}