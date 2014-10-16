package com.morcinek.android.codegenerator.plugin.general.utils;

import com.google.common.collect.Lists;
import com.morcinek.android.codegenerator.extractor.PackageExtractor;
import com.morcinek.android.codegenerator.extractor.XMLPackageExtractor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import java.util.List;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PackageHelper {

    private final PackageExtractor packageExtractor = new XMLPackageExtractor();

    public String getPackageName(IFile selectedFile) {
        try {
            IProject project = selectedFile.getProject();
            for (String path : possiblePaths()) {
                IFile file = getFileFromPath(project, path + "AndroidManifest.xml");
                if (file.exists()) {
                    return packageExtractor.extractPackageFromManifestStream(file.getContents());
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    private List<String> possiblePaths() {
        return Lists.newArrayList("/", "/app/src/main/", "/src/main/", "/res/");
    }

    private IFile getFileFromPath(IProject project, String path) {
        return project.getFile(path);
    }
}
