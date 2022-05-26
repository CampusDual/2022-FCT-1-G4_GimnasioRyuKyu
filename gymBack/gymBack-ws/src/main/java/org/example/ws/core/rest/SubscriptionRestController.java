package org.example.ws.core.rest;

import com.ontimize.jee.server.rest.ORestController;
import org.example.api.core.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionRestController extends ORestController<ISubscriptionService> {

    @Autowired
    private ISubscriptionService subscriptionService;

    @Override
    public ISubscriptionService getService() {
        return this.subscriptionService;
    }
    
}