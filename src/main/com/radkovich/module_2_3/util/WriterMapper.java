package main.com.radkovich.module_2_3.util;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;
import main.com.radkovich.module_2_3.model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class WriterMapper {

    public static Writer mapWriterFromResultSet(ResultSet rs) throws SQLException {
        return new Writer(
                rs.getLong("writer_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                new ArrayList<>(),
                Status.valueOf(rs.getString("writer_status"))
        );
    }

    public static Post mapPostFromWriterResultSet(ResultSet rs) throws SQLException {
        return new Post(
                rs.getLong("post_id"),
                rs.getString("content"),
                rs.getTimestamp("created").toLocalDateTime(),
                rs.getTimestamp("updated").toLocalDateTime(),
                new HashSet<>(),
                Status.valueOf(rs.getString("post_status"))
        );
    }

    public static Label mapLabelFromWriterResultSet(ResultSet rs) throws SQLException {
        return new Label(
                rs.getLong("label_id"),
                rs.getString("label_name"),
                Status.valueOf(rs.getString("label_status"))
        );
    }
}
