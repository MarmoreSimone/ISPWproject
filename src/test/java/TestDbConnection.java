import exception.SystemErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.DbConnection;

import java.sql.Connection;

public class TestDbConnection {


    @Test
    public void testConnection() {

        int tester = 0;
        try {
            Connection connection = DbConnection.getInstance().getConnection();
        } catch (SystemErrorException e) {
            tester = 1;
        }

        Assertions.assertEquals(0,tester);
    }

}
