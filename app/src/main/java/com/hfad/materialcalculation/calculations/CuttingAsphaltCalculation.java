package com.hfad.materialcalculation.calculations;
/*
расчет потребности дисков на пропил одним диском в асфальтобетоне
 */

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.Norms;

public class CuttingAsphaltCalculation {

    public static double[] getMaterialsCuttingAsphalt(int depth, int length){
        double DiamondSawBladeAsphalt450=0.0;
        double DiamondSawBladeAsphalt600=0.0;
        double DiamondSawBladeAsphalt800=0.0;
        double DiamondSawBladeAsphalt1000=0.0;
        double DiamondSawBladeAsphalt1200=0.0;

        AllMaterials materials = new AllMaterials();

        //вычисление колличества дисков д.450мм по асфальту
        if (depth>0&&depth<=100&&length>=0){
            DiamondSawBladeAsphalt450 =(double)(length*depth* Norms.N50JOINT450ASPH1.getNorm())/1000;
        }
        else if(depth>100&&depth<=450&&length>=0){
            DiamondSawBladeAsphalt450=(double)(length*100*Norms.N50JOINT450ASPH1.getNorm())/1000;
        }

        //вычисление колличества дисков д.600мм по старому бетону
        if (depth>100&&depth<=180&&length>=0){
            DiamondSawBladeAsphalt600 =(double)(length*(depth-100)*Norms.N52JOINT600ASPH1.getNorm())/1000;
        }
        else if(depth>180&&depth<=450&&length>=0){
            DiamondSawBladeAsphalt600=(double)(length*80*Norms.N52JOINT600ASPH1.getNorm())/1000;
        }

        //вычисление колличества дисков д.800мм по старому бетону
        if (depth>180&&depth<=280&&length>=0){
            DiamondSawBladeAsphalt800 =(double)(length*(depth-180)*Norms.N54JOINT800ASPH1.getNorm())/1000;
        }
        else if(depth>280&&depth<=450&&length>=0){
            DiamondSawBladeAsphalt800=(double)(length*100*Norms.N54JOINT800ASPH1.getNorm())/1000;
        }

        //вычисление колличества дисков д.1000мм по старому бетону
        if (depth>280&&depth<=360&&length>=0){
            DiamondSawBladeAsphalt1000 =(double)(length*(depth-280)*Norms.N56JOINT1000ASPH1.getNorm())/1000;
        }
        else if(depth>360&&depth<=450&&length>=0){
            DiamondSawBladeAsphalt1000=(double)(length*80*Norms.N56JOINT1000ASPH1.getNorm())/1000;
        }

        //вычисление колличества дисков д.1200мм по старому бетону
        if (depth>360&&depth<=450){
            DiamondSawBladeAsphalt1200 =(double)(length*(depth-360)*Norms.N56_1JOINT1200ASPH1.getNorm())/1000;
        }

        //ввод вычисленных материалов в итоговый массив
        materials.putValue(MaterialsSawBlades.DiamondSawBladeAsphalt450,DiamondSawBladeAsphalt450);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeAsphalt600,DiamondSawBladeAsphalt600);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeAsphalt800,DiamondSawBladeAsphalt800);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeAsphalt1000,DiamondSawBladeAsphalt1000);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeAsphalt1200,DiamondSawBladeAsphalt1200);

        return materials.getValues();
    }
}
