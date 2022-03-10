package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.TileMapComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.core.impl.AssetTextureManager;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;

/**
 * @author Hudson Schumaker
 */
public class TileMapSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 2;
    private final int id;

    public TileMapSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignatures();
        LogService.getInstance().engine("TileMapSystem created with id: " + id);
    }

    @Override
    protected void setOnSignatures() {
        this.setOnSignature(TileMapComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
    }

    @Override
    public void update(Graphics2D render) {
        for (var entity : getSystemEntities()) {
            var mapTransform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var tileMap = (TileMapComponent) entity.getComponent(TileMapComponent.COMPONENT_TYPE_ID);

            for (var tile : tileMap.getTiles()) {
                var image = AssetTextureManager.getTileTexture(tile.getTileName()).getTexture();

                render.drawImage(
                        image,
                        (int) mapTransform.getX() + (int) tile.getTransformComponent().getX(),
                        (int) mapTransform.getY() + (int) tile.getTransformComponent().getY(),
                        tileMap.getTileSize() * tileMap.getScale().getX(),
                        tileMap.getTileSize() * tileMap.getScale().getY(),
                        null);
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

    @Override
    public String toString() {
        return "TileMapSystem{" +
                "id=" + id +
                '}';
    }
}
