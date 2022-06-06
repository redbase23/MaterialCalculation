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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsProofmate;
import com.hfad.materialcalculation.ENUMS.MaterialsRods;
import com.hfad.materialcalculation.ENUMS.MaterialsSealantHot;
import com.hfad.materialcalculation.ENUMS.Norms;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.CuttingAsphaltCalculation;
import com.hfad.materialcalculation.calculations.CuttingKameraAsphCalculation;
import com.hfad.materialcalculation.calculations.CuttingKameraCalculation;
import com.hfad.materialcalculation.calculations.LandsCalculation;
import com.hfad.materialcalculation.calculations.SealingHotCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class ContractionAsphaltActivity extends AppCompatActivity {

    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET, pionerkaDephET, widthKameraET, depthCutKameraET, depthSealKameraET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    Spinner sealant;
    Spinner proofmate;
    Spinner rod;
    CheckBox rodCheckBox;
    CheckBox pionerkaCheck;
    CheckBox landsCheckBox;
    RadioButton SealantHotRB;
    RadioButton ProofmateRB;
    RadioGroup HowToSealChoiseRG;
    TextView depthSealKameraTV;
    TextView rodsChooseTV;
    View dividerDepthSeal;
    View dividerRods;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contraction_asphalt_activity);
        //получение объектов окон ввода
        lenghtET=findViewById(R.id.lenghtET5);
        pionerkaDephET=findViewById(R.id.pionerkaDephET5);
        pionerkaDephET.setVisibility(View.GONE);
        widthKameraET=findViewById(R.id.widthKameraET5);
        depthCutKameraET=findViewById(R.id.depthCutKameraET5);
        depthSealKameraET=findViewById(R.id.depthSealKameraET5);
        depthSealKameraET.setVisibility(View.GONE);
        //получение кнопок и установка их на чальной видимосити
        btnResult=findViewById(R.id.calculateMaterialsButton5);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia5);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button5);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        pionerkaCheck = findViewById(R.id.PionerkaCheckBox5);
        landsCheckBox=findViewById(R.id.landsCheckBox5);
        SealantHotRB=findViewById(R.id.SealantHotRB5);
        ProofmateRB=findViewById(R.id.ProofmateRB5);
        HowToSealChoiseRG=findViewById(R.id.HowToSealChoiseRG5);
        rodCheckBox =findViewById(R.id.RodCheckBox5);
        rodCheckBox.setVisibility(View.GONE);
        depthSealKameraTV=findViewById(R.id.depthSealKameraTV5);
        depthSealKameraTV.setVisibility(View.GONE);
        rodsChooseTV =findViewById(R.id.rodsChooseTV5);
        rodsChooseTV.setVisibility(View.GONE);
        dividerDepthSeal=findViewById(R.id.divider6_5);
        dividerDepthSeal.setVisibility(View.GONE);
        dividerRods=findViewById(R.id.divider9_5);
        dividerRods.setVisibility(View.GONE);
        sealant = findViewById(R.id.sealantHotChoose5);
        proofmate =findViewById(R.id.ProofmateChoose5);
        rod = findViewById(R.id.RodChooseSpinner5);
        rod.setVisibility(View.GONE);
        resultMaterials = findViewById(R.id.resultMaterials5);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,widthKameraET,depthCutKameraET,depthSealKameraET};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //создание списка мастик с помощью адаптера
        ArrayAdapter<String> listAdapterSealant = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MaterialsSealantHot.getNames());

        sealant.setAdapter(listAdapterSealant);
        sealant.setVisibility(View.GONE);

        //создание списка профилей
        ArrayAdapter<String> listAdapterProofmate=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MaterialsProofmate.getNames());
        proofmate.setAdapter(listAdapterProofmate);
        proofmate.setVisibility(View.GONE);

        //скрытие и вывод на экран списка мастик и профилей
        HowToSealChoiseRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.SealantHotRB5:
                        sealant.setVisibility(View.VISIBLE);
                        rodCheckBox.setVisibility(View.VISIBLE);
                        depthSealKameraET.setVisibility(View.VISIBLE);
                        depthSealKameraET.setText(null);
                        proofmate.setVisibility(View.GONE);
                        depthSealKameraTV.setVisibility(View.VISIBLE);
                        rodsChooseTV.setVisibility(View.VISIBLE);
                        dividerDepthSeal.setVisibility(View.VISIBLE);
                        dividerRods.setVisibility(View.VISIBLE);
                        break;
                    case R.id.ProofmateRB5:
                        sealant.setVisibility(View.GONE);
                        rodCheckBox.setChecked(false);
                        rodCheckBox.setVisibility(View.GONE);
                        depthSealKameraET.setVisibility(View.GONE);
                        depthSealKameraET.setText("10");
                        proofmate.setVisibility(View.VISIBLE);
                        depthSealKameraTV.setVisibility(View.GONE);
                        rodsChooseTV.setVisibility(View.GONE);
                        dividerDepthSeal.setVisibility(View.GONE);
                        dividerRods.setVisibility(View.GONE);
                        break;
                }
            }
        });

        //создание списка уплотнительных шнуров
        ArrayAdapter<String> listAdapterRod = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MaterialsRods.getDiametersString());
        rod.setAdapter(listAdapterRod);

        //скрытие и выбод на экран списка шнуров

        rodCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rod.setVisibility(View.VISIBLE);
                }
                else{
                    rod.setVisibility(View.GONE);
                }
            }
        });

        //скрытие и вывод на экран глубины пионерного пропила
        pionerkaCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pionerkaDephET.setVisibility(View.VISIBLE);
                }
                else{
                    pionerkaDephET.setVisibility(View.GONE);
                }
            }
        });

        //проверка ввода значений глубины пионерного пропила
        pionerkaDephET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!pionerkaDephET.getText().toString().equals("")){
                    int val= Integer.parseInt(pionerkaDephET.getText().toString());
                    if(val<=0||val>450){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина пионерного пропила",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        pionerkaDephET.setText(null);
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
        //проверка ввода значений глубины  заливки камеры
        depthSealKameraET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!depthSealKameraET.getText().toString().equals("")){
                    int val= Integer.parseInt(depthSealKameraET.getText().toString());
                    if(val<=0||val>70){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина заливки камеры",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        depthSealKameraET.setText(null);
                    }
                }
            }
        });
    }

    //подсчет материалов
    public  void calculateMaterials (View view){
        //материалы
        materials.clear();
        StringBuilder result=new StringBuilder();

        String Sealant;
        int RodDiametr;
        String rodname;
        String proofmateName;

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет материалов на пионерный пропил
        if(pionerkaCheck.isChecked()) {
            int intPionerkaDeph = Integer.parseInt(pionerkaDephET.getText().toString());

            if (intPionerkaDeph <= 450 && intPionerkaDeph > 0) {
                materials.putValuesArray(CuttingAsphaltCalculation.getMaterialsCuttingAsphalt(intPionerkaDeph, ilLenght));
            }
        }

        //рассчет материалов на нарезку камеры
        int intkameraWidth =Integer.parseInt(widthKameraET.getText().toString());
        int intkameraCutDepth =Integer.parseInt(depthCutKameraET.getText().toString());

        if(intkameraCutDepth>0&&intkameraCutDepth<=70&&intkameraWidth>0&&intkameraWidth<=15){
            materials.putValuesArray(CuttingKameraAsphCalculation.getMaterialsKameraAsphCut(intkameraCutDepth,intkameraWidth,ilLenght));
        }

        //рассчет материалов для снятия фаски

        if(landsCheckBox.isChecked()){
            materials.putValuesArray(LandsCalculation.getMaterialsLandsSaw(ilLenght));
        }

        //выбор и расчет герметика
        Sealant =sealant.getSelectedItem().toString();

        int intSealantdepth=Integer.parseInt(depthSealKameraET.getText().toString());

        if (SealantHotRB.isChecked()) {
            if (intkameraWidth > 0 && intkameraWidth <= 15 && intSealantdepth > 0) {
                materials.putValuesArray(SealingHotCalculation.getMaterialsSealingHot(ilLenght, intkameraWidth, intSealantdepth, Sealant));
            }
        }
        //расчет улотнительного профиля
        if (ProofmateRB.isChecked()){
            proofmateName=proofmate.getSelectedItem().toString();
            materials.putValue(proofmateName,ilLenght* Norms.N15_2PROOFMATE.getNorm());
        }

        //выбор и рассчет уплотнительного шнура
        RodDiametr=Integer.parseInt(rod.getSelectedItem().toString());
        if(rodCheckBox.isChecked()&&SealantHotRB.isChecked()){
            rodname="ROD"+RodDiametr;
            materials.putValue(rodname,ilLenght* Norms.N15ROD.getNorm());
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
        itemToSend = "Шов сжатия в асфальтобетоне ";
        if(pionerkaCheck.isChecked()){
            itemToSend+="ложный :";
        }
        else{
            itemToSend+="технологический :";
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