package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.Entity;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.gfx.Scene;
import com.schumakerteam.alpha.gfx.Window;
import com.schumakerteam.alpha.log.LogService;
import com.schumakerteam.alpha.systems.MovementSystem;
import com.schumakerteam.alpha.systems.RenderSystem;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Game implements Runnable {

    private DisplayMode displayMode;
    private GraphicsEnvironment environment;
    private GraphicsDevice device;

    private Scene scene;
    private Window windowGame;
    private boolean isRunning = false;
    //private double deltaTime = 0.0;

    private final int width;
    private final int height;
    private Font small;

    private int FPS = 0;
    private int UPS = 0;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.displayMode = new DisplayMode(width, height, 32, 60);
        this.environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = environment.getDefaultScreenDevice();
    }

    public void initialize() {
        this.scene = new Scene(this.width, this.height);
        this.windowGame = new Window(scene);
        this.scene.initialize();
        //this.device.setDisplayMode(displayMode);
        //this.device.setFullScreenWindow(windowGame);

        //this.small = new Font("Arial Unicode", Font.BOLD, 14);
        this.scene.requestFocus();
    }

    public void setup() {
       // AssetManager.addTexture("tank-panther-right.png");
       // AssetManager.addTexture("truck-ford-left.png");
       // AssetManager.addTexture("radar.png");
        List<String> textures = Arrays.asList("tank-panther-right.png", "truck-ford-left.png", "radar.png");

        var future = AssetManager.loadInBatch(textures);

        Registry r = Registry.getInstance();
        r.addSystem(new MovementSystem());
        r.addSystem(new RenderSystem());

        //var displayModes = this.device.getDisplayModes();
        //for (var dm : displayModes) {
         //   LogService.getInstance().info(dm.toString());
       // }

        Entity tank = r.createEntity();
        tank.addComponent(new TransformComponent(new Vector2D(0, 10), Vector2D.Scale(), 0.0));
        tank.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank.addComponent(new SpriteComponent("tank-panther-right.png"));

        Entity tank2 = r.createEntity();
        tank2.addComponent(new TransformComponent(new Vector2D(0, 50), Vector2D.Scale2x(), 0.0));
        tank2.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank2.addComponent(new SpriteComponent("tank-panther-right.png"));

        Entity tank3 = r.createEntity();
        tank3.addComponent(new TransformComponent(new Vector2D(0, 90), Vector2D.Scale3x(), 45.0));
        tank3.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank3.addComponent(new SpriteComponent("tank-panther-right.png"));

        Entity truck = r.createEntity();
        truck.addComponent(new TransformComponent(new Vector2D(1000, 155), Vector2D.Scale2x(), 0.0));
        truck.addComponent(new RigidBodyComponent(Vector2D.Backward()));
        truck.addComponent(new SpriteComponent("truck-ford-left.png"));

        Entity tank4 = r.createEntity();
        tank4.addComponent(new TransformComponent(new Vector2D(0, 200), Vector2D.Scale(), 0.0));
        tank4.addComponent(new RigidBodyComponent(Vector2D.Down()));
        tank4.addComponent(new SpriteComponent("tank-panther-right.png"));

        Entity tank5 = r.createEntity();
        tank5.addComponent(new TransformComponent(new Vector2D(0, 264), Vector2D.Scale(), 0.0));
        tank5.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank5.addComponent(new SpriteComponent("tank-panther-right.png"));

        Entity tank6 = r.createEntity();
        tank6.addComponent(new TransformComponent(new Vector2D(0, 300), Vector2D.Scale(), 0.0));
        tank6.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank6.addComponent(new SpriteComponent("tank-panther-right.png"));

        Entity radar = r.createEntity();
        radar.addComponent(new TransformComponent(new Vector2D(50, 10), Vector2D.Scale(), 0.0));
        radar.addComponent(new SpriteComponent("radar.png"));

        Entity radar2 = r.createEntity();
        radar2.addComponent(new TransformComponent(new Vector2D(50, 74), Vector2D.Scale(), 0.0));
        radar2.addComponent(new SpriteComponent("radar.png"));

        Entity radar3 = r.createEntity();
        radar3.addComponent(new TransformComponent(new Vector2D(50, 138), Vector2D.Scale(), 0.0));
        radar3.addComponent(new SpriteComponent("radar.png"));

        Entity radar4 = r.createEntity();
        radar4.addComponent(new TransformComponent(new Vector2D(50, 200), Vector2D.Scale(), 0.0));
        radar4.addComponent(new SpriteComponent("radar.png"));

        var spriteComponent = (SpriteComponent) tank2.getComponent(SpriteComponent.COMPONENT_TYPE_ID);
        spriteComponent.setFlip(true);

        spriteComponent = (SpriteComponent) tank4.getComponent(SpriteComponent.COMPONENT_TYPE_ID);
        spriteComponent.setFlip(true);


        //truck.removeComponent(RigidBodyComponent.COMPONENT_TYPE_ID);

        r.addEntityToSystems(tank);
        r.addEntityToSystems(tank2);
        r.addEntityToSystems(tank3);
        r.addEntityToSystems(tank4);
        r.addEntityToSystems(tank5);
        r.addEntityToSystems(truck);
        r.addEntityToSystems(radar);
        r.addEntityToSystems(radar2);
        r.addEntityToSystems(radar3);
        r.addEntityToSystems(radar4);
        try {
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        this.isRunning = true;
    }

    public void processInput() {
    }

    public void update(double deltaTime) {
        var system = (MovementSystem) Registry.getInstance().getSystem(MovementSystem.SYSTEM_TYPE_ID);
        system.update(deltaTime);
        Registry.getInstance().update();
    }

    public void render() {
        Graphics2D g = (Graphics2D) scene.getBufferStrategy().getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        var render = (RenderSystem) Registry.getInstance().getSystem(RenderSystem.SYSTEM_TYPE_ID);
        render.update(g);

        g.setColor(Color.blue);
       // g.setFont(small);
        g.drawString("FPS:" + FPS + " UPS: " + UPS, 32, 32);
        g.dispose();

        scene.getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void run() {
        initialize();
        setup();

        final int MAX_FRAMES_PER_SECOND = 120; // FPS
        final int MAX_UPDATES_SECOND = 80; // UPS

        final double uOPTIMAL_TIME = 1000000000.0 / MAX_UPDATES_SECOND;
        final double fOPTIMAL_TIME = 1000000000.0 / MAX_FRAMES_PER_SECOND;

        double uDeltaTime = 0.0;
        double fDeltaTime = 0.0;

        int frames = 0;
        int updates = 0;

        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long currentTime = System.nanoTime();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            if (uDeltaTime >= uOPTIMAL_TIME) {
                processInput();
                update(uDeltaTime / 1000000000.0); // Create the deltaTime like Unity
                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }

            if (fDeltaTime >= fOPTIMAL_TIME) {
                render();
                frames++;
                fDeltaTime -= fOPTIMAL_TIME;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                windowGame.setTitle("FPS: " + frames + " UPS: " + updates);
                this.FPS = frames;
                this.UPS = updates;
                frames = 0;
                updates = 0;
            }
        }
    }

    public void start() {
        Thread thread = new Thread(this, "Engine2DJ");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public void stop() {
        try {
            Thread.currentThread().join();
            isRunning = false;
        } catch (InterruptedException ignore) {
        }
    }
}