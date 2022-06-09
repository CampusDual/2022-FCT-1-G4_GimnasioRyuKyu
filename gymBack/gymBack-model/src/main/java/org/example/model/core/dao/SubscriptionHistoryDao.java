package org.example.model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("SubscriptionHistoryDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/SubscriptionHistoryDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class SubscriptionHistoryDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID_SUB = "id_sub";
    public static final String ATTR_SUB_TIMESTAMP = "sub_timestamp";
    public static final String ATTR_PRICE= "price";
    public static final String ATTR_MONTHS= "months";
    public static final String ATTR_ID_SUBHISTORY = "id_sub_history";
}