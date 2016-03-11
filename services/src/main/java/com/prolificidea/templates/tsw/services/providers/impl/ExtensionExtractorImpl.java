package com.prolificidea.templates.tsw.services.providers.impl;

import com.prolificidea.templates.tsw.services.DTOs.TechnologyDTO;
import com.prolificidea.templates.tsw.services.providers.ExtensionExtractor;
import com.prolificidea.templates.tsw.services.providers.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtensionExtractorImpl implements ExtensionExtractor {
    @Autowired
    TechnologyService technologyService;

    public TechnologyDTO extractExtension(String filename) {
        List<TechnologyDTO> techs;
        String[] splicedFilename = filename.split("\\.");
        if(splicedFilename.length < 2)
        {
            TechnologyDTO tech = new TechnologyDTO();
            tech.setDescription("Directory");
            return tech;
        }
        int extensionIndex = splicedFilename.length - 1;
        String language = getLanguage(splicedFilename[extensionIndex]);
        techs = technologyService.searchTechnologys("description", language);

        if (techs == null)
            return null;

        for (TechnologyDTO tech : techs) {
            if(tech.getDescription().equals(language))
                return tech;
        }
        return null;
    }

    private String getLanguage(String extension) {
        if (extension.equals("cc") || extension.equals("cpp") || extension.equals("C") || extension.equals("c++"))
            return "C++";
        if (extension.equals("cs"))
            return "C#";
        if (extension.equals("js"))
            return "JavaScript";
        if (extension.equals("java"))
            return "Java";
        if (extension.equals("py"))
            return "Python";
        if (extension.equals("c"))
            return "C";
        if (extension.equals("php"))
            return "PHP";
        if (extension.equals("pl"))
            return "Perl";
        if (extension.equals("hs") || extension.equals("lhs"))
            return "Haskell";
        if (extension.equals("scala") || extension.equals("sc"))
            return "Scala";
        if (extension.equals("rb") || extension.equals("rbw"))
            return "Ruby";
        if (extension.equals("lua"))
            return "Lua";
        if (extension.equals("vb"))
            return "VB .NET";
        return "";
    }
}
