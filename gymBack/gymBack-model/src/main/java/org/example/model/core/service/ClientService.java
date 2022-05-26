package org.example.model.core.service;

import ch.qos.logback.core.net.server.Client;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IClientService;

import org.example.model.core.dao.ClientDao;
import org.example.model.core.dao.SubscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
                return this.daoHelper.query(this.clientDao, keyMap, attrList);
    }

    @Override
    public EntityResult clientInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.clientDao, attrMap);
    }

    @Override
    public EntityResult clientUpdate(Map<String, Object> attrMap, Map<?, ?> keyMap)
            throws OntimizeJEERuntimeException {
     /*   List<String> attr = new ArrayList<String>();
        attr.add(ClientDao.SUB_EXPIRATION_DATE);
        this.daoHelper.query(this.clientDao,attrMap,attr);
        Calendar c; */
        return this.daoHelper.update(this.clientDao, attrMap, keyMap);
    }

    @Override
    public EntityResult clientDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientDao, keyMap);
    }

    private void insertNonRelatedData(Map<String, Object> nonCandidateData) {
        for (Map.Entry<String, Object> entry : nonCandidateData.entrySet()) {
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

}
