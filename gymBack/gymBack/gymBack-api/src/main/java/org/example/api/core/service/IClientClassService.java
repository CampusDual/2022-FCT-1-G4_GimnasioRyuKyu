package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IClientClassService {
    public EntityResult clientCQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult clientCInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult clientCUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult clientCDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    public EntityResult classCQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult classCInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult classCUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult classCDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
