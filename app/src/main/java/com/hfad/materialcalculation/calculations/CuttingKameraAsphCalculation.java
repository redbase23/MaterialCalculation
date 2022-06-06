package com.hfad.materialcalculation.calculations;
/*
расчет материалов на нарезку камеры шва в асфальтобетоне
 */

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.Norms;

public class CuttingKameraAsphCalculation {

    public static double[]getMaterialsKameraAsphCut(int depthCut, int widthCut, int lenght){
        double DiamondSawBladeAsphalt450=0.0;
        AllMaterials materials = new AllMaterials();

        //вычисление дисков д.450 по асфальту
        if(widthCut>0&&widthCut<=10&&lenght>=0){
            DiamondSawBladeAsphalt450=(depthCut*lenght* Norms.N57JOINT450ASPH2.getNorm())/1000;
        }
        else if(widthCut>10&&widthCut<=15&&lenght>=0){
            DiamondSawBladeAsphalt450=(depthCut*lenght*Norms.N58JOINT450ASPH3.getNorm())/1000;
        }

        //ввод вычисленных материалов в массив
        materials.putValue(MaterialsSawBlades.DiamondSawBladeAsphalt450,DiamondSawBladeAsphalt450);

        return materials.getValues();
    }
}
