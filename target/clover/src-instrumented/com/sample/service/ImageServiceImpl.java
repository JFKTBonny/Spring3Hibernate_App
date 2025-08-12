/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.sample.dao.ImageDao;
import com.sample.model.Image;

@Service("imageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImageServiceImpl implements ImageService {public static class __CLR4_5_24444me8ci2o8{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}public static <T> T caseInc(int i,java.util.function.Supplier<T> s){R.inc(i);return s.get();}public static void caseInc(int i,Runnable r){R.inc(i);r.run();}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_5_2();if(20240131180750L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation shall match the runtime version.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.5.2#20240131180750,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u002f\u0068\u006f\u006d\u0065\u002f\u0062\u006f\u006e\u006e\u0079\u002f\u0041\u0057\u0053\u002f\u0053\u0070\u0072\u0069\u006e\u0067\u0033\u0048\u0069\u0062\u0065\u0072\u006e\u0061\u0074\u0065\u005f\u0041\u0070\u0070\u002f\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1754991290747L,8589935092L,164,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_5_2_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	@Autowired
	private ImageDao imageDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveImage(Image image) {try{__CLR4_5_24444me8ci2o8.R.inc(148);
		__CLR4_5_24444me8ci2o8.R.inc(149);try{
			
		} catch (Exception e){
			
		}
		__CLR4_5_24444me8ci2o8.R.inc(150);getImageDao().saveImage(image);
	}finally{__CLR4_5_24444me8ci2o8.R.flushNeeded();}}
	
	public List<Image> listImages() {try{__CLR4_5_24444me8ci2o8.R.inc(151);
		__CLR4_5_24444me8ci2o8.R.inc(152);return getImageDao().listImages();
	}finally{__CLR4_5_24444me8ci2o8.R.flushNeeded();}}

	public Image getImage(int imgid) {try{__CLR4_5_24444me8ci2o8.R.inc(153);
		__CLR4_5_24444me8ci2o8.R.inc(154);return getImageDao().getImage(imgid);
	}finally{__CLR4_5_24444me8ci2o8.R.flushNeeded();}}
	
	
	protected void setImageDao(ImageDao imageDao) {try{__CLR4_5_24444me8ci2o8.R.inc(155);
		__CLR4_5_24444me8ci2o8.R.inc(156);this.imageDao = imageDao;
	}finally{__CLR4_5_24444me8ci2o8.R.flushNeeded();}}
	
	private ImageDao getImageDao(){try{__CLR4_5_24444me8ci2o8.R.inc(157);
		__CLR4_5_24444me8ci2o8.R.inc(158);if((((imageDao == null)&&(__CLR4_5_24444me8ci2o8.R.iget(159)!=0|true))||(__CLR4_5_24444me8ci2o8.R.iget(160)==0&false))) {{
			__CLR4_5_24444me8ci2o8.R.inc(161);BeanFactory beanfactory = new ClassPathXmlApplicationContext("sdnext-servlet.xml");
			__CLR4_5_24444me8ci2o8.R.inc(162);imageDao = (ImageDao) beanfactory.getBean("imageDao");
		}
		}__CLR4_5_24444me8ci2o8.R.inc(163);return imageDao;
	}finally{__CLR4_5_24444me8ci2o8.R.flushNeeded();}}
}
