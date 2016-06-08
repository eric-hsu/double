package com.kingdee.ebank.bank;

import com.google.common.collect.Maps;
import com.kingdee.ebank.provider.Provider;
import com.kingdee.ebank.service.Service;

import java.util.Map;

public class BankApiProvider implements Provider {
    private Map<String, Service> services = Maps.newHashMap();

    private Service getService(String name) {
        Service service = services.get(name);
        if(service == null)
            throw new IllegalArgumentException("No service registered with name: " + name);
        return service;
    }

    public Map<String, Service> getServices() {
        return services;
    }

    public void setServices(Map<String, Service> services) {
        this.services = services;
    }

    public String executeService(String name, Map<String, Object> params) {
        return getService(name).perform(params);
    }
}
