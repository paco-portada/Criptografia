import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Claves {
    //Programa que crea una pareja de claves (pública y privada) y las muestra
    public static void main(String[] args) {

        //Asigna al objeto claves de tipo keyPair el par de claves generadas
        //por el método GeneraParejaClave()
        System.out.println ("Generando pareja de claves pública-privada (PKI)\n");
        KeyPair claves = null;
        try {
            //Crea el objeto para generar un par de claves mediante RSA
            KeyPairGenerator genera = KeyPairGenerator.getInstance("RSA");
            genera.initialize(512); //asigna tamaño de la clave
            claves = genera.generateKeyPair(); //genera la pareja de claves
        } catch (NoSuchAlgorithmException ex) {
            System.out.printf ("Error: %s\n", ex.getMessage());
            ex.printStackTrace();
        }

        //Imprime el valor de las claves generadas
        if (claves != null) {
            System.out.println ("Pareja de claves generada");
            System.out.println ("-------------------------");
            System.out.printf("Algoritmo Kprivada: %s\n\n", claves.getPrivate().getAlgorithm());
            System.out.printf("Codificación Kprivada: %s\n\n", claves.getPrivate().getFormat());
            System.out.printf("Bytes Kprivada: %s\n\n", claves.getPrivate().toString());
            System.out.printf("Algoritmo Kpública: %s\n\n", claves.getPublic().getAlgorithm());
            System.out.printf("Codificación Kpública: %s\n\n", claves.getPublic().getFormat());
            System.out.printf("Bytes Kpública: %s\n\n", claves.getPublic().toString());
        }
    }
}
