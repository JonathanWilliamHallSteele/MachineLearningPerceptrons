import java.util.Stack;

/**
 * solution is where the main method exists, it is used to run the methods inside of Forward_Propagation and Train_Test
 * @author Jon Steele jonwhsteele@gmail.com
 */
public class solution {

    public static void main (String[] args) throws InterruptedException {

        //Feel free to change the inputs and outputs variables. It can handle as many rows of data as you want,
        //as long as they are of equal length

        double[][] inputs = new double[][]{
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 1},
                {0, 1, 1}
        };

        double[][] outputs = new double[][]{
                {0},
                {1},
                {1},
                {0}
        };

        //Initializing variables for forward propagation
        Instances instances = new Instances(inputs, outputs); //1 Instance holds the data for 1 instance, or row of data
        Sensor[] sensors = new Sensor[3]; //one Sensor holds the weights and inputs for each sensor
        sensors = initializeSensors();
        Forward_Propagation forward_propagation;

        //Initializing variables for training
        forward_propagation = new Forward_Propagation();
        Train_Test train_test = new Train_Test();
        double output = 0;
        double actualOutput = 0;

        System.out.println("Before training, the neural network makes random predictions... \n");

        Thread.sleep(2000);

        for (int row = 0; row < instances.getLength(); row++) {

            updateSensorActivation(sensors, instances.instances.get(row)); //changing the inputs for each Sensor
            forward_propagation.setOutput(sensors); //Performing forward_propagation on those new inputs
            System.out.print("Predicted: " + sigmoid(forward_propagation.getOutput()) + "\tActual: ");
            System.out.println(outputs[row][0]);

        }

        Thread.sleep(4000);

        System.out.println("\nNot great... Let's train the neural network to optimize the weights and bias...");

        Thread.sleep(2000);

        for (int i = 0; i < 1000; i++) { //Training the entire dataset 1000 times
            for (int row = 0; row < instances.getLength(); row++) {//for each row in the dataset

                //first we must update the sensors so their inputs match this instance
                updateSensorActivation(sensors, instances.instances.get(row));

                //now we can predict the output of the sensors, with their activations
                output = sigmoid(forward_propagation.setOutput(sensors));
                //instances.instances.get(row).outputs.add(output);

                //next we feed the sensors and their forward propagation output into train_test
                train_test = new Train_Test(sensors, output);

                //So we can adjust their weights
                actualOutput = outputs[row][0];
                train_test.adjustWeights(actualOutput); // <---- Training Weights
                train_test.adjustBias(actualOutput); // <---- Training Bias

                //now we have a new set of sensors, with weights and biases updated
                //we can replace this instance of sensors, with the adjusted sensors in train_test
                sensors = train_test.getSensors();

            }
        }

        Thread.sleep(2000);

        System.out.println("\nHere are the networks trained predictions... \n");

        Thread.sleep(2000);

        for (int row = 0; row < instances.getLength(); row++) {

            updateSensorActivation(sensors, instances.instances.get(row));
            forward_propagation = new Forward_Propagation(sensors);
            System.out.print("Predicted: " + sigmoid(forward_propagation.getOutput()) + "\tActual: ");
            System.out.println(outputs[row][0]);

        }

        Thread.sleep(3000);

        System.out.println("\nThats better! " +
                "\n\nNow, we can test how our neural network predicts new data, with the new test case of {0, 0, 0} inputs\n");

        Thread.sleep(6000);

        int[] newData = {0, 0, 0};

        sensors[0].setActivation(newData[0]);
        sensors[1].setActivation(newData[1]);
        sensors[2].setActivation(newData[2]);

        forward_propagation = new Forward_Propagation(sensors);
        System.out.println("Prediction of test case: " + sigmoid(forward_propagation.getOutput()));

    }

    private static Sensor[] updateSensorActivation(Sensor[] sensors, Instance instance) {

        sensors[0].setActivation(instance.sensor1);
        sensors[1].setActivation(instance.sensor2);
        sensors[2].setActivation(instance.sensor3);

        return sensors;
    }

    /**
     * initializeSensors() simply instantiates three sensors, places them in an array of size 3, and returns that array
     * @return
     */
    private static Sensor[] initializeSensors() {

        Sensor sensor1 = new Sensor();
        Sensor sensor2 = new Sensor();
        Sensor sensor3 = new Sensor();

        Sensor[] sensors = new Sensor[3];

        sensors[0] = sensor1;
        sensors[1] = sensor2;
        sensors[2] = sensor3;

        return sensors;
    }

    static double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
}
