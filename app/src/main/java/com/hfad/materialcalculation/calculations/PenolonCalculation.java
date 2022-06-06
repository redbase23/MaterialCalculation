package com.hfad.materialcalculation.calculations;

import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsAnother;
import com.hfad.materialcalculation.ENUMS.Norms;

public class PenolonCalculation {
    public static double[]getMaterialsPenolonExpansionJoint(int length, int width, int height,String filling){
        AllMaterials materials = new AllMaterials();
        double val;

        switch (filling){
            case "DOSKA":
                val = (double)(length*(width-5)*height)/1000000;
                break;
            case "PENOLON":
                val =(double)(length*height)/1000;
                break;
            case "MONTAZHNAYA_PENA":
                val = (double)(length*width*height* Norms.N18PENAMONTAZHNAYA.getNorm())/1000000;
                break;
            default:
                filling = "FILLING_MATERIAL";
                val=(double)(length*(width-5)*height)/1000000;
                break;
        }
        materials.putValue(filling,val);
        return materials.getValues();
    }
}
