package com.hfad.materialcalculation.calculateActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsProofmate;
import com.hfad.materialcalculation.ENUMS.MaterialsRods;
import com.hfad.materialcalculation.ENUMS.Norms;
import com.hfad.materialcalculation.MyApplication;
import com.hfad.materialcalculation.R;
import com.hfad.materialcalculation.TotalResultActivity;
import com.hfad.materialcalculation.common.CustomTextWatcher;

public class ProofmateActivity extends AppCompatActivity {
    //материалы
    AllMaterials materials = new AllMaterials();

    EditText lenghtET;
    Button btnResult;
    Button btnSendVia;
    Button btnAddToTotal;
    Spinner proofmateSpinner;
    TextView resultMaterials;

    int ilLenght=0;
    String resultat="";

    //подготовка данных для отправки в итоговый лист
    String itemToSend;
    double [] valToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proofmate_activity);

        //получение всех объетов с разметки и определение их начальных свойств
        lenghtET=findViewById(R.id.lenghtET13);
        btnResult=findViewById(R.id.calculateMaterialsButton13);
        btnResult.setEnabled(false);
        btnSendVia=findViewById(R.id.sendvia13);
        btnSendVia.setVisibility(View.INVISIBLE);
        btnAddToTotal=findViewById(R.id.addToTotal_Button13);
        btnAddToTotal.setVisibility(View.INVISIBLE);
        proofmateSpinner = findViewById(R.id.ProofmateChoose13);
        resultMaterials = findViewById(R.id.resultMaterials13);

        //проверка заполенения всех окон ввода и отображение кнопки подсчёта
        EditText[] edList ={lenghtET};
        CustomTextWatcher textWatcher = new CustomTextWatcher(edList,btnResult);
        for(EditText editText: edList) editText.addTextChangedListener(textWatcher);

        //создание списка профилей
        ArrayAdapter<String> listAdapterProofmate=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MaterialsProofmate.getNames());
        proofmateSpinner.setAdapter(listAdapterProofmate);
    }
    //подсчет материалов
    public  void calculateMaterials (View view){
        //материалы
        materials.clear();
        StringBuilder result=new StringBuilder();

        String  proofmateName;

        //получение длины шва
        ilLenght = Integer.parseInt(lenghtET.getText().toString());

        //выбор и рассчет уплотнительного шнура
        proofmateName=proofmateSpinner.getSelectedItem().toString();
        materials.putValue(proofmateName,ilLenght*Norms.N15_2PROOFMATE.getNorm());

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
        itemToSend = "Профиль резиновый "+proofmateName+": "+ilLenght+"п.м.";

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