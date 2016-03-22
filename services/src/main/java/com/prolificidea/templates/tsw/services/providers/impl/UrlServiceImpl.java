package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
    private RestTemplate restCall;

    public UrlServiceImpl() {
    }

    public String getContent(String downloadUrl) {
        HttpHeaders headers = new HttpHeaders();
        downloadUrl = downloadUrl.replace("%20", " ");

        headers.set("Authorization", "Basic YjE0NTY4NzdAdHJidm4uY29tOkVudEFsbFN0YXJSZWRvbmVBbGxBcXVpcmVk");
        HttpEntity<String> contentHttpEntity = new HttpEntity<String>("parameters", headers);

        try {
            ResponseEntity<String> fileContentResults = restCall.exchange(
                    downloadUrl,
                    HttpMethod.GET, contentHttpEntity, String.class);

            if (fileContentResults.getStatusCode() != HttpStatus.OK)
                return "";

            return fileContentResults.getBody();
        }
        catch (HttpClientErrorException e)
        {
           // e.printStackTrace();
            return "";
        }
    }

    public boolean compareSolution(String submittedSolution, ChallengeDTO challenge) {
        byte[] answerByte = challenge.getSolution();
        String challengeSolution = new String(answerByte, Charset.forName("UTF-8"));

        int linesToCompare = challenge.getNumberOfLinesToCompare();

        if (linesToCompare == 0)
            return compareFilesContent(submittedSolution, challengeSolution);

        return compareFileLines(submittedSolution, challengeSolution, linesToCompare);
    }

    private boolean compareFilesContent(String submittedSolution, String challengeSolution) {
        submittedSolution = submittedSolution.replace("\r","").trim();
        challengeSolution = challengeSolution.replace("\r","");
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
        submittedSolution = submittedSolution.replace("\r","");
        challengeSolution = challengeSolution.replace("\r","");
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
