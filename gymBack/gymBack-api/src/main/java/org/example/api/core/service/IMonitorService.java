package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IMonitorService {

    public EntityResult monitorQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult monitorInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult monitorUpdate(Map<String, Object> attrMap, Map<String,Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult monitorDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
