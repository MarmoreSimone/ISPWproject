package mytest;

import exception.SystemErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.DbConnection;

public class TestDbConnection {


    @Test
    void testConnection() {

        int tester = 0;
        try {
            DbConnection.getInstance().getConnection();
        } catch (SystemErrorException e) {
            tester = 1;
        }

        Assertions.assertEquals(0,tester);
    }

}
