package com.hfad.materialcalculation.calculations;
/*
рассчет потербности дисков на пропил одним диском в бетоне набравшем прочность
 */


import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.Norms;

public class CuttingCuredCalculation {

    //вычисление материалов на пропил в старом бетоне и возврат массива
    public static double[] getMaterialsCuttingCured(int depth,int length){
        double DiamondSawBladeCured450=0.0;
        double DiamondSawBladeCured600=0.0;
        double DiamondSawBladeCured800=0.0;
        double DiamondSawBladeCured1000=0.0;
        double DiamondSawBladeCured1200=0.0;

        AllMaterials materials = new AllMaterials();

        //вычисление колличества дисков д.450мм по старому бетону
        if (depth>0&&depth<=100&&length>=0){
            DiamondSawBladeCured450 =(double)(length*depth* Norms.N34JOINT450CURED1.getNorm())/1000;
        }
        else if(depth>100&&depth<=450&&length>=0){
            DiamondSawBladeCured450=(double)(length*100*Norms.N34JOINT450CURED1.getNorm())/1000;
        }

        //вычисление колличества дисков д.600мм по старому бетону
        if (depth>100&&depth<=180&&length>=0){
            DiamondSawBladeCured600 =(double)(length*(depth-100)*Norms.N36JOINT600CURED1.getNorm())/1000;
        }
        else if(depth>180&&depth<=450&&length>=0){
            DiamondSawBladeCured600=(double)(length*80*Norms.N36JOINT600CURED1.getNorm())/1000;
        }

        //вычисление колличества дисков д.800мм по старому бетону
        if (depth>180&&depth<=280&&length>=0){
            DiamondSawBladeCured800 =(double)(length*(depth-180)*Norms.N38JOINT800CURED1.getNorm())/1000;
        }
        else if(depth>280&&depth<=450&&length>=0){
            DiamondSawBladeCured800=(double)(length*100*Norms.N38JOINT800CURED1.getNorm())/1000;
        }

        //вычисление колличества дисков д.1000мм по старому бетону
        if (depth>280&&depth<=360&&length>=0){
            DiamondSawBladeCured1000 =(double)(length*(depth-280)*Norms.N42JOINT1000CURED1.getNorm())/1000;
        }
        else if(depth>360&&depth<=450&&length>=0){
            DiamondSawBladeCured1000=(double)(length*80*Norms.N42JOINT1000CURED1.getNorm())/1000;
        }

        //вычисление колличества дисков д.1200мм по старому бетону
        if (depth>360&&depth<=450){
            DiamondSawBladeCured1200 =(double)(length*(depth-360)*Norms.N44JOINT1200CURED1.getNorm())/1000;
        }

        //ввод вычисленных материалов в итоговый массив
        materials.putValue(MaterialsSawBlades.DiamondSawBladeCured450,DiamondSawBladeCured450);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeCured600,DiamondSawBladeCured600);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeCured800,DiamondSawBladeCured800);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeCured1000,DiamondSawBladeCured1000);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeCured1200,DiamondSawBladeCured1200);

        return materials.getValues();
    }
}
