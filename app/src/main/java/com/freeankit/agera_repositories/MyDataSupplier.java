package com.freeankit.agera_repositories;

import android.support.annotation.NonNull;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/12/2017 (MM/DD/YYYY )
 */

//public class MyDataSupplier implements Observable, com.google.android.agera.Supplier<String>, Receiver<String> {
public class MyDataSupplier implements MutableRepository<String> {
    List<Updatable> mUpdatables = new ArrayList<>();

    private String mValue;

    @Override
    public void addUpdatable(@NonNull Updatable updatable) {
        mUpdatables.add(updatable);
    }

    @Override
    public void removeUpdatable(@NonNull Updatable updatable) {
        mUpdatables.remove(updatable);
    }

    @Override
    public void accept(@NonNull String value) {
        mValue = value;
        // Notify the updatables that we have new data
        for (Updatable updatable : mUpdatables) {
            updatable.update();
        }
    }

    @NonNull
    @Override
    public String get() {
        return mValue;
    }
}
