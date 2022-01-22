package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.core.impl.AssetManager;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public final class RenderSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 1;
    private final int id;
    private Graphics2D render;

    public RenderSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(SpriteComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("RenderSystem created with id: " + id);
    }

    public void update(Graphics2D g) {
        this.render = g;
        this.update();
    }

    @Override
    protected void update() {
        for (var entity : getSystemEntities()) {
            var transform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var sprite = (SpriteComponent) entity.getComponent(SpriteComponent.COMPONENT_TYPE_ID);

        /*    render.draw(new Rectangle.Double(
                    transform.getPosition().getX(),
                    transform.getPosition().getY(),
                    sprite.w,
                    sprite.h)
            );*/

            if (sprite.isFlipped()) {
                render.drawImage(
                        this.flip(sprite.getSpriteName()),
                        (int) transform.getPosition().getX(),
                        (int) transform.getPosition().getY(),
                        sprite.getWidth() * (int) transform.getScale().getX(),
                        sprite.getHeight() * (int) transform.getScale().getY(),
                        null);
            } else {
                render.drawImage(
                        AssetManager.getImage(sprite.getSpriteName()),
                        (int) transform.getPosition().getX(),
                        (int) transform.getPosition().getY(),
                        sprite.getWidth() * (int) transform.getScale().getX(),
                        sprite.getHeight() * (int) transform.getScale().getY(),
                        null);
            }
        }
    }

    private BufferedImage flip(String spriteName) {
        var dimension = AssetManager.getImageDimension(spriteName);
        var image = AssetManager.getImage(spriteName);

        BufferedImage bufferedImage = new BufferedImage(dimension.getLeft(), dimension.getRight(), BufferedImage.TYPE_INT_RGB);

        Graphics gb = bufferedImage.getGraphics();
        gb.drawImage(image, 0, 0, null);
        gb.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-dimension.getLeft(), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufferedImage = op.filter(bufferedImage, null);

        return bufferedImage;
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
