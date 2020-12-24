package com.udacity.sandwichclub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);

        RViewAdapter adapter = new RViewAdapter(sandwiches);

        RecyclerView list_view = findViewById(R.id.sandwiches_listview);
        LinearLayoutManager linear_layout_manager = new LinearLayoutManager(this);

        list_view.setLayoutManager(linear_layout_manager);
        list_view.setAdapter(adapter);
    }
}
