package com.morcinek.android.codegenerator.plugin.preference;

import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.plugin.Activator;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PreferenceTemplateProvider extends ResourceTemplatesProvider {

    private final PreferenceHelper preferencesHelper = Activator.getDefault().createPreferenceHelper();

    @Override
    public String provideTemplateForName(String templateName) {
        if (preferencesHelper.containsTemplate(templateName)) {
            return preferencesHelper.getTemplate(templateName);
        }
        return super.provideTemplateForName(templateName);
    }
}
