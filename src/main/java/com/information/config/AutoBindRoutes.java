package com.information.config;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.information.annotation.ControllerRoute;
import com.information.utils.PackageUtil;
import com.jfinal.config.Routes;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;

public class AutoBindRoutes extends Routes {

    private boolean autoScan = true;

    private String packageName;
    
	private List<Class<? extends Controller>> excludeClasses = Lists.newArrayList();

    protected final Log logger = Log.getLog(getClass());

    private String suffix = "Controller";

    public AutoBindRoutes(){
    	
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
        List<Class<? extends Controller>> controllerClasses=PackageUtil.scanPackage(packageName,true,"WEB-INF/classes/");
        ControllerRoute requestMapping = null;
        for (Class controller : controllerClasses) {
            if (excludeClasses.contains(controller)) {
                continue;
            }
            requestMapping = (ControllerRoute) controller.getAnnotation(ControllerRoute.class);
            if(autoScan){
            	if(requestMapping==null){
            		this.add(controllerKey(controller), controller);
                    logger.debug("routes.add(" + controllerKey(controller) + ", " + controller.getName() + ")");
            	}else{
            		 this.add(requestMapping.url(), controller);
                     logger.debug("routes.add(" + requestMapping.url() + ", " + controller.getName() + ")");
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
