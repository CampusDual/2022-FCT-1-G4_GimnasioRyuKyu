package org.example.model.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.example.api.core.service.IRoomClassService;
import org.example.model.core.dao.RoomClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
