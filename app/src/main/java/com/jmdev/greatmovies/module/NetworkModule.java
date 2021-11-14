package com.jmdev.greatmovies.module;



import static com.jmdev.greatmovies.test.util.Constants.BASE_URL;
import static com.jmdev.greatmovies.test.util.Constants.CONNECTION_TIMEOUT;
import static com.jmdev.greatmovies.test.util.Constants.READ_TIMEOUT;
import static com.jmdev.greatmovies.test.util.Constants.WRITE_TIMEOUT;

import com.jmdev.greatmovies.networking.APIService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModule {



    private static OkHttpClient client = new OkHttpClient.Builder()

            // establish connection to server
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte read from the server
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte sent to server
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

            .retryOnConnectionFailure(false)

            .build();

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static APIService provideRepoService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}