package com.hfad.materialcalculation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailItemActivity extends AppCompatActivity {
    public static final String ITEM_ID= "itemId";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        //добавление панели инструментов
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        //добавление кнопки назад
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        TextView itemNameTV = findViewById(R.id.itemNameTV);
        TextView materialsTV = findViewById(R.id.materialsTV);

        //получение из интента id
        int id = (int) getIntent().getExtras().get(ITEM_ID);

        itemNameTV.setText(((MyApplication)getApplication()).getItem(id));
        materialsTV.setText(((MyApplication)getApplication()).getItemMaterialsString(id));

    }
    //заполнение меню панели инструментов
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share_detail);
        shareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        //получение из интента id
        int id = (int) getIntent().getExtras().get(ITEM_ID);

        setShareActionIntent(((MyApplication)getApplication()).getItem(id)+"\n"+
                ((MyApplication)getApplication()).getItemMaterialsString(id));
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);
    }
}