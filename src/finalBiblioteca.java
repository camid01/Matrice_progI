public class finalBiblioteca {
    final static int MAXF = 3;  // 3 Hileras (Temáticas)
    final static int MAXC = 20; // 20 Espacios por estantería

    public static void main(String[] args) {
        // Matriz de libros (Los negativos son prestados)
        int[][] matrizLibros = {
                {0, 12, 15, -18, 0, 5, -55, 63, 88, 0, 0, -74, -99, 0, 0, 11, 25, 64, 0, 0},
                {0, 0, 2, -6, 52, 0, 0, -85, 87, 89, 0, 0, 1, 10, 20, -30, 0, 0, 0, 0},
                {0, 8, -23, 24, 33, 84, -98, 0, 0, 21, 22, -34, -36, -44, 0, 4, 13, 26, 0, 0}
        };

        // Arreglo de tematicas (Indice 0 -> Fila 0, etc)
        char[] tematicas = {'A', 'C', 'T'};

        int codigLibro = 100;
        int estanteria = 2;
        char letraTematica = 'C';
        int codigoSiFuePrestado = -98;

        System.out.println("--- Matriz de Libros Cargada ---");
        imprimirMatriz(matrizLibros);
        recorrerFilas(matrizLibros,codigLibro,estanteria,letraTematica,tematicas,codigoSiFuePrestado);
        System.out.println();
        imprimirArr(tematicas);
        System.out.println();
        imprimirMatriz(matrizLibros);
    }

    private static void recorrerFilas(int[][] matrizLibros, int codigLibro, int estanteria, char letraTematica,char [] tematicas, int codigoSiFuePrestado) {
        int fila = 0, contPrestados = 0, cantidadMayorPrestados = 0, filaMayor=0;

        while(fila < MAXF){
            contPrestados = contadorDeLibrosPrestados(matrizLibros[fila]);

            if (existeElLibro(matrizLibros[fila],codigoSiFuePrestado)){
                if(codigoSiFuePrestado < 0){
                    System.out.println("El libro con el codigo " + codigoSiFuePrestado + " fue prestado");
                }else{
                    System.out.println("El libro con el codigo " + codigoSiFuePrestado + " no fue prestado");
                }
            }
            if(tematicas[fila] == letraTematica){
                registrarLibro(matrizLibros[fila],codigLibro,estanteria);
            }
            if (cantidadMayorPrestados < contPrestados){
                cantidadMayorPrestados = contPrestados;
                filaMayor = fila;
            }
            fila++;
        }
        System.out.println("La estanteria que contiene mas libros prestados es la estanteria : " + filaMayor + " donde su tematica es " + tematicas[filaMayor] );
    }

    private static boolean existeElLibro(int[] matrizLibro, int codigoSiFuePrestado) {
        int pos = 0;
        boolean existe = false;
        while (pos<MAXC-1 && !existe){
            if (matrizLibro[pos] == codigoSiFuePrestado){
                existe = true;
            }
            pos++;
        }
        return existe;
    }

    private static int contadorDeLibrosPrestados(int[] fila) {
        int contador = 0, i = 0;

        while(i < MAXC-1){
            if(fila[i] < 0){
                contador++;
            }
            i++;
        }
        return contador;
    }

    private static void registrarLibro(int[] arrFila, int codigLibro, int estanteria) {
        int inicio ,fin, pos =0, contEstanteria=0;
        while (pos < MAXC){
            inicio = buscarInicio(arrFila,pos);
            if (inicio<MAXC){
                fin = buscarFin(arrFila,inicio);
                contEstanteria++;

                if (contEstanteria == estanteria){
                    int posLibro = buscarLugarParaInsertarLibro(arrFila,codigLibro,inicio,fin);
                    corrimientoDerecha(arrFila,posLibro);
                    arrFila[posLibro] = codigLibro;
                }else{
                    pos = fin +1;
                }

            }else{
                pos = MAXC;
            }
        }

    }

    private static int buscarLugarParaInsertarLibro(int[] arrFila, int codigLibro, int inicio, int fin) {
        int posicion = inicio;
        boolean encontrado = false;

        while(posicion <= fin && !encontrado) {
            if (codigLibro < Math.abs(arrFila[posicion])) {
                encontrado = true;
            } else {
                posicion++;
            }
        }
        return posicion;
    }

    private static void corrimientoDerecha(int[] arrFila, int posLibro) {
        for (int i = MAXC-1; i > posLibro; i--){
            arrFila[i] = arrFila[i-1];
        }
    }

    private static int buscarFin(int[] arrFila, int pos) {
        while(pos < MAXC && arrFila[pos] != 0){
            pos++;
        }
        return pos-1;
    }

    private static int buscarInicio(int[] arrFila, int pos) {
        while(pos < MAXC && arrFila[pos] == 0){
            pos++;
        }
        return pos;
    }

    public static void imprimirMatriz(int[][] mat) {
        for (int i = 0; i < MAXF; i++) {
            for (int j = 0; j < MAXC; j++) {
                System.out.print("[" + mat[i][j] + "]");
            }
            System.out.println();
        }
    }

    public static void imprimirArr(char [] mat) {
        for (int j = 0; j < MAXF; j++) {
            System.out.print("[" + mat[j] + "]");
        }
    }
}