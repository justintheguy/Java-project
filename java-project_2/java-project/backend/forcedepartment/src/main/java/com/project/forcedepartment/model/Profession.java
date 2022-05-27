package com.project.forcedepartment.model;


public class Profession {

    //private Map<String, Double> professions = new HashMap<>();
    private String professionName;
    private double experience_year;

    public Profession() {
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public double getExperience_year() {
        return experience_year;
    }

    public void setExperience_year(double experience_year) {
        this.experience_year = experience_year;
    }

    public String getProfessionName() {
        return professionName;
    }
}
