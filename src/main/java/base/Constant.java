package base;
import io.restassured.http.ContentType;
public interface Constant {

    String APP_BASE_URL = "https://reqres.in";
    String APP_BASE_PATH = "/api";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 8000L;

}
