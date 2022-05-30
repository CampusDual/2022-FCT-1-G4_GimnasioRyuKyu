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
        if(!keyMap.isEmpty()) {
            updateActive(keyMap);
        }
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
                    int id_sub = (int) entry.getValue();
                    Map<String, Object> keys = new HashMap<>();
                    keys.put(SubscriptionDao.ID, id_sub);
                    List<String> attrList = new ArrayList<>();
                    attrList.add(SubscriptionDao.SUB_MONTHS);
                    attrList.add(SubscriptionDao.PRICE);
                    EntityResult entityResult = this.daoHelper.query(clientDao, keys, attrList, ClientDao.CLIENT_SUB);
                    List<Object> months = (List<Object>) entityResult.get(SubscriptionDao.SUB_MONTHS);
                    String monthString = months.get(0).toString();
                    int month = Integer.parseInt(monthString);


                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.MONTH, month);
                    attrMap.put(clientDao.SUB_EXPIRATION_DATE, c);
            }
        }
    }


    private void updateActive(Map<String, Object> keyMap){
        List<String> attrList=new ArrayList<>();
        attrList.add(ClientDao.SUB_EXPIRATION_DATE);
        try {
            EntityResult entityResult = this.daoHelper.query(clientDao, keyMap, attrList, ClientDao.CLIENT_Q);

            List<Object> expirations = (List<Object>) entityResult.get(ClientDao.SUB_EXPIRATION_DATE);
            String expDateString = expirations.get(0).toString();
            String expDateArray[] = expDateString.split("-");
            int year = Integer.parseInt(expDateArray[0]);
            int month = Integer.parseInt(expDateArray[1]);
            int day = Integer.parseInt(expDateArray[2]);
            Calendar expDate = new GregorianCalendar(year, month, day);

            Calendar today = Calendar.getInstance();

            Map<String, Object> attrMap=new HashMap<>();
            if(expDate.compareTo(today)<0){
                attrMap.put(ClientDao.ACTIVE,false);
            }else{
                attrMap.put(ClientDao.ACTIVE,true);
            }
            clientUpdate(attrMap,keyMap);

        }
        catch(NullPointerException e){
            System.out.println("El cliente no tiene suscripción");
        }

    }




}
