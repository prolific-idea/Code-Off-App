package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.providers.UrlService;
import com.prolificidea.templates.tsw.services.providers.impl.PersonAndEntryFactoryImpl;
import com.prolificidea.templates.tsw.services.providers.impl.SolutionRepoPollServiceImpl;
import com.prolificidea.templates.tsw.services.providers.impl.UrlServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

@RestController
public class UrlServiceTestController {
    @Autowired
    UrlService urlService;

    @Autowired
    PersonAndEntryFactoryImpl personAndEntryFactory;

    @Autowired
    SolutionRepoPollServiceImpl solutionRepoPollService;

    @RequestMapping(value = "/api/compare", method = RequestMethod.GET)
    public boolean compare() {
        return false;
    }

    @RequestMapping(value = "/api/report", method = RequestMethod.GET)
    public String report() {
        return solutionRepoPollService.getReport().toString();
    }
}
