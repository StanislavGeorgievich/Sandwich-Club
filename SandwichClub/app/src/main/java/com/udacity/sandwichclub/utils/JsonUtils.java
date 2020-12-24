package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String sandwich_json) {
        List<String> also_knows_as_list = new ArrayList<>(), ingredients_list = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(sandwich_json);
            JSONObject name = json.getJSONObject("name");
            JSONArray also_knows_as = name.getJSONArray("alsoKnownAs");
            JSONArray ingredients = json.getJSONArray("ingredients");

            for (int i = 0; i < also_knows_as.length(); i++) {
                also_knows_as_list.add(also_knows_as.getString(i));
            }

            for (int i = 0; i < ingredients.length(); i++) {
                ingredients_list.add(ingredients.getString(i));
            }

            return new Sandwich(
                    name.getString("mainName"), also_knows_as_list, json.getString("placeOfOrigin"),
                    json.getString("description"), json.getString("image"), ingredients_list
                    );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
