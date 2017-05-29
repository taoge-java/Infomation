$(function(){
	$("#btn_sub").click(function(){
		var number=$("input[name='student.number']").val();
		if($("input[name='student.name']").val()==""){
			$("#error").html("姓名不能为空!");
			return false;
		}
		if(number==""){
			$("#error").html("学生学号不能为空!");
			return false;
		}
		if(number.length>10){
			$("#error").html("学生学号长度不能大于10!");
			return false;
		}
		if($("input[name='student.address']").val()==""){
			$("#error").html("学生地址不能为空!");
			return false;
		}
		if($("input[name='student.tel']").val()==""){
			$("#error").html("学生电话不能为空!");
			return false;
		}
		if($("input[name='student.join_school_time']").val()==""){
			$("#error").html("学生入学时间不能为空!");
			return false;
		}
		
		var sex=$('input:radio[name="student.sex"]:checked').attr("value");
		if(sex==null){
			$("#error").html("请选择学生性别!");
			return false;
		}
		/**
		 * 手机号码格式校验
		 */
		var tel=$("input[name='student.tel']").val();
		var telExp=/^13\d{9}|15\d{9}|18\d{9}$/;
		if(!telExp.test(tel)){
			$("#error").html("电话号码格式错误!");
			return false;
		}
		/**
		 * 日期格式校验
		 */
		var timeExp=/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
		var joinTime=$("input[name='student.join_school_time']").val();
		if(!timeExp.test(joinTime)){
			$("#error").html("日期格式错误!");
			return false;
		}
		$.ajax({
			url:"$BASE_PATH/Student/student/Ajaxadd",
			type:"post",
			dataType:"json",
			data:{
				"stu_number":$("input[name='student.number']").val(),
				"stu_jointime":$("input[name='student.join_school_time']").val(),
				"stu_tel":$("input[name='student.tel']").val(),
			},
			success:function(data){
				if(data.code!=-1){
					layer.alert('内容', {
	   					  icon: 1,
	   					  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
	   					})
					$("#student_form").submit();
					
				}else{
					alert(data.message);
				}
			},
			error:function(){
				alert("提交失败!");
			}
		});
	});
})

