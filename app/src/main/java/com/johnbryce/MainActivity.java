package com.johnbryce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final List<String> NAMES;

    static {
        NAMES = new ArrayList<>();
        NAMES.add("Daniel");
        NAMES.add("Israel");
        NAMES.add("Michael");
        NAMES.add("Alina");
        NAMES.add("Emmanuel");
        NAMES.add("Tamar");
        NAMES.add("Daria");
        NAMES.add("Rany");
    }

    private ArrayAdapter<String> namesAdapter;
    private ListView lvNames;
    private Button btnAdd;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViewsById();

        namesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                NAMES);

        lvNames.setAdapter(namesAdapter);

        lvNames.setOnItemClickListener((parent, view, position, id) -> {
            String message = String.format("Position: %s, Name: %s", position, NAMES.get(position));
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG)
                    .show();
        });

        btnAdd.setOnClickListener(view -> {
            handleAddName();
        });
    }

    private void handleAddName() {
        String name = etName.getText().toString();

        if (!TextUtils.isEmpty(name)) {
            if (!exists(name)) {
                addNameToList(name);
            } else {
                Toast.makeText(this, "Already exist!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Empty field!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private boolean exists(String name) {
        return NAMES.stream()
                .map(String::toLowerCase)
                .anyMatch(s -> s.equals(name.toLowerCase()));
    }

    private void addNameToList(String name) {
        NAMES.add(name);
        namesAdapter.notifyDataSetChanged();
    }

    private void findAllViewsById() {
        lvNames = findViewById(R.id.lv_names);
        btnAdd = findViewById(R.id.btn_add);
        etName = findViewById(R.id.et_name);
    }
}