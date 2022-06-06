package com.hfad.materialcalculation.calculations;


import com.hfad.materialcalculation.AllMaterials;
import com.hfad.materialcalculation.ENUMS.MaterialsSawBlades;
import com.hfad.materialcalculation.ENUMS.Norms;

/*
рассчет материалов для сниятия фаски
 */
public class LandsCalculation {
    //вычисление материалов на пропил камерв в старом бетоне и возврат массива
    public static double[] getMaterialsLandsSaw(int length){
        AllMaterials materials = new AllMaterials();
        double DiamondBladeLands230 =0.0;

        //вычисление колличества дисков д.230мм для снятия фаски
        DiamondBladeLands230 =length* Norms.N79JOINT230LANDS2.getNorm();

        //ввод вычисленных материалов в массив
        materials.putValue(MaterialsSawBlades.DiamondBladeLands230,DiamondBladeLands230);
        return  materials.getValues();
    }
}
