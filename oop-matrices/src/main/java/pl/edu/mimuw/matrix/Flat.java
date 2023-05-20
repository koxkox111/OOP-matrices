package pl.edu.mimuw.matrix;

public class Flat extends Full implements IDoubleMatrix {
    protected final double value;

    public Flat(Shape shape, double value) {
        super(new double[0][0], shape);
        this.value = value;
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return value;
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                ret[i][j] = value;
            }
        }
        return ret;
    }

    @Override
    public double normOne() {
        return shape.columns * Math.abs(value);
    }

    @Override
    public double normInfinity() {
        return shape.rows * Math.abs(value);
    }

    @Override
    public double frobeniusNorm() {
        return Math.sqrt(shape.rows * shape.columns * Math.abs(value * value));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        for (int i = 0; i < shape.rows; i++) {
            if (shape.columns >= 3) {
                str.append(value + " ... " + value + "\n");
            } else {
                String space = "";
                for (int j = 0; j < shape.columns; j++) {
                    str.append(space + value);
                    space = " ";
                }
                str.append("\n");
            }
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
                for (int k = 0; k < shape.columns; k++) {
                    ret[i][j] += get(i, k) * other.get(k, j);
                }
            }
        }
        return DoubleMatrixFactory.full(ret);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        return DoubleMatrixFactory.flat(shape, scalar * value);
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        return DoubleMatrixFactory.flat(shape, value + scalar);
    }

    @Override
    public IDoubleMatrix minus(double scalar) {
        return DoubleMatrixFactory.flat(shape, value - scalar);
    }
}
