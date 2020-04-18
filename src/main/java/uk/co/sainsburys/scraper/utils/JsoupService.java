package uk.co.sainsburys.scraper.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupService {

  public Document getDocument(String url) throws IOException {
    return Jsoup.connect(url).get();
  }

}
