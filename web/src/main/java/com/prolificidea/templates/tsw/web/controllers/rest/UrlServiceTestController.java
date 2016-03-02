package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.services.providers.UrlService;
import com.prolificidea.templates.tsw.services.providers.impl.SolutionRepoPollServiceImpl;
import com.prolificidea.templates.tsw.services.providers.impl.UrlServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class UrlServiceTestController {
    @Autowired
    UrlService urlService;

    @Autowired
    SolutionRepoPollServiceImpl solutionRepoPollService;

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public String test() {
        urlService.setOwnerRepoBranchFile("prolific-idea", "Code-Off-App", "master", "pom.xml");

        String results = urlService.getContent();

        return results;
    }

    @RequestMapping(value = "/api/compare", method = RequestMethod.GET)
    public boolean compare() {
        UrlService urlService = new UrlServiceImpl();
        File f = new File("C:\\Users\\theodore.badenhorst\\CodeOff\\Code-Off-App\\pom.xml");
        return urlService.compareSolution(f, f, 0);
    }

    @RequestMapping(value = "/api/time", method = RequestMethod.GET)
    public String Time() {

        return solutionRepoPollService.getTime();
    }
    @RequestMapping(value = "/api/JSON", method = RequestMethod.GET)
    public String UrlJson() {
        String temp ="Null";
        try {
           temp= solutionRepoPollService.getEntryRepo(solutionRepoPollService.getJSONFromURL("https://api.github.com/repos/prolific-idea/Code-Off/forks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
