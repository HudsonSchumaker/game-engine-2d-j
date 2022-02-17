package com.schumakerteam.alpha.sfx;

import com.schumakerteam.alpha.io.GeAudioLoader;
import com.schumakerteam.alpha.log.LogService;

import javax.sound.sampled.*;
import java.io.IOException;

public class StreamAudioClip extends GeAudioClip implements Runnable {

    private static final int BUFFER_SIZE = 4096;
    private boolean playCompleted;
    private final String fileName;

    public StreamAudioClip(String fileName, boolean isLoop) {
        super(isLoop);
        this.fileName = fileName;
    }

    @Override
    public void stop() {
        this.playCompleted = true;
    }

    @Override
    public void run() {
        var audioLoader = new GeAudioLoader();
        try {
            while (!playCompleted) {
                var audioStream =  audioLoader.loadAudioFromDisk(fileName);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
                audioLine.open(format);
                audioLine.start();

                byte[] bytesBuffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                    audioLine.write(bytesBuffer, 0, bytesRead);
                }

                audioLine.drain();
                audioLine.close();
                audioStream.close();

                if (!isLoop()) {
                    playCompleted = true;
                }
            }
        } catch (LineUnavailableException | IOException ignore) {
            LogService.getInstance().error("Could not stream audio");
        }
    }
}
