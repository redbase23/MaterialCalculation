package com.hfad.materialcalculation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hfad.materialcalculation.calculateActivities.ContractionAsphaltActivity;
import com.hfad.materialcalculation.calculateActivities.ContractionBottomActivity;
import com.hfad.materialcalculation.calculateActivities.ContractionJointActivity;
import com.hfad.materialcalculation.calculateActivities.ExpansionAsphaltActivity;
import com.hfad.materialcalculation.calculateActivities.ExpansionBottomActivity;
import com.hfad.materialcalculation.calculateActivities.ExpansionJointActivity;
import com.hfad.materialcalculation.calculateActivities.PaddingJointActivity;

public class JointCompleteFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.joinComplete_choice));
        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    //реакция на выбор пункта меню (открытие активности)
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        if (position==0){
                Intent intent = new Intent(getActivity(), ContractionJointActivity.class);
                startActivity(intent);
        }
        if (position==1){
                Intent intent = new Intent(getActivity(), ExpansionJointActivity.class);
                startActivity(intent);
        }
        if (position==2){
            Intent intent = new Intent(getActivity(), ContractionBottomActivity.class);
            startActivity(intent);
        }
        if (position==3){
            Intent intent = new Intent(getActivity(), ExpansionBottomActivity.class);
            startActivity(intent);
        }
        if (position==4){
            Intent intent = new Intent(getActivity(), ContractionAsphaltActivity.class);
            startActivity(intent);
        }
        if (position==5){
            Intent intent = new Intent(getActivity(), ExpansionAsphaltActivity.class);
            startActivity(intent);
        }
        if (position==6){
            Intent intent = new Intent(getActivity(), PaddingJointActivity.class);
            startActivity(intent);
        }
    }
}