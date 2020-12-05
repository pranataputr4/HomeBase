package com.rpl.homebase.data;

import com.rpl.homebase.MD5Hash;
import com.rpl.homebase.database.JdbcUtilities;
import com.rpl.homebase.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SuryaNKT
 */
public class UserData extends JdbcUtilities{
    public UserData(){
        super();
    }
    
    public ArrayList<String> getDosenList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_user` WHERE user_level = 'dosen'";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("user_name"));
        }
        return list; 
    }
    
    public ArrayList<String> getIDDosenList() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT * from `tb_user` WHERE user_level = 'dosen'";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query);
        while(result.next()){
            list.add(result.getString("user_id"));
        }
        return list; 
    }
    
    public User getUser(int id)throws Exception { 
        String query = "SELECT * from tb_user WHERE user_id = ?";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query, id);
        if(result.next()){
            User temp = new User(result.getInt("user_id"),
                        result.getString("user_username"),
                        result.getString("user_password"),
                        result.getString("user_name"),
                        result.getString("user_gender"),
                        result.getString("user_level"),
                        result.getString("user_bidang"));
            return temp;
        }
        return null;
    }
    
    public User getUser(String username, String password)throws Exception { 
        String query = "SELECT * from tb_user WHERE user_username = ? AND user_password = ?";
        ResultSet result = this.jdbcUtilities.selectFromDatabase(query, username, MD5Hash.hashPassword(password));
        if(result.next()){
            User temp = new User(result.getInt("user_id"), result.getString("user_username"),result.getString("user_password"),result.getString("user_name"),result.getString("user_gender"),result.getString("user_level"));
            return temp;
        }
        return null;
    }
    
    public ObservableList<User> getDataUserList() throws SQLException{
       ObservableList<User> userList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM tb_user WHERE user_level = 'dosen'";
       ResultSet result = this.jdbcUtilities.selectFromDatabase(sql);
       try{
           User user;
           while(result.next()){
               user = new User(result.getInt("user_id"),
                        result.getString("user_username"),
                        result.getString("user_password"),
                        result.getString("user_name"),
                        result.getString("user_gender"),
                        result.getString("user_level"),
                        result.getString("user_bidang"));
               userList.add(user);
               
           }
       }catch(Exception ex){
           System.out.println("Error Observable: "+ex.toString()); 
       }
       return userList;
    }
    
    public void signUp(String username, String password, String name, String gender, String level)throws Exception{
        String query = "INSERT INTO tb_user (user_username, user_password, user_name, user_gender, user_level)"
                    + "VALUES (?,?,?,?,?)";
        String md5 = MD5Hash.hashPassword(password);
        this.jdbcUtilities.executeInsert(query, username, md5, name, gender, level);
    }
    
    public void insert(String username, String password, String name, String gender, String bidang)throws Exception{
        String query = "INSERT INTO tb_user (user_username, user_password, user_name, user_gender, user_level, user_bidang)"
                    + "VALUES (?,?,?,?,?,?)";
        String md5 = MD5Hash.hashPassword(password);
        this.jdbcUtilities.executeInsert(query, username, md5, name, gender, "dosen", bidang);
    }
    
    public void edit(int id, String username, String name, String gender, String bidang) throws SQLException{
        String query = "UPDATE tb_user SET user_username = ? , user_name = ?, user_gender = ? , user_bidang = ?"
                + "WHERE user_id = ?";
        this.jdbcUtilities.executeUpdate(query, username, name , gender, bidang, id);
    }
    
    public void delete(int id) throws SQLException{
        String query = "DELETE FROM tb_user WHERE user_id = ?";
        this.jdbcUtilities.executeUpdate(query,id);
    }
}
