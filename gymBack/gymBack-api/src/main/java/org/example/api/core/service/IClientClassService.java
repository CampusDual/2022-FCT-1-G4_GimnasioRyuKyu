package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IClientClassService {
    public EntityResult clientClassQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult clientClassInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult clientClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult clientClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    public EntityResult showClientsClassesQuery(Map<String,Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
}
