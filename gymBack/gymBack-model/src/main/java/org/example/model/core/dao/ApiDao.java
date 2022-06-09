package org.example.model.core.dao;


import com.ontimize.jee.server.dao.common.ConfigurationFile;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository("ApiDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/apiDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ApiDao extends OntimizeJdbcDaoSupport{
    public static final String ATTR_ID_ROOM_CLASS = "id_room_class";
    public static final String ATTR_DATE = "date";
    public static final String ATTR_H_START = "h_start";
    public static final String ATTR_H_END = "h_end";
    public static final String ATTR_MONITOR="id_monitor";

    public ApiDao(){
        super();
    }
}
