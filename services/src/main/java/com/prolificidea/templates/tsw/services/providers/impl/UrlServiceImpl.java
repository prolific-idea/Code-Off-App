package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.providers.UrlService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UrlServiceImpl implements UrlService {
    private String owner;
    private String repo;
    private String branch;
    private String file;

    private RestTemplate restCall = new RestTemplate();

    public UrlServiceImpl() {
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

    private byte[] getFileContentHash(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = null;

        md.update(content.getBytes());
        hash = md.digest();

        return hash;
    }

    public boolean compareFilesContent(String file1Content, String file2Content) {
        try {
            byte[] file1Hash = getFileContentHash(file1Content);
            byte[] file2Hash = getFileContentHash(file2Content);

            return Arrays.equals(file1Hash,file2Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
