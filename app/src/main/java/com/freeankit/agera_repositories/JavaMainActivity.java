package com.freeankit.agera_repositories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Updatable;

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/12/2017 (MM/DD/YYYY )
 */

public class JavaMainActivity extends AppCompatActivity {
    //    MyDataSupplier myDataSupplier = new MyDataSupplier();
//    Updatable updatable;
    private MutableRepository<String> mStringRepo;
    private Updatable mLoggerUpdatable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStringRepo = Repositories.mutableRepository("Initial value by Agera with Repository");

        final TextView textView = findViewById(R.id.textView);

        // Create an Updatable
//        updatable = new Updatable() {
//            @Override
//            public void update() {
//                Log.d("AGERA", myDataSupplier.get());
//                textView.setText(myDataSupplier.get());
//            }
//        };
//        myDataSupplier.addUpdatable(updatable);

//        myDataSupplier.accept("Hello Agera!");


        // Create an Updatable
        mLoggerUpdatable = () -> {
            Log.d("AGERA", mStringRepo.get());
            textView.setText(mStringRepo.get());
        };
        Button button = findViewById(R.id.btn);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(view -> startActivity(new Intent(JavaMainActivity.this, ComplexRepositoryActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStringRepo.addUpdatable(mLoggerUpdatable);

        // Change the repository's value
        mStringRepo.accept("Hello World by Agera with Repository");
    }

    @Override
    protected void onStop() {
        mStringRepo.removeUpdatable(mLoggerUpdatable);
        super.onStop();
    }
}