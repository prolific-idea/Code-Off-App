package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.providers.UrlService;
import com.prolificidea.templates.tsw.services.providers.impl.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlServiceTestController {
    @Autowired
    UrlService urlService;

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public String test() {
        urlService.setOwnerRepoBranchFile("prolific-idea", "Code-Off-App", "master", "pom.xml");

        String results = urlService.getContent();

        return results;
    }

    @RequestMapping(value = "/api/compare", method = RequestMethod.GET)
    public boolean compare() {
        UrlService urlService = new UrlServiceImpl();
        return urlService.compareFilesContent("abcd", "abc");
    }
}
