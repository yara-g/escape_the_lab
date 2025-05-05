package escape_the_lab.model;

/**
 * The SpringModel class represents the model of a spring-mass system. It stores the mass and spring constant used
 * in the simulation and checks whether a given combination is correct.
 */
public class SpringModel {
    private final double mass;
    private final double springConstant;

    /**
     * Constructs a new SpringModel with the given mass and spring constant
     * @param mass the mass to be used in the simulation
     * @param springConstant the spring constant to be used in the simulation
     */
    public SpringModel(double mass, double springConstant) {
        this.mass = mass;
        this.springConstant = springConstant;
    }

//    public double getMass() {
//        return mass;
//    }
//
//    public void setMass(double mass) {
//        this.mass = mass;
//    }
//
//    public double getSpringConstant() {
//        return springConstant;
//    }
//
//    public void setSpringConstant(double springConstant) {
//        this.springConstant = springConstant;
//    }

    /**
     * Checks if the selected mass and spring constant are correct.
     * @param correctMass expected mass
     * @param correctSpringConstant expected spring constant
     * @return true if the selected values match the correct ones
     */
    public boolean isCorrectCombination(double correctMass, double correctSpringConstant) {
        return this.mass == correctMass && this.springConstant == correctSpringConstant;
    }
}
