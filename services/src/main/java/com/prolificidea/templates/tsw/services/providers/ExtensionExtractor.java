package com.prolificidea.templates.tsw.services.providers;

import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;

public interface ExtensionExtractor {
    TechnologyDTO extractExtension(String filename);
    boolean isSolutionFile(String givenSolutionFilePath, String databaseSolutionFilePath);
}
