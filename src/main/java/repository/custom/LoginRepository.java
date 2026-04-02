package repository.custom;

import model.Login;
import repository.SuperRepository;

import java.sql.SQLException;

public interface LoginRepository extends SuperRepository {
    Login findByEmailAndRole(String email, String role) throws SQLException;
}
