package com.njwangbo.oa.factory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 对象工厂
 * 
 * @author soft01
 * 
 */
public class ApplicationContext { // key bean元素的id值 value:id对应的class类的对象
	private static Map<String, Object> objMap = new HashMap<String, Object>();

	static {
		// 加载bean.xml，解析xml
		// dom4j+xpath
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(ApplicationContext.class
					.getClassLoader().getResourceAsStream("bean.xml"));
			// 解析
			parse(document);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 解析
	 * 
	 * @param document
	 */
	@SuppressWarnings("unchecked")
	private static void parse(Document document) throws Exception {
		// 所有的bean节点
		List<Element> beanElementList = document.selectNodes("/beans/bean");
		Iterator<Element> beanElementIt = beanElementList.iterator();
		while (beanElementIt.hasNext()) {
			// 代表每一个bean元素
			Element beanElement = beanElementIt.next();

			String beanId = beanElement.attributeValue("id");
			String className = beanElement.attributeValue("class");
			// System.out.println(beanId + "---" + className);
			Class clazz = Class.forName(className);

			Object obj = clazz.newInstance();

			objMap.put(beanId, obj);
			// bean元素下的property子标签
			List<Element> properyList = beanElement.selectNodes("./property");
			// System.out.println(properyList.size());
			// 循环子标签
			Iterator<Element> properyIt = properyList.iterator();
			while (properyIt.hasNext()) {
				// 具体的property标签
				Element properyElement = properyIt.next();

				String propName = properyElement.attributeValue("name");// userDao
																		// roleDao

				String propRef = properyElement.attributeValue("ref");// 引用的是其他bean的id值
				// System.out.println(propName + "---" + propRef);

				// 动态的调用set方法 如 setUserDao setRoleDao
				String methodName = "set"
						+ propName.substring(0, 1).toUpperCase()
						+ propName.substring(1);
				// setUserDao(UserDao userDao)
				// objMap.get(propRef).getClass();获得引用的那个对象的类型
				// com.njwb.dao.impl.UserDaoImpl
				// 需要 com.njwb.dao.UserDao

				Method method = clazz.getDeclaredMethod(methodName, objMap.get(
						propRef).getClass().getInterfaces());
				method.invoke(obj, objMap.get(propRef));
			}

		}

	}

	public static Object getBean(String id) {
		return objMap.get(id);
	}
	
	
}
