package mine.media;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.media.MediaBrowserCompat.MediaItem.FLAG_PLAYABLE;

/**
 * Created by Administrator on 2020/9/7.
 */
public class MediaPlaybackForegroundService extends MediaBrowserServiceCompat {

    private MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private MediaPlayer mediaPlayer;


    //版本一：官方文档版
//    private MediaSessionCompat.Callback sessionCallback = new MediaSessionCompat.Callback() {
//        @Override
//        public void onPlay() {
//            System.out.println("*********  " + getClass().getSimpleName() + ".onPlay  *********");
//
//            startService(new Intent(MediaPlaybackService.this, MediaBrowserService.class));
//
//            mMediaSession.setActive(true);
//
//
////            AudioManager am = getSystemService(AudioManager.class);
//////            AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
////
////            // Request audio focus for playback, this registers the afChangeListener
////            AudioAttributes attrs = new AudioAttributes.Builder()
////                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
////                    .build();
////
////            AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener(){
////                @Override
////                public void onAudioFocusChange(int focusChange) {
////                    System.out.println("*********  " + getClass().getSimpleName() + ".focusChange  *********");
////                }
////            };
////
////            AudioFocusRequest audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
////                    .setOnAudioFocusChangeListener(afChangeListener)
////                    .setAudioAttributes(attrs)
////                    .build();
////            int result = am.requestAudioFocus(audioFocusRequest);
////
////            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
////                // Start the service
////                startService(new Intent(getCon, MediaBrowserService.class));
////                // Set the session active  (and update metadata and state)
////                mediaSession.setActive(true);
////                // start the player (custom call)
////                player.start();
////                // Register BECOME_NOISY BroadcastReceiver
////                registerReceiver(myNoisyAudioStreamReceiver, intentFilter);
////                // Put the service in the foreground, post notification
////                service.startForeground(id, myPlayerNotification);
////            }
//
////            switch (am.requestAudioFocus(audioFocusRequest)){
////                case AudioManager.AUDIOFOCUS_GAIN:
////                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN");
////                    break;
////                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
////                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN_TRANSIENT");
////                    break;
////                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE:
////                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE");
////                    break;
////                case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
////                    System.out.println("AudioFocus is AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
////                    break;
////                case AudioManager.AUDIOFOCUS_LOSS:
////                    System.out.println("AudioFocus is AUDIOFOCUS_LOSS");
////                    break;
////                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
////                    System.out.println("AudioFocus is AUDIOFOCUS_LOSS_TRANSIENT");
////                    break;
////                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
////                    System.out.println("AudioFocus is AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
////                    break;
////                case AudioManager.AUDIOFOCUS_NONE:
////                    System.out.println("AudioFocus is AUDIOFOCUS_NONE");
////                    break;
////                case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
////                    System.out.println("AudioFocus is AUDIOFOCUS_REQUEST_DELAYED");
////                    break;
////                case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
////                    System.out.println("AudioFocus is AUDIOFOCUS_REQUEST_FAILED");
////                    break;
////                case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
////                    System.out.println("AudioFocus is AUDIOFOCUS_REQUEST_GRANTED");
////                    break;
////            }
//
//        }

//        @Override
//        public void onStop() {
////            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
////            // Abandon audio focus
////            am.abandonAudioFocusRequest(audioFocusRequest);
////            unregisterReceiver(myNoisyAudioStreamReceiver);
////            // Stop the service
////            service.stopSelf();
////            // Set the session inactive  (and update metadata and state)
////            mediaSession.setActive(false);
////            // stop the player (custom call)
////            player.stop();
////            // Take the service out of the foreground
////            service.stopForeground(false);
//        }
//
//        @Override
//        public void onPause() {
////            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
////            // Update metadata and state
////            // pause the player (custom call)
////            player.pause();
////            // unregister BECOME_NOISY BroadcastReceiver
////            unregisterReceiver(myNoisyAudioStreamReceiver);
////            // Take the service out of the foreground, retain the notification
////            service.stopForeground(false);
//        }
//
//    };

    //版本二：自实现版
    private MediaSessionCompat.Callback sessionCallback = new MediaSessionCompat.Callback() {
        @Override
        public void onCommand(String command, Bundle extras, ResultReceiver cb) {
            super.onCommand(command, extras, cb);
            System.out.println("*********  " + getClass().getSimpleName() + ".onCommand  *********");
            switch (command) {
                case "resetPosition":
                    mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, mediaPlayer.getCurrentPosition(), 1);
                    mMediaSession.setPlaybackState(mStateBuilder.build());
                    break;
            }

        }

