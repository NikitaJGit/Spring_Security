package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao{

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Role getRoleUser() {
        return entityManager.find(Role.class,1L);
    }

    @Override
    public Role getRoleAdmin() {
        return entityManager.find(Role.class, 2L);
    }
}
