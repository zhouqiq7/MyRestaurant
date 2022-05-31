package com.qizhou.myrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.qizhou.myrestaurant.entities.Food;

import java.util.HashMap;

public class FoodDetailActivity extends AppCompatActivity {

    private HashMap<Food, Integer> foodAdded;
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        foodAdded = (HashMap<Food, Integer>) getIntent().getSerializableExtra("foodadded");
        food = (Food) getIntent().getSerializableExtra("food");

        ImageView foodImage = findViewById(R.id.food_detail_image);
        foodImage.setImageResource(food.getImage());

        TextView foodName = findViewById(R.id.food_detail_name);
        foodName.setText(food.getName());

        TextView foodDescription = findViewById(R.id.food_detail_description);
        foodDescription.setText(food.getDescription());

        TextView foodPrice = findViewById(R.id.food_detail_price);
        foodPrice.setText("$" + String.valueOf(food.getPrice()));
    }

    public void onAddFoodClicked(View view) {
        EditText quantity = findViewById(R.id.food_detail_quantity);
        String text = quantity.getText().toString();
        if (addFood(text)) {
            Intent intent = new Intent(this, FoodListActivity.class);
            intent.putExtra("foodadded", foodAdded);
            intent.putExtra("category", food.getCategory());
            startActivity(intent);
        }
    }

    private boolean addFood(String text) {
        if (text.isEmpty()) {
            Toast.makeText(this, "Quantity is empty", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (foodAdded.containsKey(food)) {
                foodAdded.put(food, foodAdded.get(food) + Integer.parseInt(text));
            } else {
                foodAdded.put(food, Integer.parseInt(text));
            }
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, FoodListActivity.class);
            intent.putExtra("foodadded", foodAdded);
            intent.putExtra("category", food.getCategory());
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
