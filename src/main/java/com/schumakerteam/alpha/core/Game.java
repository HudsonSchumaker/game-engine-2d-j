package com.schumakerteam.alpha.core;

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

import java.awt.*;

public class Game implements Runnable {

    private DisplayMode displayMode;
    private GraphicsEnvironment environment;
    private GraphicsDevice device;

    private Scene scene;
    private Window windowGame;
    private boolean isRunning = false;

    private double x = 1.0;
    private double y = 100.0;
    //private double deltaTime = 0.0;

    private final int width;
    private final int height;
    private Font small;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.displayMode = new DisplayMode(width, height, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
        this.environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = environment.getDefaultScreenDevice();
    }

    public void initialize() {
        this.scene = new Scene(this.width, this.height);
        this.windowGame = new Window(scene);
        this.scene.initialize();
        // this.device.setFullScreenWindow(windowGame);
        // this.device.setDisplayMode(displayMode);

        this.small = new Font("Arial Unicode", Font.BOLD, 14);
        this.scene.requestFocus();
        this.isRunning = true;
    }

    public void setup() {
        Registry r = Registry.getInstance();
        r.addSystem(new MovementSystem());

        // LogService.getInstance().info("info");
        // LogService.getInstance().warning("warning");
        // LogService.getInstance().error("error");

        var displayModes = this.device.getDisplayModes();
        for (var dm : displayModes) {
            // LogService.getInstance().info(dm.toString());
        }
        // LogService.getInstance().engine("engine");

        Entity tank = r.createEntity();
        tank.addComponent(new TransformComponent(new Vector2D(0, 255), Vector2D.Scale(), 0.0));
        tank.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank.addComponent(new SpriteComponent(32, 32));

        Entity truck = r.createEntity();
        truck.addComponent(new TransformComponent(new Vector2D(1024, 155), Vector2D.Scale(), 0.0));
        truck.addComponent(new RigidBodyComponent(Vector2D.Backward()));
        truck.addComponent(new SpriteComponent(32, 16));

        MovementSystem ms = new MovementSystem();
        r.addSystem(ms);
        RenderSystem rs = new RenderSystem();
        r.addSystem(rs);

        r.addEntityToSystems(tank);
        r.addEntityToSystems(truck);

    }

    public void processInput() {
        x++;
    }

    public void update(double deltaTime) {
        Registry.getInstance().getSystem(MovementSystem.SYSTEM_TYPE_ID).update();
        Registry.getInstance().update();
    }

    public void render() {
        Graphics2D g = (Graphics2D) scene.getBufferStrategy().getDrawGraphics();
        //g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.black);
        g.fillRect(0, 0, 1024, 768);
        g.setColor(Color.blue);

        // TODO credo!!!
        var render = (RenderSystem) Registry.getInstance().getSystem(RenderSystem.SYSTEM_TYPE_ID);
        render.setGraphics2D(g);
        render.update();

        g.fillRect(500, 500, 16, 16);
        g.draw(new Rectangle.Double(x, y, 8, 8));
        g.setColor(Color.red);
        g.setFont(small);
        g.drawString("Game Engine 2D J", 32, 32);
        g.dispose();

        scene.getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void run() {
        initialize();
        setup();

        final int MAX_FRAMES_PER_SECOND = 120; // FPS
        final int MAX_UPDATES_SECOND = 60; // UPS

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
                frames = 0;
                updates = 0;
            }

            //
        }
    }

    public void start() {
        Thread thread = new Thread(this, "Engine2DJ");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
}

