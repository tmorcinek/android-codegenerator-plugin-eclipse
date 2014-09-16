package com.morcinek.android.codegenerator.plugin.utils;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PreferencesHelper {

    public static final String JAVA_SOURCE_PATH_PREFERENCE = "directoryPreference";
    public static final String FILE_TEMPLATE_PREFERENCE = "File_template";

    private final IPreferenceStore preferenceStore;

    public PreferencesHelper(IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
        setupDefaults();
    }

    private void setupDefaults() {
        preferenceStore.setDefault(JAVA_SOURCE_PATH_PREFERENCE, "src");
//        TemplatesProvider templatesProvider = new ResourceTemplatesProvider();
//        String fileTemplate = templatesProvider.provideTemplateForName("File_template");
//        store.setDefault(PreferencePage.FILE_TEMPLATE_PREFERENCE, fileTemplate);
    }

    public void setJavaSourcePath(String value) {
        preferenceStore.setValue(JAVA_SOURCE_PATH_PREFERENCE, value);
    }

    public String getJavaSourcePath() {
        return preferenceStore.getString(JAVA_SOURCE_PATH_PREFERENCE);
    }
}
