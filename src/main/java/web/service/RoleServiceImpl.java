package web.service;

import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService{

    private UserDao userDao;
    private RoleDao roleDao;
    public RoleServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Role getRoleUser() {
        return roleDao.getRoleUser();
    }

    @Override
    @Transactional
    public Role getRoleAdmin() {
        return roleDao.getRoleAdmin();
    }
}
