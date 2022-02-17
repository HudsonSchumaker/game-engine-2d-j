package com.schumakerteam.alpha.sandbox;

import javax.sound.sampled.AudioSystem;

public class AudioSupport {

     public static void main(String ...args) {
         var audioFileTypes = AudioSystem.getAudioFileTypes();
         for (var t : audioFileTypes) {
             System.out.println(t.getExtension());
         }
     }
}
