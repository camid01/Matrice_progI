public class ej2 {
    final static int MAXFIL = 7;
    final static int MAXCOL = 20;
    final static int MAXARR = 7;
    final static double P = 0.5;

    public static void main(String[] args){
        int[][] matriz = {
                {0, 300, 298, 298, 297, 0, 240, 233, 245, 240, 0, 257, 254, 254, 0, 234, 230, 222, 0, 0},
                {0, 310, 302, 284, 271, 0, 280, 263, 263, 0, 0, 0, 264, 264, 0, 234, 230, 0, 0, 0},
                {0, 310, 302, 294, 0, 0, 250, 243, 0, 245, 0, 257, 255, 253, 0, 234, 229, 0, 0, 0},
                {0, 315, 320, 395, 398, 0, 351, 333, 353, 0, 0, 0, 334, 354, 0, 454, 490, 499, 0, 0},
                {0, 410, 400, 397, 0, 0, 250, 243, 0, 0, 0, 257, 255, 253, 0, 234, 229, 220, 0, 0},
                {0, 0, 420, 430, 430, 450, 420, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 415, 425, 435, 420, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int diasProgresivos [] = {1,2,5,0,0,0,0};//ASUMIMOS QUE SIEMPRE VA A IR DE MENOR A MAYOR
        int total = contarDiasProgresivos(diasProgresivos);
        mostrarArreglo(diasProgresivos);
        imprimirMatriz(matriz);
        recorrerFilas(matriz,diasProgresivos);
        mostrarArreglo(diasProgresivos);
        imprimirMatriz(matriz);
        cumplioConPorcentaje(diasProgresivos,total);
    }

    private static void cumplioConPorcentaje(int[] diasProgresivos, int total) {
        int diasQueNoCumplieron = contarDiasProgresivos(diasProgresivos);

        double diasQueSI= total - diasQueNoCumplieron;
        double porcentajeTotal = diasQueSI / total;

        if(porcentajeTotal >= P){
            System.out.println("Para un porcentaje de "  + P +" el atleta cumplio con el " + porcentajeTotal + " porciento");
        }else{
            System.out.println("El atleta no cumplio con el porcentaje requerido");
        }
    }

    private static int contarDiasProgresivos(int[] diasProgresivos) {
        int i = 0,cant = 0;
        while(i < MAXARR && (diasProgresivos[i] != 0)){
            cant++;
        i++;
        }
        return cant;
    }

    private static void recorrerFilas(int[][] matriz, int[] diasProgresivos) {
        for (int fila = 0; fila < MAXFIL; fila++) {
            int diaActual = fila + 1;  // fila 0 → día 1, fila 1 → día 2, etc.

            if (estaEnArreglo(diaActual, diasProgresivos)) {
                procesarDiaProgresivo(matriz[fila],diaActual,diasProgresivos);
            }
        }

    }
    private static boolean estaEnArreglo(int dia, int[] dias) {
        int pos = 0;
        boolean encontrado = false;

        while (pos < MAXARR && !encontrado && dias[pos] != 0) {
            if (dias[pos] == dia) {
                encontrado = true;
            } else {
                pos++;
            }
        }
        return encontrado;
    }

    private static void procesarDiaProgresivo(int[] fila,int diaActual,int [] dias) {
        int inicio = 0,fin = -1,conSec = 0,cont = 0;
        while(inicio < MAXCOL){
            inicio = buscarInicio(fila,fin+1);
            if(inicio < MAXCOL){
                fin = buscarFin(fila,inicio);
                conSec++;
                if(secEsProgresiva(fila,inicio,fin)) {
                    cont++;
                }
            }
        }
        if(conSec == cont){
            eliminoDiaDeArreglo(dias,diaActual);
        }
    }

    private static void eliminoDiaDeArreglo(int[] dias, int diaActual) {
        int i = 0;
        while (i < MAXARR) {
            if(dias[i] == diaActual){
                corrimientoIzquierda(dias,i);
            }
            i++;
        }
    }

    private static void corrimientoIzquierda(int[] dias, int i) {
        for(;i < MAXARR - 1;i++){
            dias[i] = dias[i+1];
        }
        dias[MAXARR - 1] = 0;
    }

    private static boolean secEsProgresiva(int [] fila,int inicio, int fin) {
        boolean progresiva = false;
        while(inicio < fin && !progresiva){
            if(fila[inicio] > fila[inicio+1]){
                progresiva = true;
            }
            inicio++;
        }
        return progresiva;
    }

    private static int buscarFin(int[] arr, int pos) {
        while ((pos < MAXCOL) && (arr[pos] != 0)){
            pos++;
        }
        return pos-1;
    }

    private static int buscarInicio(int[] arr, int pos) {
        while (pos < MAXCOL && (arr[pos] == 0)){
            pos++;
        }
        return pos;
    }

    private static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }
    private static void mostrarArreglo(int[] arr){
        for(int pos = 0; pos < MAXARR; pos++){
            System.out.println("arreglo["+pos+"] - "+ arr[pos]);
        }
    }
}
