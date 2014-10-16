package com.morcinek.android.codegenerator.plugin.general.eclipse;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class CodeDialogBundle {

    private Map<String, String> values = Maps.newHashMap();

    public String getCode() {
        return values.get("code");
    }

    public void setCode(String code) {
        values.put("code", code);
    }

    public String getSourcePath() {
        return values.get("sourcePath");
    }

    public void setSourcePath(String sourcePath) {
        values.put("sourcePath", sourcePath);
    }

    public String getPackage() {
        return values.get("package");
    }

    public void setPackage(String packageName) {
        values.put("package", packageName);
    }

    public String getResourceName() {
        return values.get("resourceName");
    }

    public void setResourceName(String packageName) {
        values.put("resourceName", packageName);
    }

    public boolean showCreateFileButton(){
        return values.containsKey("sourcePath");
    }
}
