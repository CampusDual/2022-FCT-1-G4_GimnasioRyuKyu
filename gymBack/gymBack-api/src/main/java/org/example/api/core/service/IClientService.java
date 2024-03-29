package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IClientService {

    public EntityResult clientQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult monthInscriptionsQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult activeClientQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult monthIncomeQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult monthActivesQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult activesVsInactivesQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult clientInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult clientUpdate(Map<String, Object> attrMap, Map<String,Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult clientDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
