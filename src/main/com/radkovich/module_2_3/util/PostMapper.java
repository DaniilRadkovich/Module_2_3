package main.com.radkovich.module_2_3.util;

import main.com.radkovich.module_2_3.model.Label;
import main.com.radkovich.module_2_3.model.Post;
import main.com.radkovich.module_2_3.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class PostMapper {

    public static Post mapPostFromResultSet(ResultSet rs) throws SQLException {
        return new Post(
                rs.getLong("post_id"),
                rs.getString("content"),
                rs.getTimestamp("created").toLocalDateTime(),
                rs.getTimestamp("updated").toLocalDateTime(),
                new HashSet<>(),
                Status.valueOf(rs.getString("post_status"))
        );
    }

    public static void addLabelToPostFromResultSet(Post post, ResultSet rs) throws SQLException {
        Long labelId = rs.getLong("label_id");
        if (!rs.wasNull()) {
            Label label = new Label(
                    labelId,
                    rs.getString("label_name"),
                    Status.valueOf(rs.getString("label_status"))
            );
            post.getLabels().add(label);
        }
    }
}
