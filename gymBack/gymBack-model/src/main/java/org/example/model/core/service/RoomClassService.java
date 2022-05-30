package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IRoomClassService;
import org.example.model.core.dao.ClientClassDao;
import org.example.model.core.dao.ClientDao;
import org.example.model.core.dao.RoomClassDao;
import org.example.model.core.dao.SubscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("RoomClassService")
@Lazy
public class RoomClassService implements IRoomClassService {

    @Autowired private RoomClassDao roomClassDao;

    @Autowired private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult roomClassQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.roomClassDao, keyMap, attrList, RoomClassDao.QUERY_ROOM_CLASS);
    }

    @Override
    public EntityResult roomClassInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.roomClassDao, attrMap);
    }

    @Override
    public EntityResult roomClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        if (attrMap.containsKey("id_assing_class")) {
            attrMap.put("id_room_class", attrMap.remove("id_assing_class"));
        }
        return this.daoHelper.update(this.roomClassDao, attrMap, keyMap);
    }

    @Override
    public EntityResult roomClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.roomClassDao, keyMap);
    }

    private void isValidHour(Map<String,Object> attrMap){
        for(Map.Entry<String,Object> entry:attrMap.entrySet()){
            Timestamp h_start_o, h_end_o;
            Date date_o;
            if(entry.getKey().equals(RoomClassDao.ATTR_H_START)){
                h_start_o= (Timestamp) entry.getValue();
                System.out.println(h_start_o);
            }
            if(entry.getKey().equals(RoomClassDao.ATTR_H_END)){
                h_end_o= (Timestamp) entry.getValue();
                System.out.println(h_end_o);
            }
            if(entry.getKey().equals(RoomClassDao.ATTR_DATE)){
                date_o= (Date) entry.getValue();
                System.out.println(date_o);
            }

            }
    }

}
