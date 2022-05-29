package com.qizhou.myrestaurant;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qizhou.myrestaurant.entities.Category;
import com.qizhou.myrestaurant.entities.Food;
import com.qizhou.myrestaurant.entities.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_list);

        Menu menu = (Menu) getIntent().getSerializableExtra("menu");
        HashMap<Food, Integer> foodIntegerHashMap = (HashMap<Food, Integer>) getIntent().getSerializableExtra("foodadded");
        List<Food> foods = initFoods(menu);
        ListView foodList = findViewById(R.id.foods_list);
        foodList.setAdapter(new FoodAdapter(this, foods));

        TextView totalPrice = findViewById(R.id.added_foods_total_price);
        totalPrice.setText("Total: " + getTotalPrice(foodIntegerHashMap));
    }

    private String getTotalPrice(HashMap<Food, Integer> foodIntegerHashMap) {
        if (foodIntegerHashMap.isEmpty()) {
            return "";
        }
        return "";
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
                convertView = layoutInflater.inflate(R.layout.foods_list, parent, false);
            }

            Food food = foods.get(position);
            TextView foodName = convertView.findViewById(R.id.foods_list_name);
            foodName.setText(food.getName());

            return convertView;
        }
    }

    private List<Food> initFoods(Menu menu) {
        List<Food> foods = new ArrayList<>();
        if (Category.humber.equals(menu.getCategory())) {
            foods.add(new Food("Beef Burger", R.drawable.menu_hamburger, menu.getCategory(), "This is a delicious burger", 11.99));
            foods.add(new Food("Chicken Burger", R.drawable.menu_hamburger, menu.getCategory(), "This is a delicious burger", 10.99));
            foods.add(new Food("Fish Burger", R.drawable.menu_hamburger, menu.getCategory(), "This is a delicious burger", 12.99));
            foods.add(new Food("No meet Burger", R.drawable.menu_hamburger, menu.getCategory(), "This is a delicious burger", 11.99));
            foods.add(new Food("Pork Burger", R.drawable.menu_hamburger, menu.getCategory(), "This is a delicious burger", 10.99));
        } else if (Category.steak.equals(menu.getCategory())) {
            foods.add(new Food("Beef Steak", R.drawable.menu_steak, menu.getCategory(), "This is a delicious steak", 11.99));
            foods.add(new Food("Chicken Steak", R.drawable.menu_steak, menu.getCategory(), "This is a delicious steak", 13.99));
            foods.add(new Food("Fish Steak", R.drawable.menu_steak, menu.getCategory(), "This is a delicious steak", 15.99));
            foods.add(new Food("No meet Steak", R.drawable.menu_steak, menu.getCategory(), "This is a delicious steak", 11.99));
            foods.add(new Food("Pork Steak", R.drawable.menu_steak, menu.getCategory(), "This is a delicious steak", 10.99));
        } else {
            foods.add(new Food("Beef Salad", R.drawable.menu_salad, menu.getCategory(), "This is a delicious salad", 11.99));
            foods.add(new Food("Chicken Salad", R.drawable.menu_salad, menu.getCategory(), "This is a delicious salad", 13.99));
            foods.add(new Food("Fish Salad", R.drawable.menu_salad, menu.getCategory(), "This is a delicious salad", 15.99));
            foods.add(new Food("No meet Salad", R.drawable.menu_salad, menu.getCategory(), "This is a delicious salad", 11.99));
            foods.add(new Food("Pork Salad", R.drawable.menu_salad, menu.getCategory(), "This is a delicious salad", 10.99));
        }
        return foods;
    }
}
