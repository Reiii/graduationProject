<!DOCTYPE html>
<html>
<head>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	<title>忘记密码</title>
	<style>
		.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
	</style>
</head>
<body>
	<div id="forget">
		<el-row type="flex" justify="center">
			<el-col :span="10">
				<el-container>
					<el-header>
						<el-steps :active="step">
						    <el-step title="步骤 1" description="输入邮箱"></el-step>
						  	<el-step title="步骤 2" description="发送验证邮件"></el-step>
						</el-steps>
					</el-header>
					<el-main>
						<el-row type="flex" justify="center">
							<el-col :span="10">
								<div class="step1" v-bind:style="step1Style">
									<el-row>
										<el-col>
											<label>电子邮箱</label>
											<el-input v-model="email" placeholder="请输入邮箱">
											</el-input>
										</el-col>
									</el-row>
									<el-row>
										<el-col>
											<el-button type="primary" @click="vertifyEmail">确认</el-button>
										</el-col>
									</el-row>
								</div>
								<div class="step2" v-bind:style="step2Style">
									<label>已发送验证邮件到邮箱，请到邮箱确认</label>
								</div>
							</el-col>
						</el-row>
					</el-main>
				</el-container>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var forget = new Vue({
		el: "#forget",
		data:{
			step: 1,
			email: '',
			step1Style: {
				display: 'block'
			},
			step2Style: {
				display: 'none'
			}
		},
		methods: {
			vertifyEmail(){
			    var win = this;
				axios.get("http://localhost:8080/user/setPassword/setEmail",{
                    params:{
                        email: this.email
                    }
				}
						).then((response) => {
						var msg = response.data.status;
						if(msg == '已发送邮件'){
							this.step = 2;
							this.step1Style.display = "none";
							this.step2Style.display = "block";
						}else{
							win.$alert(msg, "错误", {
								confirmButtonText: '确定'
							});
						}
					}).catch(function(error){
						win.$alert("无法连接网络", "错误", {
							confirmButtonText: '确定'
						});
					})
				
			}
		}
	})
</script>
</html>