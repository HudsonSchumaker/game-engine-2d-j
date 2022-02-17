package com.schumakerteam.alpha.io;

import com.schumakerteam.alpha.log.LogService;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.util.Objects;

import static com.schumakerteam.alpha.common.Commons.AUDIO_PATH;

public final class GeAudioLoader {

    public GeAudioLoader() {}

    public AudioInputStream loadAudioFromDisk(String fileName) {
        try {
            return AudioSystem.getAudioInputStream(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(AUDIO_PATH + fileName)));
        } catch (IOException | UnsupportedAudioFileException ignore) {
            LogService.getInstance().error("Could not load audio: " + fileName);
            return null;
        }
    }
}
