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
import com.hfad.materialcalculation.ENUMS.MaterialsAnother;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.PenolonCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class PenolonActivity extends AppCompatActivity {
    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET,joinDeph, widthJointExp;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    Spinner penolonSpinner;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penolon_activity);

        lenghtET=findViewById(R.id.lenghtET14);
        widthJointExp=findViewById(R.id.widthJointExp14);
        joinDeph = findViewById(R.id.expJointDepth14);
        btnResult=findViewById(R.id.calculateMaterialsButton14);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia14);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button14);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        penolonSpinner= findViewById(R.id.PenolonChooseSpinner14);
        resultMaterials = findViewById(R.id.resultMaterials14);

        //проверка заполнения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,joinDeph,widthJointExp};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //создание списка заполнения шва расширения
        String[] penolonArray = new String[3];
        penolonArray[0]= MaterialsAnother.DOSKA.name();
        penolonArray[1]= MaterialsAnother.PENOLON.name();
        penolonArray[2]= MaterialsAnother.MONTAZHNAYA_PENA.name();

        ArrayAdapter<String> listAdapterPenolon=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                penolonArray);
        penolonSpinner.setAdapter(listAdapterPenolon);

        //проверка ввода значений глубины шва
        joinDeph.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!joinDeph.getText().toString().equals("")){
                    int val= Integer.parseInt(joinDeph.getText().toString());
                    if(val<=0||val>450){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина пропила шва",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        joinDeph.setText(null);
                    }
                }
            }
        });

        //проверка ввода значений ширины шва
        widthJointExp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!widthJointExp.getText().toString().equals("")){
                    int val= Integer.parseInt(widthJointExp.getText().toString());
                    if(val<=0||val>50){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана ширина шва",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        widthJointExp.setText(null);
                    }
                }
            }
        });
    }
    //выполнение расчета материалов
    public  void calculateMaterials (View view){
        StringBuilder result=new StringBuilder();
        materials.clear();

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет заполнения шва расширения
        materials.putValuesArray(PenolonCalculation.getMaterialsPenolonExpansionJoint(
                ilLenght,
                Integer.parseInt(widthJointExp.getText().toString()),
                Integer.parseInt(joinDeph.getText().toString()),
                penolonSpinner.getSelectedItem().toString()));

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
        itemToSend = "Заполнение  податливой прокладкой: "+ilLenght+"п.м.";
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