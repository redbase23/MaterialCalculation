package com.hfad.materialcalculation;


import com.hfad.materialcalculation.ENUMS.MaterialsAnother;
import com.hfad.materialcalculation.ENUMS.MaterialsProofmate;
import com.hfad.materialcalculation.ENUMS.MaterialsRods;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.MaterialsSealantHot;
import com.hfad.materialcalculation.common.Round;

public class AllMaterials {

    private String[] NamesSawBlades= MaterialsSawBlades.getNames();
    private String[] NamesSealantHot= MaterialsSealantHot.getNames();
    private String[] NamesRods= MaterialsRods.getNames();
    private String[] NamesAnotherMaterials= MaterialsAnother.getNames();
    private String[] NamesProofmate= MaterialsProofmate.getNames();

    private String[] DescriptionsSawBlades = MaterialsSawBlades.getDescriptions();
    private String[] DescriptionsSealantHot = MaterialsSealantHot.getDescriptions();
    private String[] DescriptionsRods=MaterialsRods.getDescriptions();
    private String[] DescriptionsAnotherMaterials=MaterialsAnother.getDescriptions();
    private String[] DescriptionProofmate=MaterialsProofmate.getDescriptions();


    public double[] Values;
    public String[] NamesAllMaterials;
    public String[] DescriptionsAllMaterials;


    public AllMaterials(){

        //создание общего массива названий материалов при помощи метода arraycopy
        NamesAllMaterials=new String[NamesSawBlades.length+NamesSealantHot.length+NamesRods.length+NamesAnotherMaterials.length+
                NamesProofmate.length];
        System.arraycopy(NamesSawBlades,0,NamesAllMaterials,0,NamesSawBlades.length);
        System.arraycopy(NamesSealantHot,0,NamesAllMaterials,NamesSawBlades.length,NamesSealantHot.length);
        System.arraycopy(NamesRods,0,NamesAllMaterials,(NamesSawBlades.length+NamesSealantHot.length),NamesRods.length);
        System.arraycopy(NamesAnotherMaterials,0,NamesAllMaterials,
                (NamesSawBlades.length+NamesSealantHot.length+NamesRods.length),NamesAnotherMaterials.length);
        System.arraycopy(NamesProofmate,0,NamesAllMaterials,
                (NamesSawBlades.length+NamesSealantHot.length+NamesRods.length+NamesAnotherMaterials.length),NamesProofmate.length);



        /*
        //альтернативный метод создания общего массива названий
        int count=0;
        for(int i=0;i<NamesSawBlades.length;i++){
            NamesAllMaterials[i]=NamesSawBlades[i];
            count++;
        }
        for(int j=0;j<NamesSealantHot.length;j++){
            NamesAllMaterials[count++]=NamesSealantHot[j];
        }
        for(int k=0;k<NamesRods.length;k++){
            NamesAllMaterials[count++]=NamesRods[k];
        }
        for(int m=0;m<NamesAnotherMaterials.length;m++){
            NamesAllMaterials[count++]=NamesAnotherMaterials[m];
        }
        */

        //создание общего массива описаний материалов
        DescriptionsAllMaterials =new String[DescriptionsSawBlades.length+DescriptionsSealantHot.length
                +DescriptionsRods.length+DescriptionsAnotherMaterials.length+NamesProofmate.length];
        int count2=0;
        for(int i=0;i<DescriptionsSawBlades.length;i++){
            DescriptionsAllMaterials[i]=DescriptionsSawBlades[i];
            count2++;
        }
        for(int j=0;j<DescriptionsSealantHot.length;j++){
            DescriptionsAllMaterials[count2++]=DescriptionsSealantHot[j];
        }
        for(int k=0;k<DescriptionsRods.length;k++){
            DescriptionsAllMaterials[count2++]=DescriptionsRods[k];
        }
        for(int m=0;m<DescriptionsAnotherMaterials.length;m++){
            DescriptionsAllMaterials[count2++]=DescriptionsAnotherMaterials[m];
        }
        for(int n=0;n<DescriptionProofmate.length;n++){
            DescriptionsAllMaterials[count2++]=DescriptionProofmate[n];
        }

        //создание массива объема материалов с начальными нулевыми значениями
        Values=new double[NamesAllMaterials.length];
        for(double element: Values){element=0.0;}
    }

    //добавление материала, название из перечисления
    public void putValue(Enum<?> materialEnum, double val){
        for(int i=0;i<NamesAllMaterials.length;i++){
            if (materialEnum.name().equals(NamesAllMaterials[i])){
                Values[i]+=val;
                break;
            }
        }
    }

    //добавления материала, название из строки
    public void putValue(String nameStr, double val){
        for(int i=0;i<NamesAllMaterials.length;i++)
            if (nameStr.equals(NamesAllMaterials[i])){
                Values[i]+=val;
                break;
            }
    }

    //добавление массива объемов материалов
    public void putValuesArray(double [] values){
        for (int i=0;i<NamesAllMaterials.length;i++){
            Values[i]+=values[i];
        }
    }

    //вывод всех материалов с колличеством в строку
    public StringBuilder result() {
        StringBuilder resultMat = new StringBuilder("");

        for (int i = 0; i < Values.length; i++) {
            if (Values[i] > 0) {
                resultMat.append(DescriptionsAllMaterials[i]).append(": ")
                        .append(Round.roundDouble(Values[i], 1)).append('\n');
            }
        }
        return resultMat;
    }

    //получить массив всех объемов материалов
    public double [] getValues(){
        return Values;
    }

    //очитска всех объемов
    public void clear(){
        for(int i=0; i<Values.length;i++){
            Values[i]=0.0;
        }
    }


}
