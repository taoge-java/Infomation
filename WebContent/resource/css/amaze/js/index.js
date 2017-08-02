/**
 * 构造方法
 */
//function  Person(name,sex){
//	this.name=name;
//	this.sex=sex;
//}
var person=function(){
	this.name="sds";
}

var man;
man.prototype=new person();


alert(man.name);

