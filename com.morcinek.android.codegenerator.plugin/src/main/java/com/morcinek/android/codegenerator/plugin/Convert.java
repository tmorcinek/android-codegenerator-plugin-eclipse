package com.morcinek.android.codegenerator.plugin;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.extractor.PackageExtractor;
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
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import java.io.FileInputStream;
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

            String packageName = getPackageName(getRootPath(selection));
            CodeDialog dialog = new CodeDialog(window.getShell(), resource.getName(), packageName, string);// new input dialog
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