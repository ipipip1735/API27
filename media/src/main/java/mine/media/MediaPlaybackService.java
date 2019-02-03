package mine.media;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.service.media.MediaBrowserService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import java.util.List;

/**
 * Created by Administrator on 2019/1/31.
 */
public class MediaPlaybackService extends MediaBrowserServiceCompat {

    private static final String MY_MEDIA_ROOT_ID = "media_root_id";
    private static final String MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id";

    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaButtonReceiver.handleIntent(mMediaSession, intent);

        return super.onStartCommand(intent, flags, startId);
    }

    private MediaSessionCompat.Callback sessionCallback = new MediaSessionCompat.Callback() {
        @Override
        public void onPlay() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onPlay  *********");

            startService(new Intent(MediaPlaybackService.this, MediaBrowserService.class));

            mMediaSession.setActive(true);


//            AudioManager am = getSystemService(AudioManager.class);
////            AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
//
//            // Request audio focus for playback, this registers the afChangeListener
//            AudioAttributes attrs = new AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .build();
//
//            AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener(){
//                @Override
//                public void onAudioFocusChange(int focusChange) {
//                    System.out.println("*********  " + getClass().getSimpleName() + ".focusChange  *********");
//                }
//            };
//
//            AudioFocusRequest audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
//                    .setOnAudioFocusChangeListener(afChangeListener)
//                    .setAudioAttributes(attrs)
//                    .build();
//            int result = am.requestAudioFocus(audioFocusRequest);
//
//            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                // Start the service
//                startService(new Intent(getCon, MediaBrowserService.class));
//                // Set the session active  (and update metadata and state)
//                mediaSession.setActive(true);
//                // start the player (custom call)
//                player.start();
//                // Register BECOME_NOISY BroadcastReceiver
//                registerReceiver(myNoisyAudioStreamReceiver, intentFilter);
//                // Put the service in the foreground, post notification
//                service.startForeground(id, myPlayerNotification);
//            }

//            switch (am.requestAudioFocus(audioFocusRequest)){
//                case AudioManager.AUDIOFOCUS_GAIN:
//                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN");
//                    break;
//                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
//                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN_TRANSIENT");
//                    break;
//                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE:
//                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE");
//                    break;
//                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
//                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS:
//                    System.out.println("AudioFocus is AUDIOFOCUS_LOSS");
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
//                    System.out.println("AudioFocus is AUDIOFOCUS_LOSS_TRANSIENT");
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
//                    System.out.println("AudioFocus is AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
//                    break;
//                case AudioManager.AUDIOFOCUS_NONE:
//                    System.out.println("AudioFocus is AUDIOFOCUS_NONE");
//                    break;
//                case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
//                    System.out.println("AudioFocus is AUDIOFOCUS_REQUEST_DELAYED");
//                    break;
//                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
//                    System.out.println("AudioFocus is AUDIOFOCUS_REQUEST_FAILED");
//                    break;
//                case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
//                    System.out.println("AudioFocus is AUDIOFOCUS_REQUEST_GRANTED");
//                    break;
//            }

        }

        @Override
        public void onStop() {
//            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
//            // Abandon audio focus
//            am.abandonAudioFocusRequest(audioFocusRequest);
//            unregisterReceiver(myNoisyAudioStreamReceiver);
//            // Stop the service
//            service.stopSelf();
//            // Set the session inactive  (and update metadata and state)
//            mediaSession.setActive(false);
//            // stop the player (custom call)
//            player.stop();
//            // Take the service out of the foreground
//            service.stopForeground(false);
        }

        @Override
        public void onPause() {
//            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
//            // Update metadata and state
//            // pause the player (custom call)
//            player.pause();
//            // unregister BECOME_NOISY BroadcastReceiver
//            unregisterReceiver(myNoisyAudioStreamReceiver);
//            // Take the service out of the foreground, retain the notification
//            service.stopForeground(false);
        }

    };

