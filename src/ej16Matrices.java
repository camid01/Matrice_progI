/*
* b. Para MAT1 y MAT2 elimine de cada secuencia el primer carácter vocal.
* c. Para MAT1 agregue al principio de cada secuencia el primer carácter de la secuencia de mayor tamaño de dicha fila. Considere agregar un espacio
* al final de la fila para mantener la estructura de secuencia delimitada por separadores espacio.
* d. En cada fila, si se verifica que la secuencia de mayor tamaño de la fila para MAT1 es mayor que la primera secuencia en dicha fila para MAT2,
* las intercambie (la que está en MAT1 pasa a MAT2 y la que está en MAT2 pasa a MAT1) sin usar estructuras auxiliares (otros arreglos o matrices).
* e. Para un valor de fila ingresado por el usuario verifique e imprima si la primera secuencia de MAT1 en dicha fila es igual a la primera secuencia de MAT2 en dicha fila.
*
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ej16Matrices {
    final static int MAXF = 3;
    final static int MAXC = 20;

        public static void main(String[] args) {

            char[][] mat1 = {
                    {' ', 'a', 'u', 't', 'o', 's', ' ', 'r', 'o', 'j', 'o', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'e', 'l', 'e', 'f', 'a', 'n', 't', 'e', ' ', 's', 'i', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'i', 's', 'l', 'a', ' ', 'm', 'a', 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
            };

            char[][] mat2 = {
                    {' ', 'a', 'u', 't', 'o', 's', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'a', 'v', 'i', 'o', 'n', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'o', 's', 'o', ' ', 'p', 'e', 'z', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
            };

            int valorFila = obtenerValor();


            System.out.println("--- MATRIZ 1 (Original) ---");
            imprimirMatriz(mat1);

            System.out.println("\n--- MATRIZ 2 (Original) ---");
            imprimirMatriz(mat2);


            procesarFilas(mat1,mat2,valorFila);

            System.out.println();
            System.out.println("--- MATRIZ 1 (MODIFICADA) ---");
            imprimirMatriz(mat1);

            System.out.println("\n--- MATRIZ 2 (MODIFICADA) ---");
            imprimirMatriz(mat2);
        }


    private static int obtenerValor() {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int valor = 0;
        boolean esValido = false;

        while (!esValido) {
            try {
                System.out.println("Ingrese una fila (entre 0 y " + (MAXF - 1) + "):");
                valor = Integer.parseInt(entrada.readLine());

                if (valor >= 0 && valor < MAXF) {
                    esValido = true;
                } else {
                    System.out.println("Error: La fila no existe. Debe ser menor a " + MAXF);
                }

            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un número entero.");
            }
        }
        return valor;
    }

    private static void procesarFilas(char[][] mat1, char[][] mat2, int valorFila) {
        int indice = 0, fila = 0;
        for (; fila < MAXF; fila++) {

            // --- PUNTO B ---
            eliminarPrimeraVocalDeLaFila(mat1[fila]);
            eliminarPrimeraVocalDeLaFila(mat2[fila]);

            // --- PUNTO C ---
            indice = obtenerIndiceSecMayor(mat1[fila]);
            if (indice != -1) {
                char letra = mat1[fila][indice];
                agregarLetraEnTodaLaSecuencia(mat1[fila], letra);
            }

            // --- PUNTO D ---
            // Recalculamos el índice porque al agregar letras, todo se movio
            indice = obtenerIndiceSecMayor(mat1[fila]);
            intercambiarSecuencias(mat1[fila], mat2[fila], indice);

            //--- PUNTO E---
            if (vericamosSonIguales(mat1[valorFila], mat2[valorFila])){
                System.out.println("siiiiiii son iguales");
            }

        }
    }

    private static boolean vericamosSonIguales(char[] filaMat1, char[] filaMat2) {
            boolean sonIguales = true;
            int inicioA,inicioB,finA,finB;

            inicioA = buscarInicio(filaMat1,0);
            finA = buscarFin(filaMat1,inicioA);
            int tamanioA = finA-inicioA+1;

            inicioB = buscarInicio(filaMat2,0);
            finB = buscarFin(filaMat2,inicioB);
            int tamanioB = finB-inicioB+1;

            if(tamanioA != tamanioB){
                sonIguales = false;
            }
            return sonIguales;
    }

    // Para el Punto B
    private static void eliminarPrimeraVocalDeLaFila(char[] fila) {
        int pos = 0;
        while (pos < MAXC) {
            int inicio = buscarInicio(fila, pos);
            if (inicio < MAXC) {
                int fin = buscarFin(fila, inicio);
                boolean borre = false;
                int k = inicio;
                while (k <= fin && !borre) {
                    if (esVocal(fila[k])) {
                        corrimientoIzquierda(fila, k);
                        borre = true;
                        fin--;
                    }
                    k++;
                }
                pos = fin + 1;
            } else {
                pos = MAXC;
            }
        }
    }

    private static boolean esVocal(char c) {
        char min = Character.toLowerCase(c);
        return min == 'a' || min == 'e' || min == 'i' || min == 'o' || min == 'u';
    }

    private static void intercambiarSecuencias(char[] arrFilaA, char[] arrFilaB, int indiceMat1) {
        int inicio,fin,inicioB,finB;

        inicioB = buscarInicio(arrFilaB,0);
        finB = buscarFin(arrFilaB,inicioB);
        int tamanioSecB = finB - inicioB +1;

            inicio = indiceMat1;
            if (inicio<MAXC){
                fin = buscarFin(arrFilaA,inicio);
                int tamanioSecA = fin - inicio + 1;
                System.out.println("tam a" + tamanioSecA + " tamb " + tamanioSecB);
                if (tamanioSecA == tamanioSecB){
                    System.out.println("entro al intercambiar igual tamanip");
                    intercambiar(arrFilaA,arrFilaB,inicio,inicioB,fin,finB);
                }
                //sabemos que en mat1 > sec en mat2
                if (tamanioSecA > tamanioSecB){
                    int diferencia = tamanioSecA - tamanioSecB;
                    for (int i = 0; i<diferencia; i++){
                        corrimientoDerecha(arrFilaB,finB);
                    }
                    // 2. Intercambiamos
                    // finB ahora cambio porque hicimos corrimiento, pero para intercambiar
                    // usamos los tamaños originales o actualizamos índices.
                    // Truco: intercambiamos hasta el largo de la MAS LARGA (que ahora ambas tienen espacio)
                    intercambiar(arrFilaA, arrFilaB, inicio, inicioB, tamanioSecA, tamanioSecA);

                    // 3. Achicamos MAT1
                    // Borramos desde donde termina la NUEVA palabra chica (inicio + tamanioSecB)
                    eliminarEspacio(arrFilaA, diferencia, tamanioSecA - diferencia);

                }
            }
    }



    private static void eliminarEspacio(char[] arrFilaA, int diferencia, int finA) {
        for (int i=0; i<diferencia; i++){
            corrimientoIzquierda(arrFilaA,finA);
        }
    }

    private static void corrimientoIzquierda(char[] arrFilaA, int finA) {
        for(int i = finA; i < MAXC - 1; i++){
            arrFilaA[i] = arrFilaA[i+1];
        }

        arrFilaA[MAXC-1] = ' ';
    }

    private static void corrimientoDerecha(char[] arrFilaB, int finB) {
        for(int i = MAXC-1; i > finB; i--){
            arrFilaB[i] = arrFilaB[i-1];
        }
    }

    private static void intercambiar(char[] arrMatA, char[] arrMatB, int inicio, int inicioB, int fin, int finB) {

        while (inicio<=fin && inicioB<=finB){
            char aux = arrMatA[inicio];
            arrMatA[inicio] = arrMatB[inicioB];
            arrMatB[inicioB] = aux;
            inicio++;
            inicioB++;
        }
    }

    private static void agregarLetraEnTodaLaSecuencia(char[] arrFila, char letra) {
        int pos = 0, inicio,fin;
        while (pos < MAXC){
            inicio = buscarInicio(arrFila,pos);
            if (inicio<MAXC){
                fin = buscarFin(arrFila,inicio);
                // 1. PRIMERO HACEMOS LUGAR (Empujamos desde el inicio de la palabra)
                corrimientoDerecha(arrFila, inicio);
                // 2. AHORA ESCRIBIMOS LA LETRA
                arrFila[inicio] = letra;
                // 3. Ajustamos indices (como agregamos una letra, todo se movió 1 lugar)
                fin++;
                pos = fin +1;
            }else{
                pos = MAXC;
            }
        }
    }

    private static int obtenerIndiceSecMayor(char[]arrFila) {
            int pos = 0, inicio,fin, tamanioMayor = 0, indiceMayor = 0;
            while (pos < MAXC){
                inicio = buscarInicio(arrFila,pos);
                if (inicio<MAXC){
                    fin = buscarFin(arrFila,inicio);

                    int tamanio = fin - inicio +1;
                    if (tamanioMayor < tamanio){
                        tamanioMayor = tamanio;
                        indiceMayor = inicio;
                    }
                    pos = fin +1;
                }else{
                    pos = MAXC;
                }

            }
            return indiceMayor;
    }


    private static int buscarFin ( char[] fila, int pos){
        while (pos < MAXC && fila[pos] != ' ') {
            pos++;
        }
        return pos - 1;
    }

    private static int buscarInicio ( char[] fila, int pos){
        while (pos < MAXC && fila[pos] == ' ') {
            pos++;
        }
        return pos;
    }

    private static void imprimirMatriz(char[][] matriz) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.print("[" + matriz[i][j] + "]");
                }
                System.out.println();
            }
        }
}
