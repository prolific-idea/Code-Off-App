package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by matthew.jordaan on 2016/03/02.
 */

@RestController
@RequestMapping("/api/entry")
public class EntryRestController {

    @Autowired
    EntryService entryService;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Entry> getEntryByPersonId(@PathVariable int id) {
        return entryService.getEntriesByPerson(id);
    }
}
