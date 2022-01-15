package com.example.kanuledatawebsite.servicetests;

import com.example.kanuledatawebsite.dataaccesslayer.Databasedao;
import com.example.kanuledatawebsite.services.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DatabaseServiceTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    Databasedao dbDao;

    @Autowired
    DatabaseService databaseService;


    @Test
    public void testGetColumns() throws SQLException {
        ArrayList<String> exspectedColumnNames = new ArrayList<>(Arrays.asList("feature1","feature2","feature3"));
        var columNames=databaseService.getColumns("TESTTABLE");
        assertThat(columNames).isEqualTo(exspectedColumnNames);
    }
}
