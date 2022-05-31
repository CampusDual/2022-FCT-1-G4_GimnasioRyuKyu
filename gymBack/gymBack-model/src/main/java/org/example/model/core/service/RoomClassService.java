package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IRoomClassService;
import org.example.model.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
        transformToHour(attrMap);
        checkMaxCapacityNew(attrMap);
        return this.daoHelper.insert(this.roomClassDao, attrMap);
    }

    @Override
    public EntityResult roomClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        if (attrMap.containsKey("id_assing_class")) {
            attrMap.put("id_room_class", attrMap.remove("id_assing_class"));
        }
        transformToHour(attrMap);
        checkMaxCapacityUpdate(attrMap,keyMap);
        return this.daoHelper.update(this.roomClassDao, attrMap, keyMap);
    }

    @Override
    public EntityResult roomClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.roomClassDao, keyMap);
    }

    private void transformToHour(Map<String, Object> attrMap) {
        for (Map.Entry<String, Object> entry : attrMap.entrySet()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
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

  /*  private boolean isValidHour(Map<String, Object> attrMap) {
        for (Map.Entry<String, Object> entry : attrMap.entrySet()) {
            Timestamp h_start, h_end;
            Date date;
            Map<String, Object> keys = new HashMap<>();
            List<String> attrList = new ArrayList<>();
            if (entry.getKey().equals(RoomClassDao.ATTR_DATE)) {
                date = (Date) entry.getValue();
                keys.put(RoomClassDao.ATTR_DATE, date);
            }
            if (entry.getKey().equals(RoomClassDao.ATTR_H_START)) {
                h_start = (Timestamp) entry.getValue();
                System.out.println("h_s" + h_start);
                Time h_start_time = new Time(h_start.getTime()); //se puede pasar de timestamp a time ez
                keys.put(RoomClassDao.CAST_H_START, h_start_time);
            }
            if (entry.getKey().equals(RoomClassDao.ATTR_H_END)) {
                h_end = (Timestamp) entry.getValue();
                System.out.println("h_s" + h_end);
                Time h_end_time = new Time(h_end.getTime()); //se puede pasar de timestamp a time ez
                keys.put(RoomClassDao.CAST_H_END, h_end_time);
            }
            attrList.add(RoomClassDao.ATTR_ID_ROOM_CLASS);
            try {
                EntityResult entityResult = this.daoHelper.query(roomClassDao, keys, attrList, RoomClassDao.QUERY_ALL_ROOM_CLASS);
                return false;
            } catch (NullPointerException e) {

            }

        }
        return true;
    } */

    private void checkMaxCapacityNew(Map<String, Object> attrMap){
        for (Map.Entry<String, Object> entry : attrMap.entrySet()) {
            List<String> attrList = new ArrayList<>();
            Map<String, Object> keys = new HashMap<>();
            int capacity = 0,id_room;
            if(entry.getKey().equals(RoomClassDao.ATTR_ID_ROOM)){
                 id_room=(int)entry.getValue();
                keys.put(RoomClassDao.ATTR_ID_ROOM,id_room);
            }
            if(entry.getKey().equals(RoomClassDao.ATTR_CAPACITY)){
                 capacity=(int)entry.getValue();
            }
            attrList.add(RoomDao.ATTR_MAX_ROOM_CAPACITY);
            EntityResult entityResult = this.daoHelper.query(roomClassDao, keys, attrList, RoomClassDao.QUERY_ROOMCLASS_ROOM);
            List<Object> maxCapacities = (List<Object>) entityResult.get(RoomDao.ATTR_MAX_ROOM_CAPACITY);
            String maxCapacityString = maxCapacities.get(0).toString();
            int maxCapacity = Integer.parseInt(maxCapacityString);

            if(capacity>maxCapacity){
                attrMap.put(RoomClassDao.ATTR_CAPACITY,maxCapacity);
            }
        }
    }
    private void checkMaxCapacityUpdate(Map<String, Object> attrMap,Map<String, Object> keyMap){
        for (Map.Entry<String, Object> entry : attrMap.entrySet()) {
            List<String> attrList = new ArrayList<>();
            Map<String, Object> keys = new HashMap<>();
            int capacity = 0, id_room_class;
            if (entry.getKey().equals(RoomClassDao.ATTR_CAPACITY)) {
                capacity = (int) entry.getValue();
                for(Map.Entry<String,Object>key:keyMap.entrySet()){
                        id_room_class = (int) key.getValue();
                        keys.put(RoomClassDao.ATTR_ID_ROOM_CLASS, id_room_class);
                    attrList.add(RoomClassDao.ATTR_ID_ROOM);
                    EntityResult entityResult = this.daoHelper.query(roomClassDao, keys, attrList, RoomClassDao.QUERY_ROOMCLASS_ROOM);
                    List<Object> idRooms = (List<Object>) entityResult.get(RoomClassDao.ATTR_ID_ROOM);
                    String idRoomString = idRooms.get(0).toString();
                    int idRoom = Integer.parseInt(idRoomString);
                    attrList.clear();
                    keys.clear();
                    keys.put(RoomClassDao.ATTR_ID_ROOM,idRoom);
                }
                attrList.add(RoomDao.ATTR_MAX_ROOM_CAPACITY);
                EntityResult entityResult = this.daoHelper.query(roomClassDao, keys, attrList, RoomClassDao.QUERY_ROOMCLASS_ROOM);
                List<Object> maxCapacities = (List<Object>) entityResult.get(RoomDao.ATTR_MAX_ROOM_CAPACITY);
                String maxCapacityString = maxCapacities.get(0).toString();
                int maxCapacity = Integer.parseInt(maxCapacityString);
                if (capacity > maxCapacity) {
                    attrMap.put(RoomClassDao.ATTR_CAPACITY, maxCapacity);
                }
            }
        }
    }

    /* private void (Map<String, Object> attrMap, Map<String, Object> keyMap) {
        for (Map.Entry<String, Object> entry : keyMap.entrySet()) {
            System.out.println(entry);
        }
    } */

}
