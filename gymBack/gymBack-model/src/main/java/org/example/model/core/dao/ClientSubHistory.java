package org.example.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("ClientSubHistory")
@Lazy
@ConfigurationFile(configurationFile = "dao/ClientSubHistoryDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ClientSubHistory extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID_SUB = "id_sub";
    public static final String ATTR_TIMESTAMP = "timestamp";
    public static final String ATTR_ID_CLIENT= "id_client";
    public static final String ATTR_ID_CLIENT_SUBS_HISTORY = "id_client_subs_history";
}