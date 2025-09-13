package MockBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.product.management.model.dto.ProductDto;
import java.io.File;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerMockBundle {
  public static ProductDto getReplaceProductRequest() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(
        new File("src/test/resources/ReplaceProductRequest.json"),
        new TypeReference<ProductDto>() {
        }
    );
  }
}
