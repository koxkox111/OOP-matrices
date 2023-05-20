package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class Row extends Full implements IDoubleMatrix {
    private final double[] rowValue;

    public Row(Shape shape, double[] rowValue) {
        super(new double[0][0], shape);
        this.rowValue = rowValue.clone();
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);

        return rowValue[column];
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[shape.rows][shape.columns];

        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                ret[i][j] = rowValue[i];
            }
        }
        return ret;
    }

    @Override
    public double normInfinity() {

        double ret = 0;
        for (int i = 0; i < shape.columns; i++) {
            ret += Math.abs(rowValue[i]);
        }

        return ret;

    }

    @Override
    public double normOne() {
        double maxValue = 0;
        for (int i = 0; i < shape.columns; i++) {
            maxValue = Math.max(Math.abs(rowValue[i]), maxValue);
        }
        return maxValue * shape.rows;
    }

    @Override
    public double frobeniusNorm() {
        double ret = 0;
        for (int i = 0; i < shape.columns; i++) {
            ret += Math.abs(rowValue[i] * rowValue[i]);
        }
        return Math.sqrt(ret * shape.rows);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;
        Shape newShape = Shape.matrix(shape.rows, other.shape().columns);
        double[] ret = new double[newShape.columns];

        for (int i = 0; i < shape.columns; i++) {
            for (int j = 0; j < other.shape().columns; j++) {
                ret[j] += rowValue[i] * other.get(i, j);
            }
        }
        return DoubleMatrixFactory.row(newShape, ret);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[] ret = Arrays.copyOf(rowValue, rowValue.length);

        for (int i = 0; i < ret.length; i++) {
            ret[i] *= scalar;
        }
        return DoubleMatrixFactory.row(shape, ret);
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        double[] ret = Arrays.copyOf(rowValue, rowValue.length);

        for (int i = 0; i < ret.length; i++) {
            ret[i] += scalar;
        }
        return DoubleMatrixFactory.row(shape, ret);
    }

    @Override
    public IDoubleMatrix minus(double scalar) {
        return plus(-scalar);
    }
}
