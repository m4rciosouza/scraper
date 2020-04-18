package uk.co.sainsburys.scraper.services;

import org.jsoup.nodes.Document;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

  List<String> parseTitles(Document document);
  List<BigDecimal> parseUnitPrices(Document document);
  List<String> parseDetailsUrls(Document document);

}
