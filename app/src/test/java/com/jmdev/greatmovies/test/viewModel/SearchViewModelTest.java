package com.jmdev.greatmovies.test.viewModel;



import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.jmdev.greatmovies.networking.APIService;
import com.jmdev.greatmovies.test.model.Movie;
import com.jmdev.greatmovies.test.model.MovieDBResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import static org.junit.Assert.assertThat;
@RunWith(MockitoJUnitRunner.class)
//@RunWith(JUnit4.class)
public class SearchViewModelTest {

    @Rule // -> allows liveData to work on different thread besides main, must be public!
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

    private SelectedMovieViewModel viewModel;
    @Mock
    Application app= Mockito.mock(Application.class);
    private final String fakeUrl="http://me.test.com";
    private Observer mockObserver;




        //pendiente implementacion observer
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockObserver=Mockito.mock(Observer.class);
        viewModel=new SelectedMovieViewModel(fakeRetrofit());

    }

    //pendientes pruebas de insercion y cambio de datos
    @Test
    public void testGetDataMovies(){

    }

//fake retrofit para pruebas de bad request y errores en api_key
    private APIService fakeRetrofit(){
        return new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(fakeUrl)
                .build().create(APIService.class);

    }



}