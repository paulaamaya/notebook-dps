package iterator;

import java.util.Iterator;

/**
 * A class for a list of songs implemented using an Array.
 */
public class YourSongs {

    Song[] songs;

    public YourSongs() {
        songs = new Song[3];

        songs[0] = new Song("Britney Spears", "Hit Me Baby One More Time");
        songs[1] = new Song("Aqua", "Barbie Girl");
        songs[2] = new Song("Spice Girls", "Wannabe");

    }

    public Iterator<Song> iterator(){
        return new YourSongsIterator(this.songs);
    }
}
