package com.gxwsxx.tool.tv;

import static org.junit.Assert.*;

import com.gxwsxx.tool.tv.datamodel.TvDm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by annpud on 16/4/26.
 */
public class TvHandlerTest {

    @Test public void testHandleTime() throws Exception {
        TvHandler h = new TvHandler();
        TvDm d = new TvDm();
        d = h.handleTime(d);
        System.out.println();
    }
}
