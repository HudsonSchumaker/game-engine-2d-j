package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.common.IdComponentMap;
import com.schumakerteam.alpha.core.impl.AssetTextureManager;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class SpriteComponent extends Component {

    public static final int COMPONENT_TYPE_ID = IdComponentMap.getTypeId(SpriteComponent.class.getName());
    private final int id;
    private final int width;
    private final int height;
    private String spriteName;
    private boolean flip = false;
    private int zOrder = 0;

    // TODO fix constructor call and w, h init
    public SpriteComponent(String spriteName) {
        var dimension = AssetTextureManager.getTextureDimension(spriteName);
        this.width = dimension.getLeft();
        this.height = dimension.getRight();
        this.spriteName = spriteName;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("SpriteComponent created with id: " + id);
    }

    public SpriteComponent(int z, String spriteName) {
        var dimension = AssetTextureManager.getTextureDimension(spriteName);
        this.width = dimension.getLeft();
        this.height = dimension.getRight();
        this.zOrder = z;
        this.spriteName = spriteName;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("SpriteComponent created with id: " + id);
    }

    public SpriteComponent(int width, int height, String spriteName) {
        this.width = width;
        this.height = height;
        this.spriteName = spriteName;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("SpriteComponent created with id: " + id);
    }

    public SpriteComponent(int width, int height, int z, String spriteName) {
        this.width = width;
        this.height = height;
        this.zOrder = z;
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

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public void setFlip(boolean f) {
        this.flip = f;
    }

    public boolean isFlipped() {
        return this.flip;
    }

    public int getzOrder() {
        return this.zOrder;
    }

    public void setzOrder(int zOrder) {
        this.zOrder = zOrder;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }

    @Override
    public String toString() {
        return "SpriteComponent{" + "id=" + id + ", width=" + width + ", height=" + height + ", spriteName='" + spriteName + '\'' + ", flip=" + flip + '}';
    }
}
