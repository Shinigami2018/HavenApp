import vlc
import time
import sys

def play_stream(url):
    print("Starting Lo-Fi stream...")
    player = vlc.MediaPlayer(url)
    player.play()
    time.sleep(2)  # Allow VLC to start before checking status

    if player.is_playing():
        print("Stream is playing. Press Ctrl+C to stop.")
    else:
        print("Failed to start stream. Check the URL.")

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print("\nStopping stream...")
        player.stop()
        sys.exit()

if name == "main":
    stream_url = "https://your-lofi-stream-url.com/"  # Replace with actual stream URL
    play_stream(stream_url)