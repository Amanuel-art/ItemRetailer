

import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NonConstrainedDatabase {

    private static String id;
    private static String name;

    public static void comment(Connection connection, JSONObject jsonObject) throws SQLException {
        String comment_sql_query = "INSERT INTO comment(id, name, author, created_utc, parent_id, body, score) VALUES (?, ?, ?, ?, ?, ?,?);";
        PreparedStatement stmt1 = connection.prepareStatement(comment_sql_query);
        id = (String) jsonObject.get("id");
        name = (String) jsonObject.get("name");
        String author = (String) jsonObject.get("author");
        String created_utc = (String) jsonObject.get("created_utc");
        String parent_id = (String) jsonObject.get("parent_id");
        String body = (String) jsonObject.get("body");
        long score = (long) jsonObject.get("score");

        stmt1.setString(1, id);
        stmt1.setString(2, name);
        stmt1.setString(3, author);
        stmt1.setString(4, created_utc);
        stmt1.setString(5, parent_id);
        stmt1.setString(6, body);
        stmt1.setLong(7, score);
        stmt1.execute();
        stmt1.close();
    }
    public static void link(Connection connection,JSONObject jsonObject) throws SQLException {
        String link_sql_query= "INSERT INTO link(id,name,subreddit_id) VALUES (?, ?, ?)";
        PreparedStatement stmt2 = connection.prepareStatement(link_sql_query);
        String link_id = (String) jsonObject.get("link_id");
        stmt2.setString(1, link_id);
        stmt2.setString(2,name);
        String subreddit_id = (String) jsonObject.get("subreddit_id");
        stmt2.setString(3, subreddit_id);
        stmt2.execute();
        stmt2.close();
    }
    public static void subreddit(Connection connection,JSONObject jsonObject) throws SQLException {
        String subreddit_sql_query="INSERT INTO subreddit(id,name) VALUES(?,?)";
        PreparedStatement stmt3 = connection.prepareStatement(subreddit_sql_query);

        String subreddit = (String) jsonObject.get("subreddit");
        stmt3.setString(1,id);
        stmt3.setString(2, subreddit);
        stmt3.execute();
        stmt3.close();
    }
}
