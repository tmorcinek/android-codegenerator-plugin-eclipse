package com.morcinek.android.codegenerator.plugin.preference.page;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class MenuTemplatePage extends AbstractTemplatePage {

    private static final String MENU_TEMPLATE_PREFERENCE = "Menu_template";

    public MenuTemplatePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench iWorkbench) {
        super.init(iWorkbench);
        setDescription("Setup Template for Menu code generation:");
        setupDefault(MENU_TEMPLATE_PREFERENCE);
    }

    @Override
    public void createFieldEditors() {
        addField(new StringFieldEditor(MENU_TEMPLATE_PREFERENCE, "", getFieldEditorParent()));
    }
}