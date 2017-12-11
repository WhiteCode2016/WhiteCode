package com.whitecode.bean;

import java.io.Serializable;

/**
 *
 * Created by White on 2017/12/11.
 */
public class LabelValueBean<T> implements Serializable {
    private String label;
    private T value;

    public LabelValueBean() {
    }

    public LabelValueBean(String label, T value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
