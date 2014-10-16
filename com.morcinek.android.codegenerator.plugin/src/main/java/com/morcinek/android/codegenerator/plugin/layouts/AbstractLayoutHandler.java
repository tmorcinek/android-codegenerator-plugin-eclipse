package com.morcinek.android.codegenerator.plugin.layouts;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialog;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialogBundle;
import com.morcinek.android.codegenerator.plugin.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.error.ErrorHandler;
import com.morcinek.android.codegenerator.plugin.preference.PreferenceHelper;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
import com.morcinek.android.codegenerator.plugin.utils.PackageHelper;
import com.morcinek.android.codegenerator.plugin.utils.PathHelper;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractLayoutHandler extends AbstractHandler {

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final PackageHelper packageHelper = new PackageHelper();

    private final PathHelper pathHelper = new PathHelper();

    private final PreferenceHelper preferenceHelper = Activator.getDefault().createPreferenceHelper();

    @Override
    public Object execute(ExecutionEvent executionEvent) throws ExecutionException {
        final IFile selectedFile = environmentHelper.getSelectedFile(executionEvent);
        final IWorkbenchWindow window = environmentHelper.getActiveWindow(executionEvent);
        try {
            CodeDialog dialog = createCodeDialog(selectedFile, window.getShell(), getGeneratedCode(selectedFile));
            if (dialog.open() == IStatus.OK) {
                CodeDialogBundle bundle = dialog.getBundle();

                String finalCode = pathHelper.getMergedCodeWithPackage(bundle.getPackage(), bundle.getCode());
                switch (dialog.getReturnValue()) {
                    case CodeDialog.RETURN_VALUE_CREATE_FILE:
                        String fileName = pathHelper.getFileName(selectedFile.getName(), getResourceName());
                        String folderName = pathHelper.getFolderPath(bundle.getSourcePath(), bundle.getPackage());
                        IFile generatedFile = environmentHelper.createFileWithGeneratedCode(selectedFile, fileName, folderName, finalCode);
                        IDE.openEditor(window.getActivePage(), generatedFile, true);
                        break;
                    case CodeDialog.RETURN_VALUE_COPY:
                        ClipboardHelper.copy(finalCode);
                        break;
                    default:
                        preferenceHelper.setSourcePath(bundle.getSourcePath());
                        break;
                }
            }
        } catch (Exception exception) {
            errorHandler.handleError(exception);
        }
        return null;
    }

    private String getGeneratedCode(IFile selectedFile) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, CoreException {
        return getCodeGenerator().produceCode(selectedFile.getContents(), selectedFile.getName());
    }

    private CodeDialog createCodeDialog(IFile selectedFile, Shell shell, String producedCode) {
        CodeDialogBundle bundle = new CodeDialogBundle();
        bundle.setResourceName(selectedFile.getName());
        bundle.setCode(producedCode);
        bundle.setPackage(packageHelper.getPackageName(selectedFile));
        bundle.setSourcePath(preferenceHelper.getSourcePath());
        return new CodeDialog(shell, bundle);
    }

    protected abstract CodeGenerator getCodeGenerator();

    protected abstract String getResourceName();
}
