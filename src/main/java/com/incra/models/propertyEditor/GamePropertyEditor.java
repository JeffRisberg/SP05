package com.incra.models.propertyEditor;

import com.incra.models.Game;
import com.incra.services.GameService;

import java.beans.PropertyEditorSupport;

public class GamePropertyEditor extends PropertyEditorSupport {
    private final GameService siteService;

    public GamePropertyEditor(GameService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(siteService.findEntityById(Integer.parseInt(text)));
    }

    @Override
    public String getAsText() {
        Object value = getValue();

        if (value instanceof Game) {
            return String.valueOf(((Game) value).getId());
        } else {
            return super.getAsText();
        }
    }
}
