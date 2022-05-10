package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IClientService;

import org.example.model.core.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Lazy
@Service("ClientService")
public class ClientService implements IClientService {

    @Autowired ClientDao clientDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

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
        return this.daoHelper.update(this.clientDao, attrMap, keyMap);
    }

    @Override
    public EntityResult clientDelete(Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientDao, keyMap);
    }
}
