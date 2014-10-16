package com.morcinek.android.codegenerator.plugin.controllers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.MenuResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialog;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialogBundle;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
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
public class MenuActionController {

    public void handleAction(IFile file, IWorkbenchWindow window) throws ParserConfigurationException, CoreException, SAXException, XPathExpressionException, IOException {
        CodeDialog dialog = createCodeDialog(file, window, getGeneratedCode(file));
        if (dialog.open() == IStatus.OK) {
            ClipboardHelper.copy(dialog.getBundle().getCode());
        }
    }

    private String getGeneratedCode(IFile selectedFile) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, CoreException {
        CodeGenerator codeGenerator = Activator.getDefault().createCodeGenerator("Menu_template", new MenuResourceProvidersFactory());
        return codeGenerator.produceCode(selectedFile.getContents(), selectedFile.getName());
    }

    private CodeDialog createCodeDialog(IFile selectedFile, IWorkbenchWindow window, String producedCode) {
        CodeDialogBundle bundle = new CodeDialogBundle();
        bundle.setResourceName(selectedFile.getName());
        bundle.setCode(producedCode);
        return new CodeDialog(window.getShell(), bundle);
    }

}
