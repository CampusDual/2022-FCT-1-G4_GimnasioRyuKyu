package org.example.ws.core.rest;

import com.ontimize.jee.server.rest.ORestController;
import org.example.api.core.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassRestController extends ORestController<IClassService> {

    @Autowired
    private IClassService classService;

    @Override
    public IClassService getService() {
        return this.classService;
    }
}