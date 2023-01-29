import java.util.ArrayList;
import java.util.Stack;

/**
 * Instances is used to hold all data instances in one place, so it is easily accessible.
 * @author Jon Steele jonwhsteele@gmail.com
 */
public class Instances {

    ArrayList<Instance> instances = new ArrayList<Instance>();

    public Instances(double[][] inputs, double[][] outputs){
        instances = getInstances(inputs, outputs);
    }

    /**
     * This method is used to turn the given data into a more digestible format
     * @param inputs the instance inputs
     * @param outputs the instance output
     * @return a stack containing all instances
     */
    private static ArrayList<Instance> getInstances(double[][] inputs, double[][] outputs) {

        //If the input size doesn't match the output size, there is a format error
        if (inputs.length != outputs.length)
            throw new ArrayIndexOutOfBoundsException("input and output arrays must have the same length of rows");

        ArrayList<Instance> instances = new ArrayList<Instance>();
        Instance current = new Instance();

        for (int i = 0; i < inputs.length; i++){
            current = new Instance(inputs[i], outputs[i][0]);
            instances.add(current);
        }

        return instances;
    }

    public int getLength() {
        return instances.size();
    }
}
