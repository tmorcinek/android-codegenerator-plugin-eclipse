package com.morcinek.android.codegenerator.plugin.layouts.adapter.page;

import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.cldt.managedbuilder.ui.properties.MultiLineTextFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class AdapterTemplatePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public AdapterTemplatePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Setup Template for Adapter code generation:");
    }

    @Override
    public void createFieldEditors() {
        addField(new MultiLineTextFieldEditor(Activator.ADAPTER_TEMPLATE_PREFERENCE, "", getFieldEditorParent()));
    }
}