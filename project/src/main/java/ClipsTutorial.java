import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ClipsTutorial {

    public static void main(String[] args) {

        ClipsTutorial tutorial = new ClipsTutorial();
        tutorial.run();

    }

    private void run() {

        //Create File object with sound location
        File testSound = new File("Ring01.wav");

        //Create Audio Input stream with the File specified earlier
        try (AudioInputStream stream = AudioSystem.getAudioInputStream(testSound)) {
            //Get a clip from the audioSystem -- uses default audio mixer here since none was specified
            Clip clip = AudioSystem.getClip();
            Clip clip2 = AudioSystem.getClip();

            //Open clip with the audio stream we just made
            clip.open(stream);
            clip2.open(AudioSystem.getAudioInputStream(new File("Ring02.wav")));

            clip.start();
            clip2.start();

            clip2.loop(Clip.LOOP_CONTINUOUSLY);

            //Do while loop to continue "main" running while the clip is still playing to prevent the program from ending early
            do {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (clip.isActive());

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        //comments below were from a tutorial for an older version that no longer works. Keeping this for my own reference.

//        //Get info on all mixers currently available to the system
//        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
//        for (Mixer.Info info : mixerInfos) {
//            System.out.println(info);
//        }
//
//        //Get the first mixer in the above array, which should be the default system output
//        mixer = AudioSystem.getMixer(mixerInfos[0]);
//
//        //Constructs the DataLine's info object for the Clip format.
//        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
//
//        try {
//            //Have to cast this to a Clip -- it returns a clip but Java doesn't know that
//            clip = AudioSystem.getClip(dataInfo);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            //Tutorial had me use these -- did not explain what they are
//            URL soundURL = Main.class.getResource("Ring001.wav");
//            //Takes URL to point to the audio file
//            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL);
//
//            //Opens clip with audio -- Clips load the specified file before playing. Knows all clip data before loading
//            clip.open(audio);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Starts the clip playback -- opens a new thread to do this. Keep in mind if Main finishes running, all threads
//        //are closed automatically
//        clip.start();
//
//        //Do while loop to continue "main" running while the clip is still playing to prevent the program from ending early
//        do {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } while (clip.isActive());


    }
}
