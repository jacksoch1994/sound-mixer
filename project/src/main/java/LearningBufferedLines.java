import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class LearningBufferedLines {

    public static void main(String[] args) {

        LearningBufferedLines myLines = new LearningBufferedLines();
        myLines.run();

    }

    private void run() {

        //Create File object with sound location
        File testSound = new File("Ring01.wav");

        //Create Audio Input stream with the File specified earlier
        try (AudioInputStream stream = AudioSystem.getAudioInputStream(testSound)) {
            //Get a clip from the audioSystem -- uses default audio mixer here since none was specified
            Clip clip = AudioSystem.getClip();
            AudioFormat audioFormat = new AudioFormat(8000f,
                    16,    // sample size in bits
                    1,     // mono
                    true,  // signed
                    false); // little endian;
            SourceDataLine dataLine = AudioSystem.getSourceDataLine(audioFormat);

            dataLine.open();

            //Open clip with the audio stream we just made
            clip.open(stream);

            //clip.start();

            //Do while loop to continue "main" running while the clip is still playing to prevent the program from ending early
            do {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (clip.isActive() || dataLine.isActive());

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
