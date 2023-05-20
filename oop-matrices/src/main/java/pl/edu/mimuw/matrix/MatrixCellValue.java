package pl.edu.mimuw.matrix;

public final class MatrixCellValue {

    public final int row;
    public final int column;
    public final double value;

    public MatrixCellValue(int row, int column, double value) {
        this.column = column;
        this.row = row;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + value + " @[" + row + ", " + column + "]}";
    }

    public static MatrixCellValue cell(int row, int column, double value) {
        assert row >= 0;
        assert column >= 0;
        return new MatrixCellValue(row, column, value);
    }
}
