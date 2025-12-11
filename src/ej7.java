public class ej7 {
    final static int MAXC = 20;
    final static int MAXF = 3;
    final static int X = 3;

    public static void main(String[] args){
        int[][] matriz = {
                {0, -8, 67, 0, 14, 0, -4, 33, 0, 5, 98, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 25, 25, 0, -5, 3, 0, 25, 44, 44, 0, -4, 1, 0, 0, 0, 0, 0, 0},
                {0, 44, 44, 44, 0, -7, 15, 0, -4, 9, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        imprimirMatriz(matriz);
        recorrerFilas(matriz);
        imprimirMatriz(matriz);
    }

    private static void recorrerFilas(int[][] matriz) {
        int pixelesDescomprimidos = 0, totalPixelesDescomprimidos = 0, cantActual=0, filaMayor=0;
        for (int fila = 0; fila < MAXF; fila++) {
           pixelesDescomprimidos = descomprimirImagenes(matriz[fila]);
           if (cantActual < pixelesDescomprimidos){
               cantActual = pixelesDescomprimidos;
               filaMayor = fila;
           }
           totalPixelesDescomprimidos += pixelesDescomprimidos;
        }

        System.out.println("La cantidad de pixeles descomprimidos fue de " + totalPixelesDescomprimidos);
        System.out.println("La fila que mas descompresiones tuvo fue la fila " + filaMayor);

    }

    private static int descomprimirImagenes(int[] arrFila) {
        int pos = 0, inicio,fin,descomprimidos = 0;

        while (pos < MAXC){
            inicio = buscarInicio(arrFila,pos);
            if (inicio < MAXC){
                fin = buscarFin(arrFila,inicio);
                if (arrFila[inicio] < 0){
                    //int valor = abs(arrFila[inicio]);
                    //pasamos el valor a positivo
                    int valor = arrFila[inicio];
                    valor = -valor;
                    //contamos la cantidad de descomprimidos que tuvo esa fila
                    descomprimidos += valor;
                    if (valor > X){
                        arrFila[inicio] = arrFila[inicio+1];
                        agregarSec(arrFila,inicio,valor);
                        pos = inicio + valor;
                    }else{
                        pos = fin + 1;
                    }
                }else{
                    pos = fin + 1;
                }
            }else{
                pos = MAXC;
            }
        }
        return descomprimidos;
    }


    private static void agregarSec(int[] arrFila, int inicio, int cant) {
        for (int i = 0; i<cant-2; i++){
            corrimientoDerecha(arrFila,inicio);
        }
    }

    private static void corrimientoDerecha(int[] arrFila, int inicio) {
        for (int i = MAXC-1; i >inicio; i--) {
            arrFila[i] = arrFila[i-1];
        }
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
