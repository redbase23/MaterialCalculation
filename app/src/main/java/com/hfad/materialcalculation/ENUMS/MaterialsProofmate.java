package com.hfad.materialcalculation.ENUMS;

public enum MaterialsProofmate {
    PROOFMATE_EBF_6("Профиль Proofmate EBF6, м:",6,15),
    PROOFMATE_EBF_8("Профиль Proofmate EBF8, м:",8,25),
    PROOFMATE_EBF_10("Профиль Proofmate EBF10, м:",10,25),
    PROOFMATE_EBF_12("Профиль Proofmate EBF12, м:",12,25),
    PROOFMATE_EBF_15("Профиль Proofmate EBF15, м:",15,25),
    PROOFMATE_EBF_20("Профиль Proofmate EBF20, м:",20,32),
    PROOFMATE_EBF_25("Профиль Proofmate EBF20, м:",25,32),
    PROOFMATE_EK_15_25("Профиль Proofmate EK15_25, м:",20,45),
    PROOFMATE_EK_18_30("Профиль Proofmate EK15_25, м:",25,45),
    PROOFMATE_EK_20_40("Профиль Proofmate EK15_25, м:",30,45),
    PROOFMATE_EK_27_49("Профиль Proofmate EK15_25, м:",35,65),
    PROOFMATE_EK_30_60("Профиль Proofmate EK15_25, м:",45,90),
    ;

    private String description;
    private int width;
    private int height;

    MaterialsProofmate(String description, int width,int height){
        this.description=description;
        this.width=width;
        this.height=height;
    }

    //получение массива всех названий констант
    public static String[]getNames(){
        MaterialsProofmate[] obj = MaterialsProofmate.values();
        String[]names=new String[obj.length];
        for(int i=0;i<obj.length;i++){
            names[i]=obj[i].name();
        }
        return names;
    }

    //получение массива всех описаний
    public static String[]getDescriptions(){
        MaterialsProofmate[] obj=MaterialsProofmate.values();
        String[] names = MaterialsProofmate.getNames();

        String[]descriptions=new String[MaterialsProofmate.getNames().length];
        for(int i=0;i<descriptions.length;i++){
            if (names[i].equals(obj[i].name())){
                descriptions[i]=obj[i].description;
            }
        }
        return descriptions;
    }

    //получение массива всех ширин в формате чисел
    public static int[] getWidthsInt(){
        MaterialsProofmate []obj=MaterialsProofmate.values();
        int[]widths = new int[obj.length];

        for(int j=0; j<obj.length;j++){
            widths[j]=obj[j].width;
        }
        return widths;
    }
    //получение массива всех глубин в формате чисел
    public static int[] getHeightsInt(){
        MaterialsProofmate []obj=MaterialsProofmate.values();
        int[]heights = new int[obj.length];

        for(int j=0; j<obj.length;j++){
            heights[j]=obj[j].height;
        }
        return heights;
    }
}
