package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.BoxColliderComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.SpriteSheetComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.core.impl.AssetTextureManager;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.io.GeImageLoader;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * @author Hudson Schumaker
 */
public final class RenderSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 1;
    private final int id;
    private final GeImageLoader imageLoader;

    public RenderSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignatures();
        this.imageLoader = new GeImageLoader();
        LogService.getInstance().engine("RenderSystem created with id: " + id);
    }

    @Override
    protected void setOnSignatures() {
        this.setOnSignature(SpriteComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
    }

    @Override
    public void update(Graphics2D render) {
        for (var entity : getSystemEntities()) {
            var transform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var sprite = entity.getComponent(SpriteComponent.COMPONENT_TYPE_ID);

            if (sprite instanceof SpriteSheetComponent) {
                var spriteSheet = (SpriteSheetComponent) sprite;
                var image = AssetTextureManager.getTexture(spriteSheet.getSpriteName()).getTexture();

                if (transform.getRotation() != 0.0) {
                    image = rotateImageByDegrees(image, transform.getRotation());
                }

                if (spriteSheet.isFlipped()) {
                    image = flip(image);
                }

                render.drawImage(
                        image,
                        (int) transform.getPosition().getX(),
                        (int) transform.getPosition().getY(),
                        spriteSheet.getFrameWidth() * transform.getScale().getX(),
                        spriteSheet.getFrameHeight() * transform.getScale().getY(),
                        null);
            } else {
                var spriteComponent = (SpriteComponent) sprite;
                var image = AssetTextureManager.getTexture(spriteComponent.getSpriteName()).getTexture();

                if (transform.getRotation() != 0.0) {
                    image = rotateImageByDegrees(image, transform.getRotation());
                }

                if (spriteComponent.isFlipped()) {
                    image = flip(image);
                }

                render.drawImage(
                        image,
                        (int) transform.getPosition().getX(),
                        (int) transform.getPosition().getY(),
                        spriteComponent.getWidth() * transform.getScale().getX(),
                        spriteComponent.getHeight() * transform.getScale().getY(),
                        null);

                render.setColor(Color.green);
                render.drawString("" + entity.getId(), (int) transform.getPosition().getX(), (int) transform.getPosition().getY());

                var collider = (BoxColliderComponent) entity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);
                if (collider != null) {
                    render.drawRect(
                            (int) transform.getPosition().getX() + (int) collider.getOffset().getX(),
                            (int) transform.getPosition().getY() + (int) collider.getOffset().getY(),
                            collider.getWidth(),
                            collider.getHeight());
                }
            }
        }
    }

    // TODO check flip image
    private Image flip(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TRANSLUCENT);

        Graphics gb = bufferedImage.getGraphics();
        gb.drawImage(image, 0, 0, null);
        gb.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufferedImage = op.filter(bufferedImage, null);

        return imageLoader.createAcceleratedImage(bufferedImage);
    }

    // TODO check rotate image
    private Image rotateImageByDegrees(Image image, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2.0, (newHeight - h) / 2.0);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return imageLoader.createAcceleratedImage(rotated);
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
        return "RenderSystem{" +
                "id=" + id +
                ", imageLoader=" + imageLoader +
                '}';
    }
}
