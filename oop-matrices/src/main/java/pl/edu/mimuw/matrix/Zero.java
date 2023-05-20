package pl.edu.mimuw.matrix;

public class Zero extends Flat implements IDoubleMatrix {

    public Zero(Shape shape) {
        super(shape, 0);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rozmiar macierzy: [ " + shape.rows + ", " + shape.columns + " ]\n");
        for (int i = 0; i < shape.rows; i++) {
            if (shape.columns >= 3) {
                str.append("0 ... 0\n");
            } else {
                String space = "";
                for (int j = 0; j < shape.columns; j++) {
                    str.append(space + "0");
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
        return DoubleMatrixFactory.zero(Shape.matrix(shape.rows, other.shape().columns));
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        return DoubleMatrixFactory.zero(shape);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());

        return other;
    }
}
