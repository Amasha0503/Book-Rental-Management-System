package repository.custom.impl;

import model.Login;
import repository.custom.LoginRepository;
import util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepositoryImpl implements LoginRepository {
    @Override
    public Login findByEmailAndRole(String email, String role) throws SQLException {

        try {
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT Email, Password, Role FROM employee WHERE email = ? AND role = ?",
                    email,
                    role
            );

            if (resultSet.next()) {
                return new Login(
                        resultSet.getString(1),   // email
                        resultSet.getString(2),   // password
                        resultSet.getString(3)    // role
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

