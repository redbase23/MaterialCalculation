package com.hfad.materialcalculation.ENUMS;

public enum MaterialsSawBlades {
    DiamondSawBladeGreen450("Диск по свежему бетону д.450мм, шт"),
    DiamondSawBladeGreen600("Диск по свежему бетону д.600мм, шт") ,
    DiamondSawBladeGreen800("Диск по свежему бетону д.800мм, шт"),
    DiamondSawBladeGreen1000("Диск по свежему бетону д.1000мм, шт"),
    DiamondSawBladeGreen1200("Диск по свежему бетону д.1200мм, шт"),
    DiamondSawBladeCured350("Диск по старому бетону д.350мм, шт"),
    DiamondSawBladeCured450("Диск по старому бетону д.450мм, шт"),
    DiamondSawBladeCured600("Диск по старому бетону д.600мм, шт"),
    DiamondSawBladeCured800("Диск по старому бетону д.800мм, шт"),
    DiamondSawBladeCured1000("Диск по старому бетону д.1000мм, шт"),
    DiamondSawBladeCured1200("Диск по старому бетону д.1200мм, шт"),
    DiamondSawBladeAsphalt350("Диск по асфальтобетону д.350мм, шт"),
    DiamondSawBladeAsphalt450("Диск по асфальтобетону д.450мм, шт"),
    DiamondSawBladeAsphalt600("Диск по асфальтобетону д.600мм, шт"),
    DiamondSawBladeAsphalt800("Диск по асфальтобетону д.800мм, шт"),
    DiamondSawBladeAsphalt1000("Диск по асфальтобетону д.1000мм, шт"),
    DiamondSawBladeAsphalt1200("Диск по асфальтобетону д.1200мм, шт"),
    DiamondBladeLands230("Диск для снятия фаски д.230мм, пара");



    public String description;

    MaterialsSawBlades(String description){
        this.description =description;
    }

    public static String[]getNames(){
        MaterialsSawBlades[] obj = MaterialsSawBlades.values();
        String[]names=new String[obj.length];
        for(int i=0;i<obj.length;i++){
            names[i]=obj[i].name();
        }
        return names;
    }

    public static String[]getDescriptions(){
        MaterialsSawBlades[] obj=MaterialsSawBlades.values();
        String[] names = MaterialsSawBlades.getNames();

        String[]descriptions=new String[MaterialsSawBlades.getNames().length];
        for(int i=0;i<descriptions.length;i++){
            if (names[i].equals(obj[i].name())){
                descriptions[i]=obj[i].description;
            }
        }
        return descriptions;
    }

}
