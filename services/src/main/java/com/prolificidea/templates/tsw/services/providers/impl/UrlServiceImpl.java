package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.providers.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UrlServiceImpl implements UrlService {
    private String owner;
    private String repo;
    private String branch;
    private String file;

    private RestTemplate restCall = new RestTemplate();

    public UrlServiceImpl(){
        restCall = new RestTemplate();
    }

    public UrlServiceImpl(RestTemplate restCall, String owner, String repo, String branch, String file) {
        this.owner = owner;
        this.repo = repo;
        this.branch = branch;
        this.file = file;
        this.restCall = restCall;
    }

    public void setOwnerRepoBranchFile(String owner, String repo, String branch, String file) {
        this.owner = owner;
        this.repo = repo;
        this.branch = branch;
        this.file = file;
    }

    public String getContent() {
        //https://raw.githubusercontent.com/{owner}/{repo}/{branch}/{file}
        String results =
                restCall.getForObject(
                        "https://raw.githubusercontent.com/{owner}/{repo}/{branch}/{file}",
                        String.class, owner, repo, branch, file);

        return results;
    }
}
