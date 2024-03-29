package org.example.ws.core.rest;

import org.example.api.core.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ontimize.jee.server.rest.ORestController;
@RestController
@RequestMapping("/rooms")
public class RoomRestController extends ORestController<IRoomService>  {

    @Autowired
    private IRoomService roomService;

    @Override
    public IRoomService getService() {
        return this.roomService;
    }

}
