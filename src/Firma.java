import java.security.*;

public class Firma {
    public static void main(String[] args) {
        String texto = "texto de prueba para ser firmado";
        System.out.printf("Texto original para ser firmado: '%s'\n\n", texto);

        KeyPair clave = generarClaves();
        byte[] textoFirmado = hacerFirma(texto.getBytes(),
                clave.getPrivate());

        System.out.println("Texto firmado:");
        // Convertimos el array de bytes a una representación hexadecimal "legible" por humanos
        StringBuilder sb = new StringBuilder();
        for (byte b : textoFirmado) {
            sb.append(String.format("%02x", b));
        }
        System.out.println (sb);
        System.out.println();

        System.out.println ("Comprobando texto firmado...");
        if (verificarFirma(texto.getBytes(), clave.getPublic(), textoFirmado)) {
            System.out.println("Firma realizada y verificada correctamente");
        } else {
            System.out.println("Firma incorrecta");
        }

    }

    //método que genera una pareja de claves (pública y privada)
//que se utilizarán en la firma digital
    public static KeyPair generarClaves() {
        //inicializa el objeto claves, tipo KeyPair, a null
        KeyPair claves = null;
        try {
            //Indica el algoritmo a utilizar en la generación de claves
            KeyPairGenerator generador = KeyPairGenerator.getInstance("DSA", "SUN");
            //asigna la pareja de claves generadas al objeto tipo KeyPair, claves
            claves = generador.genKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
        //retorna un objeto tipo KeyPair
        return claves;
    }
//método que realiza la firma digital del texto o datos y la devuelve

    public static byte[] hacerFirma(byte[] datos, PrivateKey clave) {
        byte[] firmado = null;
        try {
            //crea el objeto tipo Signature
            Signature firma = Signature.getInstance("SHA256withDSA");
            //inicializa la firma con la clave privada a utilizar
            firma.initSign(clave);
            //obtine el resumen del mensaje
            firma.update(datos);
            //obtien la firma digital
            firmado = firma.sign();
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
        //devuleve la firma digital
        return firmado;
    }

//Método que verifica la firma digital, devolviendo:
//false, si la firma no es correcta o se produce una excepción
//verdadero, si la firma es correcta

    public static boolean verificarFirma(byte[] texto, PublicKey clave,
                                         byte[] textoFirmado) {
        try {
            //crea el objeto tipo Signature
            Signature firma = Signature.getInstance("SHA256withDSA");
            //verifica la clave pública
            firma.initVerify(clave);
            //actualiza el resumen de mensaje
            firma.update(texto);
            //devuelve el resultado de la verificación
            return (firma.verify(textoFirmado));
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
        return false;
    }
}
