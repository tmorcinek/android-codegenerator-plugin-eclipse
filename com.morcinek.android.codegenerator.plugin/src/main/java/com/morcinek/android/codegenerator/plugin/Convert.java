package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.TemplateCodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.ActivityResourceProvidersFactory;
import com.morcinek.android.codegenerator.codegeneration.templates.ResourceTemplatesProvider;
import com.morcinek.android.codegenerator.extractor.XMLPackageExtractor;
import com.morcinek.android.codegenerator.extractor.XMLResourceExtractor;
import com.morcinek.android.codegenerator.extractor.string.FileNameExtractor;
import com.morcinek.android.codegenerator.plugin.editor.CodeDialog;
import com.morcinek.android.codegenerator.plugin.error.ErrorHandler;
import com.morcinek.android.codegenerator.plugin.utils.ClipboardHelper;
import com.morcinek.android.codegenerator.plugin.utils.PreferencesHelper;
import org.eclipse.core.commands.AbstractHandler;
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
public class Convert extends AbstractHandler {

    private ErrorHandler errorHandler = new ErrorHandler();

    private PreferencesHelper preferencesHelper = Activator.getDefault().getPreferencesHelper();

    public Object execute(ExecutionEvent arg0) throws ExecutionException {
        final IFile selectedFile = getSelectedFile(arg0);
        final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(arg0);
        try {
            CodeGenerator codeGenerator = createCodeGenerator();
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
        return null;
    }

    private IFile getSelectedFile(ExecutionEvent arg0) {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(arg0);
        return (IFile) selection.getFirstElement();
    }

    private IFile createFileWithGenerateCode(IFile selectedFile, CodeGenerator codeGenerator, CodeDialog dialog) throws CoreException {
        String finalCode = codeGenerator.appendPackage(dialog.getGeneratedPackage(), dialog.getGeneratedCode());
        IFolder folder = selectedFile.getProject().getFolder(dialog.getJavaSourcePath() + "/" + dialog.getGeneratedPackage().replace(".", "/"));
        createIfNotExist(folder);
        IFile iFile = folder.getFile(codeGenerator.getJavaFileName(selectedFile.getName(), "Activity"));
        iFile.create(codeGenerator.getInputStreamFromString(finalCode), false, null);
        return iFile;
    }

    public void createIfNotExist(IFolder folder) throws CoreException {
        if (!folder.exists()) {
            createIfNotExist((IFolder) folder.getParent());
            folder.create(false, false, null);
        }
    }

    private CodeGenerator createCodeGenerator() {
        return new CodeGenerator(XMLResourceExtractor.createResourceExtractor(), new FileNameExtractor(), new TemplateCodeGenerator("Activity_template", new ActivityResourceProvidersFactory(), new ResourceTemplatesProvider()));
    }

    private String getPackageName(IFile selectedFile) {
        IFile file = selectedFile.getProject().getFile("/AndroidManifest.xml");
        try {
            return new XMLPackageExtractor().extractPackageFromManifestStream(file.getContents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}