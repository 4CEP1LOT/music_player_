package com.kotlin.lib_base.model;

import androidx.lifecycle.SavedStateHandle;

public class ViewModel extends androidx.lifecycle.ViewModel {

        private SavedStateHandle mState;

        public ViewModel(SavedStateHandle savedStateHandle) {
            mState = savedStateHandle;
        }


}
