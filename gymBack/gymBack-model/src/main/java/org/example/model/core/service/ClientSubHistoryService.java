package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IClientSubHistoryService;
import org.example.model.core.dao.ClientSubHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("ClientSubHistoryService")
public class ClientSubHistoryService implements IClientSubHistoryService {

    @Autowired
    ClientSubHistory clientSubHistory;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult clientSubHistoryQuery(Map<String, Object> keyMap, List<?> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientSubHistory, keyMap, attrList);
    }

    @Override
    public EntityResult clientSubHistoryInsert(Map<String, Object> attrMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.clientSubHistory, attrMap);
    }

    @Override
    public EntityResult clientSubHistoryUpdate(Map<String, Object> attrMap, Map<String,Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.clientSubHistory, attrMap, keyMap);
    }

    @Override
    public EntityResult clientSubHistoryDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientSubHistory, keyMap);
    }
}