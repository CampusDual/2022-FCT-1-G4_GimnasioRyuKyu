package org.example.model.core.service;


import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

import org.example.api.core.service.IClientService;

import org.example.model.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Lazy
@Service("ClientService")
public class ClientService implements IClientService {

    @Autowired
    ClientDao clientDao;
    @Autowired
    ClientSubHistory clientSubHistoryDao;

    UserService userService;
    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    @Autowired
    private SubscriptionService subscriptionService;


    @Override
    public EntityResult clientQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList);
    }
    public EntityResult monthInscriptionsQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList, ClientDao.MONTH_INSCRIPTIONS_QUERY);
    }
    public EntityResult activeClientQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList, ClientDao.ACTIVE_CLIENTS_QUERY);
    }
    public EntityResult monthIncomeQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList, ClientDao.MONTH_INCOME_QUERY);
    }
    public EntityResult monthActivesQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList, ClientDao.MONTH_ACTIVES_QUERY);
    }
    public EntityResult activesVsInactivesQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientDao, keyMap, attrList, ClientDao.ACTIVES_VS_INACTIVES_QUERY);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public EntityResult clientInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> nonCandidateData = removeNonRelatedData(attrMap, ClientDao.ID_SUBSCRIPTION);
        this.insertNonRelatedData(nonCandidateData);
        attrMap.putAll(nonCandidateData);
        System.out.println(attrMap);
        addToUsers(attrMap);
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
        if(attrMap.containsKey(ClientDao.ID_SUBSCRIPTION)){
        addToHistoryUpdate(attrMap,keyMap);
        }
        return this.daoHelper.update(this.clientDao, attrMap, keyMap);
    }

    private void insertNonRelatedData(Map<String, Object> nonClientData) {
        for (Map.Entry<String, Object> entry : nonClientData.entrySet()) {
            Map<String, Object> data = new HashMap<>();
            List<String> attr = new ArrayList<>();
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
        HashMap<String, Object> data = new HashMap<>();

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
                    attrMap.put(ClientDao.SUB_EXPIRATION_DATE, c);
            }
        }
    }



    private EntityResult addToHistoryUpdate(Map<String,Object> attrMap, Map<String,Object> keyMap){
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        int id_client= (int) keyMap.get(ClientDao.ID);
        int id_sub=(int) attrMap.get(ClientDao.ID_SUBSCRIPTION);

        Map<String,Object> attr=new HashMap<>();
        attr.put(ClientSubHistory.ATTR_ID_SUB,id_sub);
        attr.put(ClientSubHistory.ATTR_ID_CLIENT,id_client);
        attr.put(ClientSubHistory.ATTR_TIMESTAMP,timestamp);

        return this.daoHelper.insert(this.clientSubHistoryDao, attr);
    }

    private void addToUsers(Map<String, Object> attrMap){
        Map<String, Object> attrMapUsers =new HashMap<>();
        attrMapUsers.put(UserDao.ID, attrMap.get(ClientDao.EMAIL));
        attrMapUsers.put(UserDao.NAME, attrMap.get(ClientDao.NAME));
        attrMapUsers.put(UserDao.SURNAME, attrMap.get(ClientDao.LASTNAME));
        attrMapUsers.put(UserDao.PASSWORD, "abc123.");
        attrMapUsers.put(UserDao.DNI,attrMap.get(ClientDao.DNI) );
        attrMapUsers.put(UserDao.EMAIL, attrMap.get(ClientDao.EMAIL));
        System.out.println(attrMapUsers);
        this.daoHelper.insert(userDao,attrMapUsers);
        Map<String,Object> attrRoleUser=new HashMap<String,Object>();
        attrRoleUser.put(UserRoleDao.USER_,attrMapUsers.get(UserDao.ID));
        attrRoleUser.put(UserRoleDao.ID_ROLENAME,"1");
        this.daoHelper.insert(userRoleDao,attrRoleUser);
    }

    private void updateToUsers(Map<String,Object> attrMap, Map<String,Object> keyMap){
        Map<String,Object> keySetUser=new HashMap<>();
        keySetUser.put(UserDao.ID,getIdUser(keyMap));
        Map<String,Object> mapUser=new HashMap<>();
        if(attrMap.containsKey(ClientDao.PASSWORD)){
            mapUser.put(UserDao.PASSWORD,attrMap.get(ClientDao.PASSWORD));
        }
        if(attrMap.containsKey(ClientDao.NAME)){
            mapUser.put(UserDao.NAME,attrMap.get(ClientDao.NAME));
        }
        if(attrMap.containsKey(ClientDao.LASTNAME)){
            mapUser.put(UserDao.SURNAME,attrMap.get(ClientDao.LASTNAME));
        }
        this.daoHelper.update(userDao, attrMap,mapUser);
    }

    private String getIdUser(Map<String,Object> keyMap){
        List<String> attr=new ArrayList<>();
        attr.add(ClientDao.EMAIL);
        EntityResult er=this.daoHelper.query(clientDao,keyMap,attr);

        List<Object> erList = (List<Object>) er.get(ClientDao.EMAIL);
        return erList.get(0).toString();
    }

}
