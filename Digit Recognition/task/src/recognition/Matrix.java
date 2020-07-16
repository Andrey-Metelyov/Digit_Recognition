package recognition;

public class Matrix {
    private final double[][] data;

    Matrix(int rows, int columns) {
        data = new double[rows][columns];
    }
    Matrix(double[][] data) {
        this.data = data;
    }
    public double getAt(int row, int column) {
        return data[row][column];
    }
    public void setAt(int row, int column, double value) {
        data[row][column] = value;
    }
    public int getRows() {
        return data.length;
    }
    public int getColumns() {
        return data != null ? data[0].length : 0;
    }

    public Matrix multiply(Matrix other) {
        assert this.getColumns() == other.getRows();
        int rows = this.getRows();
        int columns = other.getColumns();
        Matrix result = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                double sum = 0.0;
                for (int r = 0; r < this.getColumns(); r++) {
                    sum += this.getAt(i, r) * other.getAt(r, j);
                }
                result.setAt(i, j, sum);
            }
        }
        return result;
    }

    public Matrix multiply(double number) {
        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setAt(i, j, getAt(i, j) * number);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                sb.append(getAt(i, j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        double[][] data = {
                {1,2,3,4},
                {5,6,7,8},
                {9,0,1,2}
        };
        double[][] data2 = {
                {1,2,3,4},
                {5,6,7,8},
                {9,0,1,2},
                {3,4,5,6}
        };
        Matrix m = new Matrix(data);
        System.out.println(m.toString());
        Matrix n = new Matrix(data2);
        System.out.println(n.toString());
        System.out.println(m.multiply(n).toString());
        System.out.println(m.multiply(2));
        System.out.println(n.multiply(0));
    }
}
