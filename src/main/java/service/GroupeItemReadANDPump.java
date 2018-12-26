package service;

import Models.ItemTable;
import entities.GroupitemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;


public class GroupeItemReadANDPump {


    /**
     * Читает данные из xls файла и заливает в базу
     */
    public static void actionFULL() {
        //чтение файла
        List<ItemTable> itemTableList = ReadItemNeededXLS.getItemList();
        actionFromList(itemTableList);
    }


    public static Map<String, GroupitemEntity> actionFromList(List<ItemTable> itemTableList) {
        //Создает Сет из данных о Группе товара
        final Set<String> groupitemEntitySet = new HashSet<>();
        itemTableList.stream().filter(p -> !p.getGroupname().isEmpty()).forEach(p -> groupitemEntitySet.add(p.getGroupname()));

        // Читает данные (Лист) о текущих группах из базы
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<GroupitemEntity> listGroupeFromBase = session.createCriteria(GroupitemEntity.class).list();
        tx1.commit();

        //Если данные в Сете есть уже в базе, то удаляет их из Сета
        listGroupeFromBase.forEach(p -> groupitemEntitySet.remove(p.getName()));

        //Если в Сете есть новые данные, то добавляет их в базу
        if (!groupitemEntitySet.isEmpty()) {
            Transaction tx2 = session.beginTransaction();
            groupitemEntitySet.forEach(p ->session.save(new GroupitemEntity(p)));
            tx2.commit();
        }


        Transaction tx3 = session.beginTransaction();
        listGroupeFromBase = session.createCriteria(GroupitemEntity.class).list();
        tx3.commit();
        Map<String, GroupitemEntity> entityMap = new HashMap<>();

        listGroupeFromBase.forEach(p -> {
            entityMap.put(p.getName(), p);
        });
        return entityMap;
    }


}
