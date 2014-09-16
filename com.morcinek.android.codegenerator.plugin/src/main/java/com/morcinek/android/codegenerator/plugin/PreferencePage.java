package com.morcinek.android.codegenerator.plugin;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Preference for program used by ContextMenuPlugin
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String JAVA_SOURCE_DIRECTORY_PREFERENCE = "directoryPreference";
    public static final String FILE_TEMPLATE_PREFERENCE = "File_template";

    public PreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("General plugin settings:");
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(JAVA_SOURCE_DIRECTORY_PREFERENCE, "&Directory:", getFieldEditorParent()));
//        addField(new StringFieldEditor(FILE_TEMPLATE_PREFERENCE, "&File Template:", getFieldEditorParent()));
    }

    @Override
    public void init(IWorkbench iWorkbench) {
    }
}