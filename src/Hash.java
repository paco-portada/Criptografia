import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static void main(String[] args) {
        String[] listaAlgoritmos = {"MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};
        String texto = "Texto de ejemplo para generar resumen";

        System.out.printf("Texto: %s\n", texto);
        for (int i = 0; i < listaAlgoritmos.length; i++) {

            try {
                MessageDigest algoritmoHash = MessageDigest.getInstance(listaAlgoritmos[i]);

                algoritmoHash.update(texto.getBytes()); //obtiene el resumen
                byte[] resumen = algoritmoHash.digest(); //completa la generación del resumen

                // Convertimos el array de bytes a una representación hexadecimal "legible" por humanos
                StringBuilder sb = new StringBuilder();
                for (byte b : resumen) {
                    sb.append(String.format("%02x", b));
                }
                // Mostramos el resumen por pantalla
                System.out.printf("Resumen %s: %s\n", listaAlgoritmos[i], sb.toString());
            } catch (NoSuchAlgorithmException ex) {
                System.out.printf("Error: %s\n", ex.getMessage());
            }
        }
    }
}
