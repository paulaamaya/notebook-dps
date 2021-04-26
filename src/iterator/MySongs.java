package iterator;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A class for a song list implemented using a HashMap.
 */
public class MySongs implements Iterable<Song> {

    HashMap<Integer, Song> mySongs;

    public MySongs() {
        mySongs = new HashMap<Integer, Song>();

        mySongs.put(0, new Song("Green Day", "American Idiot"));
        mySongs.put(1, new Song("AC/DC", "Highway to Hell"));
        mySongs.put(2, new Song("Bon Jovi", "Livin' On a Prayer"));
    }


    @Override
    public Iterator<Song> iterator() {
        return new MySongsIterator(mySongs);
    }
}
