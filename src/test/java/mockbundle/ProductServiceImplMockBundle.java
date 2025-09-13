package mockbundle;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.product.management.model.dto.ProductDto;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Clase de utilidad para proporcionar datos simulados relacionados con el servicio de productos.
 * Esta clase no puede ser instanciada externamente.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceImplMockBundle {

  /**
   * Obtiene un objeto `ProductDto` simulando el modelo 1 de datos del producto.
   * Lee los datos desde un archivo JSON ubicado en
   * `src/test/resources/product-serivice-impl/ProductDtoModel1.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados del modelo 1.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */

  public static ProductDto getProductDtoModel1() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel1.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }


  /**
   * Obtiene un objeto `ProductDto` simulando el modelo 2 de datos del producto.
   * Lee los datos desde un archivo JSON ubicado en
   * `src/test/resources/product-serivice-impl/ProductDtoModel2.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados del modelo 2.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */

  public static ProductDto getProductDtoModel2() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel2.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }


  /**
   * Obtiene un objeto `ProductDto` simulando el modelo 3 de datos del producto.
   * Lee los datos desde un archivo JSON ubicado en
   * `src/test/resources/product-serivice-impl/ProductDtoModel3.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados del modelo 3.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */

  public static ProductDto getProductDtoModel3() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel3.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  /**
   * Obtiene un objeto `ProductDto` simulando el modelo 4 de datos del producto.
   * Lee los datos desde un archivo JSON ubicado en
   * `src/test/resources/product-serivice-impl/ProductDtoModel4.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados del modelo 4.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */

  public static ProductDto getProductDtoModel4() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel4.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  /**
   * Obtiene un objeto `ProductDto` simulando el modelo 5 de datos del producto.
   * Lee los datos desde un archivo JSON ubicado en
   * `src/test/resources/product-serivice-impl/ProductDtoModel5.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados del modelo 5.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */

  public static ProductDto getProductDtoModel5() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel5.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  /**
   * Obtiene un objeto `ProductDto` simulando el modelo 6 de datos del producto.
   * Lee los datos desde un archivo JSON ubicado en
   * `src/test/resources/product-serivice-impl/ProductDtoModel6.json`.
   *
   * @return Un objeto `ProductDto` con los datos simulados del modelo 6.
   * @throws IOException Si ocurre un error al leer el archivo JSON.
   */

  public static ProductDto getProductDtoModel6() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel6.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

}
