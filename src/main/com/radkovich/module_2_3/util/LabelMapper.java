package main.com.radkovich.module_2_3.util;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelMapper {

    public static Label getLabelFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        Status status = Status.valueOf(rs.getString("status"));
        return new Label(id, name, status);
    }
}
