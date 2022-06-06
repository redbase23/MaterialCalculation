package com.hfad.materialcalculation.calculations;
/*
рассчет материалов на нарезку камеры шва в цементобетоне
 */

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.Norms;

public class CuttingKameraCalculation {
    //вычисление материалов на пропил камерв в старом бетоне и возврат массива
    public static double[] getMaterialsKameraCut(int depthCut,int widthCut, int lenght){
        double DiamondSawBladeCured350=0.0;
        AllMaterials materials = new AllMaterials();

        //вычисление колличества дисков д.350мм по старому бетону
        if(widthCut>0&&widthCut<=10&&lenght>=0){
            DiamondSawBladeCured350=(depthCut*lenght* Norms.N46JOINT350CURED2.getNorm())/1000;
        }
        else if(widthCut>10&&widthCut<=15&&lenght>=0){
            DiamondSawBladeCured350=(depthCut*lenght*Norms.N48JOINT350CURED3.getNorm())/1000;
        }

        //ввод вычисленных материалов в массив
        materials.putValue(MaterialsSawBlades.DiamondSawBladeCured350,DiamondSawBladeCured350);
        return materials.getValues();
    }
}
