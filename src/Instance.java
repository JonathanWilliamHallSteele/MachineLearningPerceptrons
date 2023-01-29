import java.util.ArrayList;
import java.util.Stack;

/**
 * Instance is a class that holds information regarding a single instance of inputs and outputs.
 * It is used as an easy means of storing and accessing instance data easily
 *
 * double sensor1, sensor2, sensor3 store the inputs for each sensor
 * Stack<Double> outputs holds the outputs. The first element always refers to the expected output!
 *
 * @author Jon Steele jonwhsteele@gmail.com
 */
public class Instance {

    double sensor1, sensor2, sensor3;
    ArrayList<Double> outputs;

    public Instance(){

        sensor1 = 0;
        sensor2 = 0;
        sensor3 = 0;

        outputs = new ArrayList<Double>();

    }

    public Instance(double[] inputs, double output){

        //Inputs should always be size 3. If not, something is wrong.
        if (inputs.length != 3)
            throw new ArrayIndexOutOfBoundsException();

        this.sensor1 = inputs[0];
        this.sensor2 = inputs[1];
        this.sensor3 = inputs[2];

        outputs = new ArrayList<Double>();
        outputs.add(output);

    }

    public void addOutput(double output) {
        outputs.add(output);
    }
}
