package mockbundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.product.management.model.dto.ProductDto;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Clase de utilidad para proporcionar datos simulados relacionados con el controlador de productos.
 * Esta clase no puede ser instanciada externamente.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerMockBundle {

  /**
   * Obtiene un objeto `ProductDto` simulando una solicitud de reemplazo de producto.
   * Lee los datos desde un archivo JSON ubicado en `src/test/resources/ReplaceProductRequest.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */
  public static ProductDto getReplaceProductRequest() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/ReplaceProductRequest.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }
}