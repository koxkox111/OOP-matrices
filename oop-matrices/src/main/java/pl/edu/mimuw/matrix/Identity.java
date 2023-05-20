package pl.edu.mimuw.matrix;

public class Identity extends Diagonal implements IDoubleMatrix {

    public Identity(int size) {
        super(Shape.matrix(size, size));
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        if (row == column) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public double normOne() {
        return 1;
    }

    @Override
    public double normInfinity() {
        return 1;
    }

    @Override
    public double frobeniusNorm() {
        return Math.sqrt(size);
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
            str.append("1" + " ");
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
        return other;
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[] ret = new double[shape.rows];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = scalar;
        }
        return DoubleMatrixFactory.diagonal(ret);
    }
}
