package com.morcinek.android.codegenerator.plugin.preference.page;

import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public PreferencePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("General plugin settings:");
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(Activator.SOURCE_PATH_PREFERENCE, "&Source Path:", getFieldEditorParent()));
    }
}