package santepis2.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUploader {
    private static String ScriptsURL = "http://localhost/santeuploads/";
    private static Object FileUtils;
    
    public static String upload(String name ,String path,String type){
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL(ScriptsURL+"upload.php").openConnection();
            httpUrlConnection.setDoOutput(true);
            
            
            httpUrlConnection.setRequestMethod("POST");
                        DataInputStream dis = null;

            OutputStream os = httpUrlConnection.getOutputStream();
            Thread.sleep(1000);
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(path));
            
            int totalByte = fis.available();
            int byteTrasferred;
            for (int i = 0; i < totalByte; i++) {
                os.write(fis.read());
                byteTrasferred = i + 1;
            }
            
            os.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpUrlConnection.getInputStream()));
            
            String s = null;
            String returnedPath = "";
            dis = new DataInputStream(fis);
            while (dis.available() != 0) {
				System.out.println(dis.readLine());
			}
            
            while ((s = in.readLine()) != null) {
                if(s!=null)
                    
                    returnedPath+=s;
                
            }
            InputStream is = null;

            
        
        

            in.close();
            fis.close();
            File F1 = new File(path);

            
            Random generator = new Random(); 
            int i = generator.nextInt(1000) + 1;
            int e = generator.nextInt(100) + 1;
String url ="" ;
            if (type == "image")
            {
            String des = "C:\\wamp64\\www\\santeuploads\\"+e+i+"image.PNG" ;
            
              url = ScriptsURL+e+i+"image.PNG";
            File f2 = new File(des) ;
            Files.copy(F1.toPath(),f2.toPath());
            System.out.println(f2.toPath().toString());}
            else 
            {
             String des = "C:\\wamp64\\www\\santeuploads\\"+e+i+"video.mp4" ;
            
             url = ScriptsURL+e+i+"video.mp4";
            File f2 = new File(des) ;
            Files.copy(F1.toPath(),f2.toPath());
                
            }
            
            
            return url;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean delete(String filename){
        try {
            URL url = new URL(ScriptsURL+"delete.php?filename="+filename);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            
            return (connection.getResponseCode() == 200?true:false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    
}
