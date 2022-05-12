package org.example.ws.core.rest;


import com.ontimize.jee.server.rest.ORestController;
import org.example.api.core.service.IRoomClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/roomsClasses")
public class RoomClassRestController extends ORestController<IRoomClassService> {

    @Autowired
    private IRoomClassService roomClassSrv;

    @Override
    public IRoomClassService getService() {
        return this.roomClassSrv;
    }

}
