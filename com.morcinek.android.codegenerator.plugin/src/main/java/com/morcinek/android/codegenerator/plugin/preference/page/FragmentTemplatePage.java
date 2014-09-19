package com.morcinek.android.codegenerator.plugin.preference.page;

import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class FragmentTemplatePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public FragmentTemplatePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Setup Template for Fragment code generation:");
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(Activator.FRAGMENT_TEMPLATE_PREFERENCE, "", getFieldEditorParent()));
    }
}