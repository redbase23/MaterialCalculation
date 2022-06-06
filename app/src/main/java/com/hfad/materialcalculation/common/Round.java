package com.hfad.materialcalculation.common;
/*
округление чисел до нужного кол-ва знаков после запятой
 */

public class Round {
    public static double roundDouble(double Number,int Scale){
        int pow=10;
        for(int i=1;i<Scale;i++ ){
            pow*=10;
        }
        double temp =Number*pow;
        int itemp =(int)temp;
        if ((temp-itemp)>=0.5) itemp+=1;
        temp=(double)itemp/pow;
        return temp;
    }
}
