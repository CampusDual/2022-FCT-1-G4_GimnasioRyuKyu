package org.example.ws.core.rest;


import com.ontimize.jee.server.rest.ORestController;
import org.example.api.core.service.IClientClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clientsClasses")
public class ClientClassRestController extends ORestController<IClientClassService> {

    @Autowired
    private IClientClassService clientClassSrv;

    @Override
    public IClientClassService getService() {
        return this.clientClassSrv;
    }

}


