package recognition;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Neuron {
    double value;

    public Neuron(double value) {
        this.value = value;
    }
}

public class Main {
    private final static String neuralNetworkFileName = "c:\\temp\\network.neu";
    static Logger logger = Logger.getLogger("Main");
    static Scanner scanner = new Scanner(System.in);
    static NeuralNetwork neuralNetwork;

    public static void main(String[] args) {
        int choice;
        int number = 0;
        do {
            choice = getChoice();
            if (choice == 1) {
                learn();
            } else {
                int[] grid = getGrid();
                number = guess(grid);
            }
        } while (choice != 2);
        System.out.println("This number is " + number);
    }

    private static int guess(int[] grid) {
        if (neuralNetwork == null) {
            neuralNetwork = new NeuralNetwork();
        }
        try {
            neuralNetwork.deserialize(neuralNetworkFileName);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        Neuron[] neurons = neuralNetwork.guess(grid);
        int max = 0;
        for (int i = 0; i < neurons.length; i++) {
            if (neurons[i].value > neurons[max].value) {
                max = i;
            }
        }
        return max;
    }

    private static void learn() {
        System.out.println("Learning...");
        neuralNetwork = new NeuralNetwork();
        neuralNetwork.learn(NumberRecognition.idealInputNeurons, NumberRecognition.idealOutputNeurons);
        try {
            neuralNetwork.serialize(neuralNetworkFileName);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Done! Saved to the file.");
    }

    private static int[] getGrid() {
        int[] grid = new int[5 * 3];
        for (int i = 0; i < 5; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 3; j++) {
                grid[3 * i + j] = line.charAt(j) == 'X' ? 1 : 0;
            }
        }
        return grid;
    }

    private static int getChoice() {
        System.out.println(
                "1. Learn the network\n" +
                "2. Guess a number");
        String choice = scanner.nextLine();
        while (!"1".equals(choice) && !"2".equals(choice)) {
            choice = scanner.nextLine();
        }
        System.out.println("Your choice: " + choice);
        return Integer.parseInt(choice);
    }

    private static int analyze(Neuron[] input) {
        /*
        biases:
        -1 for 6, 9, 0
        6 for 1
        1 for 2
        0 for 3, 5
        2 for 4
        0 for 3, 5
        -1 for 6, 9, 0
        3 for 7
        -2 for 8
        -1 for 6, 9, 0
        */
        final int[] biases = {
                -1, 6, 1,
                0, 2, 0,
                -1, 3, -2, -1};
        Neuron[] output = new Neuron[10];
        int max = 0;
        for (int i = 0; i < output.length; i++) {
//            output[i] = new Neuron(processNeurons(input, weights[i], biases[i]));
            if (output[i].value > output[max].value) {
                max = i;
            }
            logger.log(Level.INFO, String.format("%d: %d", i, output[i].value));
        }
        return max;
    }

    private static int processNeurons(Neuron[] input, int[] weights, int bias) {
        int res = 0;
        for (int i = 0; i < input.length; i++) {
            res += input[i].value * weights[i];
        }
        return res + bias;
    }
}
