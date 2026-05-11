package com.projectmine.ECommerce.Web.repository;

import com.projectmine.ECommerce.Web.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class LoginRepository {
    public User getUserDetails(String email){
        User u = new User();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "Select * From User Where email = ?";
            DatabaseConnection db = new DatabaseConnection();
            con = db.connect();
            st = con.prepareStatement(query);
            st.setString(1,email);
            rs = st.executeQuery();
            rs.next();
            u.setName(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("hash_password"));
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {                        // always close resources
            try { if (rs  != null) rs.close();  } catch (Exception ignored) {}
            try { if (st  != null) st.close();  } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
        return u;
    }
}
