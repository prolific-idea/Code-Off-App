package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.AppUser;
import com.prolificidea.templates.tsw.services.providers.AppUserService;
import com.prolificidea.templates.tsw.web.helpers.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/appUser")
public class AppUserRestController {

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody AppUser get(@PathVariable int id) {
        return appUserService.getAppUserByAppUserId(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<AppUser> getAll() {
        return appUserService.getAllAppUser();
    }

    @RequestMapping(value = "/list/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<AppUser> getAll(@PathVariable int pageNumber) {
        return appUserService.getAllAppUser(RestConstants.PAGE_SIZE * pageNumber, RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}", method = RequestMethod.GET)
    public @ResponseBody List<AppUser> search(@PathVariable String searchProperty, @PathVariable String searchCriteria) {
        return appUserService.searchAppUser(searchProperty, searchCriteria);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<AppUser> search(@PathVariable String searchProperty,
                                              @PathVariable String searchCriteria,
                                              @PathVariable int pageNumber) {

        return appUserService.searchAppUser(searchProperty,
                                            searchCriteria,
                                            RestConstants.PAGE_SIZE * pageNumber,
                                            RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Long count() {
        return appUserService.countAppUser();
    }

    @RequestMapping(value = "/create/{object}", method = RequestMethod.POST)
    public @ResponseBody AppUser save(@PathVariable AppUser object) {
        return appUserService.createAppUser(object);
    }

    @RequestMapping(value = "/update/{object}", method = RequestMethod.POST)
    public @ResponseBody
    AppUser update(@PathVariable AppUser object) {
        return appUserService.updateAppUser(object);
    }

}
