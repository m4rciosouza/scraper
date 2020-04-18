package uk.co.sainsburys.scraper.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.util.StreamUtils.*;

public class ResourceUtils {

  public static String loadHtmlAsString(String s) throws IOException {
    return copyToString(new ClassPathResource(s).getInputStream(), Charset.defaultCharset());
  }

}
