package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    private RestOperations restCall;

    private String owner;
    private String repo;
    private String branch;
    private String file;

    public void setOwnerRepoBranchFile(String owner, String repo, String branch, String file) {
        this.owner = owner;
        this.repo = repo;
        this.branch = branch;
        this.file = file;
    }

    public String getContent() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Basic VGVzaGlrYWppbjpUZXNoaWthamluMzcyNDY2");
        HttpEntity<String> contentHttpEntity = new HttpEntity<String>("parameters",headers);

        ResponseEntity<String> fileContentResults = restCall.exchange(
                "https://raw.githubusercontent.com/{owner}/{repo}/{branch}/{file}",
                HttpMethod.GET, contentHttpEntity,String.class,owner, repo, branch, file);

        return fileContentResults.getBody();
    }

    public boolean compareSolution(String submittedSolution, int challengeId) {
        ChallengeDTO challenge = challengeService.findChallenge(challengeId);
        byte[] answerByte = challenge.getSolution();
        String challengeSolution = new String(answerByte, Charset.forName("UTF-8"));

        int linesToCompare = challenge.getNumberOfLinesToCompare();

        if (linesToCompare == 0)
            return compareFilesContent(submittedSolution, challengeSolution);

        return compareFileLines(submittedSolution, challengeSolution, linesToCompare);
    }

    private boolean compareFilesContent(String submittedSolution, String challengeSolution) {
        try {
            byte[] file1Hash = getFileContentHash(submittedSolution);
            byte[] file2Hash = getFileContentHash(challengeSolution);

            return Arrays.equals(file1Hash, file2Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean compareFileLines(String submittedSolution, String challengeSolution, int linesToCompare) {
        String[] submittedSolutionLines = splitString(submittedSolution, "\n");
        String[] challengeSolutionLines = splitString(challengeSolution, "\n");

        if (submittedSolutionLines.length < linesToCompare)
            return false;

        for (int i = 0; i < linesToCompare; i++) {
            if (!challengeSolutionLines[i].equals(submittedSolutionLines[i])) {
                return false;
            }
        }
        return true;
    }

    private String[] splitString(String stringToSplit, String splitOn) {
        return stringToSplit.split(splitOn);
    }

    private byte[] getFileContentHash(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash;

        md.update(content.getBytes());
        hash = md.digest();

        return hash;
    }
}
