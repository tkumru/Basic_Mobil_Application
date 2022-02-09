package com.example.kfaumanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {
    private Toolbar toolbar2;
    private EditText editTextNameDetail;
    private EditText editTextPriceDetail;
    private TextView editTextIdDetail;
    private Database database;
    private Students id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar2 = findViewById(R.id.toolbar2);
        editTextNameDetail = findViewById(R.id.editTextNameDetail);
        editTextPriceDetail = findViewById(R.id.editTextPriceDetail);
        editTextIdDetail = findViewById(R.id.editTextIdDetail);
        database = new Database(this);
        id = (Students) getIntent().getSerializableExtra("object");

        toolbar2.setTitle("Student Details");
        setSupportActionBar(toolbar2);

        editTextIdDetail.setText(String.valueOf(id.getId()));
        editTextNameDetail.setText(id.getName());
        editTextPriceDetail.setText(String.valueOf(id.getPrice()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionDelete:
                Snackbar.make(toolbar2, "Delete?", Snackbar.LENGTH_SHORT).setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new StudentsDAO().studentDelete(database, id.getId());

                        startActivity(new Intent(DetailActivity.this, MainActivity.class));
                        finish();
                    }
                }).show();

                return true;

            case R.id.actionUpdate:
                String name = editTextNameDetail.getText().toString().trim();
                String price = editTextPriceDetail.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Snackbar.make(toolbar2, "Write student name!", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                if(TextUtils.isEmpty(price)){
                    Snackbar.make(toolbar2, "Write price of year!", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                new StudentsDAO().studentUpdate(database, id.getId(), name, Integer.parseInt(price));

                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                finish();
                return true;

            default:
                return false;
        }
    }
}