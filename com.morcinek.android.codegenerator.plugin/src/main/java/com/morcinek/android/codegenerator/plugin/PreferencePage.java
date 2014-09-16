package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.plugin.utils.PreferencesHelper;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Preference for program used by ContextMenuPlugin
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public PreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("General plugin settings:");
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(PreferencesHelper.JAVA_SOURCE_PATH_PREFERENCE, "&Directory:", getFieldEditorParent()));
//        addField(new StringFieldEditor(FILE_TEMPLATE_PREFERENCE, "&File Template:", getFieldEditorParent()));
    }

    @Override
    public void init(IWorkbench iWorkbench) {
    }
}