package uk.co.sainsburys.scraper.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import uk.co.sainsburys.scraper.models.ProductResponse;
import uk.co.sainsburys.scraper.services.ScrapperService;

@Profile("!test")
@Component
public class JobCommandLineRunner implements CommandLineRunner {

  @Autowired
  private ScrapperService scrapperService;

  private static ObjectMapper mapper = new ObjectMapper();

  @Override
  public void run(String... args) throws Exception {
    long initialTime = System.currentTimeMillis();
    System.out.println("Running scrape service...");
    ProductResponse response = scrapperService.execute();
    long finalTime = System.currentTimeMillis();
    System.out.println("Scrape service executed successfully in " + (finalTime - initialTime) + "ms.");
    System.out.println("Printing response JSON:");
    String responseJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    System.out.println(responseJson);
  }

}
