package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IRoomService {

    public EntityResult roomQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;

    public EntityResult roomInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    public EntityResult roomUpdate(Map<String, Object> attrMap, Map<?, ?> keyMap) throws OntimizeJEERuntimeException;

    public EntityResult roomDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
