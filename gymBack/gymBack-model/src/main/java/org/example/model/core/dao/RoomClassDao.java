package org.example.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("RoomClassDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/RoomClassDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class RoomClassDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID_ROOM_CLASS = "id_room_class";
    public static final String ATTR_ID_ROOM = "id_room";
    public static final String ATTR_ID_CLASS = "id_class";
    public static final String ATTR_DATE = "date";
    public static final String ATTR_H_START = "h_start";
    public static final String ATTR_H_END = "h_end";
    public static final String ATTR_CAPACITY = "capacity";
    public static final String ATTR_MONITOR="id_monitor";

    public static final String QUERY_ROOM_CLASS = "ROOMCLASS";
    public static final String QUERY_ALL_ROOM_CLASS = "ALLROOMCLASS";
    public static final String ATTR_ALLROOMS_QUERY="ALLROOMS";



    public RoomClassDao(){
        super();
    }
}