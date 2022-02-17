package com.schumakerteam.alpha.sfx;

import com.schumakerteam.alpha.log.LogService;

import javax.sound.sampled.*;
import java.io.IOException;

public final class AudioClip implements LineListener {

    private final AudioInputStream audioStream;
    private AudioFormat format;
    private Clip clip;
    private boolean isLoop;
    private boolean playCompleted;
    private long duration;

    public AudioClip(AudioInputStream audioStream, boolean isLoop) {
        this.audioStream = audioStream;
        this.isLoop = isLoop;
        this.setUp();
    }

    private void setUp() {
        try {
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            if (isLoop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            duration = clip.getMicrosecondLength() / 1000;
        } catch (Exception ignore) {
            LogService.getInstance().error("Could not set up audio");
        }
    }

    public void play() {
        try {
            clip.open(audioStream);
        } catch (IOException | LineUnavailableException ignore) {
            LogService.getInstance().error("Could not open audio");
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

    public void stop() {
        playCompleted = true;
        clip.stop();
        clip.close();
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
}
