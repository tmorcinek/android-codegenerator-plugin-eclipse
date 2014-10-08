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

    public static final int RETURN_VALUE_CREATE_FILE = 1;
    public static final int RETURN_VALUE_COPY = 2;

    private final CodeDialogBundle bundle;

    private Text sourcePathText;
    private Text packageText;
    private Text codeText;

    private int returnValue;

    public CodeDialog(Shell parentShell, CodeDialogBundle bundle) {
        super(parentShell);
        this.bundle = bundle;
    }

    public CodeDialogBundle getBundle() {
        return bundle;
    }

    public int getReturnValue() {
        return returnValue;
    }

    private void okPressed(int returnValue) {
        this.returnValue = returnValue;
        saveInput();
        okPressed();
    }

    private void saveInput() {
        bundle.setPackage(getTextValue(packageText));
        bundle.setSourcePath(getTextValue(sourcePathText));
        bundle.setCode(getTextValue(codeText));
    }

    private String getTextValue(Text text) {
        if (text != null) {
            return text.getText().trim();
        }
        return null;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(String.format("Code generated from: '%s'", bundle.getResourceName()));
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);

        Composite gridComposite = createGridComposite(area);
        if (bundle.getSourcePath() != null) {
            sourcePathText = createTextSection(gridComposite, "Source Path", bundle.getSourcePath());
        }
        if (bundle.getPackage() != null) {
            packageText = createTextSection(gridComposite, "Package", bundle.getPackage());
        }

        codeText = createText(area, bundle.getCode(), SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);

        createButtons(createGridComposite(area));
        return area;
    }

    private void createButtons(Composite gridComposite) {
        createButton(gridComposite, "Copy Code To Clipboard", new DialogSelectionAdapter(RETURN_VALUE_COPY));
        if (bundle.showCreateFileButton()) {
            createButton(gridComposite, "Create File", new DialogSelectionAdapter(RETURN_VALUE_CREATE_FILE));
        }
    }

    private Text createTextSection(Composite container, String label, String defaultText) {
        createLabel(container, label);
        return createText(container, defaultText);
    }

    private void createButton(Composite gridComposite, String text, SelectionAdapter listener) {
        Button button = new Button(gridComposite, SWT.BORDER);
        button.setLayoutData(getGridData());
        button.setText(text);
        button.addSelectionListener(listener);
    }

    private Text createText(Composite container, String defaultText, int style) {
        Text text = new Text(container, SWT.BORDER | style);
        text.setLayoutData(getGridData());
        text.setText(defaultText);
        return text;
    }

    private Text createText(Composite container, String defaultText) {
        return createText(container, defaultText, 0);
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

    private class DialogSelectionAdapter extends SelectionAdapter {

        private final int returnValue;

        DialogSelectionAdapter(int returnValue) {
            this.returnValue = returnValue;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            okPressed(returnValue);
        }
    }
}