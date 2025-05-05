package com.example.escape_the_lab.model;

/**
 * The SpringModel class represents the model of the spring-mass system. It stores the mass and
 * spring constant used in the simulation and checks whether a given combination is correct
 */
public class SpringModel {
    private double mass;
    private double springConstant;

    /**
     * Constructs a new SpringModel with the given mass and spring constant.
     * @param mass the mass to be used in the simulation
     * @param springConstant the spring constant to be used in the simulation
     */
    public SpringModel(double mass, double springConstant) {
        this.mass = mass;
        this.springConstant = springConstant;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }

//    public double getMass() {
//        return mass;
//    }
//
//
//    public double getSpringConstant() {
//        return springConstant;
//    }
//


    /**
     * Checks if the selected mass and spring constant match the correct values.
     * @param correctMass expected mass
     * @param correctSpringConstant expected spring constant
     * @return true if the selected values match the correct ones
     */
    public boolean isCorrectCombination(double correctMass, double correctSpringConstant) {
        return this.mass == correctMass && this.springConstant == correctSpringConstant;
    }
}
