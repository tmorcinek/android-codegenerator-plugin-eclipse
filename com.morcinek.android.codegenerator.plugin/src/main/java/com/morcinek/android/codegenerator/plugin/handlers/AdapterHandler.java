package com.morcinek.android.codegenerator.plugin.handlers;

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
