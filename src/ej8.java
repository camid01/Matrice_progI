/*
*
* El gerente desea ordenar el diseño de las góndolas y para ello solicitó un programa que indique cómo quedaría la góndola si los grupos (secuencias) de productos
* que se encuentran ordenados descendentemente, se invirtieran en orden ascendente.
* Además, desea conocer en cuáles estantes al menos se reordenaron X grupos de productos.
* Además, debe informar, para un X = 2, que en el estante 2 (fila 1) hubo al menos 2 reordenamientos.
* */

public class ej8 {
    final static int MAXC = 20;
    final static int MAXF = 3;
    final static int X = 2;

    public static void main(String[] args) {
        int[][] matriz = {
                {0, 120, 250, 80, 0, 0, 620, 410, 645, 0, 0, 240, 960, 0, 0, 0, 0, 0, 0, 0},
                {0, 250, 155, 90, 85, 0, 150, 625, 0, 0, 900, 750, 225, 0, 0, 0, 0, 0, 0, 0},
                {0, 580, 550, 850, 0, 0, 220, 110, 0, 0, 150, 480, 690, 0, 0, 0, 0, 0, 0, 0}
        };
        imprimirMatriz(matriz);
        recorrerFilas(matriz);
        imprimirMatriz(matriz);
    }

    private static void recorrerFilas(int[][] matriz) {
        int reordenamientos = 0, cantActual = 0, filaMayor = 0, estante= 0,estanteMayor=0;

        for (int fila = 0; fila < MAXF; fila++) {
            estante = fila +1;
            reordenamientos = procesarSec(matriz[fila]);

            if (cantActual < reordenamientos) {
                cantActual = reordenamientos;
                filaMayor = fila;
                estanteMayor = estante;
            }
        }
        System.out.println("en el estante " + estanteMayor + " fila " + filaMayor + " hubo al menos 2 reordenamientos.");
    }

    private static int procesarSec(int[] arrFila) {
        int pos = 0, contReordenamientos=0, inicio, fin;
        while (pos < MAXC){
            inicio = buscarInicio(arrFila,pos);
            if (inicio < MAXC){
                fin = buscarFin(arrFila,inicio);
                if (tieneOrdenDescendente(arrFila,inicio,fin)){
                    contReordenamientos++;
                    invertirSec(arrFila,inicio,fin);
                }
                pos = fin +1;

            }else{
                pos = MAXC;
            }
        }
        return contReordenamientos;
    }

    private static void invertirSec(int[] arrFila, int inicio, int fin) {
        int aux;
        while(inicio < fin){
            aux = arrFila[inicio];
            arrFila[inicio] = arrFila[fin];
            arrFila[fin] = aux;
            inicio++;
            fin--;
        }
    }

    private static boolean tieneOrdenDescendente(int [] fila,int inicio,int fin) {
        boolean esDescendente = true;
        while(inicio < fin && esDescendente){
            if(fila[inicio] < fila[inicio+1]){
                esDescendente = false;
            }
            inicio++;
        }
        return esDescendente;
    }


    private static int buscarFin ( int[] fila, int pos){
        while (pos < MAXC && fila[pos] != 0) {
            pos++;
        }
        return pos - 1;
    }

    private static int buscarInicio ( int[] fila, int pos){
        while (pos < MAXC && fila[pos] == 0) {
            pos++;
        }
        return pos;
    }

    public static void imprimirMatriz ( int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }
}