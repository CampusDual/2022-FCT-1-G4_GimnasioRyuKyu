package org.example.model.core.service;

import ch.qos.logback.core.net.server.Client;
import com.ontimize.jee.common.db.SQLStatementBuilder;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.api.core.service.IClientService;

import org.example.model.core.dao.ClientDao;
import org.example.model.core.dao.SubscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Lazy
@Service("ClientService")
public class ClientService implements IClientService {

    @Autowired ClientDao clientDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public EntityResult clientQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
       /* for(int i=0;i<attrList.size();i++) {
            Map<String, Object> values = this.daoHelper.query(this.clientDao, keyMap, attrList).getRecordValues(i);
            //System.out.println(values);
            updateActive(values);
        } */

       // System.out.println(daoHelper.query(this.clientDao, keyMap, attrList));
                return this.daoHelper.query(this.clientDao, keyMap, attrList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EntityResult clientInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> nonCandidateData = removeNonRelatedData(attrMap, ClientDao.ID_SUBSCRIPTION);
        this.insertNonRelatedData(nonCandidateData);
        attrMap.putAll(nonCandidateData);
        return this.daoHelper.insert(this.clientDao, attrMap);
    }

    @Override
    public EntityResult clientDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientDao, keyMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EntityResult clientUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        Map<String, Object> nonClientData = removeNonRelatedData(attrMap, ClientDao.ID_SUBSCRIPTION);
        this.insertNonRelatedData(nonClientData);
        attrMap.putAll(nonClientData);
        updateExpirationDate(attrMap);
        return this.daoHelper.update(this.clientDao, attrMap, keyMap);
    }

    private void insertNonRelatedData(Map<String, Object> nonClientData) {
        for (Map.Entry<String, Object> entry : nonClientData.entrySet()) {
            Map<String, Object> data = new HashMap<String, Object>();
            List<String> attr = new ArrayList<String>();
            EntityResult toret, query;
            switch (entry.getKey()) {
                case ClientDao.ID_SUBSCRIPTION:
                    data.put(SubscriptionDao.SUB_MONTHS, entry.getValue());
                    data.put(SubscriptionDao.PRICE, entry.getValue());
                    attr.add(SubscriptionDao.ID);
                    query = this.subscriptionService.subscriptionQuery(data, attr);
                    if (query.calculateRecordNumber() > 0) {
                        entry.setValue(query.getRecordValues(0).get(SubscriptionDao.ID));
                    } else {
                        toret = this.subscriptionService.subscriptionInsert(data);
                        entry.setValue(toret.get(SubscriptionDao.ID));
                    }
                    break;
                default: break;
            }
        }
    }

    private Map<String, Object> removeNonRelatedData(Map<String, Object> attrMap, String... attrToExclude) {
        HashMap<String, Object> data = new HashMap<String, Object>();

        for (String attr : attrToExclude) {

            if (attrMap.containsKey(attr) && attrMap.get(attr) instanceof String) {
                data.put(attr, attrMap.remove(attr));
            }
        }
        return data;
    }

    private void updateExpirationDate(Map<String, Object> attrMap){
        for(Map.Entry<String,Object> entry:attrMap.entrySet()){
            if(entry.getKey().equals(ClientDao.ID_SUBSCRIPTION )){
                int id_sub= (int) entry.getValue();
                Map<String,Object> keys=new HashMap<>();
                keys.put(ClientDao.ID_SUBSCRIPTION,id_sub);
                List<String> attrList=new ArrayList<>();
                attrList.add(SubscriptionDao.SUB_MONTHS);
                attrList.add(SubscriptionDao.PRICE);
                EntityResult entityResult=this.daoHelper.query(clientDao,keys,attrList,ClientDao.CLIENT_SUB);
                Object months=  entityResult.get(SubscriptionDao.SUB_MONTHS);
                System.out.println(entityResult);

                //System.out.println(id_sub);
              /*  Calendar c=Calendar.getInstance();
                c.add(Calendar.MONTH,2);
                attrMap.put(clientDao.SUB_EXPIRATION_DATE,c); */
            }
        }
    }

    private void updateActive(Map<String, Object> attrMap){
        Calendar today=Calendar.getInstance();
        Calendar expirationDate= (Calendar) attrMap.get(clientDao.SUB_EXPIRATION_DATE);
       // System.out.println("exp: "+expirationDate);
       // System.out.println("tod: "+today);

        if(today.compareTo(expirationDate)>0 && expirationDate!=null){
            attrMap.put(clientDao.ACTIVE,false);
        }


    }




}
