package com.kingdee.ebank.provider;

import java.util.Map;

public interface Provider {
    String executeService(String name, Map<String, Object> params);
}
