package com.morcinek.android.codegenerator.plugin.general.utils;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ClipboardHelper {

    public static void copy(String generatedCode) {
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        TextTransfer textTransfer = TextTransfer.getInstance();
        clipboard.setContents(new Object[]{generatedCode}, new Transfer[]{textTransfer});
    }
}
