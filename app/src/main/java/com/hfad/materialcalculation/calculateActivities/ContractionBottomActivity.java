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
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.CuttingGreenCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class ContractionBottomActivity extends AppCompatActivity {

    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET, depthCutET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contraction_bottom_activity);

        //получение объектов ввода
        lenghtET=findViewById(R.id.lenghtET3);
        depthCutET=findViewById(R.id.DephConractionBottomET3);
        btnResult=findViewById(R.id.calculateMaterialsButton3);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia3);
        btnSendVia.setVisibility(View.GONE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button3);
        btnAddToTotal.setVisibility(View.GONE);
        resultMaterials = findViewById(R.id.resultMaterials3);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[]edList ={lenghtET,depthCutET};
        CustomTextWatcher textWatcher =new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //проверка ввода глубины пионерного пропила
        depthCutET.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view,boolean b){
                if(!b&&!depthCutET.getText().toString().equals("")){
                    int val = Integer.parseInt(depthCutET.getText().toString());
                    if(val<=0||val>450){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Неверно указана глубина пропила",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        //очиска окна и установка фокуса
                        depthCutET.setText(null);
                        depthCutET.setFocusable(true);
                    }
                }
            }
        } );
    }

    //проведение подсчета материалов
    public void calculateMaterials(View view){
        materials.clear();
        StringBuilder result = new StringBuilder();

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет материалов на пропил в тощем бетоне
        int DethContracionBottomCut = Integer.parseInt(depthCutET.getText().toString());
        materials.putValuesArray(CuttingGreenCalculation.getMaterialsPionerkaCut(
                DethContracionBottomCut,ilLenght));

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
        itemToSend="Шов сжатия в основании: "+ilLenght+" п.м.";
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
    public void addToTotal(View view) {

        ((MyApplication) this.getApplication()).putItem(itemToSend, valToSend);

        Intent intent = new Intent(this, TotalResultActivity.class);

        startActivity(intent);
    }
}