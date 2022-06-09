package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IClientSubHistoryService {
    public EntityResult clientSubHistoryQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult clientSubHistoryInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult clientSubHistoryUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult clientSubHistoryDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
