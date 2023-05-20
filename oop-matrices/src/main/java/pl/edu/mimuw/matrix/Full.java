package pl.edu.mimuw.matrix;

public class Full implements IDoubleMatrix {
    protected final Shape shape;
    protected final double[][] values;

    public Full(double[][] values) {
        this.values = values.clone();
        shape = Shape.matrix(values.length, values[0].length);
    }

    public Full(double[][] values, Shape shape) {
        this.values = values.clone();
        this.shape = shape;
    }

    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return values[row][column];
    }

    public double[][] data() {
        return values;
    }

    public double normOne() {
        double ret = 0;
        for (int i = 0; i < shape.columns; i++) {
            double temp = 0;
            for (int j = 0; j < shape.rows; j++) {
                temp += Math.abs(get(j, i));
            }
            ret = Math.max(ret, temp);
        }
        return ret;
    }

    public double normInfinity() {
        double ret = 0;
        for (int i = 0; i < shape.rows; i++) {
            double temp = 0;
            for (int j = 0; j < shape.columns; j++) {
                temp += Math.abs(get(i, j));
            }
            ret = Math.max(ret, temp);
        }
        return ret;
    }

    public double frobeniusNorm() {
        double ret = 0;
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                ret += Math.abs(get(i, j) * get(i, j));
            }
        }
        return Math.sqrt(ret);
    }

    public Shape shape() {
        return shape;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        for (int i = 0; i < shape.rows; i++) {
            String space = "";
            for (int j = 0; j < shape.columns; j++) {
                str.append(space + get(i, j));
                space = " ";
            }
            str.append("\n");
        }
        return str.toString();
    }

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

    public IDoubleMatrix times(double scalar) {
        double[][] ret = new double[shape.rows][shape.columns];

        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                ret[i][j] = get(i, j) * scalar;
            }
        }
        return DoubleMatrixFactory.full(ret);
    }

    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        return plus(other, 1);
    }

    public IDoubleMatrix minus(IDoubleMatrix other) {
        assert other != null;
        return plus(other, -1);
    }

    public IDoubleMatrix plus(IDoubleMatrix other, int scalar) {
        assert other != null;
        assert shape.equals(other.shape());
        double[][] ret = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                double temp = other.get(i, j);
                ret[i][j] = get(i, j) + temp * scalar;
            }
        }
        return DoubleMatrixFactory.full(ret);
    }

    public IDoubleMatrix plus(double scalar) {
        double[][] ret = new double[shape.rows][shape.columns];
        for (int i = 0; i < shape.rows; i++) {
            for (int j = 0; j < shape.columns; j++) {
                ret[i][j] = scalar + get(i, j);
            }
        }
        return DoubleMatrixFactory.full(ret);
    }

    public IDoubleMatrix minus(double scalar) {
        return plus(-scalar);
    }
}
