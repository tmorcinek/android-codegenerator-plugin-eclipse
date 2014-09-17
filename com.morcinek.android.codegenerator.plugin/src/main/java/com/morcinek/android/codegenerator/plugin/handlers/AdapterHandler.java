package com.morcinek.android.codegenerator.plugin.handlers;

import com.morcinek.android.codegenerator.codegeneration.providers.factories.AdapterResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class AdapterHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent executionEvent) throws ExecutionException {
        CodeGeneratorController codeGeneratorController = createCodeGeneratorController();
        codeGeneratorController.handleExecutionEvent(executionEvent);
        return null;
    }

    private CodeGeneratorController createCodeGeneratorController() {
        return new CodeGeneratorController("Adapter_template", "Adapter", new AdapterResourceProvidersFactory(), Activator.getDefault().getPreferencesHelper());
    }
}
