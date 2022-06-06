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
import com.hfad.materialcalculation.ENUMS.MaterialsAnother;
import com.hfad.materialcalculation.ENUMS.MaterialsProofmate;
import com.hfad.materialcalculation.ENUMS.MaterialsRods;
import com.hfad.materialcalculation.ENUMS.MaterialsSealantHot;
import com.hfad.materialcalculation.ENUMS.Norms;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.calculations.CuttingCuredCalculation;
import com.hfad.materialcalculation.calculations.LandsCalculation;
import com.hfad.materialcalculation.calculations.PenolonCalculation;
import com.hfad.materialcalculation.calculations.SealingHotCalculation;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class PaddingJointActivity extends AppCompatActivity {
    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET,joinDeph, cutJointDepth, widthJointExp, depthSealKameraET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    Spinner sealant;
    Spinner proofmate;
    Spinner rod;
    Spinner penolonSpinner;
    CheckBox rodCheckBox;
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
        setContentView(R.layout.padding_joint_activity);

        lenghtET=findViewById(R.id.lenghtET10);
        widthJointExp=findViewById(R.id.widthJointExp10);
        joinDeph = findViewById(R.id.expJointDepth10);
        cutJointDepth=findViewById(R.id.cutJointDepth10);
        depthSealKameraET=findViewById(R.id.depthSealKameraET10);
        depthSealKameraET.setVisibility(View.GONE);
        btnResult=findViewById(R.id.calculateMaterialsButton10);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia10);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button10);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        SealantHotRB=findViewById(R.id.SealantHotRB10);
        ProofmateRB=findViewById(R.id.ProofmateRB10);
        HowToSealChoiseRG=findViewById(R.id.HowToSealChoiseRG10);
        rodCheckBox = findViewById(R.id.rodsCheckBox10);
        rodCheckBox.setVisibility(View.GONE);
        landsCheckBox=findViewById(R.id.landsCheckBox10);
        depthSealKameraTV=findViewById(R.id.depthSealKameraTV10);
        depthSealKameraTV.setVisibility(View.GONE);
        rodsChooseTV=findViewById(R.id.rodsChooseTV10);
        rodsChooseTV.setVisibility(View.GONE);
        dividerDepthSeal =findViewById(R.id.divider5_10);
        dividerDepthSeal.setVisibility(View.GONE);
        dividerRods=findViewById(R.id.divider8_100);
        dividerRods.setVisibility(View.GONE);
        rod = findViewById(R.id.RodChooseSpinner10);
        sealant = findViewById(R.id.sealantHotChoose10);
        proofmate =findViewById(R.id.ProofmateChoose10);
        penolonSpinner= findViewById(R.id.PenolonChooseSpinner10);
        resultMaterials = findViewById(R.id.resultMaterials10);

        //проверка заполнения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET,joinDeph,cutJointDepth,widthJointExp,depthSealKameraET};
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
                    case R.id.SealantHotRB10:
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
                    case R.id.ProofmateRB10:
                        sealant.setVisibility(View.GONE);
                        rodCheckBox.setChecked(false);
                        rodCheckBox.setVisibility(View.GONE);
                        depthSealKameraET.setVisibility(View.GONE);
                        depthSealKameraET.setText("30");
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

        rod.setVisibility(View.GONE);

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

        //создание списка заполнения шва расширения
        String[] penolonArray = new String[2];
        penolonArray[0]= MaterialsAnother.DOSKA.name();
        penolonArray[1]= MaterialsAnother.PENOLON.name();

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
        //проверка ввода значений глубины нарезки шва
        cutJointDepth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!cutJointDepth.getText().toString().equals("")){
                    int val= Integer.parseInt(cutJointDepth.getText().toString());
                    if(val<=0||val>450){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина пропила шва",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        //очистка окна и установка фокуса
                        cutJointDepth.setText(null);
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
        //проверка ввода значений глубина заливки шва
        depthSealKameraET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b&&!depthSealKameraET.getText().toString().equals("")){
                    int val= Integer.parseInt(depthSealKameraET.getText().toString());
                    if(val<=0||val>70){
                        //вывод всплывающего напоминания
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверно указана глубина заливки шва",
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

    //выполнение расчета материалов
    public  void calculateMaterials (View view){
        StringBuilder result=new StringBuilder();
        materials.clear();

        String Sealant;
        int RodDiametr;
        String rodname;
        String proofmateName;

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //рассчет материалов на пропил
        int intJointExpDepth = Integer.parseInt(cutJointDepth.getText().toString());

        if(intJointExpDepth<=450&&intJointExpDepth>0){
            materials.putValuesArray(CuttingCuredCalculation.getMaterialsCuttingCured(intJointExpDepth,ilLenght)); //first cut
            materials.putValuesArray(CuttingCuredCalculation.getMaterialsCuttingCured(intJointExpDepth,ilLenght)); //second cut
        }

        //рассчет материалов для снятия фаски
        if(landsCheckBox.isChecked()){
            materials.putValuesArray(LandsCalculation.getMaterialsLandsSaw(ilLenght));
        }

        //выбор и расчет герметика
        Sealant =sealant.getSelectedItem().toString();

        int intwidthexp=Integer.parseInt(widthJointExp.getText().toString());
        int intSealantdepthexp=Integer.parseInt(depthSealKameraET.getText().toString());

        if(SealantHotRB.isChecked()) {
            if (intwidthexp > 0 && intwidthexp <= 50 && intSealantdepthexp > 0) {
                materials.putValuesArray(SealingHotCalculation.getMaterialsSealingHot(ilLenght, intwidthexp, intSealantdepthexp, Sealant));
            }
        }

        //расчет улотнительного профиля
        if (ProofmateRB.isChecked()){
            proofmateName=proofmate.getSelectedItem().toString();
            materials.putValue(proofmateName,ilLenght* Norms.N15_2PROOFMATE.getNorm());
        }

        //выбор и рассчет уплотнительного шнура.
        RodDiametr=Integer.parseInt(rod.getSelectedItem().toString());
        if(rodCheckBox.isChecked()&&SealantHotRB.isChecked()) {
            rodname="ROD"+RodDiametr;
            materials.putValue(rodname,ilLenght* Norms.N15ROD.getNorm());
        }
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
        itemToSend = "Технологический шов с прокладкой: "+ilLenght+"п.м.";
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