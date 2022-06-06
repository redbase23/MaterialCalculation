package com.hfad.materialcalculation.ENUMS;

public enum MaterialsAnother {
    PRIMER("Праймер, л"),
    DOSKA("Доска обрезная, м3"),
    PENOLON("Пенолон, м2"),
    MONTAZHNAYA_PENA("Монтажная пена, бал"),
    FILLING_MATERIAL("Сжимаемый материал, м3");

    private String description;
    MaterialsAnother(String description){
        this.description=description;
    }

    public static String[]getNames(){
        MaterialsAnother[] obj = MaterialsAnother.values();
        String[]names=new String[obj.length];
        for(int i=0;i<obj.length;i++){
            names[i]=obj[i].name();
        }
        return names;
    }

    public static String[]getDescriptions(){
        MaterialsAnother[] obj=MaterialsAnother.values();
        String[] names = MaterialsAnother.getNames();

        String[]descriptions=new String[MaterialsAnother.getNames().length];
        for(int i=0;i<descriptions.length;i++){
            if (names[i].equals(obj[i].name())){
                descriptions[i]=obj[i].description;
            }
        }
        return descriptions;
    }
}

