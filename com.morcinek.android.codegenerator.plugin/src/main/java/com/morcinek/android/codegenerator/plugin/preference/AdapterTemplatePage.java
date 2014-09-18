package com.morcinek.android.codegenerator.plugin.preference;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;


/**
 * Preference for program used by ContextMenuPlugin
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