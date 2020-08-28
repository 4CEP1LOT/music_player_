package com.kotlin.musiclearning.view.mine;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kotlin.lib_base.model.audio.SongViewModel;

public class MineViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;

    public MineViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SongViewModel.class)) {
            //noinspection unchecked
            return (T) new SongViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
