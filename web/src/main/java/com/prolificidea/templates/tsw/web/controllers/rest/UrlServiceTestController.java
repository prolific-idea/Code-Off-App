package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.providers.UrlService;
import com.prolificidea.templates.tsw.services.providers.impl.PersonAndEntryFactoryImpl;
import com.prolificidea.templates.tsw.services.providers.impl.SolutionRepoPollServiceImpl;
import com.prolificidea.templates.tsw.services.providers.impl.UrlServiceImpl;
import org.json.JSONException;
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

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public String test() {
        urlService.setOwnerRepoBranchFile("prolific-idea/Code-Off-App", "master", "pom.xml");

        String results = urlService.getContent();

        return results;
    }

    @RequestMapping(value = "/api/compare", method = RequestMethod.GET)
    public boolean compare() {

        UrlService urlService = new UrlServiceImpl();
        urlService.setOwnerRepoBranchFile("prolific-idea/Code-Off-App", "master", "pom.xml");

        String solution = urlService.getContent();
        return urlService.compareSolution(solution, 1);
    }

    @RequestMapping(value = "/api/time", method = RequestMethod.GET)
    public String Time() {

        return solutionRepoPollService.getTime();
    }
/*    @RequestMapping(value = "/api/JSON", method = RequestMethod.GET)
    public String UrlJson() {
        String temp ="Null";
        try {
           temp= personAndEntryFactory.markSolutionsOfUserIfTheyExsistForAChallenge(101);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }*/

    @RequestMapping(value = "/api/ext", method = RequestMethod.GET)
    public String ext() {

        ConfigurableMimeFileTypeMap mimeMap = new ConfigurableMimeFileTypeMap();
        return mimeMap.getContentType("C:\\Users\\stuart.callen\\Desktop\\rishal-pi-template-spring-web-b48fdbb337ab\\rishal-pi-template-spring-web-b48fdbb337ab\\domain\\src\\main\\java\\com\\prolificidea\\templates\\tsw\\domain\\AppUser.java");
    }
}
