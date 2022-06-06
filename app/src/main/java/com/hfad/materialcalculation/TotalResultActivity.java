package com.hfad.materialcalculation;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import com.hfad.materialcalculation.common.Round;


public class TotalResultActivity extends AppCompatActivity implements TotaltemsFragment.Listener {
    public static final String SEND_ITEM ="SendItem";
    public static final String SEND_VALUES ="SendValues";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_result);

        //добавление панели инструментов
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_total);
        setSupportActionBar(toolbar);

        TotalMaterialsFragment fragmentMaterials = (TotalMaterialsFragment)
                getSupportFragmentManager().findFragmentById(R.id.total_materials_fragment); //ссылка на фрагмент в макете

    }

    //заполнение меню панели инструментов
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_total,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share_total);
        shareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent(((MyApplication) this.getApplication()).getTotalItemsString()+"\n"+
                ((MyApplication) this.getApplication()).getTotalMaterialsString());
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);
    }

    //возварт в главное меню для добавления новых объемов
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_go_to_main:
                Intent intent = new Intent(this, MainWindowActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //медот определяемый интерфейсом из фрагмента содержашего перечень объемов
    @Override
    public void itemClicked(long id){
        Intent intent = new Intent(this,DetailItemActivity.class);
        intent.putExtra(DetailItemActivity.ITEM_ID,(int)id);
        startActivity(intent);
    }
}