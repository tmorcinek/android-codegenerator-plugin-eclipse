package com.morcinek.android.codegenerator.plugin.layouts.activity.handler;

import com.morcinek.android.codegenerator.CodeGenerator;
import com.morcinek.android.codegenerator.codegeneration.providers.factories.ActivityResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.layouts.AbstractLayoutHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ActivityHandler extends AbstractLayoutHandler {

    @Override
    protected CodeGenerator getCodeGenerator() {
        return Activator.getDefault().createCodeGenerator("Activity_template", new ActivityResourceProvidersFactory());
    }

    @Override
    protected String getResourceName() {
        return "Activity";
    }
}