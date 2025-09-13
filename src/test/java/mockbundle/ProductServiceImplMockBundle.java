package MockBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.product.management.model.dto.ProductDto;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceImplMockBundle {
  public static ProductDto getProductDtoModel1() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel1.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  public static ProductDto getProductDtoModel2() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel2.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  public static ProductDto getProductDtoModel3() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel3.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  public static ProductDto getProductDtoModel4() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel4.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  public static ProductDto getProductDtoModel5() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel5.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }

  public static ProductDto getProductDtoModel6() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/product-serivice-impl/ProductDtoModel6.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }
}
