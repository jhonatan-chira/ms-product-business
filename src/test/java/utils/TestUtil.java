package utils;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

/**
 * Clase de utilidad para pruebas que proporciona métodos para la conversión y lectura de
 * archivos JSON.
 */
public class TestUtil {

  /**
   * Convierte un archivo JSON en un objeto de un tipo específico.
   *
   * @param <T>       El tipo del objeto al que se convertirá el archivo.
   * @param file      El archivo JSON a convertir.
   * @param valueType La clase del tipo de objeto al que se convertirá el archivo.
   * @return Un objeto del tipo especificado que representa los datos del archivo JSON.
   * @throws IOException Si ocurre un error al leer el archivo.
   */
  public static <T> T convertFileToObject(File file, Class<T> valueType) throws IOException {
    return new Gson().fromJson(FileUtils.readFileToString(file, StandardCharsets.UTF_8), valueType);
  }

  /**
   * Lee un archivo JSON desde una ubicación específica y lo convierte en un objeto de un tipo
   * específico.
   *
   * @param <T>       El tipo del objeto al que se convertirá el archivo.
   * @param file      El nombre del archivo JSON (sin extensión) a leer.
   * @param folder    La carpeta donde se encuentra el archivo JSON.
   * @param className La clase del tipo de objeto al que se convertirá el archivo.
   * @return Un objeto del tipo especificado que representa los datos del archivo JSON.
   * @throws IOException Si ocurre un error al leer el archivo.
   */
  public static <T> T readFile(String file, String folder, Class<T> className) throws IOException {
    return TestUtil.convertFileToObject(ResourceUtils.getFile("src/test/resources/mocks/"
        + folder + "/" + file + ".json"), className);
  }
}