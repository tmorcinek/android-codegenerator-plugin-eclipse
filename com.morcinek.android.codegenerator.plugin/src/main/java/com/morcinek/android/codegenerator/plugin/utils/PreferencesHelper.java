package com.morcinek.android.codegenerator.plugin.utils;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PreferencesHelper {

    public static final String SOURCE_PATH_PREFERENCE = "directoryPreference";
    public static final String FILE_TEMPLATE_PREFERENCE = "Activity_template";

    private final IPreferenceStore preferenceStore;

    public PreferencesHelper(IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
        setupDefaults();
    }

    private void setupDefaults() {
        preferenceStore.setDefault(SOURCE_PATH_PREFERENCE, "src");
//        TemplatesProvider templatesProvider = new ResourceTemplatesProvider();
//        String fileTemplate = templatesProvider.provideTemplateForName("Activity_template");
//        store.setDefault(PreferencePage.FILE_TEMPLATE_PREFERENCE, fileTemplate);
    }

    public void setSourcePath(String value) {
        preferenceStore.setValue(SOURCE_PATH_PREFERENCE, value);
    }

    public String getSourcePath() {
        return preferenceStore.getString(SOURCE_PATH_PREFERENCE);
    }
}
