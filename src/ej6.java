public class ej6 {
    final static int MAXC = 20;
    final static int MAXF = 3;
    public static void main(String[] args) {
        char[][] matriz = {
                {'-', 'S', 'u', 'p', 'e', 'r', ' ', 's', 'e', 'c', 'r', 'e', 't', 'o', ':', '-', '-', '-', '-', '-'},
                {'|', '|', 'n', 'o', ' ', 'c', 'o', 'r', 't', 'a', 'r', ' ', 'f', 'o', 'r', ' ', ' ', ' ', ' ', ' '},
                {' ', 'c', 'o', 'n', ' ', 'r', 'e', 't', 'u', 'r', 'n', '!', '!', '!', '!', '!', '!', '!', '!', '!'}
        };
        imprimirMat(matriz);
    }


    public static void imprimirMat(char [][]mat) {
        for (int i = 0; i < MAXF; i++) {
            for (int j = 0; j < MAXC; j++) {
                System.out.print("[" + mat[i][j] + "]");
            }
            System.out.println();
        }
    }
}
