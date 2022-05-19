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
}
