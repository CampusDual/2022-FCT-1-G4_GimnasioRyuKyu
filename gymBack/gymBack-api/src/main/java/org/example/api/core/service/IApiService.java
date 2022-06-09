package org.example.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import java.util.List;
import java.util.Map;

public interface IApiService {
    public EntityResult apiQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;
}
