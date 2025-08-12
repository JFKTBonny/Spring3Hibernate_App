/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.servlet;
 import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.core.io.ClassPathResource;

import com.sample.model.Image;
import com.sample.service.ImageService;
import com.sample.service.ImageServiceImpl;

/**
 * Servlet to handle File upload request from Client
 */
public class FileUploadServlet extends HttpServlet {public static class __CLR4_5_25p5pme8ci2oo{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}public static <T> T caseInc(int i,java.util.function.Supplier<T> s){R.inc(i);return s.get();}public static void caseInc(int i,Runnable r){R.inc(i);r.run();}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_5_2();if(20240131180750L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation shall match the runtime version.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.5.2#20240131180750,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u002f\u0068\u006f\u006d\u0065\u002f\u0062\u006f\u006e\u006e\u0079\u002f\u0041\u0057\u0053\u002f\u0053\u0070\u0072\u0069\u006e\u0067\u0033\u0048\u0069\u0062\u0065\u0072\u006e\u0061\u0074\u0065\u005f\u0041\u0070\u0070\u002f\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1754991290747L,8589935092L,241,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_5_2_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {try{__CLR4_5_25p5pme8ci2oo.R.inc(205);
    	__CLR4_5_25p5pme8ci2oo.R.inc(206);String name = "";
        //process only if its multipart content
        __CLR4_5_25p5pme8ci2oo.R.inc(207);if((((ServletFileUpload.isMultipartContent(request))&&(__CLR4_5_25p5pme8ci2oo.R.iget(208)!=0|true))||(__CLR4_5_25p5pme8ci2oo.R.iget(209)==0&false))){{
            __CLR4_5_25p5pme8ci2oo.R.inc(210);try {
                __CLR4_5_25p5pme8ci2oo.R.inc(211);List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                __CLR4_5_25p5pme8ci2oo.R.inc(212);for(FileItem item : multiparts){{
                    __CLR4_5_25p5pme8ci2oo.R.inc(213);if((((!item.isFormField())&&(__CLR4_5_25p5pme8ci2oo.R.iget(214)!=0|true))||(__CLR4_5_25p5pme8ci2oo.R.iget(215)==0&false))){{
                        __CLR4_5_25p5pme8ci2oo.R.inc(216);name = new File(item.getName()).getName();
                        __CLR4_5_25p5pme8ci2oo.R.inc(217);item.write( new File(getUploadDir() + File.separator + name));
                    }
                }}
           
               //File uploaded successfully
               }__CLR4_5_25p5pme8ci2oo.R.inc(218);request.setAttribute("message", "File Uploaded Successfully");
               
               //Saving Image
               __CLR4_5_25p5pme8ci2oo.R.inc(219);Image i = new Image();
               __CLR4_5_25p5pme8ci2oo.R.inc(220);i.setImageName(name);
               __CLR4_5_25p5pme8ci2oo.R.inc(221);ImageService service = new ImageServiceImpl();
               __CLR4_5_25p5pme8ci2oo.R.inc(222);service.saveImage(i);
               
            } catch (Exception ex) {
               __CLR4_5_25p5pme8ci2oo.R.inc(223);request.setAttribute("message", "File Upload Failed due to " + ex);
             
            }          
         
        }}else{{
            __CLR4_5_25p5pme8ci2oo.R.inc(224);request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
     
        
        }__CLR4_5_25p5pme8ci2oo.R.inc(225);response.sendRedirect(request.getContextPath() + "/index.html");
     
    }finally{__CLR4_5_25p5pme8ci2oo.R.flushNeeded();}}
  
    private String getUploadDir() {try{__CLR4_5_25p5pme8ci2oo.R.inc(226);
    	__CLR4_5_25p5pme8ci2oo.R.inc(227);String uploadDir = null;
    	__CLR4_5_25p5pme8ci2oo.R.inc(228);InputStream in = null;
    	__CLR4_5_25p5pme8ci2oo.R.inc(229);try{
    		__CLR4_5_25p5pme8ci2oo.R.inc(230);in = new ClassPathResource("database.properties").getInputStream();
    		__CLR4_5_25p5pme8ci2oo.R.inc(231);Properties prop = new Properties();

    		__CLR4_5_25p5pme8ci2oo.R.inc(232);prop.load(in);
    		__CLR4_5_25p5pme8ci2oo.R.inc(233);uploadDir = prop.getProperty("upload.dir");
    	} 
    	catch (IOException e) {

    		__CLR4_5_25p5pme8ci2oo.R.inc(234);e.printStackTrace();
    	} finally {
    		__CLR4_5_25p5pme8ci2oo.R.inc(235);if((((in != null)&&(__CLR4_5_25p5pme8ci2oo.R.iget(236)!=0|true))||(__CLR4_5_25p5pme8ci2oo.R.iget(237)==0&false)))
    			{__CLR4_5_25p5pme8ci2oo.R.inc(238);try {
    				__CLR4_5_25p5pme8ci2oo.R.inc(239);in.close();
    			} catch (IOException e) {
    				//do nothing
    			}
    	}}
    	__CLR4_5_25p5pme8ci2oo.R.inc(240);return uploadDir;
    }finally{__CLR4_5_25p5pme8ci2oo.R.flushNeeded();}}
}