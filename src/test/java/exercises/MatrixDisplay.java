package exercises;


public class MatrixDisplay {
    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(i>=j)
                {
                    matrix[i][j] = i-j;
                }
                else {
                    matrix[i][j] = j-i;
                }

                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // Move to the next line after each row

        }




    }
}
