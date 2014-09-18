package com.morcinek.android.codegenerator.plugin.preference;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PreferenceHelper {

    private final IPreferenceStore preferenceStore;

    public PreferenceHelper(IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    public void setSourcePath(String value) {
        preferenceStore.setValue(PreferencePage.SOURCE_PATH_PREFERENCE, value);
    }

    public String getSourcePath() {
        return preferenceStore.getString(PreferencePage.SOURCE_PATH_PREFERENCE);
    }

    public boolean containsTemplate(String templateName) {
        return preferenceStore.contains(templateName);
    }

    public String getTemplate(String templateName) {
        return preferenceStore.getString(templateName);
    }
}
