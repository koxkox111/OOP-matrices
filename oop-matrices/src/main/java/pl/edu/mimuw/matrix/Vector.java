package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class Vector extends Full implements IDoubleMatrix {
    private final int size;
    private final double[] values;

    public Vector(double[] values) {
        super(new double[0][0], Shape.vector(values.length));
        size = values.length;
        this.values = values.clone();
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return values[row];
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[size][1];

        for (int i = 0; i < size; i++) {
            ret[i][0] = values[i];
        }

        return ret;
    }

    @Override
    public double normOne() {
        double ret = 0;
        for (int i = 0; i < size; i++) {
            ret += Math.abs(values[i]);
        }

        return ret;
    }

    @Override
    public double normInfinity() {
        double ret = 0;
        for (int i = 0; i < size; i++) {
            ret = Math.max(ret, Math.abs(values[i]));
        }

        return ret;
    }

    @Override
    public double frobeniusNorm() {
        double ret = 0;
        for (int i = 0; i < size; i++) {
            ret += Math.abs(values[i] * values[i]);
        }

        return Math.sqrt(ret);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        for (int i = 0; i < size; i++) {
            str.append(values[i] + "\n");
        }
        return str.toString();
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[] ret = Arrays.copyOf(values, values.length);
        for (int i = 0; i < ret.length; i++) {
            ret[i] *= scalar;
        }
        return new Vector(ret);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        return plus(other, 1);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        assert other != null;
        return plus(other, -1);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other, int scalar) {
        assert other != null;
        assert shape.equals(other.shape());
        double[] ret = Arrays.copyOf(values, values.length);
        for (int i = 0; i < ret.length; i++) {
            ret[i] += (other.get(i, 0) * scalar);
        }
        return DoubleMatrixFactory.vector(ret);
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        double[] ret = Arrays.copyOf(values, values.length);
        for (int i = 0; i < ret.length; i++) {
            ret[i] += scalar;
        }
        return DoubleMatrixFactory.vector(ret);
    }

    @Override
    public IDoubleMatrix minus(double scalar) {
        return plus(-scalar);
    }
}
