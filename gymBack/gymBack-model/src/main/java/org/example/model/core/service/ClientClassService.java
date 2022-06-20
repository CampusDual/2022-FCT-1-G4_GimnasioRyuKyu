package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IClientClassService;
import org.example.model.core.dao.ClientClassDao;
import org.example.model.core.dao.ClientDao;
import org.example.model.core.dao.RoomClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ClientClassService")
@Lazy
public class ClientClassService implements IClientClassService {

    @Autowired
    private ClientClassDao clientClassDao;

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult clientClassQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientClassDao, keyMap, attrList, ClientClassDao.QUERY_CLIENT_CLASS);
    }

    @Override
    public EntityResult clientClassInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        if (attrMap.containsKey("id_assing_room")) {
            attrMap.put("id_room_class", attrMap.remove("id_assing_room"));
        }
        EntityResult toret = new EntityResultMapImpl();
        toret.setCode(EntityResult.OPERATION_WRONG);
        try {
            switch (checkIfCapacityAvailable(getIdClientNew(attrMap), getIdRoomClassNew(attrMap), attrMap)) {
                case "CapacityError":
                    toret.setMessage("CAPACITY_ERROR");
                    return toret;
                case "RepeatedClientClassError":
                    toret.setMessage("REPEATED_CLIENT_CLASS");
                    return toret;
                default:
                    return this.daoHelper.insert(this.clientClassDao, attrMap);
            }
        }catch(NullPointerException e){
            toret.setMessage("NO_SUB_ERROR"); return toret;
        }
    }

    @Override
    public EntityResult clientClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        if (attrMap.containsKey("id_assing_room")) {
            attrMap.put("id_room_class", attrMap.remove("id_assing_room"));
        }
        EntityResult toret = new EntityResultMapImpl();
        toret.setCode(EntityResult.OPERATION_WRONG);
        try {
            switch (checkIfCapacityAvailable(getIdClientNew(attrMap), getIdRoomClassNew(attrMap), attrMap)) {
                case "CapacityError":
                    toret.setMessage("CAPACITY_ERROR");
                    return toret;
                case "RepeatedClientClassError":
                    toret.setMessage("REPEATED_CLIENT_CLASS");
                    return toret;
                default:
                    return this.daoHelper.insert(this.clientClassDao, attrMap);
            }
        }catch(NullPointerException e){
            toret.setMessage("NO_SUB_ERROR"); return toret;
        }

    }

    @Override
    public EntityResult clientClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientClassDao, keyMap);
    }

    private String checkIfCapacityAvailable(int id_client, int id_room_class, Map<String, Object> attrMap) {

        List<Object> clientRoomClasses = new ArrayList<>();
        List<String> attrList = new ArrayList<>();
        Map<String, Object> keys = new HashMap<>();
        EntityResult entityResult;


        keys.put(ClientClassDao.ATTR_ID_CLIENT, id_client);
        attrList.add(ClientClassDao.ATTR_ID_ROOM_CLASS);

            entityResult = this.daoHelper.query(clientClassDao, keys, attrList, ClientClassDao.QUERY_ALL_CLIENT_CLASS);
            if(!entityResult.isEmpty()) {
                clientRoomClasses = (List<Object>) entityResult.get(ClientClassDao.ATTR_ID_ROOM_CLASS);
            }

        keys.clear();
        attrList.clear();

        if (!clientRoomClasses.isEmpty()&&clientRoomClasses.contains(id_room_class)) {

            return "RepeatedClientClassError";
        }

        keys.put(RoomClassDao.ATTR_ID_ROOM_CLASS, id_room_class);

        attrList.add(RoomClassDao.ATTR_CAPACITY);
        entityResult = this.daoHelper.query(clientClassDao, keys, attrList, ClientClassDao.QUERY_ALL_ROOM_CLASS);
        List<Object> capacities = (List<Object>) entityResult.get(RoomClassDao.ATTR_CAPACITY);
        String capacityString = capacities.get(0).toString();
        int capacity = Integer.parseInt(capacityString);

        keys.clear();
        attrList.clear();
        keys.put(ClientClassDao.ATTR_ID_ROOM_CLASS, id_room_class);
        attrList.add(ClientClassDao.ATTR_ID_CLIENT);
        int count = 0;

            entityResult = this.daoHelper.query(clientClassDao, keys, attrList, ClientClassDao.QUERY_ALL_CLIENT_CLASS);
            if(!entityResult.isEmpty()) {
                List<Object> idClients = (List<Object>) entityResult.get(ClientClassDao.ATTR_ID_CLIENT);
                count = idClients.size();
            }

        if (count + 1 <= capacity) {
            return "OK";
        }
        return "CapacityError";
    }


    private int getIdClientNew(Map<String, Object> attrMap) {
        return (int) attrMap.get(ClientClassDao.ATTR_ID_CLIENT);
    }

    private int getIdClientUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {
        if (attrMap.containsKey(ClientClassDao.ATTR_ID_CLIENT)) {
            return getIdClientNew(attrMap);
        }
        List<String> list = new ArrayList<>();
        list.add(ClientClassDao.ATTR_ID_CLIENT);
        EntityResult entityResult = this.daoHelper.query(clientClassDao, keyMap, list, ClientClassDao.QUERY_ALL_CLIENT_CLASS);
        List<Object> listIdClient = (List<Object>) entityResult.get(ClientClassDao.ATTR_ID_CLIENT);
        String idClientString = listIdClient.get(0).toString();
        return Integer.parseInt(idClientString);
    }

    private int getIdRoomClassNew(Map<String, Object> attrMap) {
        return (int) attrMap.get(ClientClassDao.ATTR_ID_ROOM_CLASS);
    }

    private int getIdRoomClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {
        if(attrMap.containsKey(ClientClassDao.ATTR_ID_ROOM_CLASS)){
          return getIdRoomClassNew(attrMap);
        }
        List<String> attrList = new ArrayList<>();
        attrList.add(ClientClassDao.ATTR_ID_ROOM_CLASS);
        EntityResult entityResult = this.daoHelper.query(clientClassDao, keyMap, attrList, ClientClassDao.QUERY_ALL_CLIENT_CLASS);
        List<Object> roomClasses = (List<Object>) entityResult.get(ClientClassDao.ATTR_ID_ROOM_CLASS);
        String roomClassString = roomClasses.get(0).toString();
        return Integer.parseInt(roomClassString);
    }


}
