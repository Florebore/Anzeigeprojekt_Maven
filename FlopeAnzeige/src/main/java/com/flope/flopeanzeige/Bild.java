/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flope.flopeanzeige;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Florian
 */
public class Bild {
    
    private Image sperrbilddatei;
    private URL bildURL;
    private File bild;
    private String bildurl;
    private ObjectProperty<Image> sperrbild = new SimpleObjectProperty<>(this, "sperrbild",null);
    private Path imagepathsource;
    private final Path imagepathdestination;
   
    
    public Bild() throws URISyntaxException {
    
    this.imagepathdestination = settargetpath("C:/Users/Florian/Desktop/Sperrbild/Anzeige1.jpeg");
    }
    
    public void setsperrbild(Image bild){
       // this.sperrbilddatei = bild;
       this.sperrbild.set(bild);
    }
    
    public Image getsperrbild(){
        return sperrbild.get();
       
    }
    
    public ObjectProperty<Image> getimageproperty(){
        return sperrbild;
    }
    
    public void seturl(String url) {
    
        this.bildurl = url;              }
    
    public String geturl(){
    
        return this.bildurl;            }
    
    public void savepictofolder () throws IOException{
        
    CopyOption[] options = new CopyOption[]{
      StandardCopyOption.REPLACE_EXISTING,
      StandardCopyOption.COPY_ATTRIBUTES};
    
    Files.copy(bild.toPath(), imagepathdestination, options);
   
    
    
    
    
    }
    
    public void setsourcepath(Path path) {
        
        this.imagepathsource = path;
        
        
        
    }
    
    public Path getsourcepath(){
        
        return this.imagepathsource;
    }
    
    public Path gettargetpath(){
        
        return this.imagepathdestination;
    }
    
    public final Path settargetpath (String path) throws URISyntaxException {
        
        
        
       Path destinationpath;
        destinationpath = Paths.get(path);
        
        System.out.println(destinationpath);
        
        return destinationpath;
        
    }
    
    public void setImagefile(File imagedatei)
            
    {this.bild = imagedatei;}
    
    public File getImagefile(){
        
        return this.bild;
    }
   
    public void setbildurl(URL imageurl){
    
    this.bildURL = imageurl;
    }
    
    public URL getbildURL (){return this.bildURL;}
    
}


