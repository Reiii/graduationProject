<!DOCTYPE html>
<html>
<head>
	<title>同城活动</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	<script src="/js/formatDate.js"></script>
	<style>
		.pic {
			width:170px;
			height:200px;
			float: left
		}
		img {
			
			height: 100%;
		}
		li {
			list-style: none
		}
		#activity_list {
			width: 750px;
			height: 200px;
			margin: auto;
		}
		.item {
			width: 750px;
			height: 200px;
		}
		a {
			display: inline-block;
			text-decoration: none;
			color: blue;
		}
		
	</style>
</head>
<body>
	<div id="activity">
		<el-menu  :default-active="activeIndex" class="el-menu-demo" mode="horizontal" >
			<el-menu-item index="1"><a href="http://localhost:8080/mall/home">二手商城</a></el-menu-item>
        	<el-menu-item index="2"><a href="http://localhost:8080/forum/home">交流论坛</a></el-menu-item>
        	<el-menu-item index="3"><a href="http://localhost:8080/activity/home">同城活动</a></el-menu-item>
        	<el-menu-item index="4"><a href="http://localhost:8080/user/userinfo">个人中心</a></el-menu-item>
		</el-menu>
		<br>
		<el-container>
			<el-header>
				<el-row type="flex" justify="center">
					<el-col :span="12">	
						<el-input placeholder="请输入关键字.." v-model="keyword">
							<el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
						</el-input>
					</el-col>
				</el-row>
			</el-header>
			<el-main>
				<div id="activity_list">
					
							<div class="item" v-for=" i in items">
								<div class="pic" style="overflow: hidden">
									<img :src="i.cover">
								</div>
								<div class="info">
                                    <ul>
                                        <li><a @click="handleDetail(i.activity_id)">{{ i.title}}</a></li>
                                        <li>开始时间: {{ new Date(parseInt(i.start_time)).pattern("yyyy-MM-dd hh:mm:ss") }}</li>
										<li>结束时间: {{ new Date(parseInt(i.end_time)).pattern("yyyy-MM-dd hh:mm:ss") }}</li>
                                        <li>地址:{{ i.province }} {{ i.city }} {{ i.address }}</li>
                                        <li>费用: {{ i.cost }}</li>
                                    </ul>
								</div>

							</div>
						
				</div>
			</el-main>
			 <el-footer>
            <el-row type="flex" justify="center">
                <el-pagination layout="prev, pager, next" :page-size="20" v-bind:current-page="currentPage" v-bind:page-count="endPage" @current-change="handleCurrentChange"></el-pagination>
            </el-row>
        </el-footer>
		</el-container>



	</div>
</body>
<script>
	var activity = new Vue({
		el: "#activity",
		data: {
			activeIndex: "3",
			items: '',
			keyword: '',
			currentPage: '',
			endPage: ''
		},
		methods: {
			handleSearch(){
				var win = this
				if(this.keyword != ''){
					axios.get("http://localhost:8080/activity/getActivities", {
						params: {
							keyword: this.keyword
						}
					}).then((response) => {
						this.items = response.data.data;
						this.currentPage = response.data.currentPage;
						this.endPage = response.data.endPage;
					}).catch(function(error){
						console.log("lost connection.")
                    	win.$alert('网络连接丢失', '错误', {
                        	confirmButtonText: '确定'
                    	});
					});
				}
			},
			handleDetail(id){
				window.open("http://localhost:8080/activity/getActivity?activity_id=" + id, "blank");
			},
			handlePage(page){
				var win = this;
                var param;
                if(this.keyword != ''){
					param = {
					    keyword: this.keyword,
						page: page
					}
				}else{
                    param = {
                        page: page
                    }
				}
                axios.get("http://localhost:8080/activity/getActivities",{
                    params: param
                }).then((response) => {
                    this.items = response.data.data;
                this.currentPage = response.data.currentPage;
                this.endPage = response.data.endPage;
            }).catch(function(error){
                    console.log("lost connection.")
                    win.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
                });
			},
			handleCurrentChange(val){
                this.handlePage(val);
			}
		},
		mounted(){
			var win = this;
			axios.get("http://localhost:8080/activity/getAllActivities").then((response) => {
						this.items = response.data.data;
						this.currentPage = response.data.currentPage;
						this.endPage = response.data.endPage;
					}).catch(function(error){
						console.log("lost connection.")
                    	win.$alert('网络连接丢失', '错误', {
                        	confirmButtonText: '确定'
                    	});
					});
		}
	})
</script>
</html>