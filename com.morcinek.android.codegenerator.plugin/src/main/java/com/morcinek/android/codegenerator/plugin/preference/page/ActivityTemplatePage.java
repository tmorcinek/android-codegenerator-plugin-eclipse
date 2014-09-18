package com.morcinek.android.codegenerator.plugin.preference.page;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ActivityTemplatePage extends AbstractTemplatePage {

    private static final String ACTIVITY_TEMPLATE_PREFERENCE = "Activity_template";

    public ActivityTemplatePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        super.init(iWorkbench);
        setDescription("Setup Template for Activity code generation:");
        setupDefault(ACTIVITY_TEMPLATE_PREFERENCE);
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(ACTIVITY_TEMPLATE_PREFERENCE, "", getFieldEditorParent()));
    }
}