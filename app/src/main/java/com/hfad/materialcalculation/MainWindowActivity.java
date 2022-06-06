package com.hfad.materialcalculation;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        //добавление панели инструментов
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main_window);
        setSupportActionBar(toolbar);

        //связывание SectionPagerAdapter с View
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager)findViewById(R.id.pager_main);
        pager.setAdapter(pagerAdapter);

        //связывание ViewPager c TabLayout
        TabLayout tableLayout = (TabLayout)findViewById(R.id.tabs_main);
        tableLayout.setupWithViewPager(pager);

    }
    //установка элементов меню на панель приложения
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_window,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //обработка нажатий в панели инструментов
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_go_to_total:
                Intent intent = new Intent(this,TotalResultActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //создание адаптера для добавлениея фрагментов в ViewPager
    private class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter (FragmentManager fm){
            super(fm);
        }

        @Override
        public  int getCount(){
            return 2; //сообщаем сколько должен содержать компонент ViewPager
        }

        //Указываем какой компонент должен выводиться на каждой странице
        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new JointCompleteFragment();
                case 1:
                    return new JoinDetailFragment();
            }
            return null;

        }

        //добавление текста на вкладки
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return getResources().getText(R.string.joinCompleteFrament);
                case 1:
                    return getResources().getText(R.string.joinPartsFrament);
            }
            return null;
        }
    }
}