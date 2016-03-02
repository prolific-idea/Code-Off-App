package com.prolificidea.templates.tsw.services.providers;

import java.io.File;

public interface UrlService {

    void setOwnerRepoBranchFile(String owner, String repo, String branch, String file);

    String getContent();

    boolean compareSolution(File solution, File answer, int challengeId);

    boolean compareSolution(String solution, String answer, int challengeId);
}
