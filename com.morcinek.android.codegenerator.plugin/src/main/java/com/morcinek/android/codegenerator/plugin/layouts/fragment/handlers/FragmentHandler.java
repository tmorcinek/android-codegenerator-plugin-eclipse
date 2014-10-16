package com.morcinek.android.codegenerator.plugin.layouts.fragment.handlers;

import com.morcinek.android.codegenerator.plugin.general.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.layouts.fragment.FragmentActionHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class FragmentHandler extends AbstractHandler {

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    @Override
    public Object execute(ExecutionEvent executionEvent) throws ExecutionException {
        final IFile selectedFile = environmentHelper.getSelectedFile(executionEvent);
        final IWorkbenchWindow window = environmentHelper.getActiveWindow(executionEvent);
        new FragmentActionHandler().handleAction(selectedFile, window);
        return null;
    }
}