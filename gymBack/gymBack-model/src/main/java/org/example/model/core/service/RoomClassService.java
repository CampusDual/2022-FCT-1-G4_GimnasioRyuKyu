package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IRoomClassService;
import org.example.model.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service("RoomClassService")
@Lazy
public class RoomClassService implements IRoomClassService {

    @Autowired
    private RoomClassDao roomClassDao;

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult roomClassQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.roomClassDao, keyMap, attrList, RoomClassDao.QUERY_ROOM_CLASS);
    }

    @Override
    public EntityResult roomClassInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        try {
            transformToHour(attrMap);
            checkMaxCapacity(getIdRoomNew(attrMap), attrMap);
            return this.daoHelper.insert(this.roomClassDao, attrMap);
        } catch (Exception e) {
            EntityResult toret = new EntityResultMapImpl();
            toret.setCode(EntityResult.OPERATION_WRONG);
            toret.setMessage("ROOM_TIME_ERROR");
            return toret;
        }
    }

    @Override
    public EntityResult roomClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        if (attrMap.containsKey("id_assing_class")) {
            attrMap.put("id_room_class", attrMap.remove("id_assing_class"));
        }
        try {
            transformToHour(attrMap);
            checkMaxCapacity(getIdRoomUpdate(attrMap, keyMap), attrMap);
            return this.daoHelper.update(this.roomClassDao, attrMap, keyMap);
        } catch (Exception e) {
            EntityResult toret = new EntityResultMapImpl();
            toret.setCode(EntityResult.OPERATION_WRONG);
            toret.setMessage("ROOM_TIME_ERROR");
            return toret;
        }
    }

    @Override
    public EntityResult roomClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.roomClassDao, keyMap);
    }

    private void transformToHour(Map<String, Object> attrMap) {
        for (Map.Entry<String, Object> entry : attrMap.entrySet()) {
            if (entry.getKey().equals(RoomClassDao.ATTR_H_START)) {
                Timestamp h_start;
                h_start = (Timestamp) entry.getValue();
                Time h_start_time = new Time(h_start.getTime());
                attrMap.put(RoomClassDao.ATTR_H_START, h_start_time);
            }
            if (entry.getKey().equals(RoomClassDao.ATTR_H_END)) {
                Timestamp h_end;
                h_end = (Timestamp) entry.getValue();
                Time h_end_time = new Time(h_end.getTime());
                attrMap.put(RoomClassDao.ATTR_H_END, h_end_time);
            }
        }
    }


    private void checkMaxCapacity(int idRoom, Map<String, Object> attrMap) {
        List<String> attrList = new ArrayList<>();
        Map<String, Object> keys = new HashMap<>();
        int capacity = 0;
        if(attrMap.containsKey(RoomClassDao.ATTR_CAPACITY)) {
            capacity = (int) attrMap.get(RoomClassDao.ATTR_CAPACITY);
            keys.put(RoomDao.ATTR_ID, idRoom);
            attrList.add(RoomDao.ATTR_MAX_ROOM_CAPACITY);
            EntityResult entityResult = this.daoHelper.query(roomClassDao, keys, attrList, RoomClassDao.ATTR_ALLROOMS_QUERY);
            List<Object> maxCapacities = (List<Object>) entityResult.get(RoomDao.ATTR_MAX_ROOM_CAPACITY);
            String maxCapacityString = maxCapacities.get(0).toString();
            int maxCapacity = Integer.parseInt(maxCapacityString);
            if (capacity > maxCapacity) {
                attrMap.put(RoomClassDao.ATTR_CAPACITY, maxCapacity);
            }
        }
    }

    private int getIdRoomNew(Map<String, Object> attrMap) {
        return (int) attrMap.get(RoomClassDao.ATTR_ID_ROOM);
    }

    private int getIdRoomUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {
        if (attrMap.containsKey(RoomClassDao.ATTR_ID_ROOM)) {
            return getIdRoomNew(attrMap);
        }
        List<String> list = new ArrayList<>();
        list.add(RoomClassDao.ATTR_ID_ROOM);
        EntityResult entityResult = this.daoHelper.query(roomClassDao, keyMap, list, RoomClassDao.QUERY_ALL_ROOM_CLASS);
        List<Object> idRooms = (List<Object>) entityResult.get(RoomClassDao.ATTR_ID_ROOM);
        String idRoomString = idRooms.get(0).toString();
        return Integer.parseInt(idRoomString);
    }


}
