package com.morcinek.android.codegenerator.plugin.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CodeDialog extends org.eclipse.jface.dialogs.Dialog {

    private final String resourceName;

    private String generatedPackage;
    private String generatedCode;

    private Text packageText;
    private Text codeText;

    public CodeDialog(Shell parentShell, String resourceName, String generatedPackage, String generatedCode) {
        super(parentShell);
        this.resourceName = resourceName;
        this.generatedPackage = generatedPackage;
        this.generatedCode = generatedCode;
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public String getGeneratedPackage() {
        return generatedPackage;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(String.format("Code generated from: '%s'", resourceName));
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        createPackageSection(createGridComposite(area));
        createTextSection(area);
        createButtons(createGridComposite(area));
        return area;
    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    @Override
    protected void cancelPressed() {
        saveInput();
        super.cancelPressed();
    }

    private void saveInput() {
        generatedPackage = packageText.getText();
        generatedCode = codeText.getText();
    }

    private void createButtons(Composite gridComposite) {
        createButton(gridComposite, "Copy Code To Clipboard", new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                cancelPressed();
            }
        });
        createButton(gridComposite, "Create File", new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                okPressed();
            }
        });
    }

    private void createButton(Composite gridComposite, String text, SelectionAdapter listener) {
        Button button = new Button(gridComposite, SWT.BORDER);
        button.setLayoutData(getGridData());
        button.setText(text);
        button.addSelectionListener(listener);
    }

    private Composite createGridComposite(Composite area) {
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(new GridLayout(2, false));
        return container;
    }

    private void createPackageSection(Composite container) {
        new Label(container, SWT.NONE).setText("Package");
        packageText = new Text(container, SWT.BORDER);
        packageText.setLayoutData(getGridData());
        packageText.setText(generatedPackage);
    }

    private void createTextSection(Composite container) {
        codeText = new Text(container, SWT.BORDER);
        codeText.setLayoutData(getGridData());
        codeText.setText(generatedCode);
    }

    private GridData getGridData() {
        GridData gridData = new GridData();
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        return gridData;
    }
}