package musicbox;

import java.io.File;
import java.util.Random;
import static musicbox.GetMetaData.getAllMetaData;
import static musicbox.GetMetaData.getTrackLenght;
import static musicbox.GetMetaData.location;
import javazoom.jlgui.basicplayer.BasicPlayer;

public class MusicBox {
    /**ATRIBUTOS DE LA CLASE MUSICBOX
     A continuación se muestran los atributos propios de la clase MusicBox*/
    public static String song;
    private static int actual;
    private static int counter;
    private static DoubleList list = new DoubleList();
    
    /**MÉTODO PARA SELECCIONAR LA CANCIÓN EN EL REPRODUCTOR
     El método recibe un string con el nombre de la canción a reproducir*/
    public void setSong(String songName) {
        this.song = songName;
    }
    public BasicPlayer player;
    
    //CONSTRUCTOR DE LA CLASE MUSICBOX
    public MusicBox() {
        player = new BasicPlayer();
    }

    /**MÉTODO PARA REPRODUCIR CANCIONES
     Reproduce la canción seleccionada al ser invocado*/
    public void play() throws Exception {
        player.play();
    }

    /**MÉTODO PARA ABRIR ARCHIVOS DE UN DIRECTORIO
     Abre el fichero establecido con la dirección del mp3*/
    public void openFile(String link) throws Exception {
        player.open(new File(link));
        player.play();
    }

    /**MÉTODO PARA PAUSAR CANCIONES 
     Pausa la canción seleccionada al ser invocado*/
    public void pause() throws Exception {
        player.pause();
    }

    /**MÉTODO PARA REANUDAR LAS CANCIONES
     Continua la reproducción si hay una canción en pausa*/
    public void resume() throws Exception {
        player.resume();
    }

    /**MÉTODO PARA DETENER LAS CANCIONES
     Detiene la canción seleccionada al ser invocada*/
    public void stop() throws Exception {
        player.stop();
    }
    
    /*MÉTODO PARA RETROCEDER CANCIONES DE UNA LISTA DE REPRODUCCIÓN
    Busca la canción anterior en la lista de reproducción y la reproduce*/
    public void previous() throws Exception {
        player.stop();
        try {
            actual = getActual();
            counter = 0;
            while (Integer.parseInt(list.getElementPosition(counter, 5).toString()) != actual - 1) {
                counter++;
            }
            setSong (list.getElementPosition(counter, 1).toString() + ".mp3");
        }
        catch (java.lang.NullPointerException e) { 
        }
    }
    
    /**MÉTODO PARA ADELANTAR CANCIONES DE UNA LISTA DE REPRODUCCIÓN
     Busca la canción siguiente en la lista de reproducción y la reproduce*/
    public void next() throws Exception {
        player.stop();
        try {
            actual = getActual();
            counter = 0;
            while (Integer.parseInt(list.getElementPosition(counter, 5).toString()) != actual + 1) {
                counter++;
            }
            setSong (list.getElementPosition(counter, 1).toString() + ".mp3");
        }
        catch (java.lang.NullPointerException e) { 
        }
    }
    
    /**MÉTODO PARA ABRIR ARCHIVOS .MP3*/
    public  void playMP3 () throws Exception {
        MusicBox player = new MusicBox();
        try {
            player.openFile(location + song);
            player.play();
        } 
        catch (Exception ex) {
            System.err.println("Sorry! File not found");
        }
    }
    
    /**MÉTODO PARA REPRODUCCIÓN ALEATORIA
     Reproduce una canción random dentro del directorio establecido*/
    public void randomPlayer() throws Exception {
        stop();
        list =getAllMetaData();
        actual = randomGenerator(0, list.size());
        try {
            setSong (list.getElementPosition(actual, 1).toString() + ".mp3");
        }
        catch (java.lang.NullPointerException e) {
            randomPlayer();
        }
    }
    
    /**GENERADOR DE NÚMEROS DE PISTA RANDOM
     Genera un número random entre los archivos preestablecidos*/
    public static int randomGenerator(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**MÉTODO PARA REPRODUCCIÓN EN COLA
     Reproduce la canción seleccionada y al finalizar, la siguiente a ésta*/
    public void playerList() throws Exception {
        try {
            actual = getActual();
            actual++;
            playMP3();
            delayEffect(getTrackLenght(location + song)*1000);
            counter = list.size() - actual;
            actual = Integer.parseInt(list.getElementPosition(counter, 5).toString());
            while (counter != 0) {
                try {
                    if (counter == 1) {
                        next();
                        break;
                    }
                    next();
                    delayEffect(getTrackLenght(location + song)*1000);
                    counter--; 
                }
                catch (java.lang.NullPointerException e) {
                }
            }
        }
        catch (java.lang.NullPointerException e) {
            
        }
    }

    /*OBTENER CANCIÓN EN REPRODUCCIÓN
    Genera un int con el número de pista en reproducción*/
    public static int getActual () {
        list = getAllMetaData();
        while (list.size() != actual) {
            
            if((list.getElementPosition(counter, 1).toString() + ".mp3").equals(song)) {
                break; 
            }
            counter++;
            actual++;
        }
        try {
            actual = Integer.parseInt(list.getElementPosition(counter, 5).toString());
        }
        catch (java.lang.NumberFormatException e) {
        }
        return actual;
    }
    
    /*GENERADOR DE EFECTO DELAY
    Crea un efecto delay para la reproducción en cola*/
    public static void delayEffect (int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
        }                       
    }    
}