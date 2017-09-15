package com.information.config;

import java.util.List;

import javax.sql.DataSource;


import com.google.common.collect.Lists;
import com.information.utils.StrKit;
import com.jfinal.ext.kit.ClassSearcher;
import com.jfinal.ext.plugin.tablebind.INameStyle;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.IDataSourceProvider;
import com.jfinal.plugin.activerecord.Model;
/**
 * 表自动扫描
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年5月25日 上午9:23:44
 */
public class AutoTableBindPlugin extends ActiveRecordPlugin{

	private static final Log log = Log.getLog(AutoTableBindPlugin.class);
    @SuppressWarnings("rawtypes")
	private List<Class<? extends Model>> excludeClasses = Lists.newArrayList();
    private List<String> packageList = Lists.newArrayList(); 
    private List<String> includeJars = Lists.newArrayList();
    private boolean autoScan = true;
    private boolean includeAllJarsInLib;
    private INameStyle nameStyle;
    
    public AutoTableBindPlugin(DataSource dataSource) {
        this(dataSource, SimpleNameStyles.DEFAULT);
        
    }
    public AutoTableBindPlugin(DataSource dataSource, INameStyle nameStyle) {
        super(dataSource);
        this.nameStyle = nameStyle;
    }
    
    public AutoTableBindPlugin(String confitName,DataSource dataSource, INameStyle nameStyle) {
        super(confitName,dataSource);
        this.nameStyle = nameStyle;
    }
    public AutoTableBindPlugin(IDataSourceProvider dataSourceProvider,String... packages) {
        this(dataSourceProvider, SimpleNameStyles.DEFAULT);
    }
    
    public AutoTableBindPlugin(IDataSourceProvider dataSourceProvider, INameStyle nameStyle) {
        super(dataSourceProvider);
        this.nameStyle = nameStyle;
    }
    
    public AutoTableBindPlugin(String configName,IDataSourceProvider dataSourceProvider) {
        super(configName,dataSourceProvider);
    }
    
    public AutoTableBindPlugin(String configName,IDataSourceProvider dataSourceProvider, INameStyle nameStyle) {
        super(configName,dataSourceProvider);
        this.nameStyle = nameStyle;
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    public AutoTableBindPlugin addExcludeClasses(Class<? extends Model>... clazzes) {
        for (Class<? extends Model> clazz : clazzes) {
            excludeClasses.add(clazz);
        }
        return this;
    }
    
    @SuppressWarnings("rawtypes")
	public AutoTableBindPlugin addExcludeClasses(List<Class<? extends Model>> clazzes) {
        if (clazzes != null) {
            excludeClasses.addAll(clazzes);
        }
        return this;
    }
    public AutoTableBindPlugin addJars(List<String> jars) {
        if (jars != null) {
            includeJars.addAll(jars);
        }
        return this;
    }
    public AutoTableBindPlugin addJars(String... jars) {
        if (jars != null) {
            for (String jar : jars) {
                includeJars.add(jar);
            }
        }
        return this;
    }
    
    public AutoTableBindPlugin scanPackages(String...packages){
       if(packages != null){
          for (String pack : packages) {
             packageList.add(pack);
          }
       }
       return this;
    }
    
    @Override
    @SuppressWarnings({"rawtypes"})
    public boolean start() {
        List<Class<? extends Model>> modelClasses = ClassSearcher.of(Model.class).injars(includeJars).includeAllJarsInLib(includeAllJarsInLib).search();
        TableBind tb = null;
        for (Class modelClass : modelClasses) {
            //指定了扫描包的情况下根据包名来进行加载映射关系,否则就全部加载
            if(!packageList.isEmpty()){
                for (String pack : packageList) {
                   if(modelClass.getName().startsWith(pack)){
                      scanModel(tb,modelClass);
                   }
                }
            }else{
               scanModel(tb,modelClass);
            }
        }
        return super.start();
    }
    
    
    @Override
    public boolean stop() {
        return super.stop();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes"})
    private void scanModel(TableBind tb,Class clazz){
	    if (!excludeClasses.contains(clazz)) {
	         tb = (TableBind) clazz.getAnnotation(TableBind.class);
	         String tableName;
	         if (tb == null) {
	             if (autoScan) {
	              tableName = nameStyle.name(clazz.getSimpleName());
	              this.addMapping(tableName, clazz);
	              log.debug("addMapping(" + tableName + ", " + clazz.getName() + ")");
	             }
	         } else {
	             tableName = tb.tableName();
	             if (StrKit.isNotEmpoty(tb.pkName())) {
	                 this.addMapping(tableName, tb.pkName(), clazz);
	                 log.debug("addMapping(" + tableName + ", " + tb.pkName() + "," + clazz.getName() + ")");
	             } else {
	                 this.addMapping(tableName, clazz);
	                 log.debug("addMapping(" + tableName + ", " + clazz.getName() + ")");
	             }
	         }
	     }
    }
    
    public AutoTableBindPlugin autoScan(boolean autoScan) {
        this.autoScan = autoScan;
        return this;
    }
    
    
    public AutoTableBindPlugin includeAllJarsInLib(boolean includeAllJarsInLib) {
        this.includeAllJarsInLib = includeAllJarsInLib;
        return this;
    }
}
