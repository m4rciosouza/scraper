package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import uk.co.sainsburys.scraper.services.ProductDetailsService;

import java.util.List;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

  private static final String CALORIES_SELECTOR = ".nutritionTable tbody tr";
  private static final String DESCRIPTION_SELECTOR = "#information .productText p";
  private static final String TD = "td";
  private static final String KCAL = "kcal";
  private static final String EMPTY = "";

  @Override
  public Integer parseCalories(Document document) {
    Elements nutritionTable = document.select(CALORIES_SELECTOR);
    if (nutritionTable.isEmpty() || nutritionTable.size() < 2) {
      return null;
    }
    String caloriesText = nutritionTable.get(1).select(TD).first().html();
    if (caloriesText == null) {
      return null;
    }
    return new Integer(caloriesText.replace(KCAL, EMPTY));
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
