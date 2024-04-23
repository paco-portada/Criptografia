import java.security.Provider;
import java.security.Security;
import java.util.Set;

public class ObtenerListaServicios {
    public static void main(String[] args) {

        // Obtener todos los proveedores de seguridad
        Provider[] providers = Security.getProviders();

        for (Provider provider : providers) {
            // Obtener los servicios de MessageDigest del proveedor
            Set<Provider.Service> services = provider.getServices();

            System.out.printf("Proveedor: %s\n", provider.getName());
            System.out.printf("-----------%s\n", "-".repeat(provider.getName().length()));
            for (Provider.Service service : services) {
                // Obtener el servicio (algoritmo)
                String algoritmo = service.getAlgorithm();

                // Imprimir el algoritmo 
                System.out.printf("Servicio: %s - Tipo: %s\n",
                        algoritmo, service.getType());
            }
            System.out.println();
        }

    }
}
