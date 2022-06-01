package org.example.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("ClientClassDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/ClientClassDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ClientClassDao extends OntimizeJdbcDaoSupport {
    public static final String ATTR_ID_ROOM_CLASS = "id_room_class";
    public static final String ATTR_ID_CLIENT = "id_client";
    public static final String ATTR_ID_CLIENT_CLASS = "id_client_class";
    public static final String QUERY_CLIENT_CLASS = "CLIENTCLASS";
    public static final String QUERY_ALL_ROOM_CLASS = "ALLROOMCLASS";
    public static final String QUERY_ALL_CLIENT_CLASS = "ALLCLIENTCLASS";

}
