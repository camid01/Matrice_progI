package Finales;
public class sopaDeLetras {

    // Tamaño aproximado del arreglo P según las casillas visibles
    public static final int MAXP = 22;

    // Dimensiones de la matriz M (5 filas x 10 columnas)
    public static final int MAXF = 5;
    public static final int MAXC = 10;

    public static void main(String[] args) {

        char[] P = {
                ' ', 'C', 'A', 'S', 'A', ' ', 'P', 'E', 'R', 'R', 'O', ' ', ' ', 'T', 'U', 'D', 'A', 'I', ' ', ' ', ' ', ' '
        };

        char[][] M = {
                {'P', 'P', 'G', 'C', 'I', 'Y', 'P', 'X', 'N', 'P'},
                {'M', 'C', 'A', 'S', 'A', 'C', 'S', 'I', 'A', 'S'},
                {'F', 'V', 'J', 'O', 'L', 'J', 'J', 'W', 'G', 'M'},
                {'P', 'E', 'R', 'O', 'P', 'B', 'Z', 'T', 'D', 'F'},
                {'S', 'M', 'T', 'U', 'D', 'A', 'I', 'N', 'W', 'S'}
        };

        System.out.println("--- Matriz M ---");
        imprimirMatriz(M);

        System.out.println("\n--- Arreglo P ---");
        System.out.println(new String(P));

        procesarArreglo(P,M);
        System.out.println();

    }

    private static void procesarArreglo(char[] arrP, char[][] matriz) {
        int inicio, fin, pos = 0;

        int totalGeneralPalabras = 0;

        while (pos < MAXP){
            inicio = obtenerInicio(arrP, pos);
            if (inicio < MAXP){
                fin = obtenerFin(arrP, inicio);

                //  ACUMULAMOS lo que nos devuelva procesarFilas
                totalGeneralPalabras += procesarFilas(arrP, matriz, inicio, fin);

                pos = fin + 1;
            } else {
                pos = MAXP;
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("RESULTADO FINAL: Se encontraron " + totalGeneralPalabras + " palabras en total.");
    }

    private static int procesarFilas(char[] arrP, char[][] matriz, int inicio, int fin) {
        int colEncontrada = -1;
        int contadorPalabraActual = 0;

        for (int fila = 0; fila < MAXF; fila++) {
            colEncontrada = buscoPalabra(arrP, matriz[fila], inicio, fin);

            if (colEncontrada != -1) {
                contadorPalabraActual++;
                System.out.println(" en (" + fila + "," + colEncontrada + ")");
            }
        }

        return contadorPalabraActual;
    }

    private static int buscoPalabra(char[] arrP, char[] arrFila, int inicio, int fin) {
        int pos = 0;
        int inicioPalabraEncontrada = -1;
        boolean encontre = false;

        // Mientras no llegue al final y no la haya encontrado
        while (pos < MAXC && !encontre) {
            if (arrFila[pos] == arrP[inicio]) {
                if (sonIguales(arrP, arrFila, inicio, fin, pos)) {
                    inicioPalabraEncontrada = pos;
                    encontre = true;
                }
            }
            pos++;
        }
        return inicioPalabraEncontrada;
    }

    private static boolean sonIguales(char[] arrP, char[] arrFila, int inicioP, int finP, int inicioM) {
        boolean sonIguales = true;

        // Mientras me falten letras de la palabra por revisar Y sigan coincidiendo
        while (inicioP <= finP && sonIguales) {
            // Si mi puntero de la matriz llegó al limite, no puedo seguir iterando -> Falso
            if (inicioM > MAXC-1) {
                sonIguales = false;
            } else if (arrFila[inicioM] != arrP[inicioP]) {
                sonIguales = false;
            } else {
                inicioP++;
                inicioM++;
            }
        }
        return sonIguales;
    }

    private static int obtenerFin ( char[] fila, int pos){
        while (pos < MAXP && fila[pos] != ' ') {
            pos++;
        }
        return pos - 1;
    }

    private static int obtenerInicio ( char[] fila, int pos){
        while (pos < MAXP && fila[pos] == ' ') {
            pos++;
        }
        return pos;
    }

    public static void imprimirMatriz(char[][] mat) {
        for (int i = 0; i < MAXF; i++) {
            System.out.print("|");
            for (int j = 0; j < MAXC; j++) {
                System.out.print(mat[i][j] + "|");
            }
            System.out.println();
        }
    }
}
