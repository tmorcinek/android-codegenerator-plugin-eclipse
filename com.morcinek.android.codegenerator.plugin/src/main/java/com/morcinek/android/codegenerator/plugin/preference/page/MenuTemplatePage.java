package com.morcinek.android.codegenerator.plugin.preference.page;

import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.cldt.managedbuilder.ui.properties.MultiLineTextFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class MenuTemplatePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public MenuTemplatePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Setup Template for Menu code generation:");
    }

    @Override
    public void createFieldEditors() {
        addField(new MultiLineTextFieldEditor(Activator.MENU_TEMPLATE_PREFERENCE, "", getFieldEditorParent()));
    }
}