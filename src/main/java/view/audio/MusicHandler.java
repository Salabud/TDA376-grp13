package view.audio;

/**
 * Singleton class that manages background music for the game.
 */
public class MusicHandler {
    private final Sound mainMenuTrack;
    private Sound currentTrack;
    private static MusicHandler INSTANCE;

    private MusicHandler(){
        mainMenuTrack = new Sound("/sfx/Getting it Done.wav");
    }
    public void update(String gameState){
        if (currentTrack != null){
            currentTrack.stop();
        }
        if(gameState.equals("MAIN_MENU")){
            currentTrack = mainMenuTrack;
            currentTrack.play(-30);
        }
    }
    public static MusicHandler getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MusicHandler();
        }
        return INSTANCE;
    }
}
