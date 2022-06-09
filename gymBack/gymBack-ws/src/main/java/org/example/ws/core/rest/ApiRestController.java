package org.example.ws.core.rest;

import com.ontimize.jee.server.rest.ORestController;
import org.example.api.core.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRestController extends ORestController<IApiService> {

    @Autowired
    private IApiService apiService;

    @Override
    public IApiService getService() {
        return this.apiService;
    }

    /*@GetMapping(value = "/activity/{activity}")
    public void activity(RequestBody LocatedActivity locatedActivity) throws OntimizeJEERuntimeException{
        final int id_room_class = locatedActivity.getActivity();
    }*/

}
