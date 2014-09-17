package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.TemplateCodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.ResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.extractor.XMLResourceExtractor;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;
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

    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    public PreferencesHelper createPreferenceHelper() {
        return new PreferencesHelper(getPreferenceStore());
    }

    public CodeGenerator createCodeGenerator(String templateName, ResourceProvidersFactory resourceProvidersFactory) {
        return new CodeGenerator(XMLResourceExtractor.createResourceExtractor(),
                new FileNameExtractor(),
                new TemplateCodeGenerator(templateName, resourceProvidersFactory, new ResourceTemplatesProvider()));
    }
}
