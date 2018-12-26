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

public class ReadItemNeededXLS {
    public static String PATHTOFILES = "\\\\Post\\креслин владимир\\перемещение\\";
    public static String NAMEXLSFILEWITHNEEDEDOSTATKIBAZA8 = "Копия ПЕРЕМЕЩЕНИЯ.xls";  // Файл с данными по вместимости ячеек


    public static List<ItemTable> getItemList() {
        List<ItemTable> itemtableList = new ArrayList<>();
        try (FileInputStream inputStreamFile = new FileInputStream(new File
                (PATHTOFILES + NAMEXLSFILEWITHNEEDEDOSTATKIBAZA8))) {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStreamFile);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);


            int iPos = 0;
            int shiftV = 7;
            int columnCode = codeColumnFinder();
            int stolbecNumber = 6;
            int columnName = nameItemColumnFinder();
            int columnGroupe = groupColumnFinder();


            boolean flagToStop = false;
            while (!flagToStop) {
                int code = 0;
                boolean propuskaem = false;

                HSSFCell codeCell;

                try {
                    codeCell = sheet.getRow(iPos + shiftV).getCell(columnCode);
                } catch (NullPointerException npe) {
                    if (iPos > 10000) {
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

                int quantity = 0;
                HSSFCell quantityCell = sheet.getRow(iPos + shiftV).getCell(stolbecNumber);
                DataFormatter fmt = new DataFormatter();
                String dataFromCell = fmt.formatCellValue(quantityCell);

                String dataFromCel2 = dataFromCell.replaceAll("(,00)|\\D", "");
                try {
                    if (!dataFromCell.isEmpty()) {
                        quantity = Integer.parseInt(dataFromCel2);
                    }
                } catch (NumberFormatException n) {
                    System.out.println("code " + code + " " + dataFromCell + "  x" + dataFromCel2);
                    exit(15);
                }


                if ((quantity >= 0) && (!flagToStop) && (!propuskaem)) {
                    ItemTable item;
                    HSSFCell nameCell = sheet.getRow(iPos + shiftV).getCell(columnName);
                    DataFormatter fmtName = new DataFormatter();
                    String name = fmtName.formatCellValue(nameCell);
                    item = new ItemTable(code, quantity, name);

                    /*Записываем группу в item*/

                    HSSFCell groupCell = sheet.getRow(iPos + shiftV).getCell(columnGroupe);
                    DataFormatter fmtGroupe = new DataFormatter();
                    String groupeName = fmtGroupe.formatCellValue(groupCell);
                    item.setGroupname(groupeName);


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



