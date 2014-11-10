package primaprova.export.data.handlerCreation;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import primaprova.excel.utils.config.AmisExcelUtils;
import primaprova.excel.utils.config.SheetCreator;
import primaprova.export.data.configurations.dataCreator.DataCreator;
import primaprova.export.data.forecast.Forecast;
import primaprova.export.data.query.AMISQuery;
import primaprova.export.data.utils.codelist.CommodityParser;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by fabrizio on 11/10/14.
 */
public class HandlerExcelCreation {

    private SheetCreator sheetCreator;

    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(HandlerExcelCreation.class);


    public HandlerExcelCreation() {
        this.sheetCreator = new SheetCreator();
    }


    public void init(Forecast forecast, AMISQuery qvo, DataCreator dataModel) {


        // create the Excel file
        HSSFWorkbook workbook = new HSSFWorkbook();
        AmisExcelUtils.setCustomizedPalette(workbook);


        //Initialize font
        AmisExcelUtils.initializeHSSFFontStyles(workbook);

        int[] commodityList = forecast.getCommodityList();

        for (int commodity : commodityList) {

            String commodityString = "" + commodity;

            CommodityParser commParser = new CommodityParser();

            String commodityLabel = commParser.getCommodityLabel(commodityString);

            HSSFSheet sheet = workbook.createSheet(commodityLabel);

            int rowCounter = 0;

            this.sheetCreator.createSummary(rowCounter, sheet, workbook, dataModel, commodityLabel);

        }

        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("demoExcel.xlsx"));
            workbook.write(out);
            out.close();
            LOGGER.debug("demoEXcel.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




