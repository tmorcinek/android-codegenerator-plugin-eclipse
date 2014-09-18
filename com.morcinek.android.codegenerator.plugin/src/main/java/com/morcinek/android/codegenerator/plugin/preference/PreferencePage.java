package com.morcinek.android.codegenerator.plugin.preference;

import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Preference for program used by ContextMenuPlugin
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String SOURCE_PATH_PREFERENCE = "directoryPreference";

    public PreferencePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        setDescription("General plugin settings:");
        IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
        setPreferenceStore(preferenceStore);
        setupDefaults(preferenceStore);
    }

    private void setupDefaults(IPreferenceStore preferenceStore) {
        preferenceStore.setDefault(SOURCE_PATH_PREFERENCE, "src");
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(SOURCE_PATH_PREFERENCE, "&Directory:", getFieldEditorParent()));
    }
}