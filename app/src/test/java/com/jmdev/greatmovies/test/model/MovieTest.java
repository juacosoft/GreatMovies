package com.jmdev.greatmovies.test.model;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import android.content.Context;
import android.content.res.AssetManager;


import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

public class MovieTest {
    private Movie movie;
    private Movie movie2;
    @Mock
    private Context context;
    @Mock
    AssetManager assetManager;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doReturn(assetManager).when(context).getAssets();
        movie =new Movie();
        //this.context= RuntimeEnviron;
        //movie2=new Gson().fromJson(loadJSONFromAsset(),Movie.class);
        //movie=new Gson().fromJson(loadJSONFromAsset(),Movie.class);

    }

    @After
    public void testDow()throws Exception{
        System.out.println("Test Complete");
    }

    @Test
    public void testMovieDetails()throws Exception{
        //Assert.assertEquals(movie,movie2);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("fakemovie.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}