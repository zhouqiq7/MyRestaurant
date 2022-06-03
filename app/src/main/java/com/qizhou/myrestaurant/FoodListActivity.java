package com.qizhou.myrestaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qizhou.myrestaurant.entities.Category;
import com.qizhou.myrestaurant.entities.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {
    private HashMap<Food, Integer> foodAdded;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        category = (Category) getIntent().getSerializableExtra("category");
        foodAdded = (HashMap<Food, Integer>) getIntent().getSerializableExtra("foodadded");
        List<Food> foods = initFoods(category);

        Intent intent = new Intent(this, FoodDetailActivity.class);
        ListView foodListView = findViewById(R.id.foods_list);
        foodListView.setAdapter(new FoodAdapter(this, foods));
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("food", foods.get(position));
                intent.putExtra("foodadded", foodAdded);
                startActivity(intent);
            }
        });

        TextView totalPrice = findViewById(R.id.added_food_total_price);
        totalPrice.setText("Total: " + getTotalPrice(foodAdded));

        Button seeCartButton = findViewById(R.id.added_foods_view_cart);
        seeCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodListActivity.this, CartActivity.class);
                intent.putExtra("foodadded", foodAdded);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });

    }

    private String getTotalPrice(HashMap<Food, Integer> foodAdded) {
        if (foodAdded.isEmpty()) {
            return "";
        }
        double totalPrice = 0;
        for (Food food : foodAdded.keySet()) {
            totalPrice += food.getPrice() * foodAdded.get(food);
        }
        return String.valueOf(totalPrice);
    }

    private static class FoodAdapter extends BaseAdapter {
        Context context;
        List<Food> foods;

        public FoodAdapter(Context context, List<Food> foods) {
            this.context = context;
            this.foods = foods;
        }

        @Override
        public int getCount() {
            return foods.size();
        }

        @Override
        public Object getItem(int position) {
            return foods.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                convertView = layoutInflater.inflate(R.layout.food_list, parent, false);
            }

            Food food = foods.get(position);
            TextView foodName = convertView.findViewById(R.id.food_list_name);
            foodName.setText(food.getName());

            return convertView;
        }
    }

    private List<Food> initFoods(Category category) {
        List<Food> foods = new ArrayList<>();
        if (Category.humber.equals(category)) {
            foods.add(new Food("Beef Burger", R.drawable.menu_hamburger, category, "This is a delicious burger, This is a delicious burger", 11.99));
            foods.add(new Food("Chicken Burger", R.drawable.menu_hamburger, category, "This is a delicious burger", 10.99));
            foods.add(new Food("Fish Burger", R.drawable.menu_hamburger, category, "This is a delicious burger", 12.99));
            foods.add(new Food("No meet Burger", R.drawable.menu_hamburger, category, "This is a delicious burger", 11.99));
            foods.add(new Food("Pork Burger", R.drawable.menu_hamburger, category, "This is a delicious burger", 10.99));
        } else if (Category.steak.equals(category)) {
            foods.add(new Food("Beef Steak", R.drawable.menu_steak, category, "This is a delicious steak", 11.99));
            foods.add(new Food("Chicken Steak", R.drawable.menu_steak, category, "This is a delicious steak", 13.99));
            foods.add(new Food("Fish Steak", R.drawable.menu_steak, category, "This is a delicious steak", 15.99));
            foods.add(new Food("No meet Steak", R.drawable.menu_steak, category, "This is a delicious steak", 11.99));
            foods.add(new Food("Pork Steak", R.drawable.menu_steak, category, "This is a delicious steak", 10.99));
        } else if (Category.salad.equals(category)){
            foods.add(new Food("Tomato Salad", R.drawable.menu_salad, category, "This is a delicious salad", 11.99));
            foods.add(new Food("Strawberry Salad", R.drawable.menu_salad, category, "This is a delicious salad", 13.99));
            foods.add(new Food("Green Salad", R.drawable.menu_salad, category, "This is a delicious salad", 15.99));
            foods.add(new Food("Mixed Salad", R.drawable.menu_salad, category, "This is a delicious salad", 11.99));
            foods.add(new Food("Seafood Salad", R.drawable.menu_salad, category, "This is a delicious salad", 10.99));
        } else if (Category.iceCream.equals(category)) {
            foods.add(new Food("Mango Ice Cream", R.drawable.menu_ice_cream, category, "This is a delicious ice cream", 11.99));
            foods.add(new Food("Mint Ice Cream", R.drawable.menu_ice_cream, category, "This is a delicious ice cream", 12.99));
            foods.add(new Food("Strawberry Ice Cream", R.drawable.menu_ice_cream, category, "This is a delicious ice cream", 8.99));
            foods.add(new Food("Blueberry Ice Cream", R.drawable.menu_ice_cream, category, "This is a delicious ice cream", 11.99));
            foods.add(new Food("Vanilla Ice Cream", R.drawable.menu_ice_cream, category, "This is a delicious ice cream", 10.99));
        } else {
            foods.add(new Food("Apple Juice", R.drawable.menu_drinks, category, "This is a delicious Juice", 8.99));
            foods.add(new Food("Orange Juice", R.drawable.menu_drinks, category, "This is a delicious Juice", 7.99));
            foods.add(new Food("Mixed Juice", R.drawable.menu_drinks, category, "This is a delicious Juice", 8.99));
            foods.add(new Food("Special Juice", R.drawable.menu_drinks, category, "This is a delicious Juice", 6.99));
            foods.add(new Food("Summer Juice", R.drawable.menu_drinks, category, "This is a delicious Juice", 9.99));
        }

        return foods;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("foodadded", foodAdded);
            intent.putExtra("category", category);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
