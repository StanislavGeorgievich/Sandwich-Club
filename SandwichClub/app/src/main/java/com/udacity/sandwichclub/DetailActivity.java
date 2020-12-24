package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ImageView ingredientsIv;
    TextView description, also_known_as, place_of_origin, ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        description = findViewById(R.id.description_tv);
        also_known_as = findViewById(R.id.also_known_tv);
        place_of_origin = findViewById(R.id.origin_tv);
        ingredients = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();

        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String sandwich_json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(sandwich_json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        description.setText(sandwich.getDescription());
        place_of_origin.setText(sandwich.getPlaceOfOrigin());
        also_known_as.setText(sandwich.getAlsoKnownAs().toString().replaceAll("[\\]\\[]", ""));
        ingredients.setText(sandwich.getIngredients().toString().replaceAll("[\\]\\[]", ""));

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
