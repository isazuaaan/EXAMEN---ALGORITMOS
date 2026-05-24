package hashtable;

import java.util.Arrays;

public class HashTableExploracionLineal {

    private final int SIZE = 10;
    private final Integer[] table;

    public HashTableExploracionLineal() {

        table = new Integer[SIZE];
        Arrays.fill(table, null);
    }

    private int hashFunction(int key) {
        return key % SIZE;
    }

    public void insert(int key) {

        int index = hashFunction(key);
        int startIndex = index;

        // Exploración lineal
        while (table[index] != null) {

            index = (index + 1) % SIZE;

            if (index == startIndex) {
                System.out.println("Tabla llena, no se pudo insertar " + key);
                return;
            }
        }

        table[index] = key;
    }

    public boolean search(int key) {

        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null) {

            if (table[index].equals(key)) {
                return true;
            }

            index = (index + 1) % SIZE;

            if (index == startIndex) {
                break;
            }
        }

        return false;
    }

    public void delete(int key) {

        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null) {

            if (table[index].equals(key)) {

                table[index] = null;

                System.out.println("Clave " + key + " eliminada.");
                return;
            }

            index = (index + 1) % SIZE;

            if (index == startIndex) {
                break;
            }
        }

        System.out.println("Clave " + key + " no encontrada.");
    }

    public void display() {

        System.out.println(Arrays.toString(table));
    }

    public static void main(String[] args) {

        HashTableExploracionLineal hashTable = new HashTableExploracionLineal();

        int[] keys = {23, 34, 45, 56, 67, 78, 89, 90};

        for (int key : keys) {
            hashTable.insert(key);
        }

        hashTable.display();

        System.out.println("Buscar 45: " + hashTable.search(45));
        System.out.println("Buscar 100: " + hashTable.search(100));

        hashTable.delete(45);
        hashTable.delete(100);

        hashTable.display();
    }
}
