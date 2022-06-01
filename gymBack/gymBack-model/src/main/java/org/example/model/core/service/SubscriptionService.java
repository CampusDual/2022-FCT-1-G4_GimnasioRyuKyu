package org.example.model.core.service;


import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.ISubscriptionService;
import org.example.model.core.dao.SubscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("SubscriptionService")
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    SubscriptionDao subscriptionDao;
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
        return this.daoHelper.insert(this.subscriptionDao, attrMap);
    }

    @Override
    public EntityResult subscriptionUpdate(Map<String, Object> attrMap, Map<?, ?> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.subscriptionDao, attrMap, keyMap);
    }

    @Override
    public EntityResult subscriptionDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.subscriptionDao, keyMap);
    }
}
