package com.morcinek.android.codegenerator.plugin.handlers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.AdapterResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialog;
import com.morcinek.android.codegenerator.plugin.eclipse.EnvironmentHelper;
import com.morcinek.android.codegenerator.plugin.error.ErrorHandler;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
import com.morcinek.android.codegenerator.plugin.utils.PackageNameHelper;
import com.morcinek.android.codegenerator.plugin.utils.PathHelper;
import com.morcinek.android.codegenerator.plugin.utils.PreferencesHelper;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public abstract class AbstractLayoutHandler extends AbstractHandler {

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final EnvironmentHelper environmentHelper = new EnvironmentHelper();

    private final PackageNameHelper packageNameHelper = new PackageNameHelper();

    private final PathHelper pathHelper = new PathHelper();

    private final PreferencesHelper preferencesHelper = Activator.getDefault().createPreferenceHelper();

    @Override
    public Object execute(ExecutionEvent executionEvent) throws ExecutionException {
        final IFile selectedFile = environmentHelper.getSelectedFile(executionEvent);
        final IWorkbenchWindow window = environmentHelper.getActiveWindow(executionEvent);
        try {
            CodeGenerator codeGenerator = Activator.getDefault().createCodeGenerator(getTemplateName(), new AdapterResourceProvidersFactory());
            String producedCode = codeGenerator.produceCode(selectedFile.getContents(), selectedFile.getName());

            CodeDialog dialog = new CodeDialog(window.getShell(), preferencesHelper.getSourcePath(), selectedFile.getName(), packageNameHelper.getPackageName(selectedFile), producedCode);
            int resultCode = dialog.open();
            preferencesHelper.setSourcePath(dialog.getSourcePath());
            String packageName = dialog.getGeneratedPackage();
            String sourcePath = dialog.getSourcePath();
            String code = dialog.getGeneratedCode();

            String finalCode = pathHelper.getMergedCodeWithPackage(packageName, code);
            String fileName = pathHelper.getFileName(selectedFile.getName(), getResourceName());
            String folderName = pathHelper.getFolderPath(sourcePath, packageName);
            if (resultCode == IStatus.OK) {
                IFile generatedFile = environmentHelper.createFileWithGeneratedCode(selectedFile, fileName, folderName, finalCode);
                IDE.openEditor(window.getActivePage(), generatedFile, true);
            } else {
                ClipboardHelper.copy(finalCode);
            }
        } catch (Exception exception) {
            errorHandler.handleError(exception);
        }
        return null;
    }

    protected abstract String getTemplateName();

    protected abstract String getResourceName();
}
