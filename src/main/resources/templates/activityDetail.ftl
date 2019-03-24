<!DOCTYPE html>
<html>
<head>
	<title>活动详情</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	<script src="/js/formatDate.js"></script>
	<style>
		.item {
			width: 900px;
			height: 300px;
		}
		.pic {
			height: 250px;
			width: 200px;
			float: left;
		}
		.info {
			margin-left: 30px;
		}
		li {
			list-style: none
		}
	</style>
</head>
<body>
	<div id="detail">
		<el-row type="flex" justify="center">
			<el-col :span="14">
				<el-container>
					<el-header>
						<el-row type="flex" justify="center">
							<el-col :span="4">
								<font size="6">活动详情</font>
							</el-col>
						</el-row>
					</el-header>
					<el-main>
						<div class="item">
								<div class="pic">
									<img :src="activity.cover">
								</div>
								<div class="info">
									<ul>
										<li><font size="5">{{ activity.title }}</font></li>
										<li>开始时间: {{ new Date(parseInt(activity.start_time)).pattern("yyyy-MM-dd hh:mm:ss") }}</li>
										<li>结束时间: {{ new Date(parseInt(activity.end_time)).pattern("yyyy-MM-dd hh:mm:ss") }}</li>
										<li>城市:{{ activity.province }} {{ activity.city }}</li>
										<li>详细地址: {{ activity.address }}</li>
										<li>费用: {{ activity.cost }}</li>
									</ul>
								</div>
							</div>
					</el-main>
					<el-footer>
						<div class="detail" v-html="activity.detail">

						</div>
					</el-footer>
				</el-container>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var detail = new Vue({
		el: "#detail",
		data: {
			activity: ''
		},
		mounted(){
			var win = this;
			axios.get("http://localhost:8080/activity/getActivityDetail")
				.then((response) => {
					this.activity = response.data
				}).catch(function(error){
					console.log("lost connection.")
                    	win.$alert('网络连接丢失', '错误', {
                        	confirmButtonText: '确定'
                    	});
				})
		}
	})
</script>
</html>