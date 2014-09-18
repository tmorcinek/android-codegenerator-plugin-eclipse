package com.morcinek.android.codegenerator.plugin.preference;

import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.codegeneration.templates.TemplatesProvider;
import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractTemplatePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private final ResourceTemplatesProvider templatesProvider = new ResourceTemplatesProvider();

    public AbstractTemplatePage(int style) {
        super(style);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

    protected void setupDefault(String templateName) {
        setDefaultTemplate(templateName, templatesProvider, getPreferenceStore());
    }

    private void setDefaultTemplate(String templateName, TemplatesProvider templatesProvider, IPreferenceStore preferenceStore) {
        preferenceStore.setDefault(templateName, templatesProvider.provideTemplateForName(templateName));
    }
}
