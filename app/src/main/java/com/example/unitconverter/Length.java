package com.example.unitconverter;


public enum Length implements PhysicalQuantity {

    CENTIMETER(0.01),
    METER(1),
    KILOMETER(1000),
    INCH(0.0254);


    private double toStandard;
    Length(double toStandard) {
        this.toStandard = toStandard;
    }

    @Override
    public double getToStandard() {
        return toStandard;
    }
}
