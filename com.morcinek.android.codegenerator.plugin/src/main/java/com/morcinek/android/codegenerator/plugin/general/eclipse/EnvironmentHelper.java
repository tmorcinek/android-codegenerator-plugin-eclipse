package com.morcinek.android.codegenerator.plugin.general.eclipse;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class EnvironmentHelper {

    public IFile getSelectedFile(ExecutionEvent executionEvent) {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(executionEvent);
        return (IFile) selection.getFirstElement();
    }

    public IWorkbenchWindow getActiveWindow(ExecutionEvent executionEvent) throws ExecutionException {
        return HandlerUtil.getActiveWorkbenchWindowChecked(executionEvent);
    }

    public IFile getNewFile(IFile selectedFile, String javaFileName, String folderPath) throws CoreException {
        IFolder folder = selectedFile.getProject().getFolder(folderPath);
        createIfNotExist(folder);
        return folder.getFile(javaFileName);
    }

    public void createFileWithGeneratedCode(IFile newFile, String finalCode) throws CoreException {
        newFile.create(getInputStreamFromString(finalCode), false, null);
    }

    public void overrideFileWithGeneratedCode(IFile newFile, String finalCode) throws CoreException {
        newFile.setContents(getInputStreamFromString(finalCode), true, true, null);
    }

    public IWorkbenchWindow getActiveWindow(IWorkbenchPart workbenchPart) {
        return workbenchPart.getSite().getWorkbenchWindow();
    }

    public IFile getFileFromEditor(IWorkbenchPart workbenchPart) {
        ITextEditor textEditor = (ITextEditor) workbenchPart;
        IFileEditorInput fileEditorInput = (IFileEditorInput) textEditor.getEditorInput();
        return fileEditorInput.getFile();
    }

    private InputStream getInputStreamFromString(String code) {
        return new ByteArrayInputStream(code.getBytes());
    }

    private void createIfNotExist(IFolder folder) throws CoreException {
        if (!folder.exists()) {
            createIfNotExist((IFolder) folder.getParent());
            folder.create(false, false, null);
        }
    }
}
