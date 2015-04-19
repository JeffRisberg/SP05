package com.incra.models.propertyEditor;

import com.incra.models.Episode;
import com.incra.services.EpisodeService;

import java.beans.PropertyEditorSupport;

public class EpisodePropertyEditor extends PropertyEditorSupport {
    private final EpisodeService boxService;

    public EpisodePropertyEditor(EpisodeService boxService) {
        this.boxService = boxService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(boxService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Episode) {
            return String.valueOf(((Episode) value).getId());
        } else {
            return super.getAsText();
        }
    }
}
