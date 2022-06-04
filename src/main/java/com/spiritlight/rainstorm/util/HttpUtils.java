package com.spiritlight.rainstorm.util;

import okhttp3.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.Map;

@ParametersAreNonnullByDefault
public class HttpUtils {
    static final OkHttpClient client = new OkHttpClient();
    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (NullPointerException | IOException npe) {
            npe.printStackTrace();
            return "Error.NPE";
        }
    }

    public static int post(String url, Map<String, String> content)
            throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body;
        for (Map.Entry<String, String> entry : content.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        return response.code();
    }
}

