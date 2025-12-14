  /*
   * Para desplazar un caracter n posiciones,
   * puedo sumarle a un char la cantidad de posiciones que quiero desplazarlo.
   *  char c = ‘a’; c = (char)(c + 5); // c pasa a almacenar ‘f’
   * */

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
        int cantPalabrasEncriptadas = recorrerFilas(matriz);
        System.out.println();
        imprimirMat(matriz);
        System.out.println();
        System.out.println("La cantidad de palabras encriptadas fue de: " + cantPalabrasEncriptadas);
    }

    private static int recorrerFilas(char[][] matriz) {
        int cantPalabrasEncriptadas = 0;
        for (int fila = 0; fila < MAXF; fila++) {
            cantPalabrasEncriptadas += procesarSec(matriz[fila]);
        }
        return  cantPalabrasEncriptadas;
    }

    private static int procesarSec(char[] arrFila) {
        int pos = 0, inicio, fin, palabras=0, nuevoFin=0;
        while (pos < MAXC){
            inicio = buscarInicio(arrFila,pos);
            if (inicio < MAXC){
                fin = buscarFin(arrFila,inicio);
                int tamanioSec = fin - inicio +1;
                //palabra = secuencia (encriptar una palabra seria encriptar la sec entera)
                palabras++;

                nuevoFin = encriptarPalabra(arrFila, inicio, fin, tamanioSec);


                pos = nuevoFin + 1;

            }else{
                pos = MAXC;
            }
        }
        return palabras;
    }

    private static int encriptarPalabra(char[] arrFila, int inicio, int finActual, int tamanioOriginal) {
        int pos = inicio;
        while (pos <= finActual && finActual < MAXC) {

            if (esVocal(arrFila[pos])) {
                // Vocal: Desplazar (sumar largo)
                arrFila[pos] = (char) (arrFila[pos] + tamanioOriginal);
                pos++;
            } else {
                // Consonante: Duplicar
                corrimientoDerecha(arrFila, pos + 1);
                arrFila[pos + 1] = arrFila[pos];
                finActual++;
                pos += 2;
            }
        }
        return finActual;
    }

    private static void corrimientoDerecha(char[] arrFila, int inicio) {
        for (int i = MAXC-1; i > inicio; i--) {
            arrFila[i] = arrFila[i-1];
        }

    }

    private static boolean esVocal(char letra) {
        return (letra == 'a') || (letra == 'e') || (letra == 'i') || (letra == 'o')  || (letra == 'u');
    }

    private static int buscarFin(char[] arrFila, int inicio) {
        while (inicio < MAXC && !esSeparador(arrFila[inicio])) {
            inicio++;
        }
        return inicio-1;
    }

    private static int buscarInicio(char[] arrFila, int pos) {
        while (pos < MAXC && esSeparador(arrFila[pos]) ) {
            pos++;
        }
        return pos;
    }

    private static boolean esSeparador(char c) {
        // Verificamos si es letra (mayúscula o minúscula)
        boolean esMayuscula = (c >= 'A' && c <= 'Z');
        boolean esMinuscula = (c >= 'a' && c <= 'z');

        // Si NO es mayúscula Y TAMPOCO es minúscula, entonces es un separador
        return !(esMayuscula || esMinuscula);
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
