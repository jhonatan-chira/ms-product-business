package MockBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.product.management.model.dto.ProductDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

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
