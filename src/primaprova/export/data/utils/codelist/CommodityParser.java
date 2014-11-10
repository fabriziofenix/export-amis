package primaprova.export.data.utils.codelist;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by fabrizio on 11/10/14.
 */
public class CommodityParser {

    private static final String COMMODITY_URL = "commodity.properties";

    private Properties prop;

    public CommodityParser(){
        this.prop=  new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(COMMODITY_URL);

        try {
            this.prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getCommodityLabel(String code){
        return this.prop.getProperty(code);
    }
}
