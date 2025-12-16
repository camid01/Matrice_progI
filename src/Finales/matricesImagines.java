package Finales;

import java.io.BufferedReader;
import java.io.InputStreamReader;

    /*
     * ENUNCIADO:
     * 1. Se desea implementar un algoritmo para verificar la similitud entre dos matrices que representan imágenes.
     * Ambas matrices están conformadas por secuencias de enteros separadas por valores 0.
     *
     * Dos imágenes son similares si el grado de similitud entre ambas es mayor a un valor S (valor real entre 0 y 1),
     * ingresado por el usuario.
     *
     * El grado de similitud se mide en base a la cantidad de secuencias iguales que comparten ambas matrices.
     * Para que una secuencia s1 de la imagen 1 sea igual a una secuencia s2 de la imagen 2, deben
     * coincidir tanto el contenido de la secuencia como la posición de la misma (inicio y fin deben coincidir en el
     * número de fila y columna).
     *
     * Dada la matriz IMG1 y la matriz IMG2.
     *
     * Para un grado de similitud S=0.75 se debe informar que ambas imágenes son similares, dado que ambas
     * matrices tienen 10 secuencias, de las cuales 8 son iguales.
     * Las secuencias 5 3 4 y 4 3 1 de la matriz IMG1 no se encuentran en IMG2 (en la misma posición y contenido).
     * Por lo tanto, el valor de referencia es 8/10 y es mayor a S=0.75.
     *
     * IMPORTANTE: El ejemplo es meramente ilustrativo, la solución planteada debe ser válida para cualquier par de matrices
     * IMG1 e IMG2. Aplicar TODAS las buenas prácticas vistas en la materia. No usar estructuras auxiliares.
     */

public class matricesImagines{
        public static final int MAXF = 5;
        public static final int MAXC = 16;

        public static void main(String[] args) {

            // MATRIZ IMG 1
            int[][] img1 = {
                    {0, 0, 2, 0, 1, 2, 0, 1, 2, 1, 3, 0, 5, 3, 4, 0},
                    {0, 9, 7, 8, 9, 2, 3, 0, 2, 3, 6, 7, 8, 9, 0, 0},
                    {0, 0, 0, 4, 3, 1, 0, 0, 0, 0, 0, 1, 9, 7, 0, 0},
                    {0, 8, 9, 7, 8, 9, 2, 3, 6, 7, 8, 9, 4, 3, 8, 0},
                    {0, 1, 2, 1, 3, 1, 1, 1, 2, 3, 1, 1, 9, 0, 0, 0}
            };

            // MATRIZ IMG 2
            int[][] img2 = {
                    {0, 0, 2, 0, 1, 2, 0, 1, 2, 1, 3, 0, 4, 2, 1, 0},
                    {0, 9, 7, 8, 9, 2, 3, 0, 2, 3, 6, 7, 8, 9, 0, 0},
                    {0, 0, 0, 4, 3, 1, 5, 0, 0, 0, 0, 1, 9, 7, 0, 0},
                    {0, 8, 9, 7, 8, 9, 2, 3, 6, 7, 8, 9, 4, 3, 8, 0},
                    {0, 1, 2, 1, 3, 1, 1, 1, 2, 3, 1, 1, 9, 0, 0, 0}
            };

            System.out.println("--- IMAGEN 1 ---");
            imprimirMatriz(img1);
            System.out.println("\n--- IMAGEN 2 ---");
            imprimirMatriz(img2);

            double valorS = obtenerValorS();
            procesarFila(img1,img2,valorS);


        }

    private static void procesarFila(int[][] img1, int[][] img2, double valorS) {
        int totalSecuenciasImg1 = 0;
        int totalSecuenciasIguales = 0;

        for (int fila = 0; fila < MAXF; fila++) {
            // Usamos Img1 como referencia para contar el total de secuencias
            //cuenta el total de secuencias de mat1
            totalSecuenciasImg1 += contarSecuencias(img1[fila]);

            // Contamos cuántas de esas coinciden en Img2 (se fija si coinciden y retorna la cantidad total de coincidencias)
            totalSecuenciasIguales += contarCoincidenciasFila(img1[fila], img2[fila]);
        }

        double gradoSimilitud = (double) totalSecuenciasIguales / totalSecuenciasImg1;

        System.out.println("Total secuencias: " + totalSecuenciasImg1);
        System.out.println("Total iguales: " + totalSecuenciasIguales);
        System.out.println("Grado calculado: " + gradoSimilitud);

        if (gradoSimilitud > valorS) {
            System.out.println("RESULTADO: Las imágenes SON similares.");
        } else {
            System.out.println("RESULTADO: Las imágenes NO son similares.");
        }
    }

    private static int contarCoincidenciasFila(int[] fila1, int[] fila2) {
        int coincidencias = 0;
        int pos = 0;

        while (pos < MAXC ) {
            // 1. Buscamos dónde empieza la secuencia en CADA fila
            // (Usamos la misma 'pos' de búsqueda para las dos)
            int inicio1 = buscarInicio(fila1, pos);
            int inicio2 = buscarInicio(fila2, pos);

            // 2. Si alguna de las dos ya no tiene secuencias, cortamos
            if (inicio1 < MAXC && inicio2 < MAXC) {

                // 3. Buscamos dónde terminan
                int fin1 = buscarFin(fila1, inicio1);
                int fin2 = buscarFin(fila2, inicio2);

                // 4. LA MAGIA: Comparamos COORDENADAS
                // ¿Empiezan en el mismo lugar? Y ¿Terminan en el mismo lugar?
                if (inicio1 == inicio2 && fin1 == fin2) {

                    // Recién acá, si las coordenadas son idénticas, miramos el contenido
                    if (sonIguales(fila1, fila2, inicio1, fin1)) {
                        coincidencias++;
                    }
                }

                if (fin1 > fin2){
                    pos = fin1 + 1;
                }else{
                    pos = fin2 + 1;
                }

            } else {
                pos = MAXC;
            }
        }
        return coincidencias;
    }

    private static int contarSecuencias(int[] fila) {
        int contador = 0;
        int pos = 0;
        while (pos < MAXC) {
            int inicio = buscarInicio(fila, pos);
            if (inicio < MAXC) {
                contador++;
                int fin = buscarFin(fila, inicio);
                pos = fin + 1;
            } else {
                pos = MAXC;
            }
        }
        return contador;
    }

    private static boolean sonIguales(int[] filaImg1, int[] filaImg2, int inicio, int fin) {
        boolean iguales = true;
        int i = inicio;
        while (i <= fin && iguales) {
            if (filaImg1[i] != filaImg2[i]) {
                iguales = false;
            }
            i++;
        }
        return iguales;
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

    private static double obtenerValorS() {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            double valor = 0.0;
            try {
                System.out.print("Ingrese el valor de similitud S (entre 0 y 1, ej: 0.75): ");
                valor = Double.parseDouble(entrada.readLine());
            } catch (Exception e) {
                System.out.println("Error de entrada: " + e);
            }
            return valor;
        }

        public static void imprimirMatriz(int[][] mat) {
            for (int i = 0; i < MAXF; i++) {
                System.out.print("|");
                for (int j = 0; j < MAXC; j++) {
                    System.out.print(mat[i][j] + "|");
                }
                System.out.println();
            }
        }
}
