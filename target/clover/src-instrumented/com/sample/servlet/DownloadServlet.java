/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.servlet;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
 
public class DownloadServlet extends HttpServlet {public static class __CLR4_5_24k4kme8ci2og{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}public static <T> T caseInc(int i,java.util.function.Supplier<T> s){R.inc(i);return s.get();}public static void caseInc(int i,Runnable r){R.inc(i);r.run();}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_5_2();if(20240131180750L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation shall match the runtime version.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.5.2#20240131180750,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u002f\u0068\u006f\u006d\u0065\u002f\u0062\u006f\u006e\u006e\u0079\u002f\u0041\u0057\u0053\u002f\u0053\u0070\u0072\u0069\u006e\u0067\u0033\u0048\u0069\u0062\u0065\u0072\u006e\u0061\u0074\u0065\u005f\u0041\u0070\u0070\u002f\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1754991290747L,8589935092L,205,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_5_2_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {try{__CLR4_5_24k4kme8ci2og.R.inc(164);
    	
    	__CLR4_5_24k4kme8ci2og.R.inc(165);String name = request.getParameter("name");
        // reads input file from an absolute path
        __CLR4_5_24k4kme8ci2og.R.inc(166);String filePath = getUploadDir() + File.separator + name;
        __CLR4_5_24k4kme8ci2og.R.inc(167);File downloadFile = new File(filePath);
        __CLR4_5_24k4kme8ci2og.R.inc(168);FileInputStream inStream = new FileInputStream(downloadFile);
         
        // obtains ServletContext
        __CLR4_5_24k4kme8ci2og.R.inc(169);ServletContext context = getServletContext();
         
        // gets MIME type of the file
        __CLR4_5_24k4kme8ci2og.R.inc(170);String mimeType = context.getMimeType(filePath);
        __CLR4_5_24k4kme8ci2og.R.inc(171);if ((((mimeType == null)&&(__CLR4_5_24k4kme8ci2og.R.iget(172)!=0|true))||(__CLR4_5_24k4kme8ci2og.R.iget(173)==0&false))) {{        
            // set to binary type if MIME mapping not found
            __CLR4_5_24k4kme8ci2og.R.inc(174);mimeType = "application/octet-stream";
        }
        }__CLR4_5_24k4kme8ci2og.R.inc(175);System.out.println("MIME type: " + mimeType);
         
        // modifies response
        __CLR4_5_24k4kme8ci2og.R.inc(176);response.setContentType(mimeType);
        __CLR4_5_24k4kme8ci2og.R.inc(177);response.setContentLength((int) downloadFile.length());
         
        // forces download
        __CLR4_5_24k4kme8ci2og.R.inc(178);String headerKey = "Content-Disposition";
        __CLR4_5_24k4kme8ci2og.R.inc(179);String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        __CLR4_5_24k4kme8ci2og.R.inc(180);response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        __CLR4_5_24k4kme8ci2og.R.inc(181);OutputStream outStream = response.getOutputStream();
         
        __CLR4_5_24k4kme8ci2og.R.inc(182);byte[] buffer = new byte[4096];
        __CLR4_5_24k4kme8ci2og.R.inc(183);int bytesRead = -1;
         
        __CLR4_5_24k4kme8ci2og.R.inc(184);while ((bytesRead = inStream.read(buffer)) != -1) {{
            __CLR4_5_24k4kme8ci2og.R.inc(187);outStream.write(buffer, 0, bytesRead);
        }
         
        }__CLR4_5_24k4kme8ci2og.R.inc(188);inStream.close();
        __CLR4_5_24k4kme8ci2og.R.inc(189);outStream.close();     
    }finally{__CLR4_5_24k4kme8ci2og.R.flushNeeded();}}
    
    private String getUploadDir() {try{__CLR4_5_24k4kme8ci2og.R.inc(190);
    	__CLR4_5_24k4kme8ci2og.R.inc(191);String uploadDir = null;
    	__CLR4_5_24k4kme8ci2og.R.inc(192);InputStream in = null;
    	__CLR4_5_24k4kme8ci2og.R.inc(193);try{
    		__CLR4_5_24k4kme8ci2og.R.inc(194);in = new ClassPathResource("database.properties").getInputStream();
    		__CLR4_5_24k4kme8ci2og.R.inc(195);Properties prop = new Properties();

    		__CLR4_5_24k4kme8ci2og.R.inc(196);prop.load(in);
    		__CLR4_5_24k4kme8ci2og.R.inc(197);uploadDir = prop.getProperty("upload.dir");
    	} 
    	catch (IOException e) {

    		__CLR4_5_24k4kme8ci2og.R.inc(198);e.printStackTrace();
    	} finally {
    		__CLR4_5_24k4kme8ci2og.R.inc(199);if((((in != null)&&(__CLR4_5_24k4kme8ci2og.R.iget(200)!=0|true))||(__CLR4_5_24k4kme8ci2og.R.iget(201)==0&false)))
    			{__CLR4_5_24k4kme8ci2og.R.inc(202);try {
    				__CLR4_5_24k4kme8ci2og.R.inc(203);in.close();
    			} catch (IOException e) {
    				//do nothing
    			}
    	}}
    	__CLR4_5_24k4kme8ci2og.R.inc(204);return uploadDir;
    }finally{__CLR4_5_24k4kme8ci2og.R.flushNeeded();}}
}