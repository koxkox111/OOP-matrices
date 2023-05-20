package pl.edu.mimuw.matrix;

public class Sparse extends Full implements IDoubleMatrix {
    private final MatrixCellValue[] list;

    public Sparse(Shape shape, MatrixCellValue[] list) {
        super(new double[0][0], shape);
        this.list = list.clone();
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        for (int i = 0; i < list.length; i++) {
            if (list[i].row == row && list[i].column == column)
                return list[i].value;
        }
        return 0;
    }

    @Override
    public double[][] data() {
        double[][] ret = new double[shape.rows][shape.columns];
        for (int i = 0; i < list.length; i++) {
            ret[list[i].row][list[i].column] = list[i].value;
        }
        return ret;
    }

    @Override
    public double normOne() {
        double[] ret = new double[shape.columns];
        for (int j = 0; j < list.length; j++) {
            MatrixCellValue temp = list[j];
            ret[temp.column] += Math.abs(temp.value);
        }
        double result = 0;
        for (int i = 0; i < ret.length; i++) {
            result = Math.max(result, ret[i]);
        }
        return result;
    }

    @Override
    public double normInfinity() {
        double[] ret = new double[shape.rows];
        for (int j = 0; j < list.length; j++) {
            MatrixCellValue temp = list[j];
            ret[temp.row] += Math.abs(temp.value);
        }
        double result = 0;
        for (int i = 0; i < ret.length; i++) {
            result = Math.max(result, ret[i]);
        }
        return result;

    }

    @Override
    public double frobeniusNorm() {
        double ret = 0;
        for (int i = 0; i < list.length; i++) {
            ret += Math.abs(list[i].value * list[i].value);
        }
        return Math.sqrt(ret);
    }
}
