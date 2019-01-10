import Models.ItemTable;
import service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/***
 * Программа для вычисления данных для перемещения. Из Центральный на выставка в офисе.
 * по данным XLS файлов
 */


public class Solution {
    public static void main(String[] args) {

//Если надо записать в файл
         startToTxtFile();

//Если надо записать в базу




    }

    private static void startToTxtFile() {
        List<ItemTable> itemTableList = ReadItemNeededXLS.getItemList();
        List<ItemTable> itemTableListStock = ReadItemStockXLS.getItemList();
        Logger.getAnonymousLogger().info("Количество данных в остатках " + Integer.toString(itemTableListStock.size()));
        Logger.getAnonymousLogger().info("Количество данных в файле нужных остатков " + Integer.toString(itemTableListStock.size()));


        Map<Integer, ItemTable> itemTableMap = itemTableListStock.stream().collect(Collectors.toMap(x -> x.getCode(), x -> x));
        for (ItemTable itemTable : itemTableList) {
            if (itemTableMap.containsKey(itemTable.getCode())) {
                itemTable.setCentral(itemTableMap.get(itemTable.getCode()).getCentral());
                itemTable.setVystavka(itemTableMap.get(itemTable.getCode()).getVystavka());
            } else {
                itemTable.setCentral(0);
                itemTable.setVystavka(0);
            }

//            System.out.println(itemTable.getCode() + "    " + itemTable.getNeeded() +
//                    "   " + itemTable.getVystavka() + " " + itemTable.getCentral());
//
        }

        Logger.getAnonymousLogger().info(Integer.toString(itemTableList.size()));
        Map<ItemTable, Integer> mapForTransfer = new HashMap<>();


        for (ItemTable itemTable : itemTableList) {

            // Logger.getAnonymousLogger().info(itemTable.getNeeded().toString());
            if ((itemTable.getNeeded() > 0) && (itemTable.getCentral() > 0)) {

                if (itemTable.getNeeded() > itemTable.getVystavka() * 1.5) {
                    if ((itemTable.getNeeded() - itemTable.getCentral()) > 0) {
                        mapForTransfer.put(itemTable, itemTable.getCentral());
                    } else {
                        mapForTransfer.put(itemTable, itemTable.getNeeded() - itemTable.getVystavka());
                    }
                }
            }

        }
        Logger.getAnonymousLogger().info(Integer.toString(mapForTransfer.size()));

        WriteTXTFiles.saveTransferFile(mapForTransfer);
    }


}
