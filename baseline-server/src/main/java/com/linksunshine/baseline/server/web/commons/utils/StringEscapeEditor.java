package com.linksunshine.baseline.server.web.commons.utils;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by ucmed on 2017/3/20.
 */
public class StringEscapeEditor extends PropertyEditorSupport {


    private boolean escapeHTML;// ±‡¬ÎHTML
    private boolean escapeJavaScript;// ±‡¬Îjavascript

    public StringEscapeEditor() {
    }

    public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
        this.escapeHTML = escapeHTML;
        this.escapeJavaScript = escapeJavaScript;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
            String value = text;
            if (escapeHTML) {
                value = HtmlUtils.htmlEscape(value);
            }
            if (escapeJavaScript) {
                value = JavaScriptUtils.javaScriptEscape(value);
            }
            setValue(value);
        }
    }
}
