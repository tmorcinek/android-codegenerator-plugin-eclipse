package com.morcinek.android.codegenerator.plugin.preference;

import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.plugin.Activator;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PreferenceTemplateProvider extends ResourceTemplatesProvider {

    private final PreferenceHelper preferenceHelper = Activator.getDefault().createPreferenceHelper();

    @Override
    public String provideTemplateForName(String templateName) {
        if (preferenceHelper.containsTemplate(templateName)) {
            return preferenceHelper.getTemplate(templateName);
        }
        return super.provideTemplateForName(templateName);
    }
}
