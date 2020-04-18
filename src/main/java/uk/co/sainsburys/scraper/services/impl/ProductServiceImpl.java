package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import uk.co.sainsburys.scraper.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

  private static final String LINKS_SELECTOR = "ul li.gridItem div.productNameAndPromotions a";
  private static final String PRICES_SELECTOR = ".pricingAndTrolleyOptions p.pricePerUnit";
  private static final String NON_NUMERIC_REGEX = "[^0-9.]";
  private static final String LEVEL_UP_URL_REGEX = "../";
  private static final String HREF = "href";
  private static final String EMPTY = "";

  @Override
  public List<String> parseTitles(Document document) {
    return document.select(LINKS_SELECTOR).eachText();
  }

  @Override
  public List<BigDecimal> parseUnitPrices(Document document) {
     return document
      .select(PRICES_SELECTOR)
      .eachText()
      .stream()
      .map(price -> new BigDecimal(price.replaceAll(NON_NUMERIC_REGEX, EMPTY)))
      .collect(Collectors.toList());
  }

  @Override
  public List<String> parseDetailsUrls(Document document) {
    return document
      .select(LINKS_SELECTOR)
      .eachAttr(HREF)
      .stream()
      .map(url -> url.replaceAll(LEVEL_UP_URL_REGEX, EMPTY))
      .collect(Collectors.toList());
  }

}
