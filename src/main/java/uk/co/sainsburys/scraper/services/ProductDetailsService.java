package uk.co.sainsburys.scraper.services;

import org.jsoup.nodes.Document;

public interface ProductDetailsService {

  Integer parseCalories(Document document);
  String parseDescription(Document document);

}
