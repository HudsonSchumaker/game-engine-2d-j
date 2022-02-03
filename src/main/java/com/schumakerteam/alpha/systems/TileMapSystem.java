package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.TileMapComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.core.impl.AssetManager;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;

public class TileMapSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 2;
    private final int id;
    private Graphics2D render;

    public TileMapSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(TileMapComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("TileMapSystem created with id: " + id);
    }

    public void update(Graphics2D g) {
        this.render = g;
        this.update();
    }

    @Override
    protected void update() {
        for (var entity : getSystemEntities()) {
            var transform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var tileMap = (TileMapComponent) entity.getComponent(TileMapComponent.COMPONENT_TYPE_ID);

            var image = AssetManager.getTileMap(tileMap.getSpriteName()).getTexture();
            for (var tile : tileMap.getTiles()) {
                /*var tileImage = image.getSubimage(
                        tile.getTileX(),
                        tile.getTileY(),
                        tileMap.getTileSize(),
                        tileMap.getTileSize());*/

                Rectangle rect = new Rectangle();
                rect.x = tile.getTileX();
                rect.y = tile.getTileY();
                rect.width = tileMap.getTileSize() * transform.getScale().getX();
                rect.height = tileMap.getTileSize() * transform.getScale().getY();

                render.clipRect(rect.x, rect.y, rect.width, rect.height);

                render.drawImage(image, tile.getTileX(), tile.getTileY(), tileMap.getTileSize(), tileMap.getTileSize(), null);
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return SYSTEM_TYPE_ID;
    }
}
