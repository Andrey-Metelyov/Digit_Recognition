package recognition;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private final static String neuralNetworkFileName = "c:\\temp\\mlnetwork.neu";
    static Logger logger = Logger.getLogger("Main");
    static Scanner scanner = new Scanner(System.in);
    static MultiLayerNeuralNetwork neuralNetwork;

    public static void main(String[] args) {
        neuralNetwork = new MultiLayerNeuralNetwork(new int[]{15, 15, 12, 10});
        int choice;
        int number = 0;
        do {
            choice = getChoice();
            if (choice == 1) {
                learn();
            } else if (choice == 2) {
                int[] grid = getGrid();
                number = guess(grid);
            } else {
                check();
            }
        } while (choice != 2);
        System.out.println("This number is " + number);
    }

    private static void check() {
        System.out.println("checking");
        try {
            neuralNetwork.deserialize(neuralNetworkFileName);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        for (int i = 0; i < NumberRecognition.idealInputs.getRows(); i++) {
            Matrix input = NumberRecognition.idealInputs.getRow(i);
            System.out.println("input:\n" + input);
            Matrix result = neuralNetwork.predict(input);
            System.out.println("result:\n" + result);
        }
    }

    private static int guess(int[] grid) {
        try {
            neuralNetwork.deserialize(neuralNetworkFileName);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        Matrix input = new Matrix(1, grid.length);
        for (int i = 0; i < grid.length; i++) {
            input.setAt(0, i, grid[i]);
        }
        Matrix result = neuralNetwork.predict(input);
        logger.log(Level.INFO, "result: " + result);
        int max = 0;
        for (int i = 0; i < result.getColumns(); i++) {
            if (result.getAt(0, i) > result.getAt(0, max)) {
                max = i;
            }
        }
        return max;
    }

    private static void learn() {
        System.out.println("Learning...");
        neuralNetwork.learn(NumberRecognition.idealInputs, NumberRecognition.idealOutputs);
        try {
            neuralNetwork.serialize(neuralNetworkFileName);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Done! Saved to the file.");
    }

    private static int[] getGrid() {
//        if (true) return new int[]{
//                1, 1, 0,
//                0, 0, 1,
//                0, 0, 1,
//                1, 0, 0,
//                1, 1, 1};
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
                "1. Learn the network\n" + "2. Guess a number");
        String choice = scanner.nextLine();
        while (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice)) {
            choice = scanner.nextLine();
        }
        System.out.println("Your choice: " + choice);
        return Integer.parseInt(choice);
    }
}