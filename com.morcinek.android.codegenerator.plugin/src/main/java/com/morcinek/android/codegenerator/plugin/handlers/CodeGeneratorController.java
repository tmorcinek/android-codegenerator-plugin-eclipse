package com.morcinek.android.codegenerator.plugin.handlers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.TemplateCodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.ResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.extractor.PackageExtractor;
import com.morcinek.android.codegenerator.extractor.XMLPackageExtractor;
import com.morcinek.android.codegenerator.extractor.XMLResourceExtractor;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;
import com.morcinek.android.codegenerator.plugin.editor.CodeDialog;
import com.morcinek.android.codegenerator.plugin.error.ErrorHandler;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
import com.morcinek.android.codegenerator.plugin.utils.PreferencesHelper;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CodeGeneratorController {

    private final ErrorHandler errorHandler = new ErrorHandler();

    private final PackageExtractor packageExtractor = new XMLPackageExtractor();

    private final CodeGenerator codeGenerator;

    private final String resourceName;

    private final PreferencesHelper preferencesHelper;

    public CodeGeneratorController(String templateName, String resourceName, ResourceProvidersFactory resourceProvidersFactory, PreferencesHelper preferencesHelper) {
        this.codeGenerator = createCodeGenerator(templateName, resourceProvidersFactory);
        this.resourceName = resourceName;
        this.preferencesHelper = preferencesHelper;
    }

    private CodeGenerator createCodeGenerator(String templateName, ResourceProvidersFactory resourceProvidersFactory) {
        return new CodeGenerator(XMLResourceExtractor.createResourceExtractor(), new FileNameExtractor(), new TemplateCodeGenerator(templateName, resourceProvidersFactory, new ResourceTemplatesProvider()));
    }

    public void handleExecutionEvent(ExecutionEvent executionEvent) throws ExecutionException {
        final IFile selectedFile = getSelectedFile(executionEvent);
        final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(executionEvent);
        try {
            String producedCode = codeGenerator.produceCode(selectedFile.getContents(), selectedFile.getName());
            CodeDialog dialog = new CodeDialog(window.getShell(), preferencesHelper.getJavaSourcePath(), selectedFile.getName(), getPackageName(selectedFile), producedCode);
            int resultCode = dialog.open();
            preferencesHelper.setJavaSourcePath(dialog.getJavaSourcePath());
            if (resultCode == IStatus.OK) {
                IFile generatedFile = createFileWithGenerateCode(selectedFile, codeGenerator, dialog);
                IDE.openEditor(window.getActivePage(), generatedFile, true);
            } else {
                ClipboardHelper.copy(codeGenerator.appendPackage(dialog.getGeneratedPackage(), dialog.getGeneratedCode()));
            }
        } catch (Exception exception) {
            errorHandler.handleError(exception);
        }
    }


    private IFile getSelectedFile(ExecutionEvent arg0) {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(arg0);
        return (IFile) selection.getFirstElement();
    }

    private IFile createFileWithGenerateCode(IFile selectedFile, CodeGenerator codeGenerator, CodeDialog dialog) throws CoreException {
        String finalCode = codeGenerator.appendPackage(dialog.getGeneratedPackage(), dialog.getGeneratedCode());
        IFolder folder = selectedFile.getProject().getFolder(dialog.getJavaSourcePath() + "/" + dialog.getGeneratedPackage().replace(".", "/"));
        createIfNotExist(folder);
        IFile iFile = folder.getFile(codeGenerator.getJavaFileName(selectedFile.getName(), resourceName));
        iFile.create(codeGenerator.getInputStreamFromString(finalCode), false, null);
        return iFile;
    }

    private void createIfNotExist(IFolder folder) throws CoreException {
        if (!folder.exists()) {
            createIfNotExist((IFolder) folder.getParent());
            folder.create(false, false, null);
        }
    }

    private String getPackageName(IFile selectedFile) {
        IFile file = selectedFile.getProject().getFile("/AndroidManifest.xml");
        try {
            return packageExtractor.extractPackageFromManifestStream(file.getContents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
