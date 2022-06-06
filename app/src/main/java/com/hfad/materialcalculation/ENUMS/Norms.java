package com.hfad.materialcalculation.ENUMS;
/*
нормы расчета материалов
 */

public enum Norms {
    N18PENAMONTAZHNAYA(40,"Заполнение шва монтажной пеной"),
    N34JOINT450CURED1(0.027,"Распиливаине набр. прочность бетона д.450мм одним диском"),
    N36JOINT600CURED1(0.02,"Распиливаине набр. прочность бетона д.600мм одним диском"),
    N38JOINT800CURED1(0.015,"Распиливаине набр. прочность бетона д.800мм одним диском"),
    N42JOINT1000CURED1(0.012,"Распиливаине набр. прочность бетона д.800мм одним диском"),
    N44JOINT1200CURED1(0.01,"Распиливаине набр. прочность бетона д.800мм одним диском"),
    N46JOINT350CURED2(0.068,"Распиливаине набр. прочность бетона д.350мм двумя дисками"),
    N48JOINT350CURED3(0.102,"Распиливаине набр. прочность бетона д.350мм тремя дисками"),
    N50JOINT450ASPH1(0.008,"Распиливаине асфальтобетона д.450мм одним диском"),
    N52JOINT600ASPH1(0.006,"Распиливаине асфальтобетона д.600мм одним диском"),
    N54JOINT800ASPH1(0.005,"Распиливаине асфальтобетона д.800мм одним диском"),
    N56JOINT1000ASPH1(0.004,"Распиливаине асфальтобетона д.1000мм одним диском"),
    N56_1JOINT1200ASPH1(0.003,"Распиливаине асфальтобетона д.1200мм одним диском"),
    N57JOINT450ASPH2(0.016,"Распиливаине асфальтобетона д.450мм двумя дисками"),
    N58JOINT450ASPH3(0.024,"Распиливаине асфальтобетона д.450мм тремя дисками"),
    N61JOINT450GREEN1(0.007,"Распиливаине свежеуложенного бетона д.450мм одним диском"),
    N63JOINT600GREEN1(0.005,"Распиливаине свежеуложенного бетона д.600мм одним диском"),
    N67JOINT800GREEN1(0.004,"Распиливаине свежеуложенного бетона д.800мм одним диском"),
    N70JOINT1000GREEN1(0.003,"Распиливаине свежеуложенного бетона д.1000мм одним диском"),
    N70_1JOINT1200GREEN1(0.002,"Распиливаине свежеуложенного бетона д.1200мм одним диском"),
    N79JOINT230LANDS2(0.0002,"Снятие фаски д.230мм пара"),
    PRIMER(55,"Праймер, л"),
    N15ROD(1.05,"Шнур уплотнительный, м"),
    N15_2PROOFMATE(1.01,"Профиль , м");


    private double norm;
    private String descript;

    Norms(double n,String d){
        norm=n;
        descript=d;
    }
    public double getNorm(){
        return norm;
    }

    public String getDescription(){ return descript; }
}
