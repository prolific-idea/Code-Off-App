package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.AppUserDetails;
import com.prolificidea.templates.tsw.services.providers.AppUserDetailsService;
import com.prolificidea.templates.tsw.web.helpers.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/appUserDetails")
public class AppUserDetailsRestController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody AppUserDetails get(@PathVariable int id) {
        return appUserDetailsService.getAppUserDetailsByAppUserDetailsId(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<AppUserDetails> getAll() {
        return appUserDetailsService.getAllAppUserDetails();
    }

    @RequestMapping(value = "/list/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<AppUserDetails> getAll(@PathVariable int pageNumber) {
        return appUserDetailsService.getAllAppUserDetails(RestConstants.PAGE_SIZE * pageNumber, RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}", method = RequestMethod.GET)
    public @ResponseBody List<AppUserDetails> search(@PathVariable String searchProperty, @PathVariable String searchCriteria) {
        return appUserDetailsService.searchAppUserDetails(searchProperty, searchCriteria);
    }

    @RequestMapping(value = "/list/{searchProperty}/{searchCriteria}/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody List<AppUserDetails> search(@PathVariable String searchProperty,
                                              @PathVariable String searchCriteria,
                                              @PathVariable int pageNumber) {

        return appUserDetailsService.searchAppUserDetails(searchProperty,
                                            searchCriteria,
                                            RestConstants.PAGE_SIZE * pageNumber,
                                            RestConstants.PAGE_SIZE);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public @ResponseBody Long count() {
        return appUserDetailsService.countAppUserDetails();
    }

    @RequestMapping(value = "/create/{object}", method = RequestMethod.POST)
    public @ResponseBody AppUserDetails save(@PathVariable AppUserDetails object) {
        return appUserDetailsService.createAppUserDetails(object);
    }

    @RequestMapping(value = "/update/{object}", method = RequestMethod.POST)
    public @ResponseBody AppUserDetails update(@PathVariable AppUserDetails object) {
        return appUserDetailsService.updateAppUserDetails(object);
    }

}
