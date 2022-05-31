package org.example.model.core.service;

import ch.qos.logback.core.net.server.Client;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IClientClassService;
import org.example.model.core.dao.ClientClassDao;
import org.example.model.core.dao.RoomClassDao;
import org.example.model.core.dao.RoomDao;
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

    @Autowired private ClientClassDao clientClassDao;

    @Autowired private DefaultOntimizeDaoHelper daoHelper;

    @Override
    public EntityResult clientClassQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.clientClassDao, keyMap, attrList, ClientClassDao.QUERY_CLIENT_CLASS);
    }

    @Override
    public EntityResult clientClassInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        if(checkIfCapacityAvailable(attrMap)){
            return this.daoHelper.insert(this.clientClassDao, attrMap);
        }
        return null;
    }

    @Override
    public EntityResult clientClassUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        if (attrMap.containsKey("id_assing_room")){
            attrMap.put("id_room_class", attrMap.remove("id_assing_room"));
        }
       if(checkIfCapacityAvailable(attrMap)) {
            return this.daoHelper.update(this.clientClassDao, attrMap, keyMap);
        }
        return null;
    }

    @Override
    public EntityResult clientClassDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.clientClassDao, keyMap);
    }

    private boolean checkIfCapacityAvailable(Map<String, Object> attrMap){
        for (Map.Entry<String, Object> entry : attrMap.entrySet()) {

            List<String> attrList = new ArrayList<>();
            Map<String, Object> keys = new HashMap<>();
            int id_room_class;
            if(entry.getKey().equals(ClientClassDao.ATTR_ID_ROOM_CLASS)) {

                id_room_class = (int) entry.getValue();
                keys.put(RoomClassDao.ATTR_ID_ROOM_CLASS, id_room_class);

                attrList.add(RoomClassDao.ATTR_CAPACITY);
                EntityResult entityResult = this.daoHelper.query(clientClassDao, keys, attrList, ClientClassDao.QUERY_ALL_ROOM_CLASS);
                List<Object> capacities = (List<Object>) entityResult.get(RoomClassDao.ATTR_CAPACITY);
                String capacityString = capacities.get(0).toString();
                int capacity = Integer.parseInt(capacityString);


                keys.clear();
                attrList.clear();
                keys.put(ClientClassDao.ATTR_ID_ROOM_CLASS,id_room_class);
                attrList.add(ClientClassDao.ATTR_ID_CLIENT);
                int count=0;
                try {
                    EntityResult entityResult2 = this.daoHelper.query(clientClassDao, keys, attrList, ClientClassDao.QUERY_ALL_CLIENT_CLASS);
                    List<Object> idClients = (List<Object>) entityResult2.get(ClientClassDao.ATTR_ID_CLIENT);
                    count = idClients.size();
                }catch(NullPointerException e){

                }
               if(count+1<=capacity){
                   return true;
               }
            }
        }
        return false;
    }
}
