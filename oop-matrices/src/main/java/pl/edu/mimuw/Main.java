package pl.edu.mimuw;

import pl.edu.mimuw.matrix.DoubleMatrixFactory;
import pl.edu.mimuw.matrix.IDoubleMatrix;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

public class Main {

    public static void main(String[] args) {
        IDoubleMatrix macierzId = DoubleMatrixFactory.identity(10);
        System.out.println(macierzId);
        IDoubleMatrix macierzDiag = DoubleMatrixFactory.diagonal(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(macierzDiag);
        IDoubleMatrix macierzAnti = DoubleMatrixFactory.antiDiagonal(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(macierzAnti);
        IDoubleMatrix macierzCol = DoubleMatrixFactory.column(Shape.matrix(10, 10), 1, 2, 3, 4, 5,
                6, 7, 8, 9, 10);
        System.out.println(macierzCol);
        IDoubleMatrix macierzRow = DoubleMatrixFactory.row(Shape.matrix(10, 10), 1, 2, 3, 4, 5, 6,
                7, 8, 9, 10);
        System.out.println(macierzRow);
        IDoubleMatrix macierzFlat = DoubleMatrixFactory.flat(Shape.matrix(10, 10), 2);
        System.out.println(macierzFlat);
        IDoubleMatrix macierzZero = DoubleMatrixFactory.zero(Shape.matrix(10, 10));
        System.out.println(macierzZero);

        MatrixCellValue cell1 = MatrixCellValue.cell(1, 2, 3);
        MatrixCellValue cell2 = MatrixCellValue.cell(2, 5, 1);
        MatrixCellValue cell3 = MatrixCellValue.cell(7, 2, -1);
        IDoubleMatrix macierzSparse = DoubleMatrixFactory.sparse(Shape.matrix(10, 10), cell1, cell2,
                cell3);
        System.out.println(macierzSparse);

        IDoubleMatrix macierzVector = DoubleMatrixFactory.vector(2, 1, 3, 7, 4, 2, 0, 2, 1, 1);
        System.out.println(macierzVector);

        double[][] temp = new double[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                temp[i][j] = (i / 2 + j * 2 + 3) * (i * i - j);
            }
        }
        IDoubleMatrix macierzFull = DoubleMatrixFactory.full(temp);
        System.out.println(macierzFull);

    }
}
