package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.common.ComponentIdMap;
import com.schumakerteam.alpha.ecs.impl.Component;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.sfx.GeAudioClip;
import com.schumakerteam.alpha.sfx.MemoryAudioClip;
import com.schumakerteam.alpha.sfx.PlayType;
import com.schumakerteam.alpha.sfx.StreamAudioClip;

public class AudioComponent extends Component {
    public static final int COMPONENT_TYPE_ID = ComponentIdMap.getTypeId(AudioComponent.class.getName());
    private final int id;

    private boolean playOnAwake;
    private final boolean loop;
    private final PlayType playType;
    private final String fileName;
    private GeAudioClip audioClip;

    public AudioComponent(boolean playOnAwake, boolean loop, PlayType playType, String fileName) {
        this.id = Registry.getInstance().getComponentId();
        this.playOnAwake = playOnAwake;
        this.loop = loop;
        this.playType = playType;
        this.fileName = fileName;

        if (playOnAwake) {
            play();
        }
    }

    public void play() {
        if (playType.name().equals(PlayType.STREAM.name())) {
            audioClip = new StreamAudioClip(fileName, loop);
            streamPlay();
        } else {
            audioClip = new MemoryAudioClip(fileName, loop);
            memoryPlay();
        }
    }

    public void stop() {
        audioClip.stop();
    }

    private void memoryPlay() {
        var stream = (MemoryAudioClip) audioClip;
        Thread thread = new Thread(stream, "MemoryAudio" + getId());
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    private void streamPlay() {
        var stream = (StreamAudioClip) audioClip;
        Thread thread = new Thread(stream, "AudioStream" + getId());
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }
}
