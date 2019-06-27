import java.io.*;
public class FileListFilter implements FilenameFilter {
	
	private String extension;
	boolean boo;
	
	public FileListFilter(){
	    boo=true;
	}
	
	public FileListFilter(String extension){
		this.extension=extension;
		boo=false;
	}
	
	public boolean accept(File directory,String filename){
	    
		boolean fileOK=true;
		if(extension!=null){
			fileOK&=filename.endsWith('.'+extension);
		}
		
		if(boo){
			return true;
		}
					
		return fileOK;    
	}
}