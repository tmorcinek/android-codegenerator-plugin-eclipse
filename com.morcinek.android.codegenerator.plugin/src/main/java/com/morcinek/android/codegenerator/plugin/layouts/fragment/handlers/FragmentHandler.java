package com.morcinek.android.codegenerator.plugin.layouts.fragment.handlers;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.FragmentResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.layouts.handler.AbstractLayoutHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class FragmentHandler extends AbstractLayoutHandler {

    @Override
    protected CodeGenerator getCodeGenerator() {
        return Activator.getDefault().createCodeGenerator("Fragment_template", new FragmentResourceProvidersFactory());
    }

    @Override
    protected String getResourceName() {
        return "Fragment";
    }
}