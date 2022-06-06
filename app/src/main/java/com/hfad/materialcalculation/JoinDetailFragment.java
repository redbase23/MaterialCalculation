package com.hfad.materialcalculation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hfad.materialcalculation.calculateActivities.CutSingleActivity;
import com.hfad.materialcalculation.calculateActivities.CuttingKameraActivity;
import com.hfad.materialcalculation.calculateActivities.HotSealingActivity;
import com.hfad.materialcalculation.calculateActivities.LandsActivity;
import com.hfad.materialcalculation.calculateActivities.PenolonActivity;
import com.hfad.materialcalculation.calculateActivities.ProofmateActivity;
import com.hfad.materialcalculation.calculateActivities.RodsActivity;

public class JoinDetailFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.joinDetail_choice));
        setListAdapter(adapter);

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    //реакция на выбор пункта меню (открытие активности)
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        if (position==0){
            Intent intent = new Intent(getActivity(), CutSingleActivity.class);
            startActivity(intent);
        }
        if (position==1){
            Intent intent = new Intent(getActivity(), CuttingKameraActivity.class);
            startActivity(intent);
        }
        if (position==2){
            Intent intent = new Intent(getActivity(), LandsActivity.class);
            startActivity(intent);
        }
        if (position==3){
            Intent intent = new Intent(getActivity(), HotSealingActivity.class);
            startActivity(intent);
        }
        if (position==4){
            Intent intent = new Intent(getActivity(), RodsActivity.class);
            startActivity(intent);
        }
        if (position==5){
            Intent intent = new Intent(getActivity(), ProofmateActivity.class);
            startActivity(intent);
        }
        if (position==6){
            Intent intent = new Intent(getActivity(), PenolonActivity.class);
            startActivity(intent);
        }
    }
}