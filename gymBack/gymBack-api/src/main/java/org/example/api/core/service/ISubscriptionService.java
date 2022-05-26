package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface ISubscriptionService {
    public EntityResult subscriptionQuery(Map<String, Object> keyMap, List<?> attrList) throws OntimizeJEERuntimeException;

    public EntityResult subscriptionInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    public EntityResult subscriptionUpdate(Map<String, Object> attrMap, Map<?, ?> keyMap) throws OntimizeJEERuntimeException;

    public EntityResult subscriptionDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;
}
