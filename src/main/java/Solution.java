import Models.ItemTable;
import service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {

        List<ItemTable> itemTableList = ReadItemNeededXLS.getItemList();
        List<ItemTable> itemTableListStock = ReadItemStockXLS.getItemList();
        Logger.getAnonymousLogger().info("xls "+Integer.toString(itemTableListStock.size()));


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


        //  GroupeItemReadANDPump.actionFromList(itemTableList);
        // NeededItemTableService.actionFromList(itemTableList);

    }


}
