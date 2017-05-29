package se.com.ui;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class VideoPlayer {

    private final AudioMediaPlayerComponent mediaPlayerComponent;

    public VideoPlayer() {
        mediaPlayerComponent = new AudioMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void stopped(MediaPlayer mediaPlayer) {
                exit(0);
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                exit(0);
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                exit(1);
            }
        });
    }

    public void start(String mrl) {
        mediaPlayerComponent.getMediaPlayer().playMedia(mrl);
    	//mediaPlayerComponent.getMediaPlayer().playMedia("https://iotap.docfactory.com/api/v1/cnt/iotap/name/topic:How_to_change_the_powder_screw_mp4_small?api-key=RUxorkQlkk2SIhrCZPk3Kw!_l3fccObOEOArjXxD8Xurg");
    }

    private void exit(int result) {
        mediaPlayerComponent.release();
        System.exit(result);
    }
}