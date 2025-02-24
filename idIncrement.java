public class idIncrement {
    public static void main(String args[]) {
        char[][] compare = new char[10][6]; // 10 rows, 6 columns

        // First pattern (Top half)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) { // Fix column indexing
                compare[i][j] = (char) (i + j); // Convert numbers to characters
            }
        }

        // Second pattern (Bottom half)
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 6; j++) { // Fix column indexing
                compare[9 - i][j] = (char)((i + 6) - 1 - j); // Reverse order
            }
        }

        // Displaying the 2D char array
        for (char[] row : compare) {
            for (char cas : row) {
                System.out.print(cas + " ");
            }
            System.out.println();
        }
    }
}
