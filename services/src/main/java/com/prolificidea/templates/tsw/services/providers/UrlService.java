package com.prolificidea.templates.tsw.services.providers;

public interface UrlService {

    void setOwnerRepoBranchFile(String owner, String repo, String branch, String file);

    String getContent();

    boolean compareFilesContent(String file1Content, String file2Content);
}
