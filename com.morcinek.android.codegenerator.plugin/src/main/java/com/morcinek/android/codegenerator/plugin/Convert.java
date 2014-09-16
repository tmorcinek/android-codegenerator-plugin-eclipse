package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.extractor.XMLResourceExtractor;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;
import com.morcinek.android.codegenerator.plugin.editor.CodeDialog;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
import com.morcinek.android.codegenerator.writer.CodeWriter;
import com.morcinek.android.codegenerator.writer.providers.ResourceProvidersFactory;
import com.morcinek.android.codegenerator.writer.templates.ResourceTemplatesProvider;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import java.io.InputStream;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class Convert extends AbstractHandler {

    public Object execute(ExecutionEvent arg0) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(arg0);
        try {
            Object firstElement = selection.getFirstElement();
            IFile resource = (IFile) firstElement;
            InputStream fileContents = resource.getContents();

            CodeGenerator codeGenerator = createCodeGenerator();

            String string = codeGenerator.produceCode(fileContents, resource.getName());
            final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(arg0);

            CodeDialog dialog = new CodeDialog(window.getShell(), resource.getName(), "com.morcinek.package", string);// new input dialog
            if (dialog.open() == IStatus.OK) {
                //TODO
            } else {
                ClipboardHelper.copy(dialog.getGeneratedCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage(e);
        }
        return null;
    }

    private CodeGenerator createCodeGenerator() {
        return new CodeGenerator(XMLResourceExtractor.createResourceExtractor(), new FileNameExtractor(), new CodeWriter(new ResourceProvidersFactory(), new ResourceTemplatesProvider()));
    }

    private void showErrorMessage(Exception e) {
        showErrorMessage(e.getMessage());
    }

    private void showErrorMessage(String message) {
        MessageDialog.openError(new Shell(), "Eclipse Maven Plugin", message);
    }
}