package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values){
	assert shape != null;
	assert values.length >= 0;
	for(int i = 0 ; i < values.length ; i++) {
		shape.assertInShape(values[i].row, values[i].column);
	}
    return new Sparse(shape,values);
  }

  public static IDoubleMatrix full(double[][] values) {
	  assert values != null;
	  assert values.length > 0;
	  assert values[0] != null;
	  for(int i = 1 ; i < values.length ; i++) {
		  assert values[i] != null && values[i].length == values[i-1].length;
	  }
	  return new Full(values);
  }

  public static IDoubleMatrix identity(int size) {
	assert size > 0;
    return new Identity(size);
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
	assert diagonalValues.length > 0;
    return new Diagonal(diagonalValues);
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
	assert antiDiagonalValues.length > 0;
    return new AntiDiagonal(antiDiagonalValues);
  }

  public static IDoubleMatrix vector(double... values){
	  assert values.length > 0;
    return new Vector(values);
  }

  public static IDoubleMatrix zero(Shape shape) {
	  assert shape != null;
    return new Zero(shape);
  }
  
  public static IDoubleMatrix column(Shape shape, double... columnValues) {
	  assert shape != null;
	  assert columnValues.length > 0;
	  return new Column(shape, columnValues);
  }
  public static IDoubleMatrix row(Shape shape, double... rowValues) {
	  assert shape != null;
	  assert rowValues.length > 0;
	  return new Row(shape, rowValues);
  }
  public static IDoubleMatrix flat(Shape shape, double value) {
	  assert shape != null;
	  return new Flat(shape, value);
  }
}
