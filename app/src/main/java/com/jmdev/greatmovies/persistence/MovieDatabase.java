package com.jmdev.greatmovies.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.jmdev.greatmovies.test.model.Movie;

@Database(entities = {Movie.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "movies_db";
    private static MovieDatabase instance;

    public static MovieDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MovieDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }
    public abstract MovieDao getMovieDao();

}
