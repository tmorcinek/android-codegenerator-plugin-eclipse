package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.TemplateCodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.ResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.codegeneration.templates.TemplatesProvider;
import com.morcinek.android.codegenerator.extractor.XMLResourceExtractor;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;
import com.morcinek.android.codegenerator.plugin.general.preference.PreferenceHelper;
import com.morcinek.android.codegenerator.plugin.general.preference.PreferenceTemplateProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class Activator extends AbstractUIPlugin {

    public static final String SOURCE_PATH_PREFERENCE = "directoryPreference";
    public static final String ACTIVITY_TEMPLATE_PREFERENCE = "Activity_template";
    public static final String ADAPTER_TEMPLATE_PREFERENCE = "Adapter_template";
    public static final String FRAGMENT_TEMPLATE_PREFERENCE = "Fragment_template";
    public static final String MENU_TEMPLATE_PREFERENCE = "Menu_template";

    private static Activator plugin;

    public static Activator getDefault() {
        return plugin;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);
        initializePreferenceDefaults(getPreferenceStore());
        plugin = this;
    }

    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    public PreferenceHelper createPreferenceHelper() {
        return new PreferenceHelper(getPreferenceStore());
    }

    public CodeGenerator createCodeGenerator(String templateName, ResourceProvidersFactory resourceProvidersFactory) {
        return new CodeGenerator(XMLResourceExtractor.createResourceExtractor(),
                new FileNameExtractor(),
                new TemplateCodeGenerator(templateName, resourceProvidersFactory, new PreferenceTemplateProvider()));
    }

    private void initializePreferenceDefaults(IPreferenceStore preferenceStore) {
        preferenceStore.setDefault(SOURCE_PATH_PREFERENCE, "src");
        ResourceTemplatesProvider templatesProvider = new ResourceTemplatesProvider();
        setDefaultTemplate(preferenceStore, ACTIVITY_TEMPLATE_PREFERENCE, templatesProvider);
        setDefaultTemplate(preferenceStore, ADAPTER_TEMPLATE_PREFERENCE, templatesProvider);
        setDefaultTemplate(preferenceStore, FRAGMENT_TEMPLATE_PREFERENCE, templatesProvider);
        setDefaultTemplate(preferenceStore, MENU_TEMPLATE_PREFERENCE, templatesProvider);
    }

    private void setDefaultTemplate(IPreferenceStore preferenceStore, String templateName, TemplatesProvider templatesProvider) {
        preferenceStore.setDefault(templateName, templatesProvider.provideTemplateForName(templateName));
    }
}
