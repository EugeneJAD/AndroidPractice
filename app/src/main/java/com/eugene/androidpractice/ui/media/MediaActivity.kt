package com.eugene.androidpractice.ui.media

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RawRes
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_media.*
import com.google.android.exoplayer2.source.MediaSource
import android.view.View
import com.eugene.androidpractice.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.upstream.*
import java.io.IOException

class MediaActivity : AppCompatActivity() {

    private lateinit var exoPlayer: SimpleExoPlayer
    private var playbackPosition = 0L
    private var currentWindow = 0
    private var playWhenReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)
        // recovering the instance state
        savedInstanceState?.let {
            playbackPosition = it.getLong("playback_position", 0L)
            currentWindow = it.getInt("current_window", 0)
            playWhenReady = it.getBoolean("play_when_ready", true)
        }
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this), DefaultTrackSelector(), DefaultLoadControl()
        )
        player_view.player = exoPlayer

//        val resVideoUri = Uri.parse("android.resource://${this.packageName}/${R.raw.test_video}")
        val assetsVideoUri = Uri.parse("file:///android_asset/test_video.mp4")
        val streamVideoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/lifesaver-18f28.appspot.com/o/flood.mp4?alt=media&token=179d7e4e-7171-4a87-b1f8-b1fc3d976c60")
        val videoSource = buildConcatenatingMediaSource(R.raw.test_video, streamVideoUri, assetsVideoUri)
        videoSource?.let {
            // Prepare the player with the source.
            exoPlayer.prepare(videoSource, false, false)
            exoPlayer.playWhenReady = playWhenReady
            exoPlayer.seekTo(currentWindow, playbackPosition)
        }
    }

    private fun buildConcatenatingMediaSource(rawId: Int,
                                              assetsVideoUri: Uri,
                                              streamVideoUri: Uri): MediaSource? =
            ConcatenatingMediaSource(
                    buildRawMediaSource(rawId),
                    buildStreamMediaSource(assetsVideoUri),
                    buildAssetsMediaSource(streamVideoUri)
            )

    private fun buildStreamMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
                DefaultHttpDataSourceFactory("androidPractice")).createMediaSource(uri)
    }

    private fun buildAssetsMediaSource(uri: Uri): MediaSource {
        //Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "androidPractice"))
        //Returns the MediaSource representing the media to be played.
        return ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    private fun buildRawMediaSource(@RawRes rawId: Int): MediaSource? {
        val rawDataSource = RawResourceDataSource(this)
        try {
            //Opens the raw source to read the specified data
            rawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(rawId)))
        } catch (e: IOException) {
            try {
                //If an IOException is thrown, callers must still call DataSource.close() to ensure
                //that any partial effects of the invocation are cleaned up.
                rawDataSource.close()
            } catch (e: IOException) {
            } //do nothing
            return null
        }
        //Returns the MediaSource representing the media to be played.
        return ExtractorMediaSource.Factory(DataSource.Factory { rawDataSource })
                .createMediaSource(rawDataSource.uri)
    }
    //Starting with API level 24 Android supports multiple windows. As our app can be visible
    // but not active in split window mode, we need to initialize the player in onStart.
    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23)
            initializePlayer()
    }
    // Before API level 24 we wait as long as possible until we grab resources,
    // so we wait until onResume before initializing the player.
    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || !::exoPlayer.isInitialized)
            initializePlayer()
    }

    public override fun onPause() {
        super.onPause()
        savePlayerState()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    private fun savePlayerState() {
        playbackPosition = exoPlayer.currentPosition
        currentWindow = exoPlayer.currentWindowIndex
        playWhenReady = exoPlayer.playWhenReady
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun hideSystemUi() {
        player_view.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun releasePlayer() {
        exoPlayer.release()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putLong("playback_position", playbackPosition)
            putInt("current_window", currentWindow)
            putBoolean("play_when_ready", playWhenReady)
        }
        super.onSaveInstanceState(outState)
    }
}
