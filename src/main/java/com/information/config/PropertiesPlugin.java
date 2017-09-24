/**
 * 
 */
package com.information.config;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.information.annotation.ConfigurationProperties;
import com.information.utils.ClassScanner;
import com.information.utils.StrKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.IPlugin;


/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月9日上午11:47:11
 */
public class PropertiesPlugin implements IPlugin{

	private Prop prop = null;
	
	private ApplicationContext applicationContext;
	
	PropertiesPlugin(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	@SuppressWarnings({ "rawtypes","unchecked" })
	@Override
	public boolean start() {
		List<Class> cla = ClassScanner.scanClassByAnnotation(ConfigurationProperties.class, false);
		for(Class c : cla){
			try {
				ConfigurationProperties configurationProperties = (ConfigurationProperties) c.getAnnotation(ConfigurationProperties.class);
				initProperties(configurationProperties);
				Object object = applicationContext.getBean(StrKit.toLowerCaseFirst(c.getSimpleName()));
				Method[] methods = c.getMethods();
				for(Method method : methods){
					if(method.getName().startsWith("set")){
						String key = StrKit.toLowerCaseFirst(method.getName().substring(3));
						method.invoke(object, convert(method.getParameterTypes()[0], prop.get(key)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
		return true;
	}

	/**
	 * @param configurationProperties 
	 * 
	 */
	private void initProperties(ConfigurationProperties configurationProperties) {
        if(StrKit.isEmpoty(configurationProperties.prefix())){
			prop = PropKit.use("config.properties");
		}else{
			prop = PropKit.use(configurationProperties.prefix());
		}
	}

	/**
     * 数据转化
     * @param type
     * @param s
     * @return
     */
    private static final Object convert(Class<?> type, String s) {

        if (type == String.class) {
            return s;
        }


        // mysql type: int, integer, tinyint(n) n > 1, smallint, mediumint
        if (type == Integer.class || type == int.class) {
            return Integer.parseInt(s);
        }

        // mysql type: bigint
        if (type == Long.class || type == long.class) {
            return Long.parseLong(s);
        }


        // mysql type: real, double
        if (type == Double.class || type == double.class) {
            return Double.parseDouble(s);
        }

        // mysql type: float
        if (type == Float.class || type == float.class) {
            return Float.parseFloat(s);
        }

        // mysql type: bit, tinyint(1)
        if (type == Boolean.class || type == boolean.class) {
            String value = s.toLowerCase();
            if ("1".equals(value) || "true".equals(value)) {
                return Boolean.TRUE;
            } else if ("0".equals(value) || "false".equals(value)) {
                return Boolean.FALSE;
            } else {
                throw new RuntimeException("Can not parse to boolean type of value: " + s);
            }
        }

        // mysql type: decimal, numeric
        if (type == java.math.BigDecimal.class) {
            return new java.math.BigDecimal(s);
        }

        // mysql type: unsigned bigint
        if (type == java.math.BigInteger.class) {
            return new java.math.BigInteger(s);
        }

        // mysql type: binary, varbinary, tinyblob, blob, mediumblob, longblob. I have not finished the test.
        if (type == byte[].class) {
            return s.getBytes();
        }

        throw new RuntimeException(type.getName() + " can not be converted, please use other type in your config class!");
    }
    
	@Override
	public boolean stop() {
		return true;
	}
}
