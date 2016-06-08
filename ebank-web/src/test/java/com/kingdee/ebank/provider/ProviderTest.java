package com.kingdee.ebank.provider;

import com.kingdee.ebank.common.SpringTransactionalContextTests;
import com.kingdee.ebank.utils.SpringContextUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ProviderTest extends SpringTransactionalContextTests {
    private static final Logger logger = LoggerFactory.getLogger(ProviderTest.class);

    @Test
    public void testExecuteService() {
        Provider p = SpringContextUtil.getBean(Provider.class);
        logger.info("ping query result: {}", p.executeService("pabc_0000", new HashMap<String, Object>()));
        logger.info("cmbc query result: {}", p.executeService("cmbc_0000", new HashMap<String, Object>()));
    }
}
