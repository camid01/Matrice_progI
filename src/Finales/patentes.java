package Finales;

public class patentes {
    final static int MAXA = 24;

    public static void main(String[] args) {
        char[] arrI = {
                ' ', 'A', 'A', '1', '2', '3', 'Z', 'Z', ' ', 'P', 'R', 'G', '0', '1', '0', ' ', 'A', 'B', '9', '8', '7', 'E', 'X', ' '
        };

        char[] arrE = {
                ' ', 'A', 'B', '9', '8', '7', 'E', 'X', ' ', 'A', 'A', '1', '2', '3', 'Z', 'Z', ' ', 'F', 'O', 'R', '0', '0', '1', ' '
        };

        // --- ARREGLOS DE RESULTADOS (Vacíos) ---
        char[] arrNoE = new char[MAXA];
        char[] arrNoI = new char[MAXA];
        System.out.println("--- PROCESANDO ---");
        imprimirArr(arrI);
        System.out.println();
        imprimirArr(arrE);



        // 1. Recorro I, busco en E. Si no está, guardo en arrNoE
        procesarPatentes(arrI, arrE, arrNoE);

        // 2. Recorro E, busco en I. Si no está, guardo en arrNoI
        procesarPatentes(arrE, arrI, arrNoI);


        System.out.println("\nRESULTADOS:");
        System.out.print("No Egresaron (arrNoE): ");
        imprimirArr(arrNoE); // Debería salir PRG010
        System.out.println();

        System.out.print("No Ingresaron (arrNoI): ");
        imprimirArr(arrNoI); // Debería salir FOR001
        System.out.println();
    }

    private static void procesarPatentes(char[] origen ,char[] comparacion, char[] destino) {
        int inicio,fin,pos = 0, posCopia=1;
        while (pos < MAXA){
            inicio = buscarInicio(origen,pos);
            if(inicio < MAXA){
                fin = buscarFin(origen,inicio);
                int tamanioSec = obtenerTamanio(inicio,fin);
                boolean existe = existePatente(origen,comparacion,inicio,fin);
                if(!existe){
                    copiarPatente(origen,inicio,fin,destino,posCopia);
                    posCopia+=tamanioSec+1;
                }
                pos = fin +1;
            }else{
                pos = MAXA;
            }
        }

    }

    private static boolean existePatente(char[] origen, char[] comparacion, int inicioOrigen, int finOrigen) {
        boolean encontrado = false;
        int inicio,fin,pos = 0;
        while (pos < MAXA){
            inicio = buscarInicio(comparacion,pos);
            if(inicio < MAXA){
                fin = buscarFin(comparacion,inicio);
                int tamanioOrigen = obtenerTamanio(inicioOrigen,finOrigen);
                int tamanioComparacion = obtenerTamanio(inicio,fin);
                if(tamanioComparacion == tamanioOrigen){
                    if (sonIguales(origen, inicioOrigen,finOrigen, comparacion, inicio,finOrigen)) {
                        encontrado = true;
                    }
                }
                pos = fin +1;
            }else{
                pos = MAXA;
            }
        }
        return encontrado;
    }

//    private static boolean sonIgualess(char[] arr1, int ini1, char[] arr2, int ini2, int largo) {
//
//        int pos = 0; // Solo para controlar que no nos pasemos del largo
//        boolean sonIguales = true;
//
//        while (pos < largo && sonIguales) {
//            if (arr1[ini1] != arr2[ini2]) {
//                sonIguales = false;
//
//            } else {
//                // ¡IMPORTANTE!
//                // Como ya comparamos estas letras, tenemos que AVANZAR los inicios
//                // para que en la próxima vuelta apunten a la siguiente letra.
//                ini1++;
//                ini2++;
//                pos++; // Avanzamos el contador de vueltas
//            }
//        }
//        return sonIguales;
//    }

    private static boolean sonIguales(char[] arr1, int ini1, int fin1, char[] arr2, int ini2, int fin2) {
        boolean sonIguales = true;

        while (ini1 <= fin1 && sonIguales) {
            if (arr1[ini1] != arr2[ini2]) {
                sonIguales = false;
            } else {
                ini1++;
                ini2++;
            }
        }
        return sonIguales;
    }

    private static int obtenerTamanio(int i , int f){
        return (f-i)+1;
    }

    private static void copiarPatente(char[] origen, int inicio, int fin, char[] destino, int posCopia) {
        while (inicio <= fin){
            destino[posCopia] = origen[inicio];
            inicio++;
            posCopia++;
        }

    }

    private static int buscarFin ( char[] fila, int pos){
        while (pos < MAXA && fila[pos] != ' ') {
            pos++;
        }
        return pos - 1;
    }

    private static int buscarInicio ( char[] fila, int pos){
        while (pos < MAXA && fila[pos] == ' ') {
            pos++;
        }
        return pos;
    }

    public static void imprimirArr(char [] mat) {
        for (int j = 0; j < MAXA; j++) {
            System.out.print("[" + mat[j] + "]");
        }
    }
}
