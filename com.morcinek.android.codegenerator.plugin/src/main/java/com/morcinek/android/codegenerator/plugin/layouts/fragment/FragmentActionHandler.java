package com.morcinek.android.codegenerator.plugin.layouts.fragment;

import com.morcinek.android.codegenerator.codegeneration.providers.factories.FragmentResourceProvidersFactory;
import com.morcinek.android.codegenerator.plugin.Activator;
import com.morcinek.android.codegenerator.plugin.layouts.LayoutActionHandler;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class FragmentActionHandler extends LayoutActionHandler {

    public FragmentActionHandler() {
        super(Activator.getDefault().createCodeGenerator("Fragment_template", new FragmentResourceProvidersFactory()), "Fragment");
    }
}
