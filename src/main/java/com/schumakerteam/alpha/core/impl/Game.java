package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.component.*;
import com.schumakerteam.alpha.core.IGame;
import com.schumakerteam.alpha.ecs.impl.Entity;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.events.Event;
import com.schumakerteam.alpha.events.EventBus;
import com.schumakerteam.alpha.events.EventType;
import com.schumakerteam.alpha.geometry.Scale2D;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.gfx.Scene;
import com.schumakerteam.alpha.gfx.Window;
import com.schumakerteam.alpha.log.LogService;
import com.schumakerteam.alpha.sfx.PlayType;
import com.schumakerteam.alpha.systems.*;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * @author Hudson Schumaker
 */
public class Game implements IGame {

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
        EventBus.getInstance().subscribe(EventType.ON_WINDOW_CLOSING, this);
    }

    @Override
    public void initialize() {
        this.scene = new Scene(this.width, this.height);
        this.windowGame = new Window(scene);
        //this.device.setDisplayMode(displayMode);
        //this.device.setFullScreenWindow(windowGame);

        this.small = new Font("Arial Unicode", Font.BOLD, 14);
        this.scene.requestFocus();
    }

    @Override
    public void setup() {
        Registry r = Registry.getInstance();
        r.addSystem(new TileMapSystem());
        r.addSystem(new MovementSystem());
        r.addSystem(new RenderSystem());
        r.addSystem(new AnimationSystem());
        r.addSystem(new CollisionSystem());
        r.addSystem(new DamageSystem());
        r.addSystem(new KeyboardInputSystem());

        List<String> textures = Arrays.asList("tank-panther-right.png", "truck-ford-left.png", "radar.png", "chopper.png");

        var future = AssetTextureManager.loadInBatch(textures);
        AssetTextureManager.addTextureFromWeb("https://schumakerteam.com/lab/car.png");
        AssetTextureManager.addTileMap("jungle.png");

        Entity map = r.createEntity();
        map.addComponent(new TransformComponent(Vector2D.Zero(), Scale2D.scale2x()));
        map.addComponent(new TileMapComponent("jungle.png", "tileMap.map", Scale2D.scale2x(), 32));

        var displayModes = this.device.getDisplayModes();
        for (var dm : displayModes) {
            LogService.getInstance().info(dm.toString());
        }

        Entity car = r.createEntity();
        car.addComponent(new TransformComponent(new Vector2D(768, 0)));
        car.addComponent(new RigidBodyComponent(new Vector2D(-3, 3)));
        car.addComponent(new SpriteComponent("car.png"));
        car.addComponent(new BoxColliderComponent(32, 32, new Vector2D(8, -2)));
        car.addComponent(new MovementComponent());

        Entity tank = r.createEntity();
        tank.addComponent(new TransformComponent(new Vector2D(0, 10), Scale2D.scale(), 0.0));
        tank.addComponent(new RigidBodyComponent(new Vector2D(2, 2)));
        tank.addComponent(new SpriteComponent("tank-panther-right.png"));
        tank.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        //tank.addComponent(new InputComponent());

        Entity tank9 = r.createEntity();
        tank9.addComponent(new TransformComponent(new Vector2D(1560, 10), Scale2D.scale(), 0.0));
        tank9.addComponent(new RigidBodyComponent(new Vector2D(-3, 0)));
        tank9.addComponent(new SpriteComponent("tank-panther-right.png"));
        tank9.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank9.addComponent(new MovementComponent());

        Entity tank2 = r.createEntity();
        tank2.addComponent(new TransformComponent(new Vector2D(0, 50), Scale2D.scale2x(), 0.0));
        tank2.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank2.addComponent(new SpriteComponent(2, "tank-panther-right.png"));
        tank2.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank2.addComponent(new MovementComponent());

        Entity tank3 = r.createEntity();
        tank3.addComponent(new TransformComponent(new Vector2D(0, 90), Scale2D.scale3x(), 0.0));
        tank3.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank3.addComponent(new SpriteComponent(2, "tank-panther-right.png"));
        tank3.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank3.addComponent(new MovementComponent());

        Entity tank7 = r.createEntity();
        tank7.addComponent(new TransformComponent(new Vector2D(32, 116), Scale2D.scale(), 0.0));
        tank7.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank7.addComponent(new SpriteComponent(10, "tank-panther-right.png"));
        tank7.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank7.addComponent(new MovementComponent());

        Entity truck = r.createEntity();
        truck.addComponent(new TransformComponent(new Vector2D(1000, 155), Scale2D.scale2x(), 0.0));
        truck.addComponent(new RigidBodyComponent(Vector2D.Backward()));
        truck.addComponent(new SpriteComponent("truck-ford-left.png"));
        truck.addComponent(new MovementComponent());

        Entity tank4 = r.createEntity();
        tank4.addComponent(new TransformComponent(new Vector2D(0, 200)));
        tank4.addComponent(new RigidBodyComponent(Vector2D.Down()));
        tank4.addComponent(new SpriteComponent("tank-panther-right.png"));
        tank4.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank4.addComponent(new MovementComponent());

        Entity tank5 = r.createEntity();
        tank5.addComponent(new TransformComponent(new Vector2D(0, 264)));
        tank5.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank5.addComponent(new SpriteComponent("tank-panther-right.png"));
        tank5.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank5.addComponent(new MovementComponent());

        Entity tank6 = r.createEntity();
        tank6.addComponent(new TransformComponent(new Vector2D(0, 300)));
        tank6.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank6.addComponent(new SpriteComponent("tank-panther-right.png"));
        tank6.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank6.addComponent(new MovementComponent());

        Entity tank8 = r.createEntity();
        tank8.addComponent(new TransformComponent(new Vector2D(0, 316)));
        tank8.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        tank8.addComponent(new SpriteComponent("tank-panther-right.png"));
        tank8.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        tank8.addComponent(new MovementComponent());

        Entity radar = r.createEntity();
        radar.addComponent(new TransformComponent(new Vector2D(50, 10)));
        radar.addComponent(new SpriteSheetComponent(512, 64, 1, 64, 64, 8, "radar.png"));
        radar.addComponent(new AnimationComponent(8, 1, 8, true));

        var spriteComponent = (SpriteComponent) tank2.getComponent(SpriteComponent.COMPONENT_TYPE_ID);
        //spriteComponent.setFlip(true);

        spriteComponent = (SpriteComponent) tank4.getComponent(SpriteComponent.COMPONENT_TYPE_ID);
        //spriteComponent.setFlip(true);

        Entity chopper0 = r.createEntity();
        chopper0.addComponent(new TransformComponent(new Vector2D(0.0, 377.0)));
        chopper0.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        chopper0.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper0.addComponent(new AnimationComponent(2, 1, 15, true));
        chopper0.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper0.addComponent(new MovementComponent());
        chopper0.destroy(9000);

        Entity chopper = r.createEntity();
        chopper.addComponent(new TransformComponent(new Vector2D(0.0, 411.0)));
        chopper.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        chopper.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper.addComponent(new AnimationComponent(2, 1, 15, true));
        chopper.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper.addComponent(new MovementComponent());

        Entity chopper2 = r.createEntity();
        chopper2.addComponent(new TransformComponent(new Vector2D(0.0, 444.0)));
        chopper2.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        chopper2.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper2.addComponent(new AnimationComponent(2, 1, 15, true));
        chopper2.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper2.addComponent(new MovementComponent());

        Entity chopper3 = r.createEntity();
        chopper3.addComponent(new TransformComponent(new Vector2D(0.0, 477.0)));
        chopper3.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        chopper3.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper3.addComponent(new AnimationComponent(2, 1, 15, true));
        chopper3.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper3.addComponent(new MovementComponent());

        Entity chopper4 = r.createEntity();
        chopper4.addComponent(new TransformComponent(new Vector2D(0.0, 511.0)));
        chopper4.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        chopper4.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper4.addComponent(new AnimationComponent(2, 1, 15, true));
        chopper4.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper4.addComponent(new MovementComponent());

        Entity chopper5 = r.createEntity();
        chopper5.addComponent(new TransformComponent(new Vector2D(0.0, 544.0)));
        chopper5.addComponent(new RigidBodyComponent(Vector2D.Forward()));
        chopper5.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper5.addComponent(new AnimationComponent(2, 1, 15, true));
        chopper5.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper5.addComponent(new MovementComponent());

        Entity chopper6 = r.createEntity();
        chopper6.addComponent(new TransformComponent(new Vector2D(0.0, 577.0)));
        chopper6.addComponent(new RigidBodyComponent(new Vector2D(5, 5)));
        chopper6.addComponent(new SpriteSheetComponent(64, 32, 10, 32, 32, 2, "chopper.png"));
        chopper6.addComponent(new AnimationComponent(2, 1, 14, true));
        chopper6.addComponent(new BoxColliderComponent(32, 32, Vector2D.offset()));
        chopper6.addComponent(new AudioComponent(true, false, PlayType.MEMORY, "helicopter.wav"));
        chopper6.addComponent(new InputComponent());

        var a = (AudioComponent) chopper6.getComponent(AudioComponent.COMPONENT_TYPE_ID);
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                a.play();
            }
        };
        //timer.schedule(task, 5000);

        spriteComponent = (SpriteComponent) chopper.getComponent(SpriteComponent.COMPONENT_TYPE_ID);
        spriteComponent.setFlip(true);

        // truck.removeComponent(RigidBodyComponent.COMPONENT_TYPE_ID);

        try {
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        this.isRunning = true;
    }

    @Override
    public void processInput() {
        this.scene.getKeys().poll();
    }

    @Override
    public void update(double deltaTime) {
        var keyboardInputSystem = (KeyboardInputSystem) Registry.getInstance().getSystem(KeyboardInputSystem.SYSTEM_TYPE_ID);
        keyboardInputSystem.update(deltaTime);

        var movementSystem = (MovementSystem) Registry.getInstance().getSystem(MovementSystem.SYSTEM_TYPE_ID);
        movementSystem.update(deltaTime);

        var collisionSystem = (CollisionSystem) Registry.getInstance().getSystem(CollisionSystem.SYSTEM_TYPE_ID);
        collisionSystem.update();

        var animationSystem = (AnimationSystem) Registry.getInstance().getSystem(AnimationSystem.SYSTEM_TYPE_ID);
        animationSystem.update();

        Registry.getInstance().update();
    }

    @Override
    public void render() {
        Graphics2D g = (Graphics2D) scene.getBufferStrategy().getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        var tileMapSystem = (TileMapSystem) Registry.getInstance().getSystem(TileMapSystem.SYSTEM_TYPE_ID);
        tileMapSystem.update(g);

        var renderSystem = (RenderSystem) Registry.getInstance().getSystem(RenderSystem.SYSTEM_TYPE_ID);
        renderSystem.update(g);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("FPS:" + FPS + " UPS: " + UPS, 32, 32);
        g.dispose();

        Toolkit.getDefaultToolkit().sync();
        scene.getBufferStrategy().show();

    }

    @Override
    public void run() {
        this.initialize();
        this.setup();
        final int MAX_FRAMES_PER_SECOND = 144; // FPS
        final int MAX_UPDATES_SECOND = 75; // UPS

        final double uOPTIMAL_TIME = 1000.0 / MAX_UPDATES_SECOND;
        final double fOPTIMAL_TIME = 1000.0 / MAX_FRAMES_PER_SECOND;

        double uDeltaTime = 0.0;
        double fDeltaTime = 0.0;

        int frames = 0;
        int updates = 0;

        long startTime = System.currentTimeMillis();
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            this.processInput();

            if (uDeltaTime >= uOPTIMAL_TIME) {
                this.update(uDeltaTime / uOPTIMAL_TIME);// Create the deltaTime like Unity
                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }

            if (fDeltaTime >= fOPTIMAL_TIME) {
                this.render();
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

    @Override
    public void notifyEvent(EventType eventType, Event<?> event) {
        LogService.getInstance().warning("OnWindowClosingEvent received.");
        this.stop();
    }

    public void start() {
        Thread thread = new Thread(this, "Engine2DJ");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public void stop() {
        isRunning = false;
        System.exit(0);
    }
}