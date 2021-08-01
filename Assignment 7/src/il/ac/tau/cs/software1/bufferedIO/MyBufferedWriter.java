package il.ac.tau.cs.software1.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter{
	
	private int size;
	private FileWriter fWriter;
	private String currentBuffer;
	private int writingInd;
	
	public MyBufferedWriter(FileWriter fWriter, int bufferSize){
		this.size = bufferSize;
		this.fWriter = fWriter;
		this.currentBuffer = "";
		this.writingInd = 0;
	}

	
	@Override
	public void write(String str) throws IOException {
		
		for(int i=0 ; i<str.length(); i++) {
			
			this.currentBuffer += str.charAt(i);
			this.writingInd++;
			
			if(writingInd == this.size) {
				//If we reached the maximum length of the buffer 
				//we''l add the current one to the file and set a new
				//blank one
				
				fWriter.write(currentBuffer);
				writingInd = 0;
				this.currentBuffer = "";
			}
		}

	}
	
	@Override
	public void close() throws IOException {
		if (this.currentBuffer.length() != 0) {
			fWriter.write(currentBuffer);
		}
		this.fWriter.close();
	}

}