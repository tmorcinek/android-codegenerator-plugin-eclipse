package com.morcinek.android.codegenerator.plugin.utils;

import com.morcinek.android.codegenerator.extractor.PackageExtractor;
import com.morcinek.android.codegenerator.extractor.XMLPackageExtractor;
import com.morcinek.android.codegenerator.plugin.eclipse.EnvironmentHelper;
import org.eclipse.core.resources.IFile;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PackageNameHelper {

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final PackageExtractor packageExtractor = new XMLPackageExtractor();

    public String getPackageName(IFile selectedFile) {
        try {
            return packageExtractor.extractPackageFromManifestStream(
                    environmentHelper.getFileContentWithPath(selectedFile.getProject(),
                            "/AndroidManifest.xml"));
        } catch (Exception e) {
            return "";
        }
    }
}
