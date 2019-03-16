package com.example.unitconverter;

public class Converter {
    public static double convert (double fromVal, PhysicalQuantity from, PhysicalQuantity to) {
        double inStandard = fromVal * from.getToStandard();
        double finalVal = inStandard * (1/to.getToStandard());
        return finalVal;
    }
}
