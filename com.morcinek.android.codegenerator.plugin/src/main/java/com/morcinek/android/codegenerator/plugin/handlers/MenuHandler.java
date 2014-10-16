package com.morcinek.android.codegenerator.plugin.handlers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.MenuResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.controllers.MenuActionController;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialogBundle;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialog;
import com.morcinek.android.codegenerator.plugin.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.error.ErrorHandler;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchWindow;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class MenuHandler extends AbstractHandler {

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final MenuActionController actionController = new MenuActionController();

    @Override
    public Object execute(ExecutionEvent executionEvent) throws ExecutionException {
        final IFile selectedFile = environmentHelper.getSelectedFile(executionEvent);
        final IWorkbenchWindow window = environmentHelper.getActiveWindow(executionEvent);
        try {
            actionController.handleAction(selectedFile, window);
        } catch (Exception exception) {
            errorHandler.handleError(exception);
        }
        return null;
    }
}
