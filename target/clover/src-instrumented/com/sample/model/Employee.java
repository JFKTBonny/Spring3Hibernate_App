/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee implements Serializable{public static class __CLR4_5_23131me8ci2nu{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}public static <T> T caseInc(int i,java.util.function.Supplier<T> s){R.inc(i);return s.get();}public static void caseInc(int i,Runnable r){R.inc(i);r.run();}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_5_2();if(20240131180750L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation shall match the runtime version.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.5.2#20240131180750,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u002f\u0068\u006f\u006d\u0065\u002f\u0062\u006f\u006e\u006e\u0079\u002f\u0041\u0057\u0053\u002f\u0053\u0070\u0072\u0069\u006e\u0067\u0033\u0048\u0069\u0062\u0065\u0072\u006e\u0061\u0074\u0065\u005f\u0041\u0070\u0070\u002f\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1754991290747L,8589935092L,129,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_5_2_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "empid")
	private Integer empId;
	
	@Column(name="empname")
	private String empName;
	
	@Column(name="empaddress")
	private String empAddress;
	
	@Column(name="salary")
	private Long salary;
	
	@Column(name="empAge")
	private Integer empAge;

	public Integer getEmpId() {try{__CLR4_5_23131me8ci2nu.R.inc(109);
		__CLR4_5_23131me8ci2nu.R.inc(110);return empId;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public void setEmpId(Integer empId) {try{__CLR4_5_23131me8ci2nu.R.inc(111);
		__CLR4_5_23131me8ci2nu.R.inc(112);this.empId = empId;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public String getEmpName() {try{__CLR4_5_23131me8ci2nu.R.inc(113);
		__CLR4_5_23131me8ci2nu.R.inc(114);return empName;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public void setEmpName(String empName) {try{__CLR4_5_23131me8ci2nu.R.inc(115);
		__CLR4_5_23131me8ci2nu.R.inc(116);this.empName = empName;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public String getEmpAddress() {try{__CLR4_5_23131me8ci2nu.R.inc(117);
		__CLR4_5_23131me8ci2nu.R.inc(118);return empAddress;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public void setEmpAddress(String empAddress) {try{__CLR4_5_23131me8ci2nu.R.inc(119);
		__CLR4_5_23131me8ci2nu.R.inc(120);this.empAddress = empAddress;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public Long getSalary() {try{__CLR4_5_23131me8ci2nu.R.inc(121);
		__CLR4_5_23131me8ci2nu.R.inc(122);return salary;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public void setSalary(Long salary) {try{__CLR4_5_23131me8ci2nu.R.inc(123);
		__CLR4_5_23131me8ci2nu.R.inc(124);this.salary = salary;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public Integer getEmpAge() {try{__CLR4_5_23131me8ci2nu.R.inc(125);
		__CLR4_5_23131me8ci2nu.R.inc(126);return empAge;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

	public void setEmpAge(Integer empAge) {try{__CLR4_5_23131me8ci2nu.R.inc(127);
		__CLR4_5_23131me8ci2nu.R.inc(128);this.empAge = empAge;
	}finally{__CLR4_5_23131me8ci2nu.R.flushNeeded();}}

}
