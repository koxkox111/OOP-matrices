package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class AntiDiagonal extends Full implements IDoubleMatrix {
    private final double[] diagonalValue;
    private final int size;

    public AntiDiagonal(double[] diagonalValue) {
        super(new double[0][0], Shape.matrix(diagonalValue.length, diagonalValue.length));
        this.diagonalValue = diagonalValue.clone();
        size = diagonalValue.length;
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        if (row + column == size - 1) {
            return diagonalValue[row];
        } else {
            return 0;
        }
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[size][size];
        for (int i = 0; i < size; i++) {
            ret[i][size - i - 1] = diagonalValue[i];
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        for (int i = 0; i < shape.rows; i++) {
            if (shape.rows - 1 - i < 3) {
                for (int k = 0; k < shape.rows - 1 - i; k++) {
                    str.append("0 ");
                }
            } else {
                str.append("0 ... 0 ");
            }
            str.append(diagonalValue[i] + " ");
            if (i < 3) {
                for (int k = 0; k < i; k++) {
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
    public IDoubleMatrix times(double scalar) {
        double[] ret = Arrays.copyOf(diagonalValue, diagonalValue.length);
        for (int i = 0; i < diagonalValue.length; i++) {
            ret[i] *= scalar;
        }

        return DoubleMatrixFactory.antiDiagonal(ret);
    }
}
