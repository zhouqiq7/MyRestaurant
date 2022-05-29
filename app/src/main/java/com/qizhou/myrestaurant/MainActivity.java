package com.qizhou.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qizhou.myrestaurant.entities.Category;
import com.qizhou.myrestaurant.entities.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainMenu = findViewById(R.id.main_menu_list);
        List<Menu> mainMenus = getMainMenus();
        mainMenu.setAdapter(new MenuAdapter(this, mainMenus));
        mainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private List<Menu> getMainMenus() {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu(Category.humber, R.drawable.menu_hamburger, "Humbers"));
        menus.add(new Menu(Category.steak, R.drawable.menu_steak, "Steaks"));
        menus.add(new Menu(Category.salad, R.drawable.menu_salad, "Salads"));
        menus.add(new Menu(Category.iceCream, R.drawable.menu_ice_cream, "Ice Creams"));
        menus.add(new Menu(Category.drink, R.drawable.menu_drinks, "Drinks"));
        return menus;
    }

    private class MenuAdapter extends BaseAdapter {
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

}