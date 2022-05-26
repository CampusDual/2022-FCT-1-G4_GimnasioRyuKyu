package org.example.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("SubscriptionDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/SubscriptionDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class SubscriptionDao extends OntimizeJdbcDaoSupport {

    public static final String ID = "id";
    public static final String PRICE = "price";
    public static final String SUB_MONTHS = "sub_months";

}