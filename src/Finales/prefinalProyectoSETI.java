package Finales;

public class prefinalProyectoSETI {
    final static int MAXC = 20, MAXF = 5;
    final static int P = 2,K = 2;
    final static char L = 'F';

    public static void main(String args[]){
        //char matrix [][] = new char [MAXF][MAXC];
        char[][] matriz = {
                {' ', '1','2','G','H',' ','2','A','3',' ','3','R','B','J',' ','6','5','K',' ',' '},
                {' ', ' ','2','1','4','5',' ','R','P',' ','D','3',' ','7','M','N','W',' ',' '},
                {' ', '4','G','8',' ','3','5','7','1',' ',' ','2','X',' ','D','4','1',' ',' '},
                {' ', ' ','5','T','T','M',' ','A','P','1','1',' ','2','1','3',' ','1','3',' '},
                {' ', ' ','6','6','4',' ','5','4',' ','A','2','1','2',' ','G','4','2','1','8',' '}
        };
        recorrerFilas(matriz);

    }

    private static void recorrerFilas(char[][] matriz) {
        int cantidadSeniales, cantidadTotalSeniales = 0,filaConsecutiva = 0,fila = 0;
        boolean encontro = false;

        while(fila < MAXF && !encontro){
            cantidadSeniales = buscarIntensidadMayorAL(matriz[fila]);
            if(cantidadSeniales >= K){
                cantidadTotalSeniales += cantidadSeniales;
                filaConsecutiva++; // SI LA FILA QUE ANALIZO CUMPLIO, SUMO 1
            }else {
                cantidadTotalSeniales = 0;
                filaConsecutiva = 0;
            }
            if(filaConsecutiva == P){
                System.out.println("La cantidad de señales fue de: " + cantidadTotalSeniales);
                encontro = true;
            }
            fila++;
        }
    }

    private static int buscarIntensidadMayorAL(char[] fila) {
        int pos = 0,inicio,fin,contadorSenial = 0,contadorTotalSenial = 0;
        while(pos < MAXC){
            inicio = buscarInicio(fila,pos);
            if(inicio < MAXC){
              fin = buscarFin(fila,inicio);
              contadorSenial = senialSuperiorAL(fila,inicio,fin);
              if(contadorSenial >= K){
                  contadorTotalSenial += contadorSenial;
              }
              pos = fin+1;
            }
            pos = MAXC;
        }
        return contadorTotalSenial;
    }

    private static int senialSuperiorAL(char[] fila, int inicio, int fin) {
        int contador = 0;

        while(inicio <= fin){
            if(fila[inicio] >= L){
                contador++;
            }
            inicio++;
        }
        return contador;
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
}
