package br.com.wro.nfe.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class ZipUtils {
	
	 private static final int EXPECTED_COMPRESSION_RATIO = 5;
	 private static final int BUFFER_SIZE = 4096;
	 
	public static final byte[] unzipBestEffort(byte[] in) {
		return unzipBestEffort(in, Integer.MAX_VALUE);
    }
	
	public static final byte[] unzipBestEffort(byte[] in, int sizeLimit) {
	      try {
	        // decompress using GZIPInputStream 
	        ByteArrayOutputStream out  =  new ByteArrayOutputStream(EXPECTED_COMPRESSION_RATIO * in.length);
	        GZIPInputStream input  = new GZIPInputStream(new ByteArrayInputStream(in));
	  
	        byte[] buf = new byte[BUFFER_SIZE];
	        int written = 0;
	        
	        while (true) {
	          try {
	            int size = input.read(buf);
	            if (size <= 0) 
	            	break;
	            if ((written + size) > sizeLimit) {
	              out.write(buf, 0, sizeLimit - written);
	              break;
	            }
	            out.write(buf, 0, size);
	            written += size;
	          } catch (Exception e) {
	            break;
	          }
	        }
	        try {
	        	out.close();
	        } catch (IOException e) {
	        }
	  
	        return out.toByteArray();
	  
	      } catch (IOException e) {
	        return null;
	      }
	    }

}
