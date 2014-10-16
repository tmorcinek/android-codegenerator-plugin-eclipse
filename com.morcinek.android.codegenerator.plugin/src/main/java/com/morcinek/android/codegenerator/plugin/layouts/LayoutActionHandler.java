package com.morcinek.android.codegenerator.plugin.layouts;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.general.action.ActionHandler;
import com.morcinek.android.codegenerator.plugin.general.eclipse.CodeDialog;
import com.morcinek.android.codegenerator.plugin.general.eclipse.CodeDialogBundle;
import com.morcinek.android.codegenerator.plugin.general.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.general.preference.PreferenceHelper;
import com.morcinek.android.codegenerator.plugin.general.utils.ClipboardHelper;
import com.morcinek.android.codegenerator.plugin.general.utils.ErrorHandler;
import com.morcinek.android.codegenerator.plugin.general.utils.PackageHelper;
import com.morcinek.android.codegenerator.plugin.general.utils.PathHelper;
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
public class LayoutActionHandler implements ActionHandler{

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final PackageHelper packageHelper = new PackageHelper();

    private final PathHelper pathHelper = new PathHelper();

    private final PreferenceHelper preferenceHelper = Activator.getDefault().createPreferenceHelper();

    private CodeGenerator codeGenerator;

    private String resourceName;

    public LayoutActionHandler(CodeGenerator codeGenerator, String resourceName) {
        this.codeGenerator = codeGenerator;
        this.resourceName = resourceName;
    }

    @Override
    public void handleAction(IFile selectedFile, IWorkbenchWindow window) {
        try {
            CodeDialog dialog = createCodeDialog(selectedFile, window.getShell(), getGeneratedCode(selectedFile));
            if (dialog.open() == IStatus.OK) {
                CodeDialogBundle bundle = dialog.getBundle();

                String finalCode = pathHelper.getMergedCodeWithPackage(bundle.getPackage(), bundle.getCode());
                switch (dialog.getReturnValue()) {
                    case CodeDialog.RETURN_VALUE_CREATE_FILE:
                        String fileName = pathHelper.getFileName(selectedFile.getName(), resourceName);
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
    }

    private String getGeneratedCode(IFile selectedFile) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, CoreException {
        return codeGenerator.produceCode(selectedFile.getContents(), selectedFile.getName());
    }

    private CodeDialog createCodeDialog(IFile selectedFile, Shell shell, String producedCode) {
        CodeDialogBundle bundle = new CodeDialogBundle();
        bundle.setResourceName(selectedFile.getName());
        bundle.setCode(producedCode);
        bundle.setPackage(packageHelper.getPackageName(selectedFile));
        bundle.setSourcePath(preferenceHelper.getSourcePath());
        return new CodeDialog(shell, bundle);
    }
}
