public class ej5 {
    final static int MAXC = 20;
    final static int MAXF = 3;
    final static int X = 3;
    final static int BYTE = 4;

    public static void main(String[] args){
        int[][] matriz = {
                {0, 67, 67, 67, 67, 67, 67, 67, 67, 0, 0, 33, 33, 33, 33, 0, 0, 0, 0, 0},
                {0, 23, 45, 45, 45, 45, 23, 0, 88, 88, 88, 88, 88, 0, 0, 78, 78, 0, 0, 0},
                {0, 0, 0, 45, 45, 45, 45, 45, 0, 0, 0, 45, 0, 45, 45, 0, 45, 0, 0, 0}
        };
        imprimirMatriz(matriz);
        recorrerFilas(matriz);
        imprimirMatriz(matriz);
    }

    private static void recorrerFilas(int[][] matriz) {
        int ahorroTotalEnteros = 0;
        int maxAhorroFila = 0;
        int filaMasComprimida = 0;

        for (int fila = 0; fila < MAXF; fila++) {
            int ahorroFila = buscarSecuencia(matriz[fila]);
            ahorroTotalEnteros += ahorroFila;

            if (ahorroFila > maxAhorroFila) {
                maxAhorroFila = ahorroFila;
                filaMasComprimida = fila;
            }
        }

        int ahorroTotalBytes = ahorroTotalEnteros * BYTE;

        System.out.println("El ahorro de almacenamiento es " + ahorroTotalEnteros + " enteros.");
        System.out.println("Se ahorraron en total " + ahorroTotalBytes + " bytes.");
        System.out.println("La fila más comprimida fue la fila " + filaMasComprimida);
    }

    private static int buscarSecuencia(int[] fila) {
        int pos = 0, inicio, fin;
        int ahorroEnteros = 0;

        while (pos < MAXC) {
            inicio = buscarInicio(fila, pos);
            if (inicio < MAXC) {
                fin = buscarFin(fila, inicio);
                int tam = obtenerTamanioSec(inicio, fin);

                if (secElementosIguales(fila, inicio, fin) && tam > X) {
                    //ahorro de almacenamiento
                    ahorroEnteros += (tam - 2);
                    comprimirSec(fila, inicio, fin);
                    fila[inicio] = -tam;
                    pos = inicio;
                } else {
                    pos = fin + 1;
                }
            } else {
                pos = MAXC;
            }
        }
        return ahorroEnteros;
    }

    private static boolean secElementosIguales(int[] fila, int inicio, int fin) {
        boolean sonIguales = true;
        while (inicio<fin && sonIguales){
            if (fila[inicio]!=fila[inicio+1]){
                sonIguales = false;
            }else{
                inicio++;
            }
        }
        return sonIguales;
    }

    private static void comprimirSec(int[] fila, int inicio, int fin) {
        int cant = fin - inicio +1;
        for(int i = 0; i < cant-2; i++){
            corrimientoIzquierda(fila,inicio);
        }
    }

    private static void corrimientoIzquierda(int[] fila, int inicio) {
        for(;inicio < MAXC-1; inicio++){
            fila[inicio] = fila[inicio+1];
        }
        fila[MAXC-1] = 0;
    }

    private static int obtenerTamanioSec(int inicio, int fin){
        return fin - inicio +1;
    }

    private static int buscarFin(int[] fila, int pos) {
        while(pos < MAXC && fila[pos] != 0){
            pos++;
        }
        return pos-1;
    }

    private static int buscarInicio(int[] fila, int pos) {
        while(pos < MAXC && fila[pos] == 0){
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
