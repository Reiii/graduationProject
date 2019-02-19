<!DOCTYPE html>
<html>
<head>
	<title>修改密码</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
    <script src="https://cdn.bootcss.com/qs/6.5.1/qs.min.js"></script>
    <script src="/js/md5.js"></script>
</head>
<body>
	<div id="change">
		<el-row type="flex" justify="center">
			<el-col :span="8">
				<el-form :model="passwordForm" :rules="passwordFormRules" ref="passwordForm" status-icon>
					<el-form-item label="设置新密码" prop="password">
						<el-input type="password" v-model="passwordForm.password"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="Rpassword">
						<el-input type="password" v-model="passwordForm.Rpassword"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="submitForm('passwordForm')">确定</el-button>
					</el-form-item>
				</el-form>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var change = new Vue({
		el: "#change",
		data(){
			var validatePassword = (rule, value, callback) => {
				if (!value) {
					callback(new Error('请输入密码'));
				} else if (value.toString().length < 6 || value.toString().length > 18) {
					callback(new Error('密码长度为6 - 18个字符'))
				} else {
					callback();
				}
			};
			var validateRpassword = (rule, value, callback) => {
	            if (value === '') {
	                callback(new Error('请再次输入密码'));
	            } else if (value !== this.passwordForm.password) {
	                callback(new Error('两次输入密码不一致!'));
	            } else {
	                callback();
	            }
	        };
			return {
				passwordForm: {
					password: '',
					Rpassword: ''
				},
				passwordFormRules: {
					password: [
						{ required: true, validator: validatePassword, trigger: 'blur'}
					],
					Rpassword: [
						{ required: true, validator: validateRpassword, trigger: 'blur'}
					]
				}
			}
		},
		methods: {
			submitForm(){
				var win = this;
			    var md5 = hex_MD5;
	            this.$refs.passwordForm.validate((valid) => {
	                if (!valid) {
	                    return false;
	                }else{
	                    axios({
							url: 'http://localhost:8080/user/setPassword/update',
							method: 'post',
							data: {
								password: md5(this.passwordForm.password)
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
						}).then(function(response){
								win.$alert(response.data.msg, '提示', {
							        confirmButtonText: '确定'
								}).then(() => {
								    if(response.data.msg == '修改成功'){
										location.href = 'http://localhost:8080/user/login';
									}
								})
							})
	                    console.log(JSON.stringify(this.passwordForm));
	                	return true;
	                }
	            });
			}
		}
	})
</script>
</html>