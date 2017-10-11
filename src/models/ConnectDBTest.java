package models;

import org.junit.Assert;
import org.junit.Test;

class ConnectDBTest {
    @Test
    public void connect() throws Exception {
        ConnectDB con = new ConnectDB();
        try {
            con.connect();
            Assert.assertTrue(con.getConn().isValid(10));
        } catch (Exception e) {
        }
    }
}