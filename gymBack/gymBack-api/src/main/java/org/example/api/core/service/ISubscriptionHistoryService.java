package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface ISubscriptionHistoryService {
    public EntityResult subscriptionHistoryQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;
    public EntityResult subscriptionHistoryInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;
    public EntityResult subscriptionHistoryUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
    public EntityResult subscriptionHistoryDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
