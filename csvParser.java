import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.*;

public class csvParser 
{
   
    public static FileWriter createOutputCsvFile(String pathToFile)
    {
        try 
        {
            FileWriter csvWriter = new FileWriter(pathToFile);
            return csvWriter; 
        }
        
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            return null;  
        }
    }

    // Parses an input csv file and calculates the source count, destination count and incoming/outgoing packet sizes 
    public static void parseCsvFile(String pathToFile, FileWriter csvWriter) {

       

        try {
            String row;
            int incomingPacketSize = 0;
            int outgoingPacketSize = 0;
            int sourceCount = 0;
            int destinationCount = 0;

            BufferedReader csvReader = new BufferedReader(new FileReader(pathToFile));
           
            while ((row = csvReader.readLine()) != null)
            {
                String[] line = row.split(",");
                String source = line[2];
                String destination = line[3];
                // remove quotes around the length string
                String length = line[5].replace("\"", "");

                if (source.equals("\"192.168.100.180\""))
                {
                    sourceCount++;
                    incomingPacketSize += Integer.parseInt(length);
                }

                if (destination.equals("\"192.168.100.180\"")) 
                {
                    destinationCount++;
                    outgoingPacketSize += Integer.parseInt(length);
                }
            }

            writeOutputToCSV(csvWriter, sourceCount, destinationCount, incomingPacketSize, outgoingPacketSize);

        }
        catch (Exception e)	{
            System.err.println("Error: " + e.getMessage());  
        }
    }

    // Write the source count, destination count and incoming/outgoing packet sizes to the output .csv file
    public static void writeOutputToCSV(FileWriter csvWriter, int source_count, int destination_count, int incoming_packet_size, int outgoing_packet_size) {

       try {
           csvWriter.append(source_count + "," + destination_count + ","+  incoming_packet_size + "," + outgoing_packet_size + "\n");
           csvWriter.flush();
        }
        catch (Exception e)	{
            System.err.println("Error: " + e.getMessage());  
        }
    }

    public static void main(String[] args) {

   
    	
    	 FileWriter csvWriter = createOutputCsvFile("/Users/saadkhan/Desktop/Assignment/csvfiles/test3.csv"); //change path accordingly
    	 
        if (csvWriter == null) {
            System.out.println("Error creating output csv file");
            System.exit(0);
        }

        String srcFilePath = "/Users/saadkhan/Desktop/Assignment/csvfiles";  //change path accordingly
        
        File sourcedirectoryAllFilePath = new File(srcFilePath);     
        String contentsListArrayData[] = sourcedirectoryAllFilePath.list();
       
        for(int i=0; i<contentsListArrayData.length; i++) 
        {
           parseCsvFile(srcFilePath+"/"+contentsListArrayData[i]+"", csvWriter);
        }
        
      
    }
}