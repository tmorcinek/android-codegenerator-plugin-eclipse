package com.morcinek.android.codegenerator.plugin.eclipse;

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

    private String sourcePath;
    private String generatedPackage;
    private String generatedCode;

    private Text sourcePathText;
    private Text packageText;
    private Text codeText;

    public static class Builder {

        private CodeDialog codeDialog;

        public Builder(Shell parentShell, String resourceName) {
            codeDialog = new CodeDialog(parentShell, resourceName);
        }

        public Builder setSourcePath(String sourcePath) {
            codeDialog.sourcePath = sourcePath;
            return this;
        }

        public Builder setPackage(String packageName) {
            codeDialog.generatedPackage = packageName;
            return this;
        }

        public Builder setCode(String code) {
            codeDialog.generatedCode = code;
            return this;
        }

        public CodeDialog create() {
            return codeDialog;
        }
    }

    public CodeDialog(Shell parentShell, String resourceName) {
        super(parentShell);
        this.resourceName = resourceName;
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

        Composite gridComposite = createGridComposite(area);
        sourcePathText = createTextSection(gridComposite, "Java Source Path", sourcePath);
        packageText = createTextSection(gridComposite, "Package", generatedPackage);

        codeText = createText(area, generatedCode);

        createButtons(createGridComposite(area));
        return area;
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

    private Text createTextSection(Composite container, String label, String defaultText) {
        createLabel(container, label);
        return createText(container, defaultText);
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public String getGeneratedPackage() {
        return generatedPackage;
    }

    public String getSourcePath() {
        return sourcePath;
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
        sourcePath = sourcePathText.getText();
    }

    private void createButton(Composite gridComposite, String text, SelectionAdapter listener) {
        Button button = new Button(gridComposite, SWT.BORDER);
        button.setLayoutData(getGridData());
        button.setText(text);
        button.addSelectionListener(listener);
    }

    private Text createText(Composite container, String defaultText) {
        Text text = new Text(container, SWT.BORDER);
        text.setLayoutData(getGridData());
        text.setText(defaultText);
        return text;
    }

    private Composite createGridComposite(Composite area) {
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(new GridLayout(2, false));
        return container;
    }

    private void createLabel(Composite container, String text) {
        new Label(container, SWT.NONE).setText(text);
    }

    private GridData getGridData() {
        GridData gridData = new GridData();
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        return gridData;
    }
}