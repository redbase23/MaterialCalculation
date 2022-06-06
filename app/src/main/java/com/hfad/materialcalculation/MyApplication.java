package com.hfad.materialcalculation;

import android.app.Application;
import com.hfad.materialcalculation.common.Round;
import java.util.ArrayList;

public class MyApplication extends Application {
    private int numberOfItems=0;
    private AllMaterials materials = new AllMaterials();
    private ArrayList<String> items=new ArrayList<>();
    private ArrayList<Double[]> valueArray=new ArrayList<>();

    public int getNumberOfItems() {
        return numberOfItems;
    }

    //добавление нового объема с материалами (массива в список)
    public void putItem(String descriptionOfItem,double [] values){
        Double [] valueArrayItem = new Double[values.length];
        for(int i=0;i<values.length;i++){
            valueArrayItem[i]=values[i];
        }

        valueArray.add(valueArrayItem);

        items.add(descriptionOfItem);
    }
    //удаление элемента и объемов
    public void deletItem(int position){
        items.remove(position);
        valueArray.remove(position);
    }

    //получение строки перечисления объемов
    public String getTotalItemsString(){

        StringBuilder itemsStrb = new StringBuilder();

        String[] itemsStr = new String[items.size()];
        itemsStr = items.toArray(itemsStr);

        for (int i=0;i<itemsStr.length;i++){
            itemsStrb.append(itemsStr[i]).append('\n');
        }
        return itemsStrb.toString();
    }
    //получение массива перечисления объемов
    public String[] getTotalItemsArray(){
        String[] itemsStr = new String[items.size()];
        itemsStr = items.toArray(itemsStr);
        return itemsStr;
    }

    //получение суммы материалов
    public String getTotalMaterialsString(){
        StringBuilder resultMat = new StringBuilder();

        //создание пустого массива материалов
        Double [] totalValues = new Double[materials.NamesAllMaterials.length];
        for(int i =0;i<totalValues.length;i++){
            totalValues[i]=0.0;
        }

        //сложение всех массивов материалов в списке
        for(int i=0;i<valueArray.size();i++){
            Double [] k=valueArray.get(i);
            for(int j=0;j<k.length;j++){
                totalValues[j]+=k[j];
            }
        }

        //создание строки для вывода суммы материалов
        for (int i = 0; i < totalValues.length; i++) {
            if (totalValues[i] > 0) {
                resultMat.append(materials.DescriptionsAllMaterials[i]).append(": ")
                        .append(Round.roundDouble(totalValues[i], 1)).append('\n');
            }
        }
        return resultMat.toString();
    }
    //получение суммы материалов
    public String getItemMaterialsString(int id){
        StringBuilder resultMat = new StringBuilder();

        Double [] itemlValues = valueArray.get(id);

        //создание строки для вывода материалов
        for (int i = 0; i < itemlValues.length; i++) {
            if (itemlValues[i] > 0) {
                resultMat.append(materials.DescriptionsAllMaterials[i]).append(": ")
                        .append(Round.roundDouble(itemlValues[i], 1)).append('\n');
            }
        }
        return resultMat.toString();
    }

    public String getItem(int id){
        return items.get(id);
    }

    //добавление колличества
    public void additionNumberOfItems(){
        numberOfItems+=1;
    }
}
