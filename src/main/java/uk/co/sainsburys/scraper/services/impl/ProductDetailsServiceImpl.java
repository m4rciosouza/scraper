package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import uk.co.sainsburys.scraper.services.ProductDetailsService;

import java.util.List;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

  public static final String CALORIES_SELECTOR = ".nutritionTable tbody tr";
  public static final String DESCRIPTION_SELECTOR = "#information .productText p";

  @Override
  public Integer parseCalories(Document document) {
    Elements nutritionTable = document.select(CALORIES_SELECTOR);
    if (nutritionTable.isEmpty() || nutritionTable.size() < 2) {
      return null;
    }
    String caloriesText = nutritionTable.get(1).select("td").first().html();
    if (caloriesText == null) {
      return null;
    }
    return new Integer(caloriesText.replace("kcal", ""));
  }

  @Override
  public String parseDescription(Document document) {
    Elements elems = document.select(DESCRIPTION_SELECTOR);
    List<String> texts = elems.eachText();
    if (texts == null || texts.size() < 1) {
      return null;
    }
    return texts.get(0);
  }

}
