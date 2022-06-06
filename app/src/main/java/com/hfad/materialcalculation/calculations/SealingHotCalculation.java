package com.hfad.materialcalculation.calculations;


import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsAnother;
import com.hfad.materialcalculation.ENUMS.MaterialsSealantHot;
import com.hfad.materialcalculation.ENUMS.Norms;

/*
расчет материалов для горячей герметизации
 */
public class SealingHotCalculation {

    //вычисление материалов герметизацию горячим герметиком и возврат массива
    public static double[] getMaterialsSealingHot(int length, int widthSealing, int depthSealing,String sealant){
        AllMaterials materials = new AllMaterials();
        double PRIMER=0.0;
        double weightSealant=0.0;

        //вычисление потребности праймер
        PRIMER = (double)length*widthSealing*depthSealing* Norms.PRIMER.getNorm()/1000/1000;

        //вычиление потребности горячий герметик
        weightSealant= (double)length*widthSealing*depthSealing* MaterialsSealantHot.getDense(sealant)/1000/1000;

        //ввод вычисленных материалов в массив
        materials.putValue(MaterialsAnother.PRIMER,PRIMER);
        materials.putValue(sealant,weightSealant);

        return materials.getValues();
    }
}
