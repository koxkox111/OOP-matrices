package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class Column extends Full implements IDoubleMatrix {
    private final double[] columnValue;

    public Column(Shape shape, double[] columnValue) {
        super(new double[0][0], shape);
        this.columnValue = columnValue.clone();
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return columnValue[row];
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[shape.rows][shape.columns];

        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                ret[i][j] = columnValue[i];
            }
        }
        return ret;
    }

    @Override
    public double normOne() {

        double ret = 0;
        for (int i = 0; i < shape.rows; i++) {
            ret += Math.abs(columnValue[i]);
        }

        return ret;

    }

    @Override
    public double normInfinity() {
        double maxValue = 0;
        for (int i = 0; i < shape.rows; i++) {
            maxValue = Math.max(Math.abs(columnValue[i]), maxValue);
        }
        return maxValue * shape.columns;
    }

    @Override
    public double frobeniusNorm() {
        double ret = 0;
        for (int i = 0; i < shape.rows; i++) {
            ret += Math.abs(columnValue[i] * columnValue[i]);
        }

        return Math.sqrt(ret * shape.columns);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        if (shape.rows >= 3) {
            for (int i = 0; i < columnValue.length; i++) {
                str.append(columnValue[i] + " ... " + columnValue[i] + "\n");
            }
        } else {
            for (int i = 0; i < columnValue.length; i++) {
                for (int j = 0; j < shape.columns; j++) {
                    str.append(columnValue[i] + " ");
                }
                str.append("\n");
            }
        }
        return str.toString();
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[] ret = Arrays.copyOf(columnValue, columnValue.length);

        for (int i = 0; i < ret.length; i++) {
            ret[i] *= scalar;
        }
        return DoubleMatrixFactory.column(shape, ret);
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        double[] ret = Arrays.copyOf(columnValue, columnValue.length);

        for (int i = 0; i < ret.length; i++) {
            ret[i] += scalar;
        }
        return DoubleMatrixFactory.column(shape, ret);
    }

    @Override
    public IDoubleMatrix minus(double scalar) {
        return plus(-scalar);
    }

}
