package com.hfad.materialcalculation.ENUMS;

public enum MaterialsRods {
    ROD8("Уплотнительный шнур д.8мм, м",8),
    ROD10("Уплотнительный шнур д.10мм, м",10),
    ROD12("Уплотнительный шнур д.12мм, м",12),
    ROD14("Уплотнительный шнур д.14мм, м",14),
    ROD15("Уплотнительный шнур д.15мм, м",15),
    ROD16("Уплотнительный шнур д.16мм, м",16),
    ROD18("Уплотнительный шнур д.18мм, м",18),
    ROD20("Уплотнительный шнур д.20мм, м",20),
    ROD21("Уплотнительный шнур д.21мм, м",21),
    ROD24("Уплотнительный шнур д.24мм, м",24),
    ROD25("Уплотнительный шнур д.25мм, м",25),
    ROD30("Уплотнительный шнур д.30мм, м",30),
    ROD35("Уплотнительный шнур д.35мм, м",35),
    ROD38("Уплотнительный шнур д.38мм, м",38),
    ROD40("Уплотнительный шнур д.40мм, м",40),
    ROD45("Уплотнительный шнур д.45мм, м",45),
    ROD("Уплотнительный шнур, м",1);

    private String description;
    private int diameter;

    MaterialsRods(String description, int diameter){
        this.description =description;
        this.diameter=diameter;

    }

    //получение массива всех названий констант
    public static String[]getNames(){
        MaterialsRods[] obj = MaterialsRods.values();
        String[]names=new String[obj.length];
        for(int i=0;i<obj.length;i++){
            names[i]=obj[i].name();
        }
        return names;
    }

    //получение массива всех описаний
    public static String[]getDescriptions(){
        MaterialsRods[] obj=MaterialsRods.values();
        String[] names = MaterialsRods.getNames();

        String[]descriptions=new String[MaterialsRods.getNames().length];
        for(int i=0;i<descriptions.length;i++){
            if (names[i].equals(obj[i].name())){
                descriptions[i]=obj[i].description;
            }
        }
        return descriptions;
    }

    //получение массива всех диаметров в формате чисел
    public static int[] getDiametersInt(){
        MaterialsRods []obj=MaterialsRods.values();
        int[]diameters = new int[obj.length];

        for(int j=0; j<obj.length;j++){
            diameters[j]=obj[j].diameter;
        }
        return diameters;
    }
    //получение массива всех диаметров в формате строковом
    public static String[] getDiametersString(){
        MaterialsRods []obj=MaterialsRods.values();
        String[]diameters = new String[obj.length];

        for(int j=0; j<obj.length;j++){
            diameters[j]=String.valueOf(obj[j].diameter);
        }
        return diameters;
    }
}
