package org.example.model.core.service;


import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.ISubscriptionService;
import org.example.model.core.dao.ClassDao;
import org.example.model.core.dao.RoomDao;
import org.example.model.core.dao.SubscriptionDao;
import org.example.model.core.dao.SubscriptionHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.sql.Timestamp;
import java.util.*;

@Lazy
@Service("SubscriptionService")
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    SubscriptionDao subscriptionDao;
    @Autowired
    SubscriptionHistoryDao subscriptionHistoryDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult subscriptionQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.subscriptionDao, keyMap, attrList);
    }

    @Override
    public EntityResult subscriptionInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        EntityResult toret = this.daoHelper.insert(this.subscriptionDao, attrMap);
        return addToSubHistoryNew(attrMap,toret);
    }

    @Override
    public EntityResult subscriptionUpdate(Map<String, Object> attrMap, Map<String,Object> keyMap)
            throws OntimizeJEERuntimeException {
        this.daoHelper.update(this.subscriptionDao, attrMap, keyMap);
        return addToSubHistoryUpdate(attrMap,keyMap);
    }

    @Override
    public EntityResult subscriptionDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.subscriptionDao, keyMap);
    }

    public EntityResult showSubsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.subscriptionDao, keyMap, attrList, SubscriptionDao.SHOW_SUBS_QUERY);
    }


    private EntityResult addToSubHistoryNew(Map<String,Object> attrMap,EntityResult entityResult){
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        int id_sub= (int) entityResult.get(SubscriptionDao.ID);
        float price;
        int months;

            price=(float) attrMap.get(SubscriptionDao.PRICE);
            months=(int) attrMap.get(SubscriptionDao.SUB_MONTHS);

        Map<String,Object> attr=new HashMap<>();
        attr.put(SubscriptionHistoryDao.ATTR_ID_SUB,id_sub);
        attr.put(SubscriptionHistoryDao.ATTR_PRICE,price);
        attr.put(SubscriptionHistoryDao.ATTR_SUB_TIMESTAMP,timestamp);
        attr.put(SubscriptionHistoryDao.ATTR_MONTHS,months);
        return this.daoHelper.insert(this.subscriptionHistoryDao, attr);
    }

    private EntityResult addToSubHistoryUpdate(Map<String,Object> attrMap,Map<String,Object> keyMap){
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        int id_sub= (int) keyMap.get(SubscriptionDao.ID);
        float price;
        int months;

        if(attrMap.containsKey(SubscriptionDao.PRICE)){
            price=(float) attrMap.get(SubscriptionDao.PRICE);
        }else{
            price=getSubPrice(keyMap);
        }
        if(attrMap.containsKey(SubscriptionDao.SUB_MONTHS)){
            months=(int)attrMap.get(SubscriptionDao.SUB_MONTHS);
        }else{
            months=getMonths(keyMap);
        }
        Map<String,Object> attr=new HashMap<>();
        attr.put(SubscriptionHistoryDao.ATTR_ID_SUB,id_sub);
        attr.put(SubscriptionHistoryDao.ATTR_PRICE,price);
        attr.put(SubscriptionHistoryDao.ATTR_SUB_TIMESTAMP,timestamp);
        attr.put(SubscriptionHistoryDao.ATTR_MONTHS,months);
       return this.daoHelper.insert(this.subscriptionHistoryDao, attr);
    }


    private float getSubPrice(Map<String,Object> keySet){
        List<String> list=new ArrayList<>();
        list.add(SubscriptionDao.PRICE);
        EntityResult entityResult=subscriptionQuery(keySet,list);
        List<Object> result = (List<Object>) entityResult.get(SubscriptionDao.PRICE);
        String priceString = result.get(0).toString();
        float price = Float.valueOf(priceString);
        return price;
    }

    private int getMonths(Map<String,Object> keySet){
        List<String> list=new ArrayList<>();
        list.add(SubscriptionDao.SUB_MONTHS);
        EntityResult entityResult=subscriptionQuery(keySet,list);
        List<Object> result = (List<Object>) entityResult.get(SubscriptionDao.SUB_MONTHS);
        String monthsString = result.get(0).toString();
        int months = Integer.parseInt(monthsString);
        return months;
    }
}
