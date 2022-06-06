package com.hfad.materialcalculation.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomTextWatcher implements TextWatcher {
    View view;
    EditText[] editList;

    public CustomTextWatcher(EditText[]edList, Button v){
        view =v;
        editList=edList;
    }

    @Override
    public void beforeTextChanged(CharSequence s,int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {}

    @Override
    public void afterTextChanged(Editable s){
        for(EditText editText: editList){
            if(editText.getText().toString().trim().length()<=0){
                view.setEnabled(false);
            }
            else view.setEnabled(true);
        }
    }
}