        @Override
        public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
            System.out.println("*********  " + getClass().getSimpleName() + ".onMediaButtonEvent  *********");
            return super.onMediaButtonEvent(mediaButtonEvent);

        }

        @Override
        public void onPrepare() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepare  *********");

            PlaybackStateCompat mPlaybackState = mMediaSession.getController().getPlaybackState();
            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_CONNECTING) {
                System.out.println(mMediaSession.getController());
                System.out.println(mMediaSession.getController().getPlaybackState());
                Uri mp3 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ltg);
                try {
                    mediaPlayer.setDataSource(getBaseContext(), mp3);
                    mediaPlayer.prepare();
                    mStateBuilder.setState(PlaybackStateCompat.STATE_BUFFERING, 0, 0);
                    mMediaSession.setPlaybackState(mStateBuilder.build());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onPrepareFromMediaId(String mediaId, Bundle extras) {
            super.onPrepareFromMediaId(mediaId, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareFromMediaId  *********");
        }

        @Override
        public void onPrepareFromSearch(String query, Bundle extras) {
            super.onPrepareFromSearch(query, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareFromSearch  *********");
        }

        @Override
        public void onPrepareFromUri(Uri uri, Bundle extras) {
            super.onPrepareFromUri(uri, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onPrepareFromUri  *********");
        }

        @Override
        public void onPlay() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onPlay  *********");

            PlaybackStateCompat mPlaybackState = mMediaSession.getController().getPlaybackState();


            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_CONNECTING) {
                mMediaSession.getController().getTransportControls().prepare();
            }

            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_STOPPED) {

                try {
                    mediaPlayer.prepare();
                    mStateBuilder.setState(PlaybackStateCompat.STATE_BUFFERING, 0, 0);
                    mMediaSession.setPlaybackState(mStateBuilder.build());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_PAUSED) {

                mPlaybackState = mStateBuilder
                        .setState(PlaybackStateCompat.STATE_PLAYING, mediaPlayer.getCurrentPosition(), 1)
                        .build();
                mMediaSession.setPlaybackState(mPlaybackState);
                mediaPlayer.start();
            }

        }

        @Override
        public void onPlayFromMediaId(String mediaId, Bundle extras) {
            super.onPlayFromMediaId(mediaId, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onPlayFromMediaId  *********");
            System.out.println("mediaId is " + mediaId);

        }

        @Override
        public void onPlayFromSearch(String query, Bundle extras) {
            super.onPlayFromSearch(query, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onPlayFromSearch  *********");
        }

        @Override
        public void onPlayFromUri(Uri uri, Bundle extras) {
            super.onPlayFromUri(uri, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onPlayFromUri  *********");
        }

        @Override
        public void onSkipToQueueItem(long id) {
            super.onSkipToQueueItem(id);
            System.out.println("*********  " + getClass().getSimpleName() + ".onSkipToQueueItem  *********");
        }

        @Override
        public void onPause() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

            PlaybackStateCompat mPlaybackState = mMediaSession.getController().getPlaybackState();

            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING) {
                mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, mediaPlayer.getCurrentPosition(), 0);
                mMediaSession.setPlaybackState(mStateBuilder.build());
                mediaPlayer.pause();
            }
        }

        @Override
        public void onSeekTo(long pos) {
            System.out.println("*********  " + getClass().getSimpleName() + ".onSeekTo  *********");
            PlaybackStateCompat mPlaybackState = mMediaSession.getController().getPlaybackState();

            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING ||
                    mPlaybackState.getState() == PlaybackStateCompat.STATE_PAUSED ||
                    mPlaybackState.getState() == PlaybackStateCompat.STATE_STOPPED) {

                mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, pos, 1);
                mMediaSession.setPlaybackState(mStateBuilder.build());
                mediaPlayer.seekTo((int) pos);
            }
        }

        @Override
        public void onSkipToNext() {
            super.onSkipToNext();
            System.out.println("*********  " + getClass().getSimpleName() + ".onSkipToNext  *********");
        }

        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
            System.out.println("*********  " + getClass().getSimpleName() + ".onSkipToPrevious  *********");
        }

        @Override
        public void onFastForward() {
            super.onFastForward();
            System.out.println("*********  " + getClass().getSimpleName() + ".onFastForward  *********");
        }

        @Override
        public void onRewind() {
            super.onRewind();
            System.out.println("*********  " + getClass().getSimpleName() + ".onRewind  *********");
        }

        @Override
        public void onStop() {
            System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

            PlaybackStateCompat mPlaybackState = mMediaSession.getController().getPlaybackState();

            if (mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING ||
                    mPlaybackState.getState() == PlaybackStateCompat.STATE_PAUSED) {
                mPlaybackState = mStateBuilder
                        .setState(PlaybackStateCompat.STATE_STOPPED, 0, 0)
                        .build();
                mMediaSession.setPlaybackState(mPlaybackState);
                mediaPlayer.stop();
            }

        }


        @Override
        public void onSetRating(RatingCompat rating) {
            super.onSetRating(rating);
            System.out.println("*********  " + getClass().getSimpleName() + ".onSetRating  *********");
        }

        @Override
        public void onSetRating(RatingCompat rating, Bundle extras) {
            super.onSetRating(rating, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onSetRating  *********");
        }

        @Override
        public void onSetCaptioningEnabled(boolean enabled) {
            super.onSetCaptioningEnabled(enabled);
            System.out.println("*********  " + getClass().getSimpleName() + ".onSetCaptioningEnabled  *********");
        }

        @Override
        public void onSetRepeatMode(int repeatMode) {
            super.onSetRepeatMode(repeatMode);
            System.out.println("*********  " + getClass().getSimpleName() + ".onSetRepeatMode  *********");
        }

        @Override
        public void onSetShuffleMode(int shuffleMode) {
            super.onSetShuffleMode(shuffleMode);
            System.out.println("*********  " + getClass().getSimpleName() + ".onSetShuffleMode  *********");
        }

        @Override
        public void onCustomAction(String action, Bundle extras) {
            super.onCustomAction(action, extras);
            System.out.println("*********  " + getClass().getSimpleName() + ".onCustomAction  *********");
        }

        @Override
        public void onAddQueueItem(MediaDescriptionCompat description) {
            super.onAddQueueItem(description);
            System.out.println("*********  " + getClass().getSimpleName() + ".onAddQueueItem  *********");
        }

        @Override
        public void onAddQueueItem(MediaDescriptionCompat description, int index) {
            super.onAddQueueItem(description, index);
            System.out.println("*********  " + getClass().getSimpleName() + ".onAddQueueItem  *********");
        }

        @Override
        public void onRemoveQueueItem(MediaDescriptionCompat description) {
            super.onRemoveQueueItem(description);
            System.out.println("*********  " + getClass().getSimpleName() + ".onRemoveQueueItem  *********");
        }
    };


    public MediaPlaybackForegroundService() {
        System.out.println("++" + getClass().getSimpleName() + ".Constructor++");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        mMediaSession = new MediaSessionCompat(this, "ssss");
        setSessionToken(mMediaSession.getSessionToken());
        System.out.println("getSessionToken is " + getSessionToken());


        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PLAY_PAUSE)//设置启用的功能
                .setState(PlaybackStateCompat.STATE_CONNECTING, PlaybackStateCompat.PLAYBACK_POSITION_UNKNOWN, 0);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        mMediaSession.setCallback(sessionCallback); //增加会话监听器
        mMediaSession.setActive(true);//会话激活


        mediaPlayer = new MediaPlayer();

        //设置重播模式
        mediaPlayer.setLooping(true);
        mMediaSession.setRepeatMode(PlaybackStateCompat.REPEAT_MODE_ONE);


        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("##Media.onPrepared##");

                MediaMetadataCompat.Builder metadataBuilder = new MediaMetadataCompat.Builder();
                metadataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, mp.getDuration());
                mMediaSession.setMetadata(metadataBuilder.build());

                mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, 0, 1);
                mMediaSession.setPlaybackState(mStateBuilder.build());

                mp.start();

            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                System.out.println("##Media.onBufferingUpdate##");

                mStateBuilder.setBufferedPosition(percent);
                mMediaSession.setPlaybackState(mStateBuilder.build());
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("##Media.setOnCompletionListener##");

            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStartCommand  *********");



        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        String channelId = "c1";
        CharSequence channelName = "cf";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationManager.createNotificationChannel(notificationChannel);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);

        builder
                // Add the metadata for the currently playing track
                .setContentTitle("gogo")
                .setContentText("ssssssssss")
                .setSubText("tttt")

                // Enable launching the player by clicking the notification
                .setContentIntent(mMediaSession.getController().getSessionActivity())

                // Stop the service when the notification is swiped away
                .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(this,
                        PlaybackStateCompat.ACTION_STOP))

                // Make the transport controls visible on the lockscreen
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                // Add an app icon and set its accent color
                // Be careful about the color
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setColor(getResources().getColor(R.color.CadetBlue, null))

                // Add a pause button
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_launcher_foreground, "pause",
                        MediaButtonReceiver.buildMediaButtonPendingIntent(this,
                                PlaybackStateCompat.ACTION_PLAY_PAUSE)))

                // Take advantage of MediaStyle features
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mMediaSession.getSessionToken())
                        .setShowActionsInCompactView(0)

                        // Add a cancel button
                        .setShowCancelButton(true)
                        .setCancelButtonIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(this,
                                PlaybackStateCompat.ACTION_STOP)));


        startForeground(1, builder.build());





        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onLoadChildren(@NonNull String s, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onLoadChildren  *********");

        System.out.println("s is " + s);
        System.out.println("result is " + result);
        System.out.println("getBrowserRootHints is " + getBrowserRootHints());
        System.out.println("getCurrentBrowserInfo is " + getCurrentBrowserInfo());

        List<MediaBrowserCompat.MediaItem> list = new ArrayList<>();

        MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat.Builder()//创建构建器
                .setMediaId("LIG")//设置媒体文件ID
                .setTitle("XXX")//设置标题
                .setSubtitle("oooooooo")//设置副标
                .setMediaUri(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ltg))//设置媒体资源URI
                .build();

        list.add(new MediaBrowserCompat.MediaItem(mediaDescriptionCompat, FLAG_PLAYABLE));//加入容器
        result.sendResult(list);//发送给客户端

