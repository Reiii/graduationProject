<!DOCTYPE html>
<html>
<head>
	<title>个人中心</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	<script src="/js/formatDate.js"></script>
	<style>
        a{
            text-decoration: none;
        }
	</style>
</head>
<body>
<body>
	<div id="personInfo">
		<el-container>
			<el-header>
				<el-menu  :default-active="activeIndex" class="el-menu-demo" mode="horizontal" >
					<el-menu-item index="1"><a href="http://localhost:8080/mall/home">二手商城</a></el-menu-item>
					<el-menu-item index="2"><a href="http://localhost:8080/forum/home">交流论坛</a></el-menu-item>
					<el-menu-item index="3"><a href="http://localhost:8080/activity/home">同城活动</a></el-menu-item>
					<el-menu-item index="4">个人中心</el-menu-item>
				</el-menu>
			</el-header>
			<el-main>
				<el-tabs :tab-position="position">
				    <el-tab-pane label="个人信息">
				    	<el-row type="flex" justify="center">
							<el-col :span="8">
								<el-form>
									<el-form-item label="电子邮件">
										<label>{{ personalForm.email }}</label>
									</el-form-item>
									<el-form-item label="昵称">
										<label>{{ personalForm.username }}</label>
									</el-form-item>
									<el-form-item label="积分">
										<label>{{ personalForm.integral }}</label>
									</el-form-item>
									<el-form-item label="信用分">
										<label>{{ personalForm.credit }}</label>
									</el-form-item>
									<el-form-item label="注册时间">
										<label>{{ personalForm.reg_time }}</label>
									</el-form-item>
									<el-form-item>
										<el-button type="primary" @click="changePassword">修改密码</el-button>
									</el-form-item>
								</el-form>
							</el-col>
						</el-row>
				    </el-tab-pane>
				    <el-tab-pane label="我的订单">
				    	<el-table :data="myOrders" stripe>
						    <el-table-column
						      prop="order_time"
						      label="日期"
						      width="180">
						    </el-table-column>
						    <el-table-column
						      prop="title"
						      label="商品"
						      width="180">
						    </el-table-column>
						    <el-table-column
						      prop="price"
						      label="价格"
						      width="180">
						    </el-table-column>
						    <el-table-column
						    	prop="means_of_transction"
						    	label="交易方式"
						    	width="180">
						    </el-table-column>
						    <el-table-column
						    	prop="status"
						    	label="订单状态"
						    	width="180">
						    </el-table-column>
                            <el-table-column
                                    label="操作"
                                    width="100">
                                <template slot-scope="scope" >
									<el-button v-if="scope.row.status == '正在进行'" type="text" @click="cancelOrder(scope.row)">取消订单</el-button>
                                    <el-button v-if="scope.row.status == '正在进行'" type="text" @click="confirmOrder(scope.row)">确认收货</el-button>
                                </template>
                            </el-table-column>
						  </el-table>
				    </el-tab-pane>
				    <el-tab-pane label="我的玩具">
                        <el-row>
                            <el-col>
                                <el-button type="primary" @click="releaseToy">发布玩具</el-button>
                            </el-col>
                        </el-row>
                        <el-table :data="myToys" stripe>
                            <el-table-column
                                    prop="title"
                                    label="标题"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="means_of_transction"
                                    label="交易方式"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="price"
                                    label="价格"
                                    width=180>
                            </el-table-column>
                            <el-table-column
                                    prop="status"
                                    label="状态"
                                    width="180">
                            </el-table-column>
							<el-table-column
                                    label="操作"
                                    width="200">
                                <template slot-scope="scope">
                                    <el-button v-if="scope.row.status='未售出'" type="text" @click="viewToy(scope.row)">查看</el-button>
                                    <el-button v-if="scope.row.status='未售出'" type="text" @click="editToy(scope.row)">编辑</el-button>
                                    <el-button v-if="scope.row.status='未售出'" type="text" @click.native.prevent="delToy(scope.row, scope.$index, myToys)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
					</el-tab-pane>
				    <el-tab-pane label="我的帖子">我的帖子</el-tab-pane>
				 </el-tabs>
			</el-main>
		</el-container>
			
		
	</div>
