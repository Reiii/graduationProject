<!DOCTYPE html>
<html>
<head>
	<title>论坛主页</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	 <script src="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.js"></script>
	 <script src="/js/formatDate.js"></script>
	 <style>
	 	.pic {
			width:170px;
			height:200px;
			float: left
		}
		img {
			width: 100%;
			height: 60%;
		}
		li {
			list-style: none
		}
		#area_list {
			width: 600px;
			height: 200px;
			margin: auto;
		}
		.area {
			width: 600px;
			height: 200px;
		}
		a {
			display: inline-block;
			color: black;
		}
	 </style>
</head>
<body>
	<div id="forum">
		<el-menu  :default-active="activeIndex" class="el-menu-demo" mode="horizontal" >
			<el-menu-item index="1"><a href="http://localhost:8080/mall/home">二手商城</a></el-menu-item>
        	<el-menu-item index="2"><a href="http://localhost:8080/forum/home">交流论坛</a></el-menu-item>
        	<el-menu-item index="3"><a href="http://localhost:8080/activity/home">同城活动</a></el-menu-item>
        	<el-menu-item index="4"><a href="http://localhost:8080/user/userinfo">个人中心</a></el-menu-item>
		</el-menu>
		<br>
		<div id="area_list">
            <div class="area" v-for=" a in areas">
                <div class="pic">
                    <img src="http://bbs.actoys.net/images/wind/41.png">
                </div>
                <ul>
                    <li><a @click="handleDetail(a.subject_id)">{{ a.name }}</a></li>
                    <li>主题: {{ a.theme_num }}</li>
                    <li>帖子: {{ a.post_num }}</li>
                    <li>最后发帖: {{ new Date(parseInt(a.last_post_time)).pattern("yyyy-MM-dd hh:mm:ss") }}</li>
                </ul>
            </div>
		</div>

	</div>
</body>
<script>
	var forum = new Vue({
		el: '#forum',
		data: {
			activeIndex: '2',
			areas: ''
		},
		methods: {
			handleDetail(subject_id){
				window.open("http://localhost:8080/forum/getSubjectArea?subject_id" + subject_id, "_blank");
			}
		},
		mounted(){
			var win = this;
			axios.get("http://localhost:8080/forum/getAllSubjectArea")
				.then((response) => {
					this.areas = response.data;
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