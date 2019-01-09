package service;


import Models.ItemTable;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class WriteTXTFiles {
    public static String NAMEOUTFILE = "out.txt";
    public static String PATHTOFILES = "\\\\Post\\креслин владимир\\перемещение\\";

    public static void saveTransferFile(Map<ItemTable, Integer> mapForOut) {

        try (OutputStreamWriter fileOutTXT = new OutputStreamWriter(new FileOutputStream(PATHTOFILES + NAMEOUTFILE, false),
                "UTF-16")) {
            if (mapForOut.size() > 0) {
                fileOutTXT.write("счет=10\n" +
                        "Дата=" + dateOut() + "\n" +
                        "Клиент=8\n");
                for (Map.Entry<ItemTable, Integer> entry : mapForOut.entrySet()) {
                    fileOutTXT.write("Следтовар\n");
                    fileOutTXT.write("Количество=" + entry.getValue() + "\n");
                    fileOutTXT.write("Кодтовара=" + entry.getKey().getCode() + "\n");
                    fileOutTXT.write("Цена=308\n");
                }
            }
            fileOutTXT.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String dateOut() {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", new Locale("ru"));
        Date currentDate = new Date();
        return dateFormat.format(currentDate);

    }

//    public static void saveTransferFile(HashMap<Integer, Integer> mapForOut, String nammm) {
//
//        try (OutputStreamWriter fileOutTXT = new OutputStreamWriter(new FileOutputStream(PATHTOFILES + nammm, false),
//                "UTF-16")) {
//            if (mapForOut.size() > 0) {
//                fileOutTXT.write("счет=10\n" +
//                        "Дата=" + ConsoleCommand.dateOut() + "\n" +
//                        "Клиент=8\n");
//                for (Map.Entry<Integer, Integer> entry : mapForOut.entrySet()) {
//                    fileOutTXT.write("Следтовар\n");
//                    fileOutTXT.write("Количество=" + entry.getValue() + "\n");
//                    fileOutTXT.write("Кодтовара=" + entry.getKey() + "\n");
//                    fileOutTXT.write("Цена=308\n");
//                }
//            }
//            fileOutTXT.flush();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
