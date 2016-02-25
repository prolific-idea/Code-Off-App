package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.domain.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByRoleId(int id);
    List<Role> getAllRole();
    List<Role> getAllRole(int startIndex, int numberOfRows);
    List<Role> searchRole(String property, String searchCriteria);
    List<Role> searchRole(String property, String searchCriteria, int startIndex, int numberOfRows);
    long countRole();
    Role createRole(Role object);
    Role updateRole(Role object);

}