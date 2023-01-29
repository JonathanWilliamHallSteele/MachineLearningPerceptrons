import java.lang.Math;
/**
 * @author Jon Steele jonwhsteele@gmail.com
 */
public class Forward_Propagation {

    double output;
    double expectedOutput;

    public Forward_Propagation(Sensor[] sensors){
        setOutput(sensors);
    }

    public Forward_Propagation(double expectedOutput, Sensor[] sensors){
        setOutput(sensors);
        this.expectedOutput = expectedOutput;
    }

    public Forward_Propagation() {

    }

    public double setOutput(Sensor[] sensors) {
        double result = 0;

        double sens1Activation = sensors[0].getOutput();
        double sens2Activation = sensors[1].getOutput();
        double sens3Activation = sensors[2].getOutput();

        double bias = sensors[0].getBias();

        output = (sens1Activation + sens2Activation + sens3Activation + bias);

        return output;
    }

    double getOutput(){
        return output;
    }

    public double getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(double expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    private static double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}