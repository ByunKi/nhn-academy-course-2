package exercise.unit_7;

public class Exercise2 {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        /* 정상적인 실행 */
        print(transpose(matrix));

        /* 비정상적인 실행 → null */
        print(transpose(null));
    }

    private static int[][] transpose(int[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException();
        }

        int[][] temp = new int[matrix.length][matrix.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[i][j] = matrix[j][i];
            }
        }

        return temp;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

    }
}
