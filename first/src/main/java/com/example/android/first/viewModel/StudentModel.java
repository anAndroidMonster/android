package com.example.android.first.viewModel;

import android.arch.lifecycle.ViewModel;

public class StudentModel extends ViewModel {
    private String name;
    private String sexy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }
}
