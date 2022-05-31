package org.example.ws.core.rest;

import com.ontimize.jee.server.rest.ORestController;
import org.example.api.core.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitors")
public class MonitorRestController extends ORestController<IMonitorService> {

    @Autowired
    private IMonitorService monitorService;

    @Override
    public IMonitorService getService() {
        return this.monitorService;
    }

}