//    private MediaSessionCompat.Callback sessionCallback = new MediaSessionCompat.Callback() {
//        @Override
//        public void onCommand(String command, Bundle extras, ResultReceiver cb) {
//            super.onCommand(command, extras, cb);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onCommand  *********");
//        }
//
//        @Override
//        public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
//            System.out.println("*********  " + getClass().getSimpleName() + ".onMediaButtonEvent  *********");
//            return super.onMediaButtonEvent(mediaButtonEvent);
//
//        }
//
//        @Override
//        public void onPrepare() {
//            super.onPrepare();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepare  *********");
//        }
//
//        @Override
//        public void onPrepareFromMediaId(String mediaId, Bundle extras) {
//            super.onPrepareFromMediaId(mediaId, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareFromMediaId  *********");
//        }
//
//        @Override
//        public void onPrepareFromSearch(String query, Bundle extras) {
//            super.onPrepareFromSearch(query, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareFromSearch  *********");
//        }
//
//        @Override
//        public void onPrepareFromUri(Uri uri, Bundle extras) {
//            super.onPrepareFromUri(uri, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareFromUri  *********");
//        }
//
//        @Override
//        public void onPlay() {
//            super.onPlay();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPlay  *********");
//        }
//
//        @Override
//        public void onPlayFromMediaId(String mediaId, Bundle extras) {
//            super.onPlayFromMediaId(mediaId, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPlayFromMediaId  *********");
//        }
//
//        @Override
//        public void onPlayFromSearch(String query, Bundle extras) {
//            super.onPlayFromSearch(query, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPlayFromSearch  *********");
//        }
//
//        @Override
//        public void onPlayFromUri(Uri uri, Bundle extras) {
//            super.onPlayFromUri(uri, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPlayFromUri  *********");
//        }
//
//        @Override
//        public void onSkipToQueueItem(long id) {
//            super.onSkipToQueueItem(id);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSkipToQueueItem  *********");
//        }
//
//        @Override
//        public void onPause() {
//            super.onPause();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
//        }
//
//        @Override
//        public void onSkipToNext() {
//            super.onSkipToNext();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSkipToNext  *********");
//        }
//
//        @Override
//        public void onSkipToPrevious() {
//            super.onSkipToPrevious();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSkipToPrevious  *********");
//        }
//
//        @Override
//        public void onFastForward() {
//            super.onFastForward();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onFastForward  *********");
//        }
//
//        @Override
//        public void onRewind() {
//            super.onRewind();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onRewind  *********");
//        }
//
//        @Override
//        public void onStop() {
//            super.onStop();
//            System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
//        }
//
//        @Override
//        public void onSeekTo(long pos) {
//            super.onSeekTo(pos);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSeekTo  *********");
//        }
//
//        @Override
//        public void onSetRating(RatingCompat rating) {
//            super.onSetRating(rating);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSetRating  *********");
//        }
//
//        @Override
//        public void onSetRating(RatingCompat rating, Bundle extras) {
//            super.onSetRating(rating, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSetRating  *********");
//        }
//
//        @Override
//        public void onSetCaptioningEnabled(boolean enabled) {
//            super.onSetCaptioningEnabled(enabled);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSetCaptioningEnabled  *********");
//        }
//
//        @Override
//        public void onSetRepeatMode(int repeatMode) {
//            super.onSetRepeatMode(repeatMode);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSetRepeatMode  *********");
//        }
//
//        @Override
//        public void onSetShuffleMode(int shuffleMode) {
//            super.onSetShuffleMode(shuffleMode);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onSetShuffleMode  *********");
//        }
//
//        @Override
//        public void onCustomAction(String action, Bundle extras) {
//            super.onCustomAction(action, extras);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onCustomAction  *********");
//        }
//
//        @Override
//        public void onAddQueueItem(MediaDescriptionCompat description) {
//            super.onAddQueueItem(description);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onAddQueueItem  *********");
//        }
//
//        @Override
//        public void onAddQueueItem(MediaDescriptionCompat description, int index) {
//            super.onAddQueueItem(description, index);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onAddQueueItem  *********");
//        }
//
//        @Override
//        public void onRemoveQueueItem(MediaDescriptionCompat description) {
//            super.onRemoveQueueItem(description);
//            System.out.println("*********  " + getClass().getSimpleName() + ".onRemoveQueueItem  *********");
//        }
//    };


    public MediaPlaybackService() {
        System.out.println("++" + getClass().getSimpleName() + ".Constructor++");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        mMediaSession = new MediaSessionCompat(this, "ssss");



        System.out.println("getSessionToken is " + getSessionToken());


        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mMediaSession.setPlaybackState(mStateBuilder.build());


        mMediaSession.setCallback(sessionCallback);


        setSessionToken(mMediaSession.getSessionToken());


    }

    @Override
    public void onLoadChildren(@NonNull String s, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onLoadChildren  *********");

        System.out.println("s is " + s);
        System.out.println("result is " + result);
        System.out.println("getBrowserRootHints is " + getBrowserRootHints());
        System.out.println("getCurrentBrowserInfo is " + getCurrentBrowserInfo());


    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onGetRoot  *********");
        System.out.println("getBrowserRootHints is " + getBrowserRootHints());
        System.out.println("getCurrentBrowserInfo is " + getCurrentBrowserInfo());
        System.out.println("-----");
        System.out.println("clientPackageName is " + clientPackageName);
        System.out.println("clientUid is " + clientUid);
        System.out.println("rootHints is " + rootHints);


        return new BrowserRoot("skxi", null);
    }

    @Override
    public void onLoadItem(String itemId, @NonNull Result<MediaBrowserCompat.MediaItem> result) {
        super.onLoadItem(itemId, result);
        System.out.println("*********  " + getClass().getSimpleName() + ".onLoadItem  *********");
        System.out.println("itemId is " + itemId);
        System.out.println("result is " + result);
//        result.sendResult(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

    }
}
