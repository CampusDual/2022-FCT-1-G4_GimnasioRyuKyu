package org.example.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


@Repository("ClientDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/ClientDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ClientDao extends OntimizeJdbcDaoSupport {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String DNI = "dni";
    public static final String GENDER = "gender";
    public static final String BIRTHDAY = "birthday";
    public static final String REGISTRATION_DATE = "registration_date";
    public static final String ACTIVE = "active";
    public static final String PHOTO = "photo";
    public static final String PASSWORD="password";
    public static final String ID_SUBSCRIPTION = "id_subscription";
    public static final String SUB_EXPIRATION_DATE = "sub_expiration_date";
    public static final String CLIENT_SUB="CLIENT_SUB_QUERY";
    public static final String ACTIVE_CLIENTS_QUERY="ACTIVE_CLIENTS";
    public static final String MONTH_INCOME_QUERY="MONTH_INCOME";
    public static final String MONTH_ACTIVES_QUERY="MONTH_ACTIVES";
    public static final String MONTH_INSCRIPTIONS_QUERY="MONTH_INSCRIPTIONS";
    public static final String ACTIVES_VS_INACTIVES_QUERY="ACTIVE_vs_INACTIVE";
}
