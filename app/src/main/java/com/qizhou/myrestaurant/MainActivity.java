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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qizhou.myrestaurant.entities.Category;
import com.qizhou.myrestaurant.entities.Food;
import com.qizhou.myrestaurant.entities.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HashMap<Food, Integer> foodAdded;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainMenu = findViewById(R.id.main_menu_list);
        List<Menu> mainMenus = getMainMenus();
        Intent intent = new Intent(this, FoodListActivity.class);

        foodAdded = getIntent().getSerializableExtra("foodadded") != null ? (HashMap<Food, Integer>) getIntent().getSerializableExtra("foodadded") :
                new HashMap<>();
        category = (Category) getIntent().getSerializableExtra("category");

        mainMenu.setAdapter(new MenuAdapter(this, mainMenus));
        mainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // intent transfer an object, reference: https://blog.csdn.net/leejizhou/article/details/51105060
                intent.putExtra("category", mainMenus.get(position).getCategory());
                intent.putExtra("foodadded", foodAdded);
                startActivity(intent);
            }
        });
    }

    private List<Menu> getMainMenus() {
        List<Menu> menus = new ArrayList<>();
        // reference https://www.udemy.com/course/android-app/learn/lecture/9000036
        menus.add(new Menu(Category.humber, R.drawable.menu_hamburger, "Humbers"));
        menus.add(new Menu(Category.steak, R.drawable.menu_steak, "Steaks"));
        menus.add(new Menu(Category.salad, R.drawable.menu_salad, "Salads"));
        menus.add(new Menu(Category.iceCream, R.drawable.menu_ice_cream, "Ice Creams"));
        menus.add(new Menu(Category.drink, R.drawable.menu_drinks, "Drinks"));
        return menus;
    }

    private static class MenuAdapter extends BaseAdapter {
        Context context;
        List<Menu> menus;

        public MenuAdapter(Context context, List<Menu> menus) {
            this.context = context;
            this.menus = menus;
        }

        @Override
        public int getCount() {
            return menus.size();
        }

        @Override
        public Object getItem(int position) {
            return menus.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                convertView = layoutInflater.inflate(R.layout.menu_list, parent, false);
            }

            Menu menu = menus.get(position);
            ImageView imageView = convertView.findViewById(R.id.main_menu_list_image);
            imageView.setImageResource(menu.getImage());

            TextView menuName = convertView.findViewById(R.id.main_menu_list_text);
            menuName.setText(menu.getName());

            return convertView;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK ){
            Intent intent = new Intent(this, FoodListActivity.class);
            intent.putExtra("foodadded", foodAdded);
            intent.putExtra("category", category);
            startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}