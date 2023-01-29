import java.sql.Time;
import java.util.Random;
/**
 * Sensor holds important values regarding each sensor in our system. Each sensor has a weight and a bias.
 * These are used to compute the output of a given activation. The activation is then "Squished" using the sigmoid
 * function, resulting in the output, obtained using getOutput()
 *
 * @author Jon Steele jonwhsteele@gmail.com
 */
public class Sensor {
    //each sensor needs a weight, bias, and random initializer function
    private double weight;
    private double bias;
    private double activation;
    private double output;

    /**
     * The unparameterized constructor randomly fills the weight and biases between 0 and 10
     */
    public Sensor(){
        Random r = new Random();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        r.setSeed(System.nanoTime());
        weight = r.nextDouble(-3, 3);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        r.setSeed(System.nanoTime());
        bias = r.nextDouble(-3, 3);
    }

    /**
     * The parameterized constructor randomly fills the weight and biases between 0 and 10, but also accepts the
     * activation, which should be either 1 or 0.
     */
    public Sensor(double activation){
        this.activation = activation;
        Random r = new Random();
        weight = r.nextDouble(1);
        bias = r.nextDouble();
        output = getOutput();
    }

    /**
     * getOutput returns the sigmoid function of the weighted, biased activation value
     * @return
     */
    public double getOutput(){
        return (activation * weight);
    }

    public double getActivation() {
        return activation;
    }

    public void setActivation(double activation) {
        this.activation = activation;
    }

    public double getWeight() {
        output = getOutput();
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    static double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public double sigmoidDerivative(){
        double x = (weight * activation + bias);
        return sigmoid(x) * (1 - sigmoid(x));
    }
}
