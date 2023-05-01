/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodos_ordenamiento;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author sebas
 */
public class Metodos_ordenamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] arreglo = llenar_arreglo_numeros_random(arreglo_vacio(10));
        imprimir_arreglo(arreglo);

        long inicio_tiempo = System.currentTimeMillis();
        //int[] arreglo_ordenado = Ordenamiento_burbuja(arreglo);
        int[] arreglo_ordenado = ordenamiento_seleccion(arreglo);

        long fin_tiempo = System.currentTimeMillis();

        double total_tiempo_segundos = (fin_tiempo - inicio_tiempo);

        System.out.println("Timepo: " + total_tiempo_segundos);
        imprimir_arreglo(arreglo_ordenado);

    }

    /**
     *
     * @param tamanio
     * @return
     */
    public static int[] arreglo_vacio(int tamanio) {
        return new int[tamanio];
    }

    /**
     *
     * @param arreglo
     * @return
     */
    public static int[] llenar_arreglo_numeros_random(int[] arreglo) {

        Random random = new Random();
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = random.nextInt(1000) + 1;
        }
        return arreglo;

    }

    /**
     *
     * @param arreglo
     */
    public static void imprimir_arreglo(int[] arreglo) {

        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(" " + arreglo[i]);
        }
        System.out.println("");

    }

    /**
     * ** Métodos de ordenamientos de arreglos***
     */
    public static int[] Ordenamiento_burbuja(int[] arreglo) {
        int tamanio_arreglo = arreglo.length;
        for (int i = 0; i < tamanio_arreglo; i++) {
            for (int j = 1; j < tamanio_arreglo - i; j++) {
                if (arreglo[j - 1] > arreglo[j]) {
                    arreglo[j - 1] = arreglo[j - 1] + arreglo[j];
                    arreglo[j] = Math.abs(arreglo[j - 1] - arreglo[j]);
                    arreglo[j - 1] = Math.abs(arreglo[j] - arreglo[j - 1]);
                }
            }
        }

        return arreglo;
    }

    public static int[] Ordenamiento_insercion(int[] arreglo) {
        int tamanio_arreglo = arreglo.length;
        int posicion, valor_temporal;
        /*  for (int i = 1; i < tamanio_arreglo; i++) {
            posicion=i;
            while (posicion > 0 && arreglo[posicion]<arreglo[posicion-1]) {                
                 arreglo[posicion-1]=arreglo[posicion-1]+arreglo[posicion];
                    arreglo[posicion]=Math.abs( arreglo[posicion-1]-arreglo[posicion]);
                     arreglo[posicion-1]=Math.abs( arreglo[posicion]-arreglo[posicion-1]);
                posicion=posicion-1;
            }
        }*/

        for (int i = 1; i < tamanio_arreglo; i++) {
            valor_temporal = arreglo[i];
            posicion = i;
            while (posicion != 0 && arreglo[posicion - 1] > valor_temporal) {
                arreglo[posicion] = arreglo[posicion - 1];
                posicion--;
            }
            arreglo[posicion] = valor_temporal;
        }
        return arreglo;
    }

    public static int[] ordenamiento_seleccion(int[] arreglo) {
        int tamanio_arreglo = arreglo.length;
        int menor, aux;
        for (int i = 0; i < tamanio_arreglo - 1; i++) {
            menor = i;
            for (int j = i + 1; j < tamanio_arreglo; j++) {
                if (arreglo[menor] > arreglo[j]) {
                    menor = j;
                }

            }
            if (menor != i) {
                aux = arreglo[i];
                arreglo[i] = arreglo[menor];
                arreglo[menor] = aux;
            }

        }
        return arreglo;
    }

    //Mezcla
    public static int[] ordenamiento_mezcla(int[] arreglo) {
        if (arreglo.length < 2) {
            return arreglo;
        }
        int medio = arreglo.length / 2;
        int[] izquierda = Arrays.copyOfRange(arreglo, 0, medio);
        int[] derecha = Arrays.copyOfRange(arreglo, medio, arreglo.length);
        return mezcla(ordenamiento_mezcla(izquierda), ordenamiento_mezcla(derecha));
    }

    public static int[] mezcla(int[] izquierda, int[] derecha) {
        int[] resultado = new int[izquierda.length + derecha.length];
        int i = 0, j = 0, k = 0;
        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i] <= derecha[j]) {
                resultado[k++] = izquierda[i++];
            } else {
                resultado[k++] = derecha[j++];
            }
        }
        while (i < izquierda.length) {
            resultado[k++] = izquierda[i++];
        }
        while (j < derecha.length) {
            resultado[k++] = derecha[j++];
        }
        return resultado;
    }

// Quick Sort
    public static void quicksort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = particion(arr, left, right);
            quicksort(arr, left, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, right);
        }
    }

    public static int particion(int[] arr, int left, int right) {
        int pivotValue = arr[right];
        int pivotIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                intercambio(arr, i, pivotIndex);
                pivotIndex++;
            }
        }
        intercambio(arr, pivotIndex, right);
        return pivotIndex;
    }

    public static void intercambio(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //ordenamiento por monto, heap
    
    
    public static void ordenar_por_monton(int[] arreglo) {
        // Convertir el arreglo en un monton
        for (int i = arreglo.length / 2 - 1; i >= 0; i--) {
            amontonar(arreglo, arreglo.length, i);
        }

        // Extraer elementos del monton uno por uno
        for (int i = arreglo.length - 1; i >= 0; i--) {
            // Mover la raíz actual al final del arreglo
            int temp = arreglo[0];
            arreglo[0] = arreglo[i];
            arreglo[i] = temp;

            // Llamar amontonar en el subárbol reducido
            amontonar(arreglo, i, 0);
        }
    }

    public static void amontonar(int[] arreglo, int n, int i) {
        int mayor = i;  // Inicializar el mayor como la raíz
        int izquierda = 2 * i + 1;  // izquierda = 2*i + 1
        int derecha = 2 * i + 2;  // derecha = 2*i + 2

        // Si el hijo izquierdo es más grande que la raíz
        if (izquierda < n && arreglo[izquierda] > arreglo[mayor]) {
            mayor = izquierda;
        }

        // Si el hijo derecho es más grande que el mayor hasta ahora
        if (derecha < n && arreglo[derecha] > arreglo[mayor]) {
            mayor = derecha;
        }

        // Si el mayor no es la raíz
        if (mayor != i) {
            int swap = arreglo[i];
            arreglo[i] = arreglo[mayor];
            arreglo[mayor] = swap;

            // Recursivamente amontonar el subárbol afectado
            amontonar(arreglo, n, mayor);
        }
    }

}
