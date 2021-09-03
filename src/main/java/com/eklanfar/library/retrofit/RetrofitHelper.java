package com.eklanfar.library.retrofit;

import com.eklanfar.library.retrofit.microblink.MicroblinkAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitHelper {

    private static final Logger log = LoggerFactory.getLogger(RetrofitHelper.class);

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::info).setLevel(HttpLoggingInterceptor.Level.BODY);

    public static MicroblinkAPI getMicroblinkAPI(final String baseUrl, final Integer timeout) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().clear();
        httpClient.readTimeout(timeout, TimeUnit.SECONDS);
        httpClient.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");
            requestBuilder.header("Accept", "application/json");
            return chain.proceed(requestBuilder.build());
        });
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(MicroblinkAPI.class);
    }

    public static String parseRetrofitErrorBody(final Response<?> response) {
        try {
            return String.format("code = %s, body = %s", response.code(), response.errorBody().string());
        } catch (Exception e) {
            log.info("FAILED - parseRetrofitErrorBody; {}", e.getMessage());
            log.error("EXCEPTION - parseRetrofitErrorBody", e);
        }
        return null;
    }

    /**
     * This class should not be instantiated.
     */
    private RetrofitHelper() {
        throw new IllegalStateException("Cannot create instance of static class");
    }
}
