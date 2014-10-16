package com.morcinek.android.codegenerator.plugin.menu.handler;

import com.morcinek.android.codegenerator.plugin.general.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.general.action.ActionHandler;
import com.morcinek.android.codegenerator.plugin.menu.MenuActionHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class MenuHandler extends AbstractHandler {

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final ActionHandler actionController = new MenuActionHandler();

    @Override
    public Object execute(ExecutionEvent executionEvent) throws ExecutionException {
        IFile selectedFile = environmentHelper.getSelectedFile(executionEvent);
        IWorkbenchWindow window = environmentHelper.getActiveWindow(executionEvent);
        actionController.handleAction(selectedFile, window);
        return null;
    }
}
