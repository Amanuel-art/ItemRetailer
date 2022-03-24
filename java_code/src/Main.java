


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;



public class Main {

    public static void main(String[] args) throws IOException, SQLException, ParseException {
        double endTime = 0;
        String path = "C:\\Users\\Konto\\Desktop\\databaseTheory_assignment2\\database_assignment2\\java_code\\src\\RC_2007-10.json";

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine().replaceAll("\\[", "").replaceAll("\\]", "");
        DatabaseConnectivity con=new DatabaseConnectivity();
        Connection connection=con.getConnection();
        int counter=0;
        double timeStart = System.currentTimeMillis();
        while (line != null) {

            try {

                Object obj = new JSONParser().parse(line);
                JSONObject jsonObject= (JSONObject) obj;
                // Insertion with constraints

                if(!ConstrainedDatabase.doesSubredditExist(connection,jsonObject)){

                    ConstrainedDatabase.subredditWithConstraints(connection,jsonObject);
                }

                if(!ConstrainedDatabase.doesLinkExist(connection,jsonObject)){
                    ConstrainedDatabase.linkWithConstraints(connection,jsonObject);
                }

                if(!ConstrainedDatabase.doesCommentExist(connection,jsonObject)){
                    ConstrainedDatabase.commentWithConstraints(connection,jsonObject);
                }


                // Insertion without constraints
               /*
                NonConstrainedDatabase.comment(connection,jsonObject);
                NonConstrainedDatabase.link(connection,jsonObject);
                NonConstrainedDatabase.subreddit(connection,jsonObject);

                */

                counter++;
                line = reader.readLine();


            } catch (IOException | org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            endTime= System.currentTimeMillis();
            System.out.println("-----------------Performance measurement result---------------------------------");
            System.out.println("There are " + counter + " comments in total \n, " +
                    "and it took "+ (endTime - timeStart) / 1000 + " seconds in total");
            System.out.println("-----------------Performance measurement End---------------------------------");

        }

    }
}
