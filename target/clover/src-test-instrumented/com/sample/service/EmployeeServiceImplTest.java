/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sample.dao.EmployeeDao;
import com.sample.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {static class __CLR4_5_26t6tme8ci2pv{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}public static <T> T caseInc(int i,java.util.function.Supplier<T> s){R.inc(i);return s.get();}public static void caseInc(int i,Runnable r){R.inc(i);r.run();}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_5_2();if(20240131180750L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation shall match the runtime version.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.5.2#20240131180750,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u002f\u0068\u006f\u006d\u0065\u002f\u0062\u006f\u006e\u006e\u0079\u002f\u0041\u0057\u0053\u002f\u0053\u0070\u0072\u0069\u006e\u0067\u0033\u0048\u0069\u0062\u0065\u0072\u006e\u0061\u0074\u0065\u005f\u0041\u0070\u0070\u002f\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1754991290935L,8589935092L,261,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_5_2_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	@Mock
	EmployeeDao employeeDao;
	private EmployeeServiceImpl employeeService;

	@Before
	public void setup() {try{__CLR4_5_26t6tme8ci2pv.R.inc(245);
		__CLR4_5_26t6tme8ci2pv.R.inc(246);employeeService = new EmployeeServiceImpl();
	}finally{__CLR4_5_26t6tme8ci2pv.R.flushNeeded();}}
	
	@Test
	public void testEmployeeNotAvailable() {__CLR4_5_26t6tme8ci2pv.R.globalSliceStart(getClass().getName(),247);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_5_2h7omo36v();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_5_26t6tme8ci2pv.R.rethrow($CLV_t2$);}finally{__CLR4_5_26t6tme8ci2pv.R.globalSliceEnd(getClass().getName(),"com.sample.service.EmployeeServiceImplTest.testEmployeeNotAvailable",__CLR4_5_2_TEST_NAME_SNIFFER.getTestName(),247,$CLV_p$,$CLV_t$);}}private void  __CLR4_5_2h7omo36v(){try{__CLR4_5_26t6tme8ci2pv.R.inc(247);
		__CLR4_5_26t6tme8ci2pv.R.inc(248);org.mockito.Mockito.when(employeeDao.getEmployee(1)).thenReturn(null);
		__CLR4_5_26t6tme8ci2pv.R.inc(249);employeeService.setEmployeeDao(employeeDao);
		__CLR4_5_26t6tme8ci2pv.R.inc(250);Employee employee = employeeService.getEmployee(1);
		
		__CLR4_5_26t6tme8ci2pv.R.inc(251);assertNull(employee);
	}finally{__CLR4_5_26t6tme8ci2pv.R.flushNeeded();}}

	@Test
	public void testEmployeeAvailable() {__CLR4_5_26t6tme8ci2pv.R.globalSliceStart(getClass().getName(),252);int $CLV_p$=0;java.lang.Throwable $CLV_t$=null;try{__CLR4_5_2577uk070();$CLV_p$=1;}catch(java.lang.Throwable $CLV_t2$){if($CLV_p$==0&&$CLV_t$==null){$CLV_t$=$CLV_t2$;}__CLR4_5_26t6tme8ci2pv.R.rethrow($CLV_t2$);}finally{__CLR4_5_26t6tme8ci2pv.R.globalSliceEnd(getClass().getName(),"com.sample.service.EmployeeServiceImplTest.testEmployeeAvailable",__CLR4_5_2_TEST_NAME_SNIFFER.getTestName(),252,$CLV_p$,$CLV_t$);}}private void  __CLR4_5_2577uk070(){try{__CLR4_5_26t6tme8ci2pv.R.inc(252);
		__CLR4_5_26t6tme8ci2pv.R.inc(253);employeeService = new EmployeeServiceImpl();
		__CLR4_5_26t6tme8ci2pv.R.inc(254);Employee dummyEmployee = new Employee();
		__CLR4_5_26t6tme8ci2pv.R.inc(255);dummyEmployee.setEmpId(1);
		__CLR4_5_26t6tme8ci2pv.R.inc(256);dummyEmployee.setEmpName("Sandy");
		__CLR4_5_26t6tme8ci2pv.R.inc(257);org.mockito.Mockito.when(employeeDao.getEmployee(1)).thenReturn(dummyEmployee);
		__CLR4_5_26t6tme8ci2pv.R.inc(258);employeeService.setEmployeeDao(employeeDao);
		__CLR4_5_26t6tme8ci2pv.R.inc(259);Employee employee = employeeService.getEmployee(1);
		
		__CLR4_5_26t6tme8ci2pv.R.inc(260);assertEquals("Name didn't matched", "Sandy", employee.getEmpName());
	}finally{__CLR4_5_26t6tme8ci2pv.R.flushNeeded();}}
}