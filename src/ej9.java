
public class ej9 {
    static final int MAXF = 3;
    static final int MAXC = 15;
    static final int X = 600;
    static final int N = 3;
    static final int SEPARADOR = 0;

    public static void main(String[] args) {
        int[][] matrizVentas = {
                {0, 0, 150, 200, 165, 0, 154, 352, 240, 256, 0, 900, 750, 0, 0},
                {0, 940, 105, 265, 845, 215, 0, 245, 765, 348, 0, 741, 125, 541, 0},
                {0, 851, 543, 625, 845, 914, 0, 754, 184, 452, 637, 917, 0, 0, 0}
        };
        int[] ArrA1 = {1,2,0};
        int[] ArrA2 = {3,0,0};
        System.out.println("Matriz:");
        imprimirMat(matrizVentas);
        System.out.println("ArrA1");
        imprimirArr(ArrA1);
        System.out.println();
        System.out.println("ArrA2");
        imprimirArr(ArrA2);
        System.out.println();
        
        procesarFila(matrizVentas,ArrA1,ArrA2);
    }

    private static void procesarFila(int[][] matriz, int[] arrA1, int[] arrA2) {
        int mesActual; double promedioVentas;
        for (int fila = 0; fila < MAXF; fila++) {
            mesActual = fila + 1;
            if (estaEnArrA1(arrA1,mesActual)){
                promedioVentas = promedioVentasMayorImporte(matriz[fila]);
                System.out.println("para el mes " + mesActual + " se informa que el promedio es $" + promedioVentas);
            }else{
                if(promedioSuperiorAX(matriz[fila])){
                    System.out.println("Para el mes " + mesActual + " dado un X = " + X + "el promedio fue superior.");
                }else {
                    System.out.println("Para el mes " + mesActual  + " el promedio diario de ventas no fue superior dado un X = " + X);
                }
            }
        }
    }


    private static double promedioVentasMayorImporte(int[] arrFila) {
        int pos =0, inicio,fin,totalDeDias = 0;
        double ventaMayor=0;
        while (pos < MAXC){
            inicio = obtenerInicioSec(arrFila,pos);
            if (inicio<MAXC){
                fin = obtenerFinSec(arrFila,inicio);
                totalDeDias++;
                ventaMayor += obtenerVentaMayorImporte(arrFila,inicio,fin);
                pos = fin +1;
            }else{
                pos = MAXC;
            }
        }

        return  ventaMayor/totalDeDias;
    }

    private static double obtenerVentaMayorImporte(int[] arrFila, int inicio, int fin) {
        double ventaMayor=0;
        while (inicio <= fin){
            if (ventaMayor < arrFila[inicio]){
                ventaMayor = arrFila[inicio];
            }
            inicio++;
        }

        return ventaMayor;
    }

    private static boolean promedioSuperiorAX(int[] arrFila) {
        int pos =0, inicio,fin,contMayor = 0,contSec=0;
        boolean esMayor = true;
        while (pos < MAXC){
            inicio = obtenerInicioSec(arrFila,pos);
            if (inicio<MAXC){
                fin = obtenerFinSec(arrFila,inicio);
                contSec++;
                double promedio = obtenerPromedio(arrFila,inicio,fin);
                if (promedio>X){
                    contMayor++;
                }
                pos = fin +1;
            }else{
                pos = MAXC;
            }
        }

        if (contMayor == contSec){
            return esMayor;
        }else{
            return !esMayor;
        }

    }

    private static double obtenerPromedio(int[] arrFila, int inicio, int fin) {
        double suma = 0;
        int cantidad = fin - inicio+1;
        while (inicio <= fin){
            suma+=arrFila[inicio];
            inicio++;
        }
        System.out.println(suma);
        return suma/cantidad;
    }

    private static int obtenerFinSec(int[] arrFila, int pos) {
        while (pos < MAXC && arrFila[pos] != SEPARADOR){
            pos++;
        }
        return pos-1;
    }

    private static int obtenerInicioSec(int[] arrFila, int pos) {
        while (pos < MAXC && arrFila[pos] == SEPARADOR){
            pos++;
        }
        return pos;
    }

    private static boolean estaEnArrA1(int[] arrA1, int mesActual) {
        int pos = 0;
        boolean encontre = false;
        while (pos < N && !encontre){
            if (arrA1[pos] == mesActual){
                encontre = true;
            }else{
                pos++;
            }
        }
        return encontre;
    }


    public static void imprimirMat(int [][]mat) {
        for (int i = 0; i < MAXF; i++) {
            for (int j = 0; j < MAXC; j++) {
                System.out.print("[" + mat[i][j] + "]");
            }
            System.out.println();
        }
    }
    public static void imprimirArr(int [] mat) {
            for (int j = 0; j < N; j++) {
                System.out.print("[" + mat[j] + "]");
            }
    }
}
