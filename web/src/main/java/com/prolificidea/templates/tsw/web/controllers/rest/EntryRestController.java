package com.prolificidea.templates.tsw.web.controllers.rest;

import com.prolificidea.templates.tsw.domain.entities.Entry;
import com.prolificidea.templates.tsw.services.DTOs.EntryDTO;
import com.prolificidea.templates.tsw.services.providers.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    List<EntryDTO> getEntryByPersonId(@PathVariable int id) {
        return entryService.getEntriesByPerson(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    List<EntryDTO> getAllEntries() {
        return entryService.findAllEntrys();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateChallenge(@RequestBody EntryDTO entryToBeUpdated) {
        if (entryToBeUpdated.getEntryId() <= 0)
            return new ResponseEntity<String>(new String("Please select a valid entry."), HttpStatus.NOT_ACCEPTABLE);
        EntryDTO entry = entryService.updateEntry(entryToBeUpdated);

        return new ResponseEntity<EntryDTO>(entry, HttpStatus.OK);
    }

}
