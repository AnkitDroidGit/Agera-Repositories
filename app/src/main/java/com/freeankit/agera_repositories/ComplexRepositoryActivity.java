package com.freeankit.agera_repositories;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Updatable;

/**
 * @author Ankit Kumar (ankitdroiddeveloper@gmail.com) on 13/12/2017 (MM/DD/YYYY )
 */

public class ComplexRepositoryActivity extends AppCompatActivity {
    private final MutableRepository<Integer> valueRepository = Repositories.mutableRepository(0);
    private Repository<String> textValueRepository;
    Updatable mTextValueUpdatable;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.btn);
        button.setText("Increment");
        button.setVisibility(View.VISIBLE);

        textValueRepository = Repositories.repositoryWithInitialValue("N/A")
                .observe(valueRepository)
                .onUpdatesPerLoop()
                .getFrom(valueRepository)
                .thenTransform(input -> String.format("%d", input))
                .compile();
        button.setOnClickListener(view -> valueRepository.accept(valueRepository.get() + 1));


        mTextValueUpdatable = () -> textView.setText(textValueRepository.get());

    }

    @Override
    protected void onStart() {
        super.onStart();
        textValueRepository.addUpdatable(mTextValueUpdatable);
    }
}
