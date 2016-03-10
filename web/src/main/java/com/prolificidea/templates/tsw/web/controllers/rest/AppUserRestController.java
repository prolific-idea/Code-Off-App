package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.AppUser;
import com.prolificidea.templates.tsw.domain.entities.AppUserRole;
import com.prolificidea.templates.tsw.services.providers.AppUserService;
import com.prolificidea.templates.tsw.services.providers.LoginService;
import com.prolificidea.templates.tsw.web.helpers.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/appUser")
public class AppUserRestController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private LoginService loginService;

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

    @RequestMapping(value = "/api/users/{userId}/grant/role/{role}", method = RequestMethod.POST)
    public ResponseEntity<String> grantPermission(Authentication authentication, @PathVariable int userId, @PathVariable String role) throws Exception {


        List<AppUserRole> roles = new ArrayList<AppUserRole>();
        final AppUser user = appUserService.getAppUserByAppUserId(userId);
        user.setAppUserRoles(roles);
        appUserService.updateAppUser(user);

        return new ResponseEntity<String>("Permissions granted", HttpStatus.OK);
    }

}
