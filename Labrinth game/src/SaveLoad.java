package src;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import src.ScoreType;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SaveLoad {
    
String url = "jdbc:mysql://localhost:3306/try1";
String username="root";
String password="Nicat2003!";
GamePanel gp;


public SaveLoad(GamePanel gp){
    this.gp = gp;




}
;

    public void Load() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM datas");
            int i=0;
            while (resultSet.next() && i<10) {
                String n=resultSet.getString("name");
                double t=resultSet.getDouble("time");
                double l=resultSet.getDouble("level");

                gp.ui.Scores[i]=new ScoreType(n,t,l);
                
                i++;
            }
            connection.close();
        } 
        
        catch (Exception e) {
            System.out.println(e);

        }
    
    }

    public void Save(){
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement st=null;
            Statement statement = connection.createStatement();
            String sql="INSERT INTO datas (name, time,level) VALUES (?,?,?)";

            st= connection.prepareStatement(sql);

            st.setString(1, gp.player.username);
            st.setDouble(2, gp.ui.playTime);
            st.setInt(3, gp.player.hasKey);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved");

          //  ResultSet resultSet = statement.executeQuery("insert into datas");

       
            connection.close();
        } 
        
        catch (Exception e) {
            System.out.println(e);

        }

    };

    }

