package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IClientClassService;
import org.example.model.core.dao.ClientClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ClientClassService")
@Lazy
public class ClientClassService implements IClientClassService {

    @Autowired private ClientClassDao clientClassDao;

    @Autowired private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult clientCQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientClassDao, keyMap, attrList);
    }

    @Override
    public EntityResult clientCInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.clientClassDao, attrMap);
    }

    @Override
    public EntityResult clientCUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.clientClassDao, attrMap, keyMap);
    }

    @Override
    public EntityResult clientCDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientClassDao, keyMap);
    }

    @Override
    public EntityResult classCQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientClassDao, keyMap, attrList);
    }

    @Override
    public EntityResult classCInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.clientClassDao, attrMap);
    }

    @Override
    public EntityResult classCUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.clientClassDao, attrMap, keyMap);
    }

    @Override
    public EntityResult classCDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientClassDao, keyMap);
    }
}
