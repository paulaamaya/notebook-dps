package iterator;

import java.util.Iterator;

/**
 * An Iterator for a list of songs implemented using an array.
 */
public class YourSongsIterator implements Iterator<Song> {

    private Song[] songs;
    private int indexKey;

    /**
     * Takes in an array of songs and creates an iterator object for it.
     *
     * @param s Array of songs to iterate upon.
     */
    public YourSongsIterator(Song[] s) {
        this.songs = s;
        this.indexKey = 0;
    }

    /**
     * Informs the user if the end of the songs list has been reached.
     *
     * @return True iff the iterator has not reached the end of the list.
     */
    @Override
    public boolean hasNext(){
        return this.indexKey < this.songs.length;
    }

    /**
     * Returns the next song in the list, if there is one.  If the end of the list has been reached, returns null.
     *
     * @return Next song on the list if not at the end.  Else returns null.
     */
    @Override
    public Song next(){
        if (this.hasNext()){
            return this.songs[this.indexKey++];
        }
        return null;
    }
}
