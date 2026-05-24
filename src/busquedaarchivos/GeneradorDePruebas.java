package busquedaarchivos;

import java.io.*;
public class GeneradorDePruebas {
    public static void main(String[] args) {
        try {
            // --- Ejercicio 1 y 3: Archivo Binario (100 bytes por registro) ---
            try (RandomAccessFile raf = new RandomAccessFile("registros.dat", "rw")) {
                for (int i = 0; i < 10; i++) {
                    String id = String.valueOf(100 + i);
                    String contenido = "ID:" + id + " - Registro numero " + i;
                    byte[] data = new byte[100]; 
                    byte[] contenidoBytes = contenido.getBytes();
                    // Copiamos el contenido al array de 100 bytes para asegurar tamaño fijo
                    System.arraycopy(contenidoBytes, 0, data, 0, Math.min(contenidoBytes.length, 100));
                    raf.write(data);
                }
            }

            // --- Ejercicio 2: Texto con Índice (50 bytes por registro) ---
            try (RandomAccessFile rafDatos = new RandomAccessFile("datos.txt", "rw");
                 PrintWriter pwIndice = new PrintWriter(new FileWriter("indice.txt"))) {
                
                String[] ids = {"A1", "B2", "C3"};
                String[] nombres = {"Juan Perez", "Maria Lopez", "Carlos Ruiz"};

                // CORRECCIÓN: Usamos ids.length en lugar de size()
                for (int i = 0; i < ids.length; i++) {
                    long posicionDondeEmpieza = rafDatos.getFilePointer();
                    String linea = ids[i] + "," + nombres[i];
                    
                    // Rellenamos con espacios para que cada línea mida exactamente 50 bytes
                    StringBuilder sb = new StringBuilder(linea);
                    while (sb.length() < 48) {
                        sb.append(" ");
                    }
                    sb.append("\r\n"); // Los 2 bytes finales del salto de línea
                    
                    rafDatos.writeBytes(sb.toString());
                    pwIndice.println(ids[i] + "," + posicionDondeEmpieza);
                }
            }

            System.out.println("Archivos creados con éxito en la carpeta del proyecto.");
            System.out.println("Ejer 1 y 3: registros.dat");
            System.out.println("Ejer 2: datos.txt e indice.txt");

        } catch (IOException e) {
            System.err.println("Error al crear archivos: " + e.getMessage());
        }
    }
}
