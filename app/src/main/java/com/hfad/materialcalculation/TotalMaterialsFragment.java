package com.hfad.materialcalculation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TotalMaterialsFragment extends Fragment {

    public TotalMaterialsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_total_materials, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();

        if(view!=null) {
            TextView totalMaterials_TextViewFragment = view.findViewById(R.id.total_materials_fragment_tv);
            totalMaterials_TextViewFragment.setText(((MyApplication) getActivity().getApplication()).getTotalMaterialsString()); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }
}
