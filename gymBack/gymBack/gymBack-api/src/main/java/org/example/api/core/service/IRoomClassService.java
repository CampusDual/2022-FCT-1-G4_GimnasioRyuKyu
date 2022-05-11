package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IRoomClassService {

    public EntityResult roomClassQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult roomClassInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult roomClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult roomClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
