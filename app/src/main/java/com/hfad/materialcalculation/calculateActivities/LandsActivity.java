package com.hfad.materialcalculation.calculateActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.LandsCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class LandsActivity extends AppCompatActivity {

    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    RadioButton twoSidesRadioButton;
    RadioButton oneSideRadioButton;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lands_activity);

        //получение всех объетов с разметки и определение их начальных свойств
        lenghtET=findViewById(R.id.lenghtET9);
        btnResult=findViewById(R.id.calculateMaterialsButton9);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia9);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button9);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        twoSidesRadioButton=findViewById(R.id.TwoSidesRadioButton_9);
        twoSidesRadioButton.setChecked(true);
        oneSideRadioButton=findViewById(R.id.OneSideRadioButton_9);
        resultMaterials = findViewById(R.id.resultMaterials9);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);
    }

    //подсчет материалов
    public void calculateMaterials(View view){
        //материалы
        materials.clear();
        StringBuilder result=new StringBuilder();

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет материалов для снятия фаски
        if(twoSidesRadioButton.isChecked()){
            materials.putValuesArray(LandsCalculation.getMaterialsLandsSaw(ilLenght));
        }
        else if(oneSideRadioButton.isChecked()){
            materials.putValuesArray(LandsCalculation.getMaterialsLandsSaw((ilLenght/2)));
        }
        //итоговое формирование потребных материалов
        result=result.append(materials.result());

        resultMaterials.setText(result);

        //отображение результата и вывод кнопок отпарвки и добавки в итог на экран
        resultat=result.toString();
        btnSendVia.setVisibility(View.VISIBLE);
        btnAddToTotal.setVisibility(View.VISIBLE);

        //скрытие клавиатуры
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnResult.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        //подготовка данных для отправки в итоговый лист
        itemToSend = "Cнятие фаски ";
        if(twoSidesRadioButton.isChecked()){
            itemToSend+="с обеих кромок шва : ";
        }
        else if (oneSideRadioButton.isChecked()){
            itemToSend+="с одной кромки шва : ";
        }
        itemToSend+=ilLenght+"п.м.";
        valToSend = materials.Values;

    }
    //передача интента неявному приложению
    public void sendVia(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,resultat);
        Intent chosenIntent =Intent.createChooser(intent,"Send message via...");
        startActivity(chosenIntent);
    }

    //передача данных в итоговый лист
    public void addToTotal(View view){

        ((MyApplication)this.getApplication()).putItem(itemToSend,valToSend);

        Intent intent = new Intent(this, TotalResultActivity.class);

        startActivity(intent);
    }
}