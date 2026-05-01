package com.projectmine.ECommerce.Web.repository;

import com.projectmine.ECommerce.Web.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class RegisterRepository {

    public boolean IsExists(User u){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            databaseConnection db = new databaseConnection();
            String query = "Select email From User Where email = ?";
            con = db.connect();
            st = con.prepareStatement(query);
            st.setString(1,u.getEmail());
            rs = st.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {                        // always close resources
            try { if (rs  != null) rs.close();  } catch (Exception ignored) {}
            try { if (st  != null) st.close();  } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return false;
    }
    public void addUser(User u){
        Connection con = null;
        PreparedStatement st = null;
        try {
            String query = "Insert Into User (username,email,hash_password) Values (?,?,?)";
            databaseConnection db = new databaseConnection();
            con = db.connect();
            st = con.prepareStatement(query);
            st.setString(1,u.getName());
            st.setString(2,u.getEmail());
            st.setString(3,u.getPassword());
            int count = st.executeUpdate();
            System.out.println(u.toString()+"\n"+count+" row/s affected");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {                        // always close resources
            try { if (st  != null) st.close();  } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }
}
