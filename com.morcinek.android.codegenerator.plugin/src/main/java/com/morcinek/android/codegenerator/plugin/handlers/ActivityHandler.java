package com.morcinek.android.codegenerator.plugin.handlers;

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