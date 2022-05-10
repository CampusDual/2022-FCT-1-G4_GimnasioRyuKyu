package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IClassService {
    public EntityResult classQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult classInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult classUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult classDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
