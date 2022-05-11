package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IRoomClassService {

    public EntityResult roomCQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult roomCInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult roomCUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult roomCDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    // OFFER STATUS
    public EntityResult classRQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
    public EntityResult classRInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult classRUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult classRDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
