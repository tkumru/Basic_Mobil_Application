package com.example.kfaumanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class SaveStudentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextName;
    private EditText editTextPrice;
    private Button buttonSave;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_student);

        toolbar = findViewById(R.id.toolbar);
        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonSave = findViewById(R.id.buttonSave);
        database = new Database(this);

        toolbar.setTitle("Save Student");
        setSupportActionBar(toolbar);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String price = editTextPrice.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Snackbar.make(v, "Write student name!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(price)){
                    Snackbar.make(v, "Write price of year!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                new StudentsDAO().studentAdd(database, name, Integer.parseInt(price));

                startActivity(new Intent(SaveStudentActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}