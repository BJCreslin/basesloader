import Models.ItemTable;
import service.GroupeItemReadANDPump;
import service.NeededItemTableService;
import service.ReadItemNeededXLS;

import java.util.List;

public class Solution {
    public static void main(String[] args) {

        List<ItemTable> itemTableList = ReadItemNeededXLS.getItemList();
        GroupeItemReadANDPump.actionFromList(itemTableList);
        NeededItemTableService.actionFromList(itemTableList);

    }


}
