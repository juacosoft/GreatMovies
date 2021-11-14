package com.jmdev.greatmovies.module;



import com.jmdev.greatmovies.test.model.UserDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserDataModule {

    public UserDataModule() { }

    @Provides
    @Singleton
    UserDataManager userDataManager() {
        return new UserDataManager();
    }
}