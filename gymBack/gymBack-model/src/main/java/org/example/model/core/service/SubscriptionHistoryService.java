package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.ISubscriptionHistoryService;
import org.example.model.core.dao.SubscriptionHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("SubscriptionHistoryService")
public class SubscriptionHistoryService implements ISubscriptionHistoryService {

    @Autowired
    SubscriptionHistoryDao subscriptionHistoryDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult subscriptionHistoryQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.subscriptionHistoryDao, keyMap, attrList);
    }

    @Override
    public EntityResult subscriptionHistoryInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.subscriptionHistoryDao, attrMap);
    }

    @Override
    public EntityResult subscriptionHistoryUpdate(Map<String, Object> attrMap, Map<String,Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.subscriptionHistoryDao, attrMap, keyMap);
    }

    @Override
    public EntityResult subscriptionHistoryDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.subscriptionHistoryDao, keyMap);
    }
}
