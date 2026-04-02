package service.custom;

import model.Login;
import service.SuperService;

import java.sql.SQLException;

public interface LoginService extends SuperService {
    boolean login(Login dto) throws SQLException;
}


