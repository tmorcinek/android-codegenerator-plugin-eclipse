package com.morcinek.android.codegenerator.plugin.layouts.adapter;

import com.morcinek.android.codegenerator.codegeneration.providers.factories.AdapterResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.layouts.LayoutActionHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class AdapterActionHandler extends LayoutActionHandler {

    public AdapterActionHandler() {
        super(Activator.getDefault().createCodeGenerator("Adapter_template", new AdapterResourceProvidersFactory()), "Adapter");
    }
}
