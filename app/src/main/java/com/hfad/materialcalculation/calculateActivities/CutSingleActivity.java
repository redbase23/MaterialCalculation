package com.hfad.materialcalculation.calculateActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.CuttingAsphaltCalculation;
import com.hfad.materialcalculation.calculations.CuttingCuredCalculation;
import com.hfad.materialcalculation.calculations.CuttingGreenCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class CutSingleActivity extends AppCompatActivity {
    AllMaterials materials = new AllMaterials();

    EditText lenghtET, depthCutET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    RadioButton CurredRadioButton;
    RadioButton GreenRadioButton;
    RadioButton AsphaltRadioButton;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cut_single_activity);

        //получение всех объетов с разметки и определение их начальных свойств
        lenghtET=findViewById(R.id.lenghtET7);
        depthCutET=findViewById(R.id.depthCutET7);
        btnResult=findViewById(R.id.calculateMaterialsButton7);
        btnResult.setEnabled(false);
        btnAddToTotal=findViewById(R.id.addToTotal_Button7);
        btnAddToTotal.setVisibility(View.GONE);
        btnSendVia=findViewById(R.id.sendvia7);
        btnSendVia.setVisibility(View.GONE);
        CurredRadioButton=findViewById(R.id.CurredRadioButton);
        CurredRadioButton.setChecked(true);
        GreenRadioButton=findViewById(R.id.GreenRadioButton);
        AsphaltRadioButton=findViewById(R.id.AsphaltRadioButton);
        resultMaterials=findViewById(R.id.resultMaterials7);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,depthCutET};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //проверка ввода значений глубины нарезки
        depthCutET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!depthCutET.getText().toString().equals("")){
                    int val= Integer.parseInt(depthCutET.getText().toString());
                    if(val<=0||val>450){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина пионерного пропила",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        depthCutET.setText(null);
                    }
                }
            }
        });
    }
    //подсчет материалов
    public void calculateMaterials(View view){
        //материалы
        materials.clear();
        StringBuilder result=new StringBuilder();

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //подсчет материалов на пропил в марочном бетоне набравшем прочность
        if(CurredRadioButton.isChecked()){
            materials.putValuesArray(CuttingCuredCalculation.getMaterialsCuttingCured(
                    Integer.parseInt(depthCutET.getText().toString()),
                    ilLenght
            ));
        }

        //подсчет материлов на пропил в свежеуложенном и тощем бетоне
        if(GreenRadioButton.isChecked()){
            materials.putValuesArray(CuttingGreenCalculation.getMaterialsPionerkaCut(
                    Integer.parseInt(depthCutET.getText().toString()),
                    ilLenght
            ));
        }

        //подсчет материалов на пропил в асфальтобетоне
        if(AsphaltRadioButton.isChecked()){
            materials.putValuesArray(CuttingAsphaltCalculation.getMaterialsCuttingAsphalt(
                    Integer.parseInt(depthCutET.getText().toString()),
                    ilLenght
            ));
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
        itemToSend = "Пропил в ";
        if(CurredRadioButton.isChecked()){
            itemToSend+="марочном бетоне, набравшем прочность :";
        }
        else if(GreenRadioButton.isChecked()){
            itemToSend+="свежеуложенном или тощем бетоне :";
        }
        else if(AsphaltRadioButton.isChecked()){
            itemToSend+="асфальтобетоне : ";
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