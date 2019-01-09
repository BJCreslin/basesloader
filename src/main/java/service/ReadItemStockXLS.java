package service;

import Models.ItemTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class ReadItemStockXLS {
    public static String PATHTOFILES = "\\\\Post\\креслин владимир\\перемещение\\";
    public static String NAMEXLSFILEWITHSTOCK = "остатки.xls";  // Файл с данными по вместимости ячеек

    public static List<ItemTable> getItemList() {
        List<ItemTable> itemtableList = new ArrayList<>();
        try (FileInputStream inputStreamFile = new FileInputStream(new File
                (PATHTOFILES + NAMEXLSFILEWITHSTOCK))) {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStreamFile);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);


            int iPos = 0;
            int shiftV = 7;


            int columnCode = codeColumnFinder();

            int centralStock = centralStockFinder();
            int base8Stock = base8StockFinder();


            int columnName = nameItemColumnFinder();


            boolean flagToStop = false;
            while (!flagToStop) {
                int code = 0;
                boolean propuskaem = false;

                HSSFCell codeCell;

                try {
                    codeCell = sheet.getRow(iPos + shiftV).getCell(columnCode);
                } catch (NullPointerException npe) {
                    if (iPos > 10) {
                        System.out.println("Ipos");
                        break;
                    }
                    iPos++;
                    continue;
                }


                DataFormatter fmtCode = new DataFormatter();
                String dataCodeFromCell = fmtCode.formatCellValue(codeCell);
                if (dataCodeFromCell.isEmpty()) {
                    iPos++;
                    continue;
                }
                if (dataCodeFromCell.equals("THEEND")) {
                    break;
                }
                dataCodeFromCell = dataCodeFromCell.replaceAll("(,00)|\\D", "");
                code = Integer.parseInt(dataCodeFromCell);

                int quantityCentral = 0;
                {
                    HSSFCell quantityCell = sheet.getRow(iPos + shiftV).getCell(centralStock);
                    DataFormatter fmt = new DataFormatter();
                    String dataFromCell = fmt.formatCellValue(quantityCell);

                    String dataFromCel2 = dataFromCell.replaceAll("(,00)|\\D", "");
                    try {
                        if (!dataFromCell.isEmpty()) {
                            quantityCentral = Integer.parseInt(dataFromCel2);
                        }
                    } catch (NumberFormatException n) {
                        System.out.println("code " + code + " " + dataFromCell + "  x" + dataFromCel2);
                        exit(15);
                    }
                }

                int quantityVystavka = 0;
                {
                    HSSFCell quantityCell = sheet.getRow(iPos + shiftV).getCell(base8Stock);
                    DataFormatter fmt = new DataFormatter();
                    String dataFromCell = fmt.formatCellValue(quantityCell);

                    String dataFromCel2 = dataFromCell.replaceAll("(,00)|\\D", "");
                    try {
                        if (!dataFromCell.isEmpty()) {
                            quantityVystavka = Integer.parseInt(dataFromCel2);
                        }
                    } catch (NumberFormatException n) {
                        System.out.println("code " + code + " " + dataFromCell + "  x" + dataFromCel2);
                        exit(15);
                    }
                }


                if ((quantityVystavka >= 0) && (!flagToStop) && (!propuskaem)) {
                    ItemTable item;
                    HSSFCell nameCell = sheet.getRow(iPos + shiftV).getCell(columnName);
                    DataFormatter fmtName = new DataFormatter();
                    String name = fmtName.formatCellValue(nameCell);
                    item = new ItemTable(code, quantityCentral, quantityVystavka, name);
                    itemtableList.add(item);

                }
                iPos++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemtableList;
    }

    private static int base8StockFinder() {
        return 6;
    }

    private static int centralStockFinder() {
        return 7;
    }


    private static int nameItemColumnFinder() {
        return 2;
    }

    private static int groupColumnFinder() {
        return 0;
    }


    private static int codeColumnFinder() {
        return 1;
    }

}
