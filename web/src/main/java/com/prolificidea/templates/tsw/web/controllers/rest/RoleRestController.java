package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.Role;
import com.prolificidea.templates.tsw.services.providers.RoleService;
import com.prolificidea.templates.tsw.web.helpers.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/role")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Role get(@PathVariable int id) {
        return roleService.getRoleByRoleId(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Role> getAll() {
        return roleService.getAllRole();
    }

    @RequestMapping(value = "/list/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<Role> getAll(@PathVariable int pageNumber) {
        return roleService.getAllRole(RestConstants.PAGE_SIZE * pageNumber, RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}", method = RequestMethod.GET)
    public @ResponseBody List<Role> search(@PathVariable String searchProperty, @PathVariable String searchCriteria) {
        return roleService.searchRole(searchProperty, searchCriteria);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<Role> search(@PathVariable String searchProperty,
                                              @PathVariable String searchCriteria,
                                              @PathVariable int pageNumber) {

        return roleService.searchRole(searchProperty,
                                            searchCriteria,
                                            RestConstants.PAGE_SIZE * pageNumber,
                                            RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Long count() {
        return roleService.countRole();
    }

    @RequestMapping(value = "/create/{object}", method = RequestMethod.POST)
    public @ResponseBody Role save(@PathVariable Role object) {
        return roleService.createRole(object);
    }

    @RequestMapping(value = "/update/{object}", method = RequestMethod.POST)
    public @ResponseBody Role update(@PathVariable Role object) {
        return roleService.updateRole(object);
    }

}
