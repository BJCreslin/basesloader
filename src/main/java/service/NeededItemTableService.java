package service;

import Models.ItemTable;
import entities.NeededtableEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class NeededItemTableService {

    public static Map<Integer, NeededtableEntity> actionFromList(List<ItemTable> itemTableList) {
        //Создает Сет из данных о Группе товара
        final Map<Integer, NeededtableEntity> itemTableMap = new HashMap<>();

        if (itemTableList.size() > 0) {

            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            //Удаляем все старые данные
            Transaction tx1 = session.beginTransaction();
            session.createQuery("DELETE FROM NeededtableEntity ").executeUpdate();
            tx1.commit();

            //Записываем новые данные

            itemTableList.stream().filter(p -> !p.getGroupname().isEmpty()).forEach(p
                    -> itemTableMap.put(p.getCode(),
                    new NeededtableEntity(p.getCode(), p.getNeeded())));

            Transaction tx2 = session.beginTransaction();
            itemTableMap.forEach((k, v) -> session.save(new NeededtableEntity(k, v.getQuantity())));
//            itemTableList.forEach(p -> session.save(new
//                    NeededtableEntity(p.getCode(), p.getNeeded())));
            tx2.commit();

            // В мапу записываем данные из Лист

        }
        return itemTableMap;
    }
}
