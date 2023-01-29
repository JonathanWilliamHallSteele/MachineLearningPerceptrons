import static java.lang.Math.exp;

/**
 * @author Jon Steele jonwhsteele@gmail.com
 */
public class Train_Test {

    Sensor[] sensors;
    private final double LEARNING_RATE = 0.05;
    double output;

    public Train_Test(Sensor[] sensors, double output){

        this.sensors = sensors;
        this.output = output;

    }

    public Train_Test() {
    }

    /**
     * adjustWeights() uses the given list of instances to adjust the weights
     * @param actual is the desired output
     */
    public void adjustWeights(double actual) {

        //Output = weight * input for each sensor. Sensor 1 holds the bias for the output neuron
        output = sigmoid((sensors[0].getOutput() + sensors[1].getOutput() + sensors[2].getOutput() + sensors[0].getBias()));

        double weight1, weight2, weight3;
        double derError1, derError2, derError3;

        //to adjust the weights, we need each weight
        weight1 = sensors[0].getWeight();
        weight2 = sensors[1].getWeight();
        weight3 = sensors[2].getWeight();

        //next, we need the derivative of the cost function with respect to each weight
        //The formula is the same for each node, 2/3 * (predicted - actual) * derivative of sigmoid * activation
        derError1 = ((sigmoid(output) - actual)) * sigmoidDerivative(output) * sensors[0].getActivation();
        derError2 = ((sigmoid(output) - actual)) * sigmoidDerivative(output) * sensors[1].getActivation();
        derError3 = ((sigmoid(output) - actual)) * sigmoidDerivative(output) * sensors[2].getActivation();

        //Once we have these elements, we can adjust each weight towards a better solution
        sensors[0].setWeight(weight1 - (LEARNING_RATE * (derError1)));
        sensors[1].setWeight(weight2 - (LEARNING_RATE * (derError2)));
        sensors[2].setWeight(weight3 - (LEARNING_RATE * (derError3)));
        System.out.print("");
    }

    /**
     * First input neuron holds the bias
     * @param actual
     */
    public void adjustBias(double actual) {

        output = sigmoid((sensors[0].getOutput() + sensors[1].getOutput() + sensors[2].getOutput() + sensors[0].getBias()));

        double bias;
        double changeInError;

        //to adjust the weights, we need each weight
        bias = sensors[0].getBias();

        //next, we need the derivative of the cost function with respect to each weight
        //The formula is the same for each node, 2/3 * (predicted - actual) * 1
        changeInError = ((output - actual)) * sigmoidDerivative(output) * 1;

        //Once we have these elements, we can adjust each weight towards a better solution
        sensors[0].setBias(bias - (LEARNING_RATE * (changeInError)));
        System.out.print("");
    }

    public Sensor[] getSensors(){
        return sensors;
    }

    private double sigmoid(double x){
        return 1/(1+exp(-x));
    }

    private double sigmoidDerivative(double x){
        return sigmoid(x) * (1 - sigmoid(x));
    }

    public void setSensors(Sensor[] sensors) {
        this.sensors = sensors;
    }
}
