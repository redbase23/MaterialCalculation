package com.hfad.materialcalculation.calculateActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSealantHot;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.SealingHotCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class HotSealingActivity extends AppCompatActivity {
    //материалы
    AllMaterials materials = new AllMaterials();


    EditText lenghtET, widthSealingET, depthSealingET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    Spinner sealantSpinner;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";
    String Sealant;

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_sealing_activity);

        //получение всех объетов с разметки и определение их начальных свойств
        lenghtET=findViewById(R.id.lenghtET11);
        widthSealingET=findViewById(R.id.widthSealingET11);
        depthSealingET=findViewById(R.id.depthSealingET11);
        btnResult=findViewById(R.id.calculateMaterialsButton11);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia11);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button11);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        sealantSpinner=findViewById(R.id.sealantHotChoose11);
        resultMaterials = findViewById(R.id.resultMaterials11);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,widthSealingET,depthSealingET};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //создание списка мастик с помощью адаптера
        ArrayAdapter<String> listAdapterSealant = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MaterialsSealantHot.getNames());

        sealantSpinner.setAdapter(listAdapterSealant);

        //проверка ввода значений ширины заливки
        widthSealingET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!widthSealingET.getText().toString().equals("")){
                    int val= Integer.parseInt(widthSealingET.getText().toString());
                    if(val<=0||val>300){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана ширина заливки",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        widthSealingET.setText(null);
                    }
                }
            }
        });

        //проверка ввода значений глубины заливки
        depthSealingET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!depthSealingET.getText().toString().equals("")){
                    int val= Integer.parseInt(depthSealingET.getText().toString());
                    if(val<=0||val>300){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина заливки",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        depthSealingET.setText(null);
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

        //расчет потребности герметика
        Sealant =sealantSpinner.getSelectedItem().toString();
        materials.putValuesArray(
                SealingHotCalculation.getMaterialsSealingHot(
                        ilLenght,
                        Integer.parseInt(widthSealingET.getText().toString()),
                        Integer.parseInt(depthSealingET.getText().toString()),
                        Sealant));

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
        itemToSend = "Герметизация ";
        itemToSend+=Integer.parseInt(widthSealingET.getText().toString())+"х";
        itemToSend+=Integer.parseInt(depthSealingET.getText().toString())+" мм: ";
        itemToSend+= ilLenght+"п.м.";

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