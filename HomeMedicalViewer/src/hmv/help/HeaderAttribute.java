package hmv.help;

import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.dicom.TagFromName;
import com.pixelmed.dicom.TransferSyntax;
import com.pixelmed.dicom.ValueRepresentation;

/**
 * @author HMV - Home Medical Viewer
 * 
 * Diese Klasse verfügt über Methoden um an wichtige Daten im Header einer DICOM-File zu kommen
 */
public class HeaderAttribute
{
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the Patientname or No Name inserted
     */
    public static String getPatientName(AttributeList aList)
    {
        if(aList.get(TagFromName.PatientName).getSingleStringValueOrEmptyString() != null)
        {
            return aList.get(TagFromName.PatientName).getSingleStringValueOrNull();
        }
        return "No Name inserted";       
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the birthdate of the Patient or -1 when not set
     */
    public static int getPatientBirthDate(AttributeList aList)
    {
        if(aList.get(TagFromName.PatientBirthDate).getSingleIntegerValueOrDefault(-1) != -1)
        {
            return aList.get(TagFromName.PatientBirthDate).getSingleIntegerValueOrDefault(-1);
        }
        // No Birthdate in DICOM-Header
        return -1;
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the Physican or No Physican registrered
     */
    public static String getRefferingPhysican(AttributeList aList)
    {
        if(aList.get(TagFromName.ReferringPhysicianName).getSingleStringValueOrEmptyString() != null)
        {
            return aList.get(TagFromName.ReferringPhysicianName).getSingleStringValueOrEmptyString();
        }
        return "No Physican registrered";
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the TransferSyntax
     */
    public static TransferSyntax getTransferSyntax(AttributeList aList)
    {
         return new TransferSyntax(aList.get(TagFromName.TransferSyntaxUID).getSingleStringValueOrEmptyString());
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the number of rows or -1 when not set
     * @throws DicomException 
     */
    public static int getRows(AttributeList aList) throws DicomException
    {
        int[] row = aList.get(TagFromName.Rows).getIntegerValues();
        if(row.length > 0)
        {
            return row[0];
        }
        return -1;
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the number of the serie who contains this DICOM-File
     * @throws DicomException 
     */
    public static int getSeriesNumber(AttributeList aList) throws DicomException
    {
        int[] series = aList.get(TagFromName.SeriesNumber).getIntegerValues();
        if(series.length > 0)
        {
            return series[0];
        }
        return -1;       
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the number of the DICOM-FILE in the specific series
     * @throws DicomException 
     */
    public static int getInstanceNumber(AttributeList aList) throws DicomException
    {
        int[] instance = aList.get(TagFromName.SeriesNumber).getIntegerValues();
        if(instance.length > 0)
        {
            return instance[0];
        }
        return -1;       
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the number of columns or -1 when not set
     * @throws DicomException 
     */ 
    public static int getColumns(AttributeList aList) throws DicomException
    {
        int[] column = aList.get(TagFromName.Columns).getIntegerValues();
        if(column.length > 0)
        {
            return column[0];
        }
        return -1;
    }
    /**
     * 
     * @param aList the AttributeList of the current Inputstream
     * @return the Array of all Pixels from the DICOM-File Image
     * @throws DicomException 
     */
    public static short[] getPixel(AttributeList aList) throws DicomException
    {
        if(ValueRepresentation.isOtherByteVR(aList.get(TagFromName.PixelData).getVR()))
        {
            byte[] bPixel = aList.get(TagFromName.PixelData).getByteValues();
            short[] sPixel = new short[bPixel.length];
            for(int i = 0; i<bPixel.length;i++)
            {
                sPixel[i] = (short) bPixel[i];
            }
            return sPixel;
        }
        return aList.get(TagFromName.PixelData).getShortValues();
    }
}