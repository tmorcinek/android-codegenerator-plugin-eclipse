package com.morcinek.android.codegenerator.plugin.preference;

import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.codegeneration.templates.TemplatesProvider;
import com.morcinek.android.codegenerator.plugin.Activator;
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
    public static final String ACTIVITY_TEMPLATE_PREFERENCE = "Activity_template";
    public static final String ADAPTER_TEMPLATE_PREFERENCE = "Adapter_template";
    public static final String MENU_TEMPLATE_PREFERENCE = "Menu_template";

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
        TemplatesProvider templatesProvider = new ResourceTemplatesProvider();
        setDefaultTemplate(templatesProvider, ACTIVITY_TEMPLATE_PREFERENCE, preferenceStore);
        setDefaultTemplate(templatesProvider, ADAPTER_TEMPLATE_PREFERENCE, preferenceStore);
        setDefaultTemplate(templatesProvider, MENU_TEMPLATE_PREFERENCE, preferenceStore);
    }

    private void setDefaultTemplate(TemplatesProvider templatesProvider, String templateName, IPreferenceStore preferenceStore) {
        preferenceStore.setDefault(templateName, templatesProvider.provideTemplateForName(templateName));
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(SOURCE_PATH_PREFERENCE, "&Directory:", getFieldEditorParent()));
        addField(new StringFieldEditor(ACTIVITY_TEMPLATE_PREFERENCE, "&Activity Template:", getFieldEditorParent()));
        addField(new StringFieldEditor(ADAPTER_TEMPLATE_PREFERENCE, "&Adapter Template:", getFieldEditorParent()));
        addField(new StringFieldEditor(MENU_TEMPLATE_PREFERENCE, "&Menu Template:", getFieldEditorParent()));
    }
}