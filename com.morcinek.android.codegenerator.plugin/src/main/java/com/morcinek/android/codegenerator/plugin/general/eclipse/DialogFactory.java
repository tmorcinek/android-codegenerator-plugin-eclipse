package com.morcinek.android.codegenerator.plugin.general.eclipse;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class DialogFactory {

    public static int openFileExistsDialog(Shell shell, String folderName, String fileName) {
        MessageDialog dialog = new MessageDialog(shell, "File already exists", null,
                String.format("File '%s/%s' already exists.\nDo you want to override file content with generated code.",
                        folderName, fileName), MessageDialog.WARNING, new String[]{"Override", "Cancel"}, 0);
        return dialog.open();
    }
}