</body>
<script>
	var info = new Vue({
		el: "#personInfo",
		data(){
			return {
				position: "left",
				activeIndex: "4",
				personalForm: {
					email: "123@qq.com",
					username: "yan",
					integral: "0",
					credit: "0",
					reg_time: "1993-4-3"
				},
				myOrders: "",
				myToys: ''
			}
		},
        mounted(){
            axios.get("http://localhost:8080/user/getUserInfo")
                    .then((response) => {
                this.personalForm.email = response.data.email;
            this.personalForm.username = response.data.username;
            this.personalForm.integral = response.data.integral;
            this.personalForm.credit = response.data.credit;
            this.personalForm.reg_time = new Date(parseInt(response.data.reg_time)).pattern("yyyy-MM-dd hh:mm:ss");
        })
        .catch(function(error){
                console.log("lost connection.")
                this.$alert('网络连接丢失', '错误', {
                    confirmButtonText: '确定'
                });
            });
            axios.get("http://localhost:8080/mall/myOrders")
                    .then((response) => {
                this.myOrders = response.data;
        })
        .catch(function(error) {
                console.log("lost connection.")
                this.$alert('网络连接丢失', '错误', {
                    confirmButtonText: '确定'
                });
            });
            axios.get("http://localhost:8080/mall/myToys")
                    .then((response) => {
                this.myToys = response.data;
        })
        .catch(function(error) {
                console.log("lost connection.")
                this.$alert('网络连接丢失', '错误', {
                    confirmButtonText: '确定'
                });
            });
        },
		methods: {
            releaseToy(){
                window.open("http://localhost:8080/mall/releaseToy", "_blank");
            },
			changePassword(){
                var win = this;
                axios.get("http://localhost:8080/user/setPassword/change").then((response) => {
                    this.$alert(response.data.status, "提示", {
						confirmButtonText: '确定'
					});
				}).catch(function(error) {
                    console.log("lost connection.")
                    this.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
                });
			},
			cancelOrder(row){
				axios({
					method: 'post',
					url: 'http://localhost:8080/mall/cancelOrder',
					data: {
					    order_id: row.order_id
					},
                    transformRequest: [
                        function (data) {
                            // Do whatever you want to transform the data
                            let ret = ''
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }
                    ],
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
				}).then((response) => {
				    if(response.data.status == '修改成功'){
				        row.status = '已取消';
					}
					this.$alert(response.data.status, '提示', {
					    confirmButtonText: '确定'
					});
				}).catch(function(error) {
                    console.log("lost connection.")
                    this.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
				});
			},
			confirmOrder(row){
                axios({
                    method: 'post',
                    url: 'http://localhost:8080/mall/confirmOrder',
                    data: {
                        order_id: row.order_id
                    },
                    transformRequest: [
                        function (data) {
                            // Do whatever you want to transform the data
                            let ret = ''
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }
                    ],
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then((response) => {
                    if(response.data.status == '修改成功'){
                    row.status = '已完成';
                }
                this.$alert(response.data.status, '提示', {
                    confirmButtonText: '确定'
                });
            }).catch(function(error) {
                    console.log("lost connection.")
                    this.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
                });
			},
			viewToy(row){
                window.open("http://localhost:8080/mall/item?commodity_id=" + row.commodity_id);
			},
			editToy(row){

			},
			delToy(row, index, data){
                axios({
                    method: 'post',
                    url: 'http://localhost:8080/mall/delToy',
                    data: {
                        commodity_id: row.commodity_id
                    },
                    transformRequest: [
                        function (data) {
                            // Do whatever you want to transform the data
                            let ret = ''
                            for (let it in data) {
                                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                            }
                            return ret
                        }
                    ],
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then((response) => {
                    if(response.data.status == '删除成功'){
                    data.splice(index, 1);
                }
                this.$alert(response.data.status, '提示', {
                    confirmButtonText: '确定'
                });
            }).catch(function(error) {
                    console.log("lost connection.")
                    this.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
                });
			}
		}
	})
</script>
</html>