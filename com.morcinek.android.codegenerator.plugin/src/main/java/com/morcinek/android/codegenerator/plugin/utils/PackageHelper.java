package com.morcinek.android.codegenerator.plugin.utils;

import com.morcinek.android.codegenerator.extractor.PackageExtractor;
import com.morcinek.android.codegenerator.extractor.XMLPackageExtractor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import java.io.InputStream;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PackageHelper {

    private final PackageExtractor packageExtractor = new XMLPackageExtractor();

    public String getPackageName(IFile selectedFile) {
        try {
            return packageExtractor.extractPackageFromManifestStream(
                    getFileContentWithPath(selectedFile,
                            "/AndroidManifest.xml"));
        } catch (Exception e) {
            return "";
        }
    }

    private InputStream getFileContentWithPath(IFile file, String filePath) throws CoreException {
        return file.getProject().getFile(filePath).getContents();
    }
}
