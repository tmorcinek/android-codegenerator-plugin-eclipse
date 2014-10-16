package com.morcinek.android.codegenerator.plugin.actions;

import com.morcinek.android.codegenerator.plugin.controllers.MenuActionController;
import com.morcinek.android.codegenerator.plugin.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.error.ErrorHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class MenuAction implements IObjectActionDelegate {

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final MenuActionController actionController = new MenuActionController();

    private IWorkbenchPart workbenchPart;

    public MenuAction() {
        System.out.println("created");
    }

    @Override
    public void setActivePart(IAction iAction, IWorkbenchPart iWorkbenchPart) {
        workbenchPart = iWorkbenchPart;
    }

    @Override
    public void run(IAction iAction) {
        IFile file = environmentHelper.getFileFromEditor(workbenchPart);
        IWorkbenchWindow window = environmentHelper.getActiveWindow(workbenchPart);
        try {
            actionController.handleAction(file, window);
        } catch (Exception exception) {
            errorHandler.handleError(exception);
        } finally {
            workbenchPart = null;
        }
    }

    @Override
    public void selectionChanged(IAction iAction, ISelection iSelection) {
    }
}
