package hmv.core;

import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.display.SourceImage;
import java.io.File;
import java.io.IOException;

/**
 * @author HMV - Home Medical Viewer
 * 
 * Diese Klasse dient als Container für alle relevanten Daten zu einem DICOM-Bild
 */
public class DicomImage 
{
    private File absPath;
    private AttributeList aList;
    private SourceImage srci;
    
    public DicomImage(File absPath) throws IOException, DicomException
    {
        this.absPath = absPath;
        this.aList = new AttributeList();
        DicomInputStream dis = new DicomInputStream(absPath);
        this.aList.read(dis);
        dis.close();
        srci = new SourceImage(absPath.getAbsolutePath());
    }
    
    public AttributeList getAList()
    {
        return this.aList;
    }
    
    public File getPath()
    {
        return this.absPath;
    }
}
