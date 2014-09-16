package com.morcinek.android.codegenerator.plugin;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "com.morcinek.android.codegenerator.plugin";

    private static Activator plugin;

    public static Activator getDefault() {
        return plugin;
    }

    public Activator() {

    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        setupDefaultPreferences();
        plugin = this;
    }

    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    private void setupDefaultPreferences() {
        IPreferenceStore store = getPreferenceStore();
        store.setDefault(PreferencePage.JAVA_SOURCE_DIRECTORY_PREFERENCE, "src");
//        TemplatesProvider templatesProvider = new ResourceTemplatesProvider();
//        String fileTemplate = templatesProvider.provideTemplateForName("File_template");
//        store.setDefault(PreferencePage.FILE_TEMPLATE_PREFERENCE, fileTemplate);
    }
}
