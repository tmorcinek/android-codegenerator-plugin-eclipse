package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.plugin.utils.PreferencesHelper;
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

    private PreferencesHelper preferencesHelper;

    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        preferencesHelper = new PreferencesHelper(getPreferenceStore());
        plugin = this;
    }

    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

}
