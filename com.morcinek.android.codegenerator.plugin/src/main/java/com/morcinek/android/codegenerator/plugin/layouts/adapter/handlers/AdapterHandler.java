package com.morcinek.android.codegenerator.plugin.layouts.adapter.handlers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.AdapterResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.layouts.AbstractLayoutHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class AdapterHandler extends AbstractLayoutHandler {

    @Override
    protected CodeGenerator getCodeGenerator() {
        return Activator.getDefault().createCodeGenerator("Adapter_template", new AdapterResourceProvidersFactory());
    }

    @Override
    protected String getResourceName() {
        return "Adapter";
    }
}
