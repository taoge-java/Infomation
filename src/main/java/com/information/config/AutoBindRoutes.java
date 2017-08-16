package com.information.config;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.information.annotation.ControllerRoute;
import com.information.utils.PackageUtil;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
/**
 * 自动路由映射
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月22日上午8:40:03
 */
public class AutoBindRoutes extends Routes {

    private boolean autoScan = true;

    private String packageName;
    
    private List<String> packageList=new ArrayList<String>();
    
	private List<Class<? extends Controller>> excludeClasses = Lists.newArrayList();

    protected final Log logger = Log.getLog(getClass());

    private String suffix = "Controller";

    public AutoBindRoutes(){
    	
    }
    
    public AutoBindRoutes setPackageName(String... packageName){
    	if(packageName==null||packageName.length==0)
    		throw new RuntimeException("packageName can not be null");
    	for(String pack:packageName){
    		packageList.add(pack);
    	}
    	return this;
    }
    
    public AutoBindRoutes setPackageName(String packageName){
    	this.packageName=packageName;
    	return this;
    }
    
    public AutoBindRoutes(String suffix){
    	this.setSuffix(suffix);
    }
    
    public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public AutoBindRoutes autoScan(boolean autoScan) {
        this.autoScan = autoScan;
        return this;
    }

    @SuppressWarnings("unchecked")
	public AutoBindRoutes addExcludeClasses(Class<? extends Controller>... clazzes) {
        if (clazzes != null) {
            for (Class<? extends Controller> clazz : clazzes) {
                excludeClasses.add(clazz);
            }
        }
        return this;
    }

    public AutoBindRoutes addExcludeClasses(List<Class<? extends Controller>> clazzes) {
        excludeClasses.addAll(clazzes);
        return this;
    }


    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void config() {
        List<Class<? extends Controller>> controllerClasses=PackageUtil.scanPackage(packageName);
        ControllerRoute controllerRoute = null;
        for (Class controller : controllerClasses) {
            if (excludeClasses.contains(controller)) {
                continue;
            }
            controllerRoute = (ControllerRoute) controller.getAnnotation(ControllerRoute.class);
            if(autoScan){
            	if(controllerRoute==null){
            		this.add(controllerKey(controller), controller);
                    logger.debug("routes.add(" + controllerKey(controller) + ", " + controller.getName() + ")");
            	}else{
            		this.add(controllerRoute.url(), controller);
                    logger.debug("routes.add(" + controllerRoute.url() + ", " + controller.getName() + ")");
            	}
            }
        }
    }

    private String controllerKey(Class<Controller> clazz) {
        Preconditions.checkArgument(clazz.getSimpleName().endsWith(suffix),
                clazz.getName()+" is not annotated with @ControllerRoute and not end with " + suffix);
        String controllerKey = "/" + StrKit.firstCharToLowerCase(clazz.getSimpleName());
        controllerKey = controllerKey.substring(0, controllerKey.indexOf(suffix));
        return controllerKey;
    }

    public AutoBindRoutes suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }
}
