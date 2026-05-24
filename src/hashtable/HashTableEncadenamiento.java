package hashtable;

import java.util.LinkedList;

public class HashTableEncadenamiento {

    private final int SIZE = 10;
    private final LinkedList<Integer>[] table;

    public HashTableEncadenamiento() {
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

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println("Índice " + i + ": " + table[i]);
        }
    }

    public static void main(String[] args) {

        HashTableEncadenamiento hashTable = new HashTableEncadenamiento();

        int[] keys = {15, 25, 35, 45, 55};

        for (int key : keys) {
            hashTable.insert(key);
        }

        hashTable.display();

        System.out.println("Buscar 25: " + hashTable.search(25));
        System.out.println("Buscar 100: " + hashTable.search(100));

        hashTable.insert(25);
    }
}
