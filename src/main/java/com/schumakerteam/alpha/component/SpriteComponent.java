package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.core.impl.AssetManager;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public final class SpriteComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 2;
    private final int id;

    private int width;
    private int height;
    private String spriteName;
    private boolean flip = false;

    // TODO fix constructor call and w, h init
    public SpriteComponent(String spriteName) {
        var dimension = AssetManager.getImageDimension(spriteName);
        this.width = dimension.getLeft();
        this.height = dimension.getRight();
        this.spriteName = spriteName;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("SpriteComponent created with id: " + id);
    }

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

    public void setFlip(boolean f) {
        this.flip = f;
    }

    public boolean isFlipped() {
        return this.flip;
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
