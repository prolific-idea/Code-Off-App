package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.DTOs.ChallengeDTO;
import com.prolificidea.templates.tsw.services.providers.ChallengeService;
import com.prolificidea.templates.tsw.services.providers.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    ChallengeService challengeService;

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

    public boolean compareSolution(File solution, File answer, int challengeId) {
        ChallengeDTO challenge = challengeService.findChallenge(challengeId);

        int linesToCompare = challenge.getNumberOfLinesToCompare();

        try {
            if (linesToCompare == 0)
                return compareFilesContent(solution, answer);
            return compareFileLines(solution, answer, linesToCompare);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean compareSolution(String solution, String answer, int challengeId) {
        ChallengeDTO challenge = challengeService.findChallenge(challengeId);

        int linesToCompare = challenge.getNumberOfLinesToCompare();

        if (linesToCompare == 0)
            return compareFilesContent(solution, answer);

        return compareFileLines(solution, answer, linesToCompare);
    }

    private boolean compareFilesContent(File solution, File answer) throws IOException {
        BufferedReader solutionFileReader = new BufferedReader(new FileReader(solution));
        BufferedReader answerFileReader = new BufferedReader(new FileReader(answer));

        String solutionLines = readFileLines(solutionFileReader);
        String answerLines = readFileLines(answerFileReader);
        return compareFilesContent(solutionLines, answerLines);
    }

    private boolean compareFileLines(File solution, File answer, int linesToCompare) throws IOException {
        BufferedReader solutionFileReader = new BufferedReader(new FileReader(solution));
        BufferedReader answerFileReader = new BufferedReader(new FileReader(answer));

        String solutionLines = readFileLines(solutionFileReader);
        String answerLines = readFileLines(answerFileReader);

        return compareFileLines(solutionLines, answerLines, linesToCompare);
    }

    private String readFileLines(BufferedReader reader) throws IOException {
        StringBuilder lines = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.append(line + "\n");
        }

        return lines.toString().trim();
    }

    private boolean compareFilesContent(String file1Content, String file2Content) {
        try {
            byte[] file1Hash = getFileContentHash(file1Content);
            byte[] file2Hash = getFileContentHash(file2Content);

            return Arrays.equals(file1Hash, file2Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private byte[] getFileContentHash(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash;

        md.update(content.getBytes());
        hash = md.digest();

        return hash;
    }

    private boolean compareFileLines(String solution, String answer, int linesToCompare) {
        String[] solutionLines = splitString(solution, "\n");
        String[] answerLines = splitString(answer, "\n");

        if (solutionLines.length < linesToCompare)
            return false;

        for (int i = 0; i < linesToCompare; i++) {
            if (!answerLines[i].equals(solutionLines[i])) {
                return false;
            }
        }
        return true;
    }

    private String[] splitString(String solution, String splitOn) {
        return solution.split(splitOn);
    }
}
