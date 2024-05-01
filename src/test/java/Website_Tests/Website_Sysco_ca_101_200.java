

package Website_Tests;
import Excel_Lib.Xls_Reader;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import Excel_Lib.Xls_Reader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import java.net.URISyntaxException;

public class Website_Sysco_ca_101_200 {
    static WebDriver driver;
    private static Object ScrollStrategy;
    Xls_Reader reader = new Xls_Reader("src/main/java/Excel_Lib/syscoca_urls.xlsx");
    String sheetname_ = "QAEnvUrls";
    int rowCount = reader.getRowCount(sheetname_);


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test(priority = 1)
    public void testLoadTheWebsiteUrlsAndtakingscreenshotOfFullpage() throws URISyntaxException {
        System.out.println("total rows: " + rowCount);

        for (int rownum = 101; rownum <= 200; rownum++) {

            String urls = reader.getCellData(sheetname_, "URL", rownum);
            System.out.println("url number" + "  " +  rownum  +"  " + "  "+ urls);
            driver.manage().window().maximize();
            driver.get(urls);
            String s = reader.getCellData(sheetname_, "URL", rownum);
            String shorturl=s.replace("http://"," ").replace("/"," ").replace(".html"," ");
            Shutterbug.shootPage(driver, Capture.FULL, 500, true).withName(rownum + shorturl).save("src/main/resources/SyscoCanadaWebsite_Screenshots_101_200");

        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
