package hmv.core;

import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.DicomFileUtilities;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.dicom.MediaImporter;
import com.pixelmed.dicom.TransferSyntax;
import hmv.help.DicomImporter;
import hmv.help.HeaderAttribute;
import hmv.help.Mutual;
import java.io.File;
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
        /* Change path to fit your System*/
        
        
        String filename = "F:\\Downloads\\iso-inhalt\\IMAGES\\00020";
        
        AttributeList aList = new AttributeList();
        TransferSyntax tSyn = new TransferSyntax("1.2.840.10008.1.2");
        DicomInputStream dis = new DicomInputStream(new File(filename));
        
        dis.setTransferSyntaxToReadDataSet(tSyn);
        long start = System.currentTimeMillis();
        aList.read(dis);
        
        short[] pixelData = HeaderAttribute.getPixel(aList);
        int rows = HeaderAttribute.getRows(aList);
        int columns = HeaderAttribute.getColumns(aList);
        Mutual mut = new Mutual(pixelData, rows, columns);
        int[] mutErg1 = mut.getGreyscale();
        
        String filename2 = "F:\\Downloads\\iso-inhalt\\IMAGES\\00021";
        AttributeList aList2 = new AttributeList();
        aList2.read(new DicomInputStream(new File(filename2)));
        long end = System.currentTimeMillis();
        pixelData = HeaderAttribute.getPixel(aList2);
        rows = HeaderAttribute.getRows(aList2);
        columns = HeaderAttribute.getColumns(aList2);
        
        Mutual mut2 = new Mutual(pixelData, rows, columns);
        
        int[] mutErg2 = mut2.getGreyscale();
        
        int gesamt = 0;
        for(int i = 0; i<mutErg1.length; i++)
        {   
            System.out.println("Grau " + i + ": " + mutErg1[i] + " " + mutErg2[i] + " --- " + (mutErg2[i]-mutErg1[i]));
        }
        for(int j = 0; j<mutErg1.length; j++)
        {
            gesamt = gesamt + (mutErg2[j]-mutErg1[j]);
        }    
        
        
        System.out.println("Gesamt: " + gesamt + " Gesamtzeit für 2 Bilder: " + (end-start));
        
        DicomImporter di = new DicomImporter();
        di.importDicomDirectory(filename);
        System.out.println(DicomFileUtilities.isDicomOrAcrNemaFile(filename));
    }
}
