/* $$ This file has been instrumented by Clover 4.5.2#20240131180750 $$ */package com.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.bean.EmployeeBean;
import com.sample.bean.FileBean;
import com.sample.model.Employee;
import com.sample.service.EmployeeService;
import com.sample.service.ImageService;

@Controller
public class EmployeeController {public static class __CLR4_5_2ssme8ci2n8{public static com_atlassian_clover.CoverageRecorder R;public static com_atlassian_clover.CloverProfile[] profiles = { };@java.lang.SuppressWarnings("unchecked") public static <I, T extends I> I lambdaInc(final int i,final T l,final int si){java.lang.reflect.InvocationHandler h=new java.lang.reflect.InvocationHandler(){public java.lang.Object invoke(java.lang.Object p,java.lang.reflect.Method m,java.lang.Object[] a) throws Throwable{R.inc(i);R.inc(si);try{return m.invoke(l,a);}catch(java.lang.reflect.InvocationTargetException e){throw e.getCause()!=null?e.getCause():new RuntimeException("Clover failed to invoke instrumented lambda",e);}}};return (I)java.lang.reflect.Proxy.newProxyInstance(l.getClass().getClassLoader(),l.getClass().getInterfaces(),h);}public static <T> T caseInc(int i,java.util.function.Supplier<T> s){R.inc(i);return s.get();}public static void caseInc(int i,Runnable r){R.inc(i);r.run();}static{com_atlassian_clover.CoverageRecorder _R=null;try{com_atlassian_clover.CloverVersionInfo.An_old_version_of_clover_is_on_your_compilation_classpath___Please_remove___Required_version_is___4_5_2();if(20240131180750L!=com_atlassian_clover.CloverVersionInfo.getBuildStamp()){com_atlassian_clover.Clover.l("[CLOVER] WARNING: The Clover version used in instrumentation shall match the runtime version.");com_atlassian_clover.Clover.l("[CLOVER] WARNING: Instr=4.5.2#20240131180750,Runtime="+com_atlassian_clover.CloverVersionInfo.getReleaseNum()+"#"+com_atlassian_clover.CloverVersionInfo.getBuildStamp());}R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getNullRecorder();_R=com_atlassian_clover.Clover.getRecorder("\u002f\u0068\u006f\u006d\u0065\u002f\u0062\u006f\u006e\u006e\u0079\u002f\u0041\u0057\u0053\u002f\u0053\u0070\u0072\u0069\u006e\u0067\u0033\u0048\u0069\u0062\u0065\u0072\u006e\u0061\u0074\u0065\u005f\u0041\u0070\u0070\u002f\u0074\u0061\u0072\u0067\u0065\u0074\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002f\u0063\u006c\u006f\u0076\u0065\u0072\u002e\u0064\u0062",1754991290747L,8589935092L,93,profiles,new java.lang.String[]{"clover.distributed.coverage",null});}catch(java.lang.SecurityException e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because it has insufficient security privileges. Please consult the Clover documentation on the security policy file changes required. ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.NoClassDefFoundError e){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised. Are you sure you have Clover in the runtime classpath? ("+e.getClass()+":"+e.getMessage()+")");}catch(java.lang.Throwable t){java.lang.System.err.println("[CLOVER] FATAL ERROR: Clover could not be initialised because of an unexpected error. ("+t.getClass()+":"+t.getMessage()+")");}R=_R;}}public static final com_atlassian_clover.TestNameSniffer __CLR4_5_2_TEST_NAME_SNIFFER=com_atlassian_clover.TestNameSniffer.NULL_INSTANCE;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") EmployeeBean employeeBean, 
			BindingResult result) {try{__CLR4_5_2ssme8ci2n8.R.inc(28);
		__CLR4_5_2ssme8ci2n8.R.inc(29);Employee employee = prepareModel(employeeBean);
		__CLR4_5_2ssme8ci2n8.R.inc(30);employeeService.addEmployee(employee);
		__CLR4_5_2ssme8ci2n8.R.inc(31);return new ModelAndView("redirect:/add.html");
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}

	@RequestMapping(value="/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {try{__CLR4_5_2ssme8ci2n8.R.inc(32);
		__CLR4_5_2ssme8ci2n8.R.inc(33);Map<String, Object> model = new HashMap<String, Object>();
		__CLR4_5_2ssme8ci2n8.R.inc(34);model.put("employees",  prepareListofBean(employeeService.listEmployeess()));
		__CLR4_5_2ssme8ci2n8.R.inc(35);return new ModelAndView("employeesList", model);
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}

	@RequestMapping(value="/listImages", method = RequestMethod.GET)
	public ModelAndView listImages() {try{__CLR4_5_2ssme8ci2n8.R.inc(36);
		__CLR4_5_2ssme8ci2n8.R.inc(37);Map<String, Object> model = new HashMap<String, Object>();
		__CLR4_5_2ssme8ci2n8.R.inc(38);model.put("images",  imageService.listImages());
		__CLR4_5_2ssme8ci2n8.R.inc(39);return new ModelAndView("listImages", model);
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("command")  EmployeeBean employeeBean,
			BindingResult result) {try{__CLR4_5_2ssme8ci2n8.R.inc(40);
		__CLR4_5_2ssme8ci2n8.R.inc(41);Map<String, Object> model = new HashMap<String, Object>();
		__CLR4_5_2ssme8ci2n8.R.inc(42);model.put("employees",  prepareListofBean(employeeService.listEmployeess()));
		__CLR4_5_2ssme8ci2n8.R.inc(43);return new ModelAndView("addEmployee", model);
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView fileUpload(@ModelAttribute("command")  FileBean fileBean,
			BindingResult result) {try{__CLR4_5_2ssme8ci2n8.R.inc(44);
		__CLR4_5_2ssme8ci2n8.R.inc(45);Map<String, Object> model = new HashMap<String, Object>();
		__CLR4_5_2ssme8ci2n8.R.inc(46);return new ModelAndView("fileUpload", model);
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {try{__CLR4_5_2ssme8ci2n8.R.inc(47);
		__CLR4_5_2ssme8ci2n8.R.inc(48);return new ModelAndView("index");
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute("command")  EmployeeBean employeeBean,
			BindingResult result) {try{__CLR4_5_2ssme8ci2n8.R.inc(49);
		__CLR4_5_2ssme8ci2n8.R.inc(50);employeeService.deleteEmployee(prepareModel(employeeBean));
		__CLR4_5_2ssme8ci2n8.R.inc(51);Map<String, Object> model = new HashMap<String, Object>();
		__CLR4_5_2ssme8ci2n8.R.inc(52);model.put("employee", null);
		__CLR4_5_2ssme8ci2n8.R.inc(53);model.put("employees",  prepareListofBean(employeeService.listEmployeess()));
		__CLR4_5_2ssme8ci2n8.R.inc(54);return new ModelAndView("addEmployee", model);
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@ModelAttribute("command")  EmployeeBean employeeBean,
			BindingResult result) {try{__CLR4_5_2ssme8ci2n8.R.inc(55);
		__CLR4_5_2ssme8ci2n8.R.inc(56);Map<String, Object> model = new HashMap<String, Object>();
		__CLR4_5_2ssme8ci2n8.R.inc(57);model.put("employee", prepareEmployeeBean(employeeService.getEmployee(employeeBean.getId())));
		__CLR4_5_2ssme8ci2n8.R.inc(58);model.put("employees",  prepareListofBean(employeeService.listEmployeess()));
		__CLR4_5_2ssme8ci2n8.R.inc(59);return new ModelAndView("addEmployee", model);
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	private Employee prepareModel(EmployeeBean employeeBean){try{__CLR4_5_2ssme8ci2n8.R.inc(60);
		__CLR4_5_2ssme8ci2n8.R.inc(61);Employee employee = new Employee();
		__CLR4_5_2ssme8ci2n8.R.inc(62);employee.setEmpAddress(employeeBean.getAddress());
		__CLR4_5_2ssme8ci2n8.R.inc(63);employee.setEmpAge(employeeBean.getAge());
		__CLR4_5_2ssme8ci2n8.R.inc(64);employee.setEmpName(employeeBean.getName());
		__CLR4_5_2ssme8ci2n8.R.inc(65);employee.setSalary(employeeBean.getSalary());
		__CLR4_5_2ssme8ci2n8.R.inc(66);employee.setEmpId(employeeBean.getId());
		__CLR4_5_2ssme8ci2n8.R.inc(67);employeeBean.setId(null);
		__CLR4_5_2ssme8ci2n8.R.inc(68);return employee;
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	private List<EmployeeBean> prepareListofBean(List<Employee> employees){try{__CLR4_5_2ssme8ci2n8.R.inc(69);
		__CLR4_5_2ssme8ci2n8.R.inc(70);List<EmployeeBean> beans = null;
		__CLR4_5_2ssme8ci2n8.R.inc(71);if((((employees != null && !employees.isEmpty())&&(__CLR4_5_2ssme8ci2n8.R.iget(72)!=0|true))||(__CLR4_5_2ssme8ci2n8.R.iget(73)==0&false))){{
			__CLR4_5_2ssme8ci2n8.R.inc(74);beans = new ArrayList<EmployeeBean>();
			__CLR4_5_2ssme8ci2n8.R.inc(75);EmployeeBean bean = null;
			__CLR4_5_2ssme8ci2n8.R.inc(76);for(Employee employee : employees){{
				__CLR4_5_2ssme8ci2n8.R.inc(77);bean = new EmployeeBean();
				__CLR4_5_2ssme8ci2n8.R.inc(78);bean.setName(employee.getEmpName());
				__CLR4_5_2ssme8ci2n8.R.inc(79);bean.setId(employee.getEmpId());
				__CLR4_5_2ssme8ci2n8.R.inc(80);bean.setAddress(employee.getEmpAddress());
				__CLR4_5_2ssme8ci2n8.R.inc(81);bean.setSalary(employee.getSalary());
				__CLR4_5_2ssme8ci2n8.R.inc(82);bean.setAge(employee.getEmpAge());
				__CLR4_5_2ssme8ci2n8.R.inc(83);beans.add(bean);
			}
		}}
		}__CLR4_5_2ssme8ci2n8.R.inc(84);return beans;
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
	
	private EmployeeBean prepareEmployeeBean(Employee employee){try{__CLR4_5_2ssme8ci2n8.R.inc(85);
		__CLR4_5_2ssme8ci2n8.R.inc(86);EmployeeBean bean = new EmployeeBean();
		__CLR4_5_2ssme8ci2n8.R.inc(87);bean.setAddress(employee.getEmpAddress());
		__CLR4_5_2ssme8ci2n8.R.inc(88);bean.setAge(employee.getEmpAge());
		__CLR4_5_2ssme8ci2n8.R.inc(89);bean.setName(employee.getEmpName());
		__CLR4_5_2ssme8ci2n8.R.inc(90);bean.setSalary(employee.getSalary());
		__CLR4_5_2ssme8ci2n8.R.inc(91);bean.setId(employee.getEmpId());
		__CLR4_5_2ssme8ci2n8.R.inc(92);return bean;
	}finally{__CLR4_5_2ssme8ci2n8.R.flushNeeded();}}
}
