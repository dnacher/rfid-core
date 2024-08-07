package com.software.rfid.core.utils;

import java.time.LocalDate;

public class UtilsGeneral {

    private UtilsGeneral(){}

    public static boolean esNumero(String s) {
        boolean esNumero;
        try {
            Double.parseDouble(s);
            esNumero = true;
        } catch (Exception ex) {
            esNumero = false;
        }
        return esNumero;
    }

    public static boolean esPorcentaje(String s){
        if(esNumero(s)){
            return Double.valueOf(s) >= 0 && Double.valueOf(s) <= 100;
        } else{
            return false;
        }
    }

    public static String getFechaFormato(LocalDate date) {
        if(date!=null){
            String fecha="";
            if(date.getDayOfMonth()<10){
                fecha+= "0" + date.getDayOfMonth() + "-";
            }else{
                fecha+= date.getDayOfMonth() + "-";
            }
            if(date.getMonthValue()<10){
                fecha+= "0" + date.getMonthValue() + "-";
            }else{
                fecha+= date.getMonthValue() + "-";
            }
            return fecha + date.getYear();
        }else{
            return "";
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
