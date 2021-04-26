package iterator;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A class for an Iterator for a list of songs implemented using a hashmap.
 */
public class MySongsIterator implements Iterator<Song> {

    private HashMap<Integer, Song> songs;
    private int indexKey;

    public MySongsIterator(HashMap<Integer, Song> s) {
        this.songs = s;
        indexKey = 0;
    }

    @Override
    public boolean hasNext() {
        return this.indexKey < this.songs.size();
    }

    @Override
    public Song next() {

        return this.songs.get(indexKey++);
    }

}
