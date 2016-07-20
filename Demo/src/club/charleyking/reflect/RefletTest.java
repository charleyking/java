package club.charleyking.reflect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert.*;
import org.junit.Test;


public class RefletTest {

	@Test
	public void testSpeak() {
		Person charles = new Person();
		charles.speak();
	}
	
	@Test
	public void testConstructor() throws Throwable {
		Class clazz = Class.forName("club.charleyking.reflect.Person");
		Constructor a = clazz.getConstructor(null);
		Person chales = (Person) a.newInstance(null);
	}
	
	@Test
	public void testConstructor2() throws Throwable {
		Class clazz = Class.forName("club.charleyking.reflect.Person");
		Constructor b = clazz.getConstructor(String.class, int.class);
		Person tom = (Person) b.newInstance("tom", 22);
	}
	
	@Test
	public void testMethod1() throws Throwable {
		Person p = new Person();
		Class clazz = Class.forName("club.charleyking.reflect.Person");
		Method method1 = clazz.getMethod("speak", null);
		method1.invoke(p, null);
	}
	
	@Test
	public void testMethod2() throws Throwable {
		Person p = new Person();
		Class clazz = Class.forName("club.charleyking.reflect.Person");
		Method method2 = clazz.getMethod("speak", String.class, int.class);
		method2.invoke(p, "tom", 22);
	}
	
	@Test
	public void testField1() throws Throwable {
		Person p = new Person("tom", 22);
		Class clazz = Class.forName("club.charleyking.reflect.Person");
		Field f = clazz.getField("name");
		f.set(p, "charleyking");
		String name = (String)f.get(p);
		System.out.println(name);
	}
}
