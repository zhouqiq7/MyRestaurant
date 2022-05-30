package com.qizhou.myrestaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qizhou.myrestaurant.entities.Category;
import com.qizhou.myrestaurant.entities.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private HashMap<Food, Integer> foodAdded;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        foodAdded = (HashMap<Food, Integer>) getIntent().getSerializableExtra("foodadded");
        category = (Category) getIntent().getSerializableExtra("category");

        List<Food> foodList = new ArrayList<>(foodAdded != null ? foodAdded.keySet() : new ArrayList<>());

        ListView cartListView = findViewById(R.id.cart_list);
        cartListView.setAdapter(new CartAdapter(this, foodList));
    }

    private static class CartAdapter extends BaseAdapter {
        Context context;
        List<Food> foods;

        public CartAdapter(Context context, List<Food> foods) {
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
                convertView = layoutInflater.inflate(R.layout.cart_list, parent, false);
            }

            Food food = foods.get(position);
            TextView foodName = convertView.findViewById(R.id.cart_list_name);
            foodName.setText(food.getName());

            TextView foodNum = convertView.findViewById(R.id.cart_list_num);
//            foodNum.setText(String.valueOf(CartActivity.foodAdded.get(food)));

            TextView foodPrice = convertView.findViewById(R.id.cart_list_price);
            foodPrice.setText(String.valueOf(food.getPrice()));

            TextView foodTotalPrice = convertView.findViewById(R.id.cart_list_total);
//            foodTotalPrice.setText(String.valueOf(food.getPrice() * CartActivity.foodAdded.get(food)));

            return convertView;
        }
    }

    public void onClearClicked(View view) {
        foodAdded.clear();
        Intent intent = new Intent(this, FoodListActivity.class);
        intent.putExtra("foodadded", foodAdded);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, FoodListActivity.class);
            intent.putExtra("foodadded", foodAdded);
            intent.putExtra("category", category);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
