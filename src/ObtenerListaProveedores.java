import java.security.Provider;
import java.security.Security;

public class ObtenerListaProveedores {
    public static void main(String[] args) {

        // Obtener todos los proveedores de seguridad
        Provider[] providers = Security.getProviders();

        for (Provider provider : providers) {
            System.out.printf("Proveedor: %s\n", provider.getName());
        }
    }
}
