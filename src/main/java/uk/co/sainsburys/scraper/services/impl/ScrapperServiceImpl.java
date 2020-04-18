package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.co.sainsburys.scraper.models.Product;
import uk.co.sainsburys.scraper.models.ProductResponse;
import uk.co.sainsburys.scraper.services.ProductDetailsService;
import uk.co.sainsburys.scraper.services.ProductService;
import uk.co.sainsburys.scraper.services.ScrapperService;
import uk.co.sainsburys.scraper.services.TotalService;
import uk.co.sainsburys.scraper.utils.JsoupService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapperServiceImpl implements ScrapperService {

  @Value("${app.base_url}")
  private String baseUrl;

  private ProductService productService;
  private ProductDetailsService productDetailsService;
  private TotalService totalService;
  private JsoupService jsoupService;

  public ScrapperServiceImpl(ProductService productService,
                             ProductDetailsService productDetailsService,
                             TotalService totalService,
                             JsoupService jsoupService) {
    this.productService = productService;
    this.productDetailsService = productDetailsService;
    this.totalService = totalService;
    this.jsoupService = jsoupService;
  }

  @Override
  public ProductResponse execute() throws IOException {
    List<Product> products = new ArrayList<>();
    Document document = jsoupService.getDocument(baseUrl);
    List<String> titles = productService.parseTitles(document);
    List<BigDecimal> unitPrices = productService.parseUnitPrices(document);
    List<String> productDetailsUrls = productService.parseDetailsUrls(document);
    int index = 0;
    for (String productDetailUrl: productDetailsUrls) {
      Document doc = jsoupService.getDocument(productDetailUrl);
      products.add(new Product(
        titles.get(index),
        productDetailsService.parseCalories(doc),
        unitPrices.get(index),
        productDetailsService.parseDescription(doc))
      );
      index++;
    }
    return new ProductResponse(products, totalService.calculate(products));
  }

}
