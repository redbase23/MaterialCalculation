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
import com.hfad.materialcalculation.calculations.CuttingKameraAsphCalculation;
import com.hfad.materialcalculation.calculations.CuttingKameraCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class CuttingKameraActivity extends AppCompatActivity {

    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET, depthCutKameraET, widthKameraET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    RadioButton CurredRadioButton;
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
        setContentView(R.layout.cutting_kamera_activity);

        //получение всех объетов с разметки и определение их начальных свойств
        lenghtET=findViewById(R.id.lenghtET8);
        depthCutKameraET=findViewById(R.id.depthCutKameraET8);
        widthKameraET=findViewById(R.id.widthKameraET8);
        btnResult=findViewById(R.id.calculateMaterialsButton8);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia8);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button8);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        CurredRadioButton=findViewById(R.id.CurredRadioButton_8);
        CurredRadioButton.setChecked(true);
        AsphaltRadioButton=findViewById(R.id.AsphaltRadioButton_8);
        resultMaterials = findViewById(R.id.resultMaterials8);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,widthKameraET,depthCutKameraET,widthKameraET};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //проверка ввода значений глубины  нарезки камеры
        depthCutKameraET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!depthCutKameraET.getText().toString().equals("")){
                    int val= Integer.parseInt(depthCutKameraET.getText().toString());
                    if(val<=0||val>70){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина нарезки камеры",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        depthCutKameraET.setText(null);
                    }
                }
            }
        });

        //проверка ввода значений ширины камеры
        widthKameraET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!widthKameraET.getText().toString().equals("")){
                    int val= Integer.parseInt(widthKameraET.getText().toString());
                    if(val<=0||val>15){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана ширина нарезки камеры",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        widthKameraET.setText(null);
                    }
                }
            }
        });
    }
    //подсчет материалов
    public void calculateMaterials (View view){
        //материалы
        materials.clear();
        StringBuilder result=new StringBuilder();

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет материалов на нарезку камеры
        int intkameraWidth =Integer.parseInt(widthKameraET.getText().toString());
        int intkameraCutDepth =Integer.parseInt(depthCutKameraET.getText().toString());

        if(intkameraCutDepth>0&&intkameraCutDepth<=70&&intkameraWidth>0&&intkameraWidth<=15){
            if(CurredRadioButton.isChecked()){
                materials.putValuesArray(CuttingKameraCalculation.getMaterialsKameraCut(intkameraCutDepth,intkameraWidth,ilLenght));
            }
            if(AsphaltRadioButton.isChecked()){
                materials.putValuesArray(CuttingKameraAsphCalculation.getMaterialsKameraAsphCut(intkameraCutDepth,intkameraWidth,ilLenght));
            }
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
        itemToSend = "Камера шва в ";
        if(CurredRadioButton.isChecked()){
            itemToSend+=" марочном бетоне : ";
        }
        else if(AsphaltRadioButton.isChecked()){
            itemToSend+=" асфальтобетоне : ";
        }
        itemToSend+=ilLenght+" п.м.";
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