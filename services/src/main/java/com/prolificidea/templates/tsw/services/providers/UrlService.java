package com.prolificidea.templates.tsw.services.providers;

import java.io.File;

public interface UrlService {

    void setOwnerRepoBranchFile(String ownerRepo, String branch, String file);

    String getContent();

    boolean compareSolution(String solution, int challengeId);
}
