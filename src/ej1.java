public class ej1 {
    final static int MAXFIL = 3;
    final static int MAXCOL= 20;
    final static int MAXB = 3;
    final static int R= 2;

    public static void main(String[] args){
        char matriz [][] = {
                {'x','t','a','C','M','O','t','a','a','t','O','C','t','t','a','O','M','C','t','x'},
                {'x','r','r','r','C','C','O','O','r','r','C','r','G','G','G','r','r','x','x','x'},
                {'x','m','G','m','h','h','L','G','G','O','h','h','m','m','O','B','M','C','x','x','x'}
        };
        char B[] = {'C','O','L'};
        imprimirMatriz(matriz);
        recorreFilas(matriz,B);
        imprimirMatriz(matriz);
    }

    private static void recorreFilas(char[][] matriz, char [] arr ) {
       int total = 0;
        for (int fila = 0; fila < MAXFIL; fila++){
            total += procesarSec(matriz[fila], arr);
        }
        System.out.println("El total eliminado es: " + total);
    }

    private static int procesarSec(char[] arr,char[] arrB ) {
        int pos = 0, inicio, fin, cantMalezas = 0;

        while (pos < MAXCOL) {
            inicio = obtenerInicio(arr, pos);

            if (inicio < MAXCOL) {
                fin = obtenerFin(arr, inicio);
                int tamanio = obtenerTamanioSec(inicio, fin);

                if (tamanio > R) {
                    int i = inicio;
                    while (i <= fin) {
                        if (esPlantaMalisiosa(arr[i], arrB)) {
                            cantMalezas = cantMalezas +1;
                            eliminarLetra(arr, i);
                            fin--; // la secuencia ahora es una posición más chica
                            // NO incremento i, porque ahora en arr[i] hay un nuevo carácter
                        } else {
                            i++; // solo avanzo si no eliminé
                        }
                    }
                }
                // Tanto si eliminé como si no, salto a la próxima zona
                pos = fin + 1;

            } else {
                // corto el while
                pos = MAXCOL;
            }
        }
        return cantMalezas;
    }

    private static void eliminarLetra(char[] arr,int pos) {
      for (int i = pos; i<MAXCOL-1; i++){
          arr[i] = arr[i+1];
      }
      arr[MAXCOL-1] = 'x';
    }


    private static boolean esPlantaMalisiosa(char letra, char[] arrB) {
        boolean esMaliciosa = true;
        int pos = 0;

        while (pos < MAXB && esMaliciosa){
            if(letra == arrB[pos]){
                esMaliciosa = false;
            }
            else{
                pos++;
            }
        }
        return esMaliciosa;
    }

    private static int obtenerTamanioSec(int inicio , int fin) {
        return fin - inicio +1;
    }

    private static int obtenerFin(char[] arr, int pos) {
        while ((pos < MAXCOL) && (arr[pos] >= 'A' && arr[pos] <= 'Z')){
            pos++;
        }
        return pos-1;
    }

    private static int obtenerInicio(char[] arr, int pos) {
        while (pos < MAXCOL && (!(arr[pos] >= 'A' && arr[pos] <= 'Z'))){
            pos++;
        }
        return pos;
    }
    public static void imprimirMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }
}