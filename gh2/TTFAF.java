package gh2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class TTFAF {
    public static void main(String[] args) {

        //insert path to MIDI file
        GuitarPlayer player = new GuitarPlayer(new java.io.File("insert path to file here"));
        player.play();
    }

}
