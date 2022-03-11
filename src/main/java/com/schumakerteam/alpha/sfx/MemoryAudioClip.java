package com.schumakerteam.alpha.sfx;

import com.schumakerteam.alpha.io.GeAudioLoader;
import com.schumakerteam.alpha.log.LogService;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * @author Hudson Schumaker
 */
public final class MemoryAudioClip extends GeAudioClip implements Runnable, LineListener {

    private final AudioInputStream audioStream;
    private Clip clip;
    private boolean playCompleted;
    private long duration;

    public MemoryAudioClip(String fileName, boolean isLoop) {
        super(isLoop);
        this.audioStream = new GeAudioLoader().loadAudioFromDisk(fileName);
        this.setUp();
    }

    private void setUp() {
        try {
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);

            duration = clip.getMicrosecondLength() / 1000;
        } catch (LineUnavailableException ignore) {
            LogService.getInstance().error("Could not set up audio");
        }
    }

    @Override
    public void stop() {
        playCompleted = true;
        clip.stop();
        clip.close();
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            if (!isLoop()) {
                playCompleted = true;
            }
        }
    }

    @Override
    public void run() {
        try {
            clip.open(audioStream);
        } catch (IOException | LineUnavailableException ignore) {
            LogService.getInstance().error("Could not open audio");
        }

        if (isLoop()) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        clip.start();
        while (!playCompleted) {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException ignore) {
            }
        }
        this.stop();
    }
}
