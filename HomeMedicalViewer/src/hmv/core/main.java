package hmv.core;

import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.DicomFileUtilities;
import com.pixelmed.dicom.MediaImporter;
import hmv.help.DicomImporter;
import java.io.IOException;

/**
 * @author HMV - Home Medical Viewer
 */
public class main 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, DicomException 
    {
        String filename = "F:\\Downloads\\iso-inhalt";
        DicomImporter di = new DicomImporter();
        di.importDicomDirectory(filename);
        System.out.println(DicomFileUtilities.isDicomOrAcrNemaFile(filename));
    }
}
