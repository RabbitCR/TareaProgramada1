package musicbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GetMetaData {
    
    /**ATRIBUTOS DE LA CLASE GETMETADATA*/
    public static String location;
        
    /**MÉTODO PARA SELECCIONAR EL DIRECTORIO POR DEFECTO
     Selecciona la ruta sobre la cual trabajará el reproductor*/
    public void setLocation (String location) {
        this.location = location;
    }
    
    /**MÉTODO PARA OBTENER LA META INFORMACIÓN DE UNA CANCIÓN ESPECÍFICA
     Recibe la dirección de la canción, extrae su meta información y la inserta en una lista*/
    public static DoubleList getMetaData (String link) {
        DoubleList list = new DoubleList ();
        try {
            MP3File mp3File = new MP3File(link);
            InputStream input = new FileInputStream(new File(link));
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();
            
            list.addEnd(metadata.get("title"),metadata.get("xmpDM:artist"),metadata.get("xmpDM:genre"),metadata.get("xmpDM:album"),metadata.get("xmpDM:trackNumber"), mp3File.getMP3AudioHeader().getTrackLengthAsString(), link);
        } 
        catch (FileNotFoundException e) {
        } 
        catch (IOException e) {
        } 
        catch (SAXException | TikaException e) {
        } 
        catch (TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
            Logger.getLogger(GetMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /**MÉTODO PARA OBTENER LA META INFORMACIÓN DE UN DIRECTORIO DE CANCIONES
     Aplica el método getMetaData(String link) a una serie de ficheros mp3 y los acumula en una lista*/
    public static DoubleList getAllMetaData () {
        DoubleList listDirectory = listDirectory();
        DoubleList dataList = new DoubleList();
        for (int i = 0; i < listDirectory.size(); i++) {
            dataList.addEnd(getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 1), 
                    getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 2), 
                    getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 3), 
                    getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 4), 
                    getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 5), 
                    getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 6), 
                    getMetaData(location + listDirectory.getElementPosition(i, 1)).getElementPosition(0, 7));
        }
        return dataList;
    }
    
    /**RETORNA UNA LISTA CON LA INFORMACIÓN DE LAS CANCIONES DISPONIBLES EN EL DIRECTORIO 
     Recorre el directorio seleccionado por defecto, extrae los nombres de la canciones y las introduce en una lista*/
    public static DoubleList listDirectory () {
        String sDirectorio = location;
        DoubleList listFiles = new DoubleList();
        File f = new File(sDirectorio);
        if (f.exists()) { 
            File[] ficheros = f.listFiles();
            for (int x = 0; x < ficheros.length; x++) {
                listFiles.addEnd(ficheros[x].getName(), null, null, null, null, null, null);
            }
        }
        else {
            System.err.println("Invalid Directory");
        }
        return filtrarElementos(listFiles);
    }
    
    /**FILTRA LOS ELEMENTOS DEL DIRECTORIO PARA OBTENER SÓLO LAS EXTENSIONES .MP3
     Filtra los elementos del directorio por defecto para asegurar que sean extensiones .mp3*/
    public static DoubleList filtrarElementos (DoubleList list) {
        int actual = 0;
        DoubleList tracks = new DoubleList();
        for (int i = 0; i < list.size(); i++) {
            if (getExtensionFile(list.getElementPosition(actual, 1).toString()).equals("mp3")) {
                tracks.addEnd(list.getElementPosition(actual, 1), null, null, null, null, null, null);
            }
            actual++;
        }
        return tracks;
    }
    
    /**OBTIENE LA EXTENSIÓN DE UN FICHERO ESPECÍFICO
     Recibe una dirección con un fichero y determina su extensión*/
    public static String getExtensionFile(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "Invalid extension";
        } 
        else {
            return filename.substring(index + 1);
        }
    }
    
    /*DETERMINA LA DURACIÓN DE UNA CANCIÓN
    Retorna la duración de la canción en curso en términos de segundos*/
    public static int getTrackLenght (String track) throws IOException, org.jaudiotagger.tag.TagException, ReadOnlyFileException, InvalidAudioFrameException {
        MP3File mp3File = new MP3File(track);
        return mp3File.getMP3AudioHeader().getTrackLength();
    }
}