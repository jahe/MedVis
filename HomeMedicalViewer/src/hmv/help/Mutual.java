package hmv.help;

/**
 * @author HMV - Home Medical Viewer
 */
public class Mutual 
{
    private int[] greyscale = new int[256];
    
    public Mutual(short[] pixelData, int rows, int columns)
    {   
        for(int i = 0;i<greyscale.length; i++)
        {
            greyscale[i] = 0;
        }
        for(int j = 0; j<pixelData.length; j++)
        {
            if(pixelData[j] < 256)
            {
                greyscale[pixelData[j]]++;
            }
            else
            {
                greyscale[pixelData[255]]++;
            }
        }
    }
    
    public int[] getGreyscale()
    {
        return this.greyscale;
    }
}
