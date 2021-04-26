package iterator;

import java.util.Iterator;

public class Client {

    public static void main(String[] args) {

        // The two playlists with different implementations
        MySongs myList =  new MySongs();
        YourSongs yourList = new YourSongs();

        // Get the two iterators for the lists
        Iterator<Song> i1 = myList.iterator();
        Iterator<Song> i2 = yourList.iterator();

        // Traverse the two lists at the same time using the same interface
        while(i1.hasNext() && i2.hasNext()){
            Song song1 = i1.next();
            Song song2 = i2.next();
            System.out.println("My next song is: " + song1.name + " by " + song1.artist);
            System.out.println("Your next song is: " + song2.name + " by " + song2.artist + "\n");
        }
    }
}
