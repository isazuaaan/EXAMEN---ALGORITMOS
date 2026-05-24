package hashtable;

import java.util.LinkedList;

public class HashTableDelete {

    private final int SIZE = 7;
    private final LinkedList<Integer>[] table;

    @SuppressWarnings("unchecked")
    public HashTableDelete() {

        table = new LinkedList[SIZE];

        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hashFunction(int key) {
        return key % SIZE;
    }

    public void insert(int key) {

        int index = hashFunction(key);

        if (!table[index].contains(key)) {
            table[index].add(key);
        } else {
            System.out.println("Clave " + key + " ya existe en la tabla.");
        }
    }

    public boolean search(int key) {

        int index = hashFunction(key);
        return table[index].contains(key);
    }

    public void delete(int key) {

        int index = hashFunction(key);

        if (table[index].remove((Integer) key)) {
            System.out.println("Clave " + key + " eliminada.");
        } else {
            System.out.println("Clave " + key + " no encontrada.");
        }
    }

    public void display() {

        for (int i = 0; i < SIZE; i++) {
            System.out.println("Índice " + i + ": " + table[i]);
        }
    }

    public static void main(String[] args) {

        HashTableDelete hashTable = new HashTableDelete();

        int[] keys = {10, 17, 24, 3, 31, 38, 45};

        for (int key : keys) {
            hashTable.insert(key);
        }

        hashTable.display();

        System.out.println("Buscar 24: " + hashTable.search(24));
        System.out.println("Buscar 50: " + hashTable.search(50));

        hashTable.delete(24);
        hashTable.delete(50);

        hashTable.display();
    }
}
