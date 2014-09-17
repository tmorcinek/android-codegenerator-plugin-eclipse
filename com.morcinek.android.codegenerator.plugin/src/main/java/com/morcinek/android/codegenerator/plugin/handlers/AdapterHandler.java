package com.morcinek.android.codegenerator.plugin.handlers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.AdapterResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.eclipse.CodeDialog;
import com.morcinek.android.codegenerator.plugin.eclipse.EnvironmentHelper;
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
public class AdapterHandler extends AbstractLayoutHandler {

    @Override
    protected String getTemplateName() {
        return "Adapter_template";
    }

    @Override
    protected String getResourceName() {
        return "Adapter";
    }
}
