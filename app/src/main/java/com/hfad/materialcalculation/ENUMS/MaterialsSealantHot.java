package com.hfad.materialcalculation.ENUMS;
/*
мастики битумно-полимерные горячего применения
 */

public enum MaterialsSealantHot {
    HOT_ROAD34231("Crafco Hot Road 34231, кг",1100),
    BERAM3060LM("Beram 3060LM, кг",1100),
    BRIT_NORD("Брит Норд, кг",1100),
    BRIT_ARCTIC3("Брит Арктик3, кг",1100),
    SEALANT("Герметик горячего применения, кг",1100);

    private String description;
    private int dense;

    MaterialsSealantHot(String description, int dense){
        this.description=description;
        this.dense=dense;
    }

    //получение плотности по названию
    public static int getDense(String sealant){
        MaterialsSealantHot[]seals= MaterialsSealantHot.values();
        for(MaterialsSealantHot s:seals){
            if (s.name().equals(sealant)){
                return s.dense;
            }
        }
        return 1100;
    }

    public static String[]getNames(){
        MaterialsSealantHot[] obj = MaterialsSealantHot.values();
        String[]names=new String[obj.length];
        for(int i=0;i<obj.length;i++){
            names[i]=obj[i].name();
        }
        return names;
    }

    public static String[]getDescriptions(){
        MaterialsSealantHot[] obj=MaterialsSealantHot.values();
        String[] names = MaterialsSealantHot.getNames();

        String[]descriptions=new String[MaterialsSealantHot.getNames().length];
        for(int i=0;i<descriptions.length;i++){
            if (names[i].equals(obj[i].name())){
                descriptions[i]=obj[i].description;
            }
        }
        return descriptions;
    }
}
