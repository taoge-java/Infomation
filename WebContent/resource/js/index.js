/***
 * 系统时间
 */
function showTime(){
				var dat=new Date();
				var year=dat.getFullYear();
				var month=dat.getMonth()+1;
				var day=dat.getDate();
				var week=dat.getDay();
				switch(week){
					case 0:
					   week="星期天";
					   break;
					case 1:
						week="星期一";
						break;
					case 2:
					   week="星期二";
					   break;
					case 3:
						week="星期三";
						break;
					case 4:
					   week="星期四";
					   break;
					case 5:
						week="星期五";
						break;
					case 6:
					   week="星期六";
					   break;
				}
				var hours=dat.getHours();
				var minue=dat.getMinutes();
				var second=dat.getSeconds();
				if(hours>=0&&hours<10){
					hours="0"+hours;
				}
				if(minue>=0&&minue<10){
					minue="0"+minue;
				}
				if(second>=0&&second<10){
					second="0"+second;
				}
				var time=year+"-"+month+"-"+day+"  "+week+" "+hours+":"+minue+":"+second;
				document.getElementById("time").innerHTML=time;
			}
			setInterval("showTime()",1000);