package model;

public class PredictionModel {

    public static String predict(double glucose, double bp, double chol) {

        if (glucose > 120 && bp > 80 && chol > 200) {
            return "HIGH RISK";
        } else {
            return "LOW RISK";
        }
    }
}