package com.haven.app.haven;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import java.io.File;
import java.util.List;
import java.util.Arrays;

public class audio {
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private int currentIndex = 0;

    public final List<String> songs = Arrays.asList(
            "src/main/resources/com/haven/app/haven/surah.mp3",
            "src/main/resources/com/haven/app/haven/halal.mp3",
            "src/main/resources/com/haven/app/haven/fatiha.mp3"
    );

    public void playSong(int index) {
        if (index < 0 || index >= songs.size()) {
            System.out.println("Invalid song index.");
            return;
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media sound = new Media(new File(songs.get(index)).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        isPlaying = true;
        currentIndex = index;

        mediaPlayer.setOnEndOfMedia(() -> {
            isPlaying = false;
            currentIndex = (currentIndex + 1) % songs.size(); // Auto-next
        });

        System.out.println("Playing: " + songs.get(index));
    }

    public void audioplay() {
        System.out.println("Audio Play/Pause");

        if (mediaPlayer == null) {
            playSong(currentIndex);
        } else {
            if (mediaPlayer.getStatus() == Status.PLAYING) {
                mediaPlayer.pause();
                isPlaying = false;
            } else {
                mediaPlayer.play();
                isPlaying = true;
            }
        }
    }

    public void audioprev() {
        System.out.println("Audio Previous");

        int prevIndex = (currentIndex - 1 + songs.size()) % songs.size();
        playSong(prevIndex);
    }

    public void audionext() {
        System.out.println("Audio Next");

        int nextIndex = (currentIndex + 1) % songs.size();
        playSong(nextIndex);
    }
}
