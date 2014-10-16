package com.morcinek.android.codegenerator.plugin.layouts.activity.action;

import com.morcinek.android.codegenerator.plugin.general.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.layouts.activity.ActivityActionHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ActivityAction implements IObjectActionDelegate {

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private IWorkbenchPart workbenchPart;

    @Override
    public void setActivePart(IAction iAction, IWorkbenchPart iWorkbenchPart) {
        workbenchPart = iWorkbenchPart;
    }

    @Override
    public void run(IAction iAction) {
        IFile file = environmentHelper.getFileFromEditor(workbenchPart);
        IWorkbenchWindow window = environmentHelper.getActiveWindow(workbenchPart);
        new ActivityActionHandler().handleAction(file, window);
        workbenchPart = null;
    }

    @Override
    public void selectionChanged(IAction iAction, ISelection iSelection) {
    }
}
