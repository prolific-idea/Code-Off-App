package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.domain.entities.Role;
import com.prolificidea.templates.tsw.persistence.RoleDao;
import com.prolificidea.templates.tsw.services.providers.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role getRoleByRoleId(int id) {
        return roleDao.find(id);
    }

    public List<Role> getAllRole() {
        return roleDao.findAll();
    }

    public List<Role> getAllRole(int startIndex, int numberOfRows) {
        return roleDao.findAll(numberOfRows, startIndex);
    }

    public List<Role> searchRole(String property, String searchCriteria) {
        return roleDao.search(property, searchCriteria);
    }

    public List<Role> searchRole(String property, String searchCriteria, int startIndex, int numberOfRows) {
        return roleDao.search(property, searchCriteria, numberOfRows, startIndex);
    }

    public long countRole() {
        return roleDao.count();
    }

    @Transactional
    public Role createRole(Role object) {
        return roleDao.create(object);
    }

    @Transactional
    public Role updateRole(Role object) {
        return roleDao.update(object);
    }

}
