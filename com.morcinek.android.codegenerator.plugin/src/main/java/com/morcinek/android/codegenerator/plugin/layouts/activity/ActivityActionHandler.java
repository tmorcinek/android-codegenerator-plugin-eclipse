package com.morcinek.android.codegenerator.plugin.layouts.activity;

import com.morcinek.android.codegenerator.codegeneration.providers.factories.ActivityResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.layouts.LayoutActionHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ActivityActionHandler extends LayoutActionHandler {

    public ActivityActionHandler() {
        super(Activator.getDefault().createCodeGenerator("Activity_template", new ActivityResourceProvidersFactory()), "Activity");
    }
}
