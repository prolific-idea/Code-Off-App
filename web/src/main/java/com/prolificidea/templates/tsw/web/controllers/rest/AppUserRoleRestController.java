package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.AppUserRole;
import com.prolificidea.templates.tsw.services.providers.AppUserRoleService;
import com.prolificidea.templates.tsw.web.helpers.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/appUserRole")
public class AppUserRoleRestController {

    @Autowired
    private AppUserRoleService appUserRoleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody AppUserRole get(@PathVariable int id) {
        return appUserRoleService.getAppUserRoleByAppUserRoleId(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<AppUserRole> getAll() {
        return appUserRoleService.getAllAppUserRole();
    }

    @RequestMapping(value = "/list/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<AppUserRole> getAll(@PathVariable int pageNumber) {
        return appUserRoleService.getAllAppUserRole(RestConstants.PAGE_SIZE * pageNumber, RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}", method = RequestMethod.GET)
    public @ResponseBody List<AppUserRole> search(@PathVariable String searchProperty, @PathVariable String searchCriteria) {
        return appUserRoleService.searchAppUserRole(searchProperty, searchCriteria);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<AppUserRole> search(@PathVariable String searchProperty,
                                              @PathVariable String searchCriteria,
                                              @PathVariable int pageNumber) {

        return appUserRoleService.searchAppUserRole(searchProperty,
                                            searchCriteria,
                                            RestConstants.PAGE_SIZE * pageNumber,
                                            RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Long count() {
        return appUserRoleService.countAppUserRole();
    }

    @RequestMapping(value = "/create/{object}", method = RequestMethod.POST)
    public @ResponseBody AppUserRole save(@PathVariable AppUserRole object) {
        return appUserRoleService.createAppUserRole(object);
    }

    @RequestMapping(value = "/update/{object}", method = RequestMethod.POST)
    public @ResponseBody AppUserRole update(@PathVariable AppUserRole object) {
        return appUserRoleService.updateAppUserRole(object);
    }

}
