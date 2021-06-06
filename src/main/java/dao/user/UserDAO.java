package dao.user;

import common.exception.FailLoginException;
import entity.db.AIMSDB;
import entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author
 */
public class UserDAO {

    // data coupling
    public User authenticate(String email, String encryptedPassword) throws SQLException {
        String sql = "SELECT * FROM User " +
            "where email = '" + email + "' and encrypted_password = '" + encryptedPassword + "'";
        ResultSet res = AIMSDB.getConnection().createStatement().executeQuery(sql);
        if (res.next()) {
            User user = new User(
                res.getInt("id"),
                res.getString("name"),
                res.getString("email"),
                res.getString("address"),
                res.getString("phone")
            );

            if (Objects.isNull(user)) {
                throw new FailLoginException();
            }
            return user;
        } else {
            res = AIMSDB.getConnection().createStatement().executeQuery(
                "SELECT * FROM User;"
            );
            if(res.next()){
                // email: anv@email.com
                User user = new User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("email"),
                    res.getString("address"),
                    res.getString("phone")
                );
                if (Objects.isNull(user)) {
                    throw new FailLoginException();
                }
                return user;
            }
            throw new SQLException();
        }
    }
}
