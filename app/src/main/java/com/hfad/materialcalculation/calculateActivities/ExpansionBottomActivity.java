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
import com.hfad.materialcalculation.calculations.CuttingGreenCalculation;
import com.hfad.materialcalculation.calculations.PenolonCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class ExpansionBottomActivity extends AppCompatActivity {
    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET,joinCutDeph, widthJointExp;
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
        setContentView(R.layout.expansion_bottom_activity_);

        lenghtET=findViewById(R.id.lenghtET4);
        widthJointExp=findViewById(R.id.widthJointExp4);
        joinCutDeph = findViewById(R.id.expJointDepth4);
        btnResult=findViewById(R.id.calculateMaterialsButton4);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia4);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button4);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        penolonSpinner= findViewById(R.id.PenolonChooseSpinner4);
        resultMaterials = findViewById(R.id.resultMaterials4);

        //проверка заполнения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,joinCutDeph,widthJointExp};
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

        //проверка ввода значений глубины нарезки шва
        joinCutDeph.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!joinCutDeph.getText().toString().equals("")){
                    int val= Integer.parseInt(joinCutDeph.getText().toString());
                    if(val<=0||val>450){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина пропила шва",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        joinCutDeph.setText(null);
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

    //выплнение расчета материалов
    public void calculateMaterials(View view){
        StringBuilder result=new StringBuilder();
        materials.clear();

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет материалов на пропил
        int intJointExpDepth = Integer.parseInt(joinCutDeph.getText().toString());

        if(intJointExpDepth<=450&&intJointExpDepth>0){
            materials.putValuesArray(CuttingGreenCalculation.getMaterialsPionerkaCut(intJointExpDepth,ilLenght)); //first cut
            materials.putValuesArray(CuttingGreenCalculation.getMaterialsPionerkaCut(intJointExpDepth,ilLenght)); //second cut
        }

        //рассчет заполнения шва расширения
        int intwidthexp=Integer.parseInt(widthJointExp.getText().toString());
        materials.putValuesArray(PenolonCalculation.getMaterialsPenolonExpansionJoint(ilLenght, intwidthexp,intJointExpDepth,penolonSpinner.getSelectedItem().toString()));

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
        itemToSend = "Шов расширения в основании: "+ilLenght+"п.м.";
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