package com.hfad.materialcalculation.calculations;
/*
рассчет материлов для нарезки пионерного пропила
 */


import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.Norms;

public class CuttingGreenCalculation {

    //вычисление массива материалов необходимых для пионерного пропила
    public static double [] getMaterialsPionerkaCut(int depth, int length){
        double DiamondSawBladeGreen450=0.0;
        double DiamondSawBladeGreen600=0.0;
        double DiamondSawBladeGreen800=0.0;
        double DiamondSawBladeGreen1000=0.0;
        double DiamondSawBladeGreen1200=0.0;

        AllMaterials materials = new AllMaterials();

        //вычисление колличества дисков д.450мм по свежему бетону
        if(depth>0&&depth<=100&&length>=0){
            DiamondSawBladeGreen450=(double)(length*depth* Norms.N61JOINT450GREEN1.getNorm())/1000;
        }
        else if(depth>100&&depth<=450&&length>=0){
            DiamondSawBladeGreen450=(double)(length*100*Norms.N61JOINT450GREEN1.getNorm())/1000;
        }

        //вычисление колличества дисков д.600мм по свежему бетону
        if(depth>100&&depth<=180&&length>=0){
            DiamondSawBladeGreen600=(double)(length*(depth-100)*Norms.N63JOINT600GREEN1.getNorm())/1000;
        }
        else if (depth>180&&depth<=450&&length>=0) {
            DiamondSawBladeGreen600 = (double)(length*80*Norms.N63JOINT600GREEN1.getNorm() )/ 1000;
        }

        //вычисление колличества дисков д.800мм по свежему бетону
        if (depth>180&&depth<=280&&length>=0){
            DiamondSawBladeGreen800 =(double)(length*(depth-180)*Norms.N67JOINT800GREEN1.getNorm())/1000;
        }
        else if(depth>280&&depth<=450&&length>=0){
            DiamondSawBladeGreen800=(double)(length*100*Norms.N67JOINT800GREEN1.getNorm())/1000;
        }

        //вычисление колличества дисков д.1000мм по старому бетону
        if (depth>280&&depth<=360&&length>=0){
            DiamondSawBladeGreen1000 =(double)(length*(depth-280)*Norms.N70JOINT1000GREEN1.getNorm())/1000;
        }
        else if(depth>360&&depth<=450&&length>=0){
            DiamondSawBladeGreen1000=(double)(length*80*Norms.N70JOINT1000GREEN1.getNorm())/1000;
        }

        //вычисление колличества дисков д.1200мм по старому бетону
        if (depth>360&&depth<=450){
            DiamondSawBladeGreen1200 =(double)(length*(depth-360)*Norms.N70_1JOINT1200GREEN1.getNorm())/1000;
        }


        materials.putValue(MaterialsSawBlades.DiamondSawBladeGreen450,DiamondSawBladeGreen450);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeGreen600,DiamondSawBladeGreen600);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeGreen800,DiamondSawBladeGreen800);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeGreen1000,DiamondSawBladeGreen1000);
        materials.putValue(MaterialsSawBlades.DiamondSawBladeGreen1200,DiamondSawBladeGreen1200);

        return materials.getValues();

    }
}

