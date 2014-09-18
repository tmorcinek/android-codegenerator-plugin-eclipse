package com.morcinek.android.codegenerator.plugin.preference.page;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class AdapterTemplatePage extends AbstractTemplatePage {

    private static final String ADAPTER_TEMPLATE_PREFERENCE = "Adapter_template";

    public AdapterTemplatePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        super.init(iWorkbench);
        setDescription("Setup Template for Adapter code generation:");
        setupDefault(ADAPTER_TEMPLATE_PREFERENCE);
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(ADAPTER_TEMPLATE_PREFERENCE, "", getFieldEditorParent()));
    }
}