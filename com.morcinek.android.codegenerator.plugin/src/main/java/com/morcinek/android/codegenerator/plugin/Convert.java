package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.extractor.XMLPackageExtractor;
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
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import java.io.FileInputStream;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class Convert extends AbstractHandler {

    public Object execute(ExecutionEvent arg0) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(arg0);
        IFile selectedFile = getResource(selection);
        try {
            final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(arg0);

            CodeGenerator codeGenerator = createCodeGenerator();
            String producedCode = codeGenerator.produceCode(selectedFile.getContents(), selectedFile.getName());
            CodeDialog dialog = new CodeDialog(window.getShell(), "src/main/java", selectedFile.getName(), getPackageName(getRootPath(selection)), producedCode);
            if (dialog.open() == IStatus.OK) {
                createFileWithGenerateCode(selectedFile, codeGenerator, dialog);
            } else {
                ClipboardHelper.copy(codeGenerator.appendPackage(dialog.getGeneratedPackage(), dialog.getGeneratedCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage(e);
        }
        return null;
    }

    private void createFileWithGenerateCode(IFile selectedFile, CodeGenerator codeGenerator, CodeDialog dialog) throws CoreException {
        String finalCode = codeGenerator.appendPackage(dialog.getGeneratedPackage(), dialog.getGeneratedCode());
        IFolder folder = selectedFile.getProject().getFolder(dialog.getJavaSourcePath() + "/" + dialog.getGeneratedPackage().replace(".", "/"));
        createIfNotExist(folder);
        IFile iFile = folder.getFile(codeGenerator.getJavaFileName(selectedFile.getName()));
        iFile.create(codeGenerator.getInputStreamFromString(finalCode), false, null);
    }

    public void createIfNotExist(IFolder folder) throws CoreException {
        if (!folder.exists()) {
            createIfNotExist((IFolder) folder.getParent());
            folder.create(false, false, null);
        }
    }

    private IFile getResource(IStructuredSelection selection) {
        Object firstElement = selection.getFirstElement();
        return (IFile) firstElement;
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

    private String getRootPath(IStructuredSelection selection) {
        File fileProject = (File) selection.getFirstElement();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        java.io.File workspaceDirectory = workspace.getRoot().getLocation().toFile();
        Path p = new Path(workspaceDirectory.getAbsolutePath() + fileProject.getProject().getFullPath());
        return p.toOSString();
    }

    private String getPackageName(String rootPath) {
        Path path = new Path(rootPath + "/AndroidManifest.xml");
        try {
            return new XMLPackageExtractor().extractPackageFromManifestStream(new FileInputStream(path.toFile()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}