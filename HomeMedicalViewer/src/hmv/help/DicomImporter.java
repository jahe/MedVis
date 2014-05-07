package hmv.help;

import com.pixelmed.dicom.DicomFileUtilities;
import java.io.File;

/**
 * @author HMV - Home Medical Viewer
 */
public class DicomImporter 
{
    public void importDicomDirectory(String pathName)
    {
        if(pathName != null)
        {
            File path = new File(pathName);
            File dicomdir = null;
            if(path.exists())
            {
                if(path.isFile() && path.getName().toLowerCase().equals("dicomdir"))
                {
                    dicomdir = path.getParentFile();
                    System.out.println(dicomdir);
                    System.out.println("Ich bin die Datei!");
                }
                else if(path.isDirectory())
                {
                    if(new File(path,"DICOMDIR") != null && new File(path,"DICOMDIR").isFile())
                    {
                        dicomdir = path;
                        System.out.println(dicomdir);
                        System.out.println("Ich bin ein Ordner der die Datei hat!");
                    }
                    else
                    {
                        // andere Dateimöglichkeiten Dicomdir oder dicomdir etc....
                    }   
                }
            }
            else
            {
                // k.A. was der User angeklickt hat!
            }
            if(dicomdir != null)
            {
                // Wir haben eine DICOMDIR. Jetzt nach validen Bildern suchen!
                dicomdir = getFiles(dicomdir);
                if(dicomdir != null)
                {
                    /* 
                        Hier gehts morgen weiter
                    */
                    
                }
                else
                {
                    //DICOMDIR gefunden aber es existieren keine weiteren DICOM-Files
                }
            }
        }
    }
    
    private File getFiles(File dicomdir)
    {
        File[] listOfFiles = dicomdir.listFiles();
        for(File file : listOfFiles)
        {
            if(DicomFileUtilities.isDicomOrAcrNemaFile(file) && !file.getName().toLowerCase().equals("dicomdir"))
            {
                System.out.println("FOUND FILE: " + file.getParentFile());
                return file.getParentFile();
            }
            if(file.isDirectory())
            {
                getFiles(file);
            }
        }
        return null;
    }
}
