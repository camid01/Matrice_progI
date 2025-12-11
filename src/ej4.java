public class ej4 {

    //filas = ventas
    //cada sec es una hora

    static final int MAXF = 4;
    static final int MAXC = 15;
    static final int X = 2;
    public static void main(String[] args) {
        int matriz [][] = {
                {0, 625, 815, 900, 0, 562, 952, 300, 0, 365, 169, 254, 0, 0, 0},
                {0, 958, 62, 57, 221, 0, 596, 623, 600, 0, 587, 889, 984, 0, 0},
                {0, 0, 700, 257, 0, 0, 0, 854, 958, 388, 0, 954, 842, 925, 0},
                {0, 988, 899, 874, 0, 254, 258, 652, 200, 0, 568, 958, 210, 0, 0}
        };
        imprimirMatriz(matriz);
        procesarFila(matriz);
        System.out.println(" ");
    }

    private static void procesarFila(int[][] matriz) {
        int contConsecutivos = 0;
        for (int fila = 0; fila<MAXF; fila++){
            if (elPromedioDeVentasFueMayor(matriz[fila])){
                contConsecutivos++;

                if (contConsecutivos >= X) {
                    System.out.println("al menos en X días consecutivos, el promedio de ventas por hora, se incrementó crecientemente , el contador de dias fue de: " + contConsecutivos);
                }
            }
            else{
                contConsecutivos = 0;

            }
        }
    }

    private static boolean elPromedioDeVentasFueMayor(int[] arrFila) {
        int pos = 0, inicio, fin;
        double promedioActual = 0;
        boolean esPromedioMayor = true;
        while (pos < MAXC && esPromedioMayor){
            inicio = obtenerInicio(arrFila,pos);
            if (inicio < MAXC){
                fin = obtenerFin(arrFila, inicio);
                double promedio = obtenerPromedioSec(arrFila,inicio,fin);
                if (promedio > promedioActual){
                    promedioActual = promedio;
                }else{
                    esPromedioMayor = false;
                }
                pos = fin + 1;
            }else{
                pos = MAXC;
            }
        }
        return esPromedioMayor;
    }

    private static double obtenerPromedioSec(int[] arrFila, int inicio, int fin) {
        double total = 0;
        int tamanioSec = fin -inicio +1;

        while (inicio<=fin){
            total += arrFila[inicio];
            inicio++;
        }
        return total / tamanioSec;
    }

    private static int obtenerFin(int[] arr, int pos) {
        while (pos < MAXC && arr[pos] != 0){
            pos++;
        }
        return pos-1;
    }

    private static int obtenerInicio(int[] arr, int pos) {
        while (pos < MAXC && arr[pos] == 0){
            pos++;
        }
        return pos;
    }
    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }
}
