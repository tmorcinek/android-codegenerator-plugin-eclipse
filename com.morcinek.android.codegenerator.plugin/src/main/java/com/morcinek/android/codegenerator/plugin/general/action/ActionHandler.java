package com.morcinek.android.codegenerator.plugin.general.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchWindow;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public interface ActionHandler {

    void handleAction(IFile file, IWorkbenchWindow window) throws ParserConfigurationException, CoreException, SAXException, XPathExpressionException, IOException;
}
