package service.custom.impl;

import model.Login;
import repository.RepositoryFactory;
import repository.custom.LoginRepository;
import service.custom.LoginService;
import util.RepositoryType;

import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    LoginRepository loginRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.LOGIN);

    @Override
    public boolean login(Login dto) throws SQLException {
        return loginRepository.findByEmailAndRole(dto.getEmail(), dto.getRole()).getPassword().equals(dto.getPassword());
    }
}
