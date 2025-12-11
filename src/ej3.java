public class ej3 {
    final static int MAXC = 20;
    final static int MAXF = 4;

    public static void main(String[] args){
        char[][] matriz = {
                {' ','e','l',' ','a','g','e','n','t','e',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ','J','a','m','e','s',' ','B','o',' ','s','e',' ',' ',' ',' ',' ',' ',' ',' '},
                {' ','e','n','c','u','e','n','t','r','a',' ','e','n',' ',' ',' ',' ',' ',' ',' '},
                {' ','C','o','l','o','n','i','a',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
        };
        imprimirMatriz(matriz);
        recorrerFilas(matriz);
        imprimirMatriz(matriz);
    }
    private static void recorrerFilas(char[][] matriz) {
        int cont = 0;
        for(int fila = 0; fila < MAXF; fila++){
            cont += buscarNombrePropio(matriz[fila]);
        }
        System.out.println("La cantidad de secuencias incriptadas fue de " + cont);
    }

    private static int buscarNombrePropio(char[] fila) {
        int inicio = 0,fin = -1,contador = 0;
        while(inicio < MAXC){
            inicio = obtenerInicio(fila,fin+1);
            if(inicio < MAXC){
                fin = obtenerFin(fila,inicio);
                if(esMayuscula(fila,inicio)){
                    contador++;
                    invertirSec(fila,inicio,fin);
                    duplicarVocales(fila, inicio, fin);
                    fin = inicio - 1;

                }else{
                    inicio = fin+1;
                }
            }else{
                inicio = MAXC;
            }
        }
        return contador;
    }
    private static void duplicarVocales(char[] fila, int inicio, int fin) {
        int pos = inicio;
        while (pos <= fin && fin < MAXC - 1) {  // mientras haya espacio
            if (esVocal(fila, pos)) {
                corrimientoDerecha(fila, pos);  // hace lugar a la derecha de 'pos'
                pos += 2;                       // salto la vocal original + su copia
                fin++;                          // la secuencia ahora es 1 más larga
            } else {
                pos++;
            }
        }
    }

    private static void invertirSec(char[] fila, int inicio, int fin) {
        char aux;
        while (inicio < fin) {
            aux = fila[inicio];
            fila[inicio] = fila[fin];
            fila[fin] = aux;
            inicio++;
            fin--;
        }
    }

    private static void corrimientoDerecha(char[] fila, int pos) {
        // corre TODO uno a la derecha desde el final hasta pos+1
        for (int i = MAXC - 1; i > pos; i--) {
            fila[i] = fila[i - 1];
        }
        // en pos+1 queda la misma letra que había en pos (porque la corrimos)
        // no hace falta asignar nada extra
    }

    public static boolean esVocal(char [] arr,int pos){
        return arr[pos] == 'a' || arr[pos] == 'e' || arr[pos] == 'i' || arr[pos] == 'o' || arr[pos] == 'u';
    }

    public static boolean esMayuscula(char [] arr,int pos){
        return arr[pos] >= 'A' && arr[pos] <= 'Z';
    }

    private static int obtenerFin(char[] arr, int pos) {
        while (pos < MAXC && arr[pos] != ' '){
            pos++;
        }
        return pos-1;
    }

    private static int obtenerInicio(char[] arr, int pos) {
        while (pos < MAXC && arr[pos] == ' '){
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