//        result.sendResult(null);//如果没有查询到对应的媒体文件，则返回Null，那么客户端将触发on

    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBind  *********");

        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onUnbind  *********");
        return super.onUnbind(intent);
    }

    @Override
    public void onSearch(@NonNull String query, Bundle extras, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSearch  *********");

        System.out.println("query is " + query);
        System.out.println("result is " + result);


        if (true) {
            List<MediaBrowserCompat.MediaItem> list = new ArrayList<>();

            MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat.Builder()//创建构建器
                    .setMediaId("LIG")//设置媒体文件ID
                    .setTitle("XXX")//设置标题
                    .setSubtitle("oooooooo")//设置副标
                    .setMediaUri(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ltg))//设置媒体资源URI
                    .build();

            list.add(new MediaBrowserCompat.MediaItem(mediaDescriptionCompat, FLAG_PLAYABLE));//加入容器
            result.sendResult(list);//发送给客户端
        } else {
            super.onSearch(query, extras, result);//如果查询失败则调用父类方法，发送result.sendResult(null);
        }

    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName,
                                 int clientUid,
                                 @Nullable Bundle rootHints) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onGetRoot  *********");
        System.out.println("getBrowserRootHints is " + getBrowserRootHints());
        System.out.println("getCurrentBrowserInfo is " + getCurrentBrowserInfo());
        System.out.println("-----");
        System.out.println("clientPackageName is " + clientPackageName);
        System.out.println("clientUid is " + clientUid);
        System.out.println("rootHints is " + rootHints);


        Bundle bundle = new Bundle();
        bundle.putBoolean(BrowserRoot.EXTRA_RECENT, true);//根列表是否为最近播放列表
        bundle.putBoolean(BrowserRoot.EXTRA_OFFLINE, true);//根列表是否为离线播放列表（本地列表，不需要访问网络）
        bundle.putBoolean(BrowserRoot.EXTRA_SUGGESTED, false);//根列表是否为建议播放列表（被标记为建议的文件排在顶部）
        return new BrowserRoot("XXXXXXXXXXX", bundle);
    }

    @Override
    public void onLoadItem(String itemId, @NonNull Result<MediaBrowserCompat.MediaItem> result) {
        super.onLoadItem(itemId, result);
        System.out.println("*********  " + getClass().getSimpleName() + ".onLoadItem  *********");
        System.out.println("itemId is " + itemId);
        System.out.println("result is " + result);


        result.sendResult(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        mMediaSession.release();
        mMediaSession = null;
        mediaPlayer.release();
    }
}
