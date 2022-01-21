package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public final class SpriteComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 2;
    private final int id;

    private int width;
    private int height;
    private String spriteName;

    public SpriteComponent(int w, int h, String spriteName) {
        this.width = w;
        this.height = h;
        this.spriteName = spriteName;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("SpriteComponent created with id: " + id);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getSpriteName() {
        return this.spriteName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }
}
