package recognition;

public class NumberRecognition {
    final static Neuron[][] idealOutputNeurons = getNeurons(new int[][]{
            {1,0,0,0,0, 0,0,0,0,0},
            {0,1,0,0,0, 0,0,0,0,0},
            {0,0,1,0,0, 0,0,0,0,0},
            {0,0,0,1,0, 0,0,0,0,0},
            {0,0,0,0,1, 0,0,0,0,0},
            {0,0,0,0,0, 1,0,0,0,0},
            {0,0,0,0,0, 0,1,0,0,0},
            {0,0,0,0,0, 0,0,1,0,0},
            {0,0,0,0,0, 0,0,0,1,0},
            {0,0,0,0,0, 0,0,0,0,1},
    });
    final static Neuron[][] idealInputNeurons = getNeurons(new int[][]{
            {       1, 1, 1,
                    1, 0, 1,
                    1, 0, 1,
                    1, 0, 1,
                    1, 1, 1 },
            {       0, 1, 0,
                    0, 1, 0,
                    0, 1, 0,
                    0, 1, 0,
                    0, 1, 0 },
            {       1, 1, 1,
                    0, 0, 1,
                    1, 1, 1,
                    1, 0, 0,
                    1, 1, 1 },
            {       1, 1, 1,
                    0, 0, 1,
                    1, 1, 1,
                    0, 0, 1,
                    1, 1, 1 },
            {       1, 0, 1,
                    1, 0, 1,
                    1, 1, 1,
                    0, 0, 1,
                    0, 0, 1 },
            {       1, 1, 1,
                    1, 0, 0,
                    1, 1, 1,
                    0, 0, 1,
                    1, 1, 1 },
            {       1, 1, 1,
                    1, 0, 0,
                    1, 1, 1,
                    1, 0, 1,
                    1, 1, 1 },
            {       1, 1, 1,
                    0, 0, 1,
                    0, 0, 1,
                    0, 0, 1,
                    0, 0, 1 },
            {       1, 1, 1,
                    1, 0, 1,
                    1, 1, 1,
                    1, 0, 1,
                    1, 1, 1 },
            {       1, 1, 1,
                    1, 0, 1,
                    1, 1, 1,
                    0, 0, 1,
                    1, 1, 1 }});

    private static Neuron[][] getNeurons(int[][] grid) {
        Neuron[][] result = new Neuron[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            result[i] = new Neuron[grid[i].length];
            for (int j = 0; j < grid[i].length; j++) {
                result[i][j] = new Neuron(grid[i][j]);
            }
        }
        return result;
    }
}
