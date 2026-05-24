package hashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class HashTableMenuOpciones {

    private static final int SIZE = 7;
    private final LinkedList<Entry>[] table;
    private int collisions = 0;

    static class Entry {

        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTableMenuOpciones() {

        table = new LinkedList[SIZE];

        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Función Hash mejorada
    private int hash(int key) {
        return (key * 5 + 3) % SIZE;
    }

    // Función Hash original
    private int hashOriginal(int key) {
        return key % SIZE;
    }

    // Inserción
    public void insert(int key, String value) {

        int index = hash(key);
        table[index].add(new Entry(key, value));
    }

    // Inserción con conteo de colisiones
    public void insertWithCollisionCount(int key, String value) {

        int index = hash(key);

        if (!table[index].isEmpty()) {
            collisions++;
        }

        table[index].add(new Entry(key, value));
    }

    public int getCollisions() {
        return collisions;
    }

    // Búsqueda
    public String search(int key) {

        int index = hash(key);

        for (Entry entry : table[index]) {

            if (entry.key == key) {
                return entry.value;
            }
        }

        return null;
    }

    // Eliminación
    public boolean delete(int key) {

        int index = hash(key);

        Iterator<Entry> iterator = table[index].iterator();

        while (iterator.hasNext()) {

            Entry entry = iterator.next();

            if (entry.key == key) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    // Mostrar tabla
    public void printTable() {

        for (int i = 0; i < SIZE; i++) {

            System.out.print("Index " + i + ": ");

            for (Entry entry : table[i]) {
                System.out.print("[" + entry.key + ": " + entry.value + "] -> ");
            }

            System.out.println("null");
        }
    }

    // ====================================================
    // MENÚ PRINCIPAL
    // ====================================================

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n=============================================");
            System.out.println("       MENÚ DE EJERCICIOS - HASH TABLE       ");
            System.out.println("=============================================");

            System.out.println("1. Ejercicio 1: Crear e Insertar");
            System.out.println("2. Ejercicio 2: Buscar una Clave");
            System.out.println("3. Ejercicio 3: Eliminar una Clave");
            System.out.println("4. Ejercicio 4: Contar Colisiones");
            System.out.println("5. Ejercicio 5: Comparar Función Hash");
            System.out.println("6. Salir");

            System.out.print("Selecciona una opción: ");

            opcion = scanner.nextInt();

            System.out.println("---------------------------------------------");

            switch (opcion) {

                case 1:

                    System.out.println("[EJERCICIO 1] Inserción de datos");

                    HashTableMenuOpciones tabla1 = new HashTableMenuOpciones();

                    tabla1.insert(15, "A");
                    tabla1.insert(28, "B");
                    tabla1.insert(43, "C");

                    tabla1.printTable();

                    break;

                case 2:

                    System.out.println("[EJERCICIO 2] Búsqueda");

                    HashTableMenuOpciones tabla2 = new HashTableMenuOpciones();

                    tabla2.insert(15, "A");
                    tabla2.insert(28, "B");
                    tabla2.insert(43, "C");

                    tabla2.printTable();

                    System.out.print("\nIngrese la clave a buscar: ");

                    int claveBuscar = scanner.nextInt();

                    String resultado = tabla2.search(claveBuscar);

                    if (resultado != null) {

                        System.out.println("Clave encontrada: " + resultado);

                    } else {

                        System.out.println("La clave no existe.");
                    }

                    break;

                case 3:

                    System.out.println("[EJERCICIO 3] Eliminación");

                    HashTableMenuOpciones tabla3 = new HashTableMenuOpciones();

                    tabla3.insert(15, "A");
                    tabla3.insert(28, "B");
                    tabla3.insert(43, "C");

                    System.out.println("Tabla original:");

                    tabla3.printTable();

                    System.out.print("\nIngrese la clave a eliminar: ");

                    int claveEliminar = scanner.nextInt();

                    boolean eliminado = tabla3.delete(claveEliminar);

                    if (eliminado) {

                        System.out.println("Clave eliminada correctamente.");

                    } else {

                        System.out.println("La clave no existe.");
                    }

                    System.out.println("\nTabla actualizada:");

                    tabla3.printTable();

                    break;

                case 4:

                    System.out.println("[EJERCICIO 4] Conteo de colisiones");

                    HashTableMenuOpciones tabla4 = new HashTableMenuOpciones();

                    tabla4.insertWithCollisionCount(15, "A");
                    tabla4.insertWithCollisionCount(28, "B");
                    tabla4.insertWithCollisionCount(43, "C");
                    tabla4.insertWithCollisionCount(66, "D");
                    tabla4.insertWithCollisionCount(23, "E");
                    tabla4.insertWithCollisionCount(55, "F");

                    tabla4.printTable();

                    System.out.println("\nNúmero de colisiones: "
                            + tabla4.getCollisions());

                    break;

                case 5:

                    System.out.println("[EJERCICIO 5] Comparación Hash");

                    System.out.println("Fórmula original: key % SIZE");
                    System.out.println("Fórmula mejorada: (key * 5 + 3) % SIZE");

                    System.out.print("\nIngrese una clave: ");

                    int keyTest = scanner.nextInt();

                    int idx1 = keyTest % SIZE;
                    int idx2 = (keyTest * 5 + 3) % SIZE;

                    System.out.println("Índice con fórmula original: " + idx1);
                    System.out.println("Índice con fórmula mejorada: " + idx2);

                    break;

                case 6:

                    System.out.println("Saliendo del programa...");
                    break;

                default:

                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}