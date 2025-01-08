package okhttp;

import dto.UserDtoLombok;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.RandomUtils.*;

public class RegistrationTests implements BaseApi {

    @Test
    public void registrationPositiveOkHttpTest() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .username(generateEmail(10))
                .password("123df4QWER!a")
                .build();
        System.out.println(userDtoLombok);
        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response.body().string());
            Assert.assertTrue(response.isSuccessful());
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registrationNegativeOkHttpTest_wrongEmail() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .username(generateString(10))
                .password("123df4QWER!a")
                .build();
        System.out.println(userDtoLombok);
        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
            System.out.println(response.body().string());

            Assert.assertEquals(response.code(), 400);
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registrationNegativeOkHttpTest_duplicateUser() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .username(generateEmail(10))
                .password("123df4QWER!a")
                .build();
        System.out.println(userDtoLombok);
        RequestBody requestBody = RequestBody.create(GSON.toJson(userDtoLombok), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if(response.isSuccessful()) {
                Response response1 = OK_HTTP_CLIENT.newCall(request).execute();
                System.out.println(response1.body().string());
                Assert.assertEquals(response1.code(),409);
            }
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }

}
