package musicbox;

import java.io.File;
import java.io.IOException;
import static musicbox.GetMetaData.getAllMetaData;
import static musicbox.GetMetaData.getMetaData;
import static musicbox.GetMetaData.listDirectory;
import musicbox.DoubleList;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

public class Arrange {
    
    /*ATRIBUTOS DE LA CLASE ARRANGE*/
    public static DoubleList listD;
    public static DoubleList listD1 = new DoubleList();
    public int actual;
       
    /**MÉTODO PARA ORDENAR POR CANCIONES
     Ordena las canciones en el directorio alfabéticamente según su título*/
    public void bySong () {
        listD = getAllMetaData();
        listD.bubbleSort(1);
    }
    
    /**MÉTODO PARA ORDENAR POR ARTISTA
     Ordena las canciones en el directorio alfabéticamente según su artista*/
    public void byArtist() {
        listD = getAllMetaData();
        listD.bubbleSort(2);
    }
    
    /**MÉTODO PARA ORDENAR POR GÉNERO
     Ordena las canciones en el directorio alfabéticamente según su género*/
    public void byGenre() {
        listD = getAllMetaData();
        listD.bubbleSort(3);
    }
    
    /**MÉTODO PARA ORDENAR POR ÁLBUM
     Ordena las canciones en el directorio alfabéticamente según su álbum*/
    public void byAlbum() {
        listD = getAllMetaData();
        listD.bubbleSort(4);
    }
    
    /**MÉTODO PARA REALIZAR BÚSQUEDAS AVANZADAS
     Recibe un parámetro y busca coincidencias con respecto a la meta información del directorio*/
    public void advancedSearch(String tag) {
        listD = getAllMetaData();
        while(listD.size() > actual) {
            try{
                if (listD.getElementPosition(actual, 1).equals(tag)) {
                    listD1.addEnd(listD.getElementPosition(actual, 1), listD.getElementPosition(actual, 2), listD.getElementPosition(actual, 3), listD.getElementPosition(actual, 4), listD.getElementPosition(actual, 5), listD.getElementPosition(actual, 6), listD.getElementPosition(actual, 7));
                }
                else if (listD.getElementPosition(actual, 2).equals(tag)) {
                    listD1.addEnd(listD.getElementPosition(actual, 1), listD.getElementPosition(actual, 2), listD.getElementPosition(actual, 3), listD.getElementPosition(actual, 4), listD.getElementPosition(actual, 5), listD.getElementPosition(actual, 6), listD.getElementPosition(actual, 7));
                }
                else if (listD.getElementPosition(actual, 3).equals(tag)) {
                    listD1.addEnd(listD.getElementPosition(actual, 1), listD.getElementPosition(actual, 2), listD.getElementPosition(actual, 3), listD.getElementPosition(actual, 4), listD.getElementPosition(actual, 5), listD.getElementPosition(actual, 6), listD.getElementPosition(actual, 7));
                }
                else if (listD.getElementPosition(actual, 4).equals(tag)) {
                    listD1.addEnd(listD.getElementPosition(actual, 1), listD.getElementPosition(actual, 2), listD.getElementPosition(actual, 3), listD.getElementPosition(actual, 4), listD.getElementPosition(actual, 5), listD.getElementPosition(actual, 6), listD.getElementPosition(actual, 7));
                }
            }
            catch (java.lang.NullPointerException e) {
            }
            actual++;
            
        }
    }
    
    /**MÉTODO PARA AGREGAR CANCIONES AL REPRODUCTOR
     Recibe un string con la dirección del fichero .mp3 y lo agrega al directorio en memoria*/
    public void add(String songName) throws IOException, ReadOnlyFileException, TagException, InvalidAudioFrameException {
        MP3File mp3File = new MP3File(songName);
        File file = new File(songName);
        listD = getMetaData("/media/andres/Datos/Music/(+44)/When Your Heart Stops Beating/Make You Smile.mp3");
        listDirectory().addEnd(listD1.getElementPosition(0, 1), null, null, null, null, null, null);
    }
}
