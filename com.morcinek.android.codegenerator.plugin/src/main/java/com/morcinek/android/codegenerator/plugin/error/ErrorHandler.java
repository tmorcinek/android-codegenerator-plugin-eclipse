package com.morcinek.android.codegenerator.plugin.error;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ErrorHandler {

    public void handleError(Exception exception) {
        exception.printStackTrace();
        showErrorMessage(exception);
    }

    private void showErrorMessage(Exception e) {
        showErrorMessage(e.getMessage());
    }

    private void showErrorMessage(String message) {
        MessageDialog.openError(new Shell(), "Eclipse Maven Plugin", message);
    }
}
