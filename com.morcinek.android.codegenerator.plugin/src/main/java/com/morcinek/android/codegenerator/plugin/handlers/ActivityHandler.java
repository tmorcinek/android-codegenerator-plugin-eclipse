package com.morcinek.android.codegenerator.plugin.handlers;

import com.morcinek.android.codegenerator.codegeneration.providers.factories.ActivityResourceProvidersFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;


/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ActivityHandler extends AbstractLayoutHandler {

    @Override
    protected String getTemplateName() {
        return "Activity_template";
    }

    @Override
    protected String getResourceName() {
        return "Activity";
    }
}