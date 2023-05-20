package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class Diagonal extends Full implements IDoubleMatrix {
    protected final int size;
    protected final double[] diagonalValue;

    public Diagonal(double[] diagonalValue) {
        super(new double[0][0], Shape.matrix(diagonalValue.length, diagonalValue.length));
        size = diagonalValue.length;
        this.diagonalValue = diagonalValue.clone();
    }

    public Diagonal(Shape shape) {
        super(new double[0][0], shape);
        size = shape.rows;
        diagonalValue = new double[0];
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        if (row == column) {
            return diagonalValue[row];
        } else {
            return 0;
        }
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[size][size];
        for (int i = 0; i < size; i++) {
            ret[i][i] = get(i, i);
        }
        return ret;
    }

    @Override
    public double normOne() {
        double ret = 0;
        for (int i = 0; i < size; i++) {
            ret = Math.max(ret, Math.abs(diagonalValue[i]));
        }
        return ret;
    }

    @Override
    public double normInfinity() {
        return normOne();
    }

    @Override
    public double frobeniusNorm() {
        double ret = 0;
        for (int i = 0; i < size; i++) {
            ret += Math.abs(diagonalValue[i] * diagonalValue[i]);
        }
        return Math.sqrt(ret);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        for (int i = 0; i < shape.rows; i++) {
            if (i < 3) {
                for (int k = 0; k < i; k++) {
                    str.append("0 ");
                }
            } else {
                str.append("0 ... 0 ");
            }
            str.append(diagonalValue[i] + " ");
            if (shape.rows - 1 - i < 3) {
                for (int k = 0; k < shape.rows - 1 - i; k++) {
                    str.append("0 ");
                }
            } else {
                str.append("0 ... 0 ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;
        double[][] ret = new double[shape.rows][other.shape().columns];

        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < other.shape().columns; j++) {
                ret[i][j] = diagonalValue[i] * other.get(i, j);
            }
        }
        return DoubleMatrixFactory.full(ret);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[] ret = Arrays.copyOf(diagonalValue, diagonalValue.length);
        for (int i = 0; i < diagonalValue.length; i++) {
            ret[i] *= scalar;
        }

        return DoubleMatrixFactory.diagonal(ret);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());

        double[][] ret = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                double temp = other.get(i, j);
                ret[i][j] = temp + get(i, j);
            }
        }
        return DoubleMatrixFactory.full(ret);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());

        double[][] ret = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                double temp = other.get(i, j);
                ret[i][j] = get(i, j) - temp;
            }
        }
        return DoubleMatrixFactory.full(ret);
    }
}
