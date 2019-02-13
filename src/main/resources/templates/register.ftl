<!DOCTYPE html>
<html>
<head>
	<title>register</title>
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
	<div id="register">
		<el-row type="flex" justify="center">
			<el-col :span="8">
				<el-form :model="regForm" :rules="regFormRules" ref="regForm" action="/doReg" method="post" status-icon>
					<el-form-item label="电子邮件" prop="email">
						<el-input v-model="regForm.email"></el-input>
					</el-form-item>
					<el-form-item label="昵称" prop="username">
						<el-input v-model="regForm.username"></el-input>
					</el-form-item>
					<el-form-item label="设置密码" prop="password">
						<el-input type="password" v-model="regForm.password"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="Rpassword">
						<el-input type="password" v-model="regForm.Rpassword"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="submitForm('regForm')">注册</el-button>
					</el-form-item>
				</el-form>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var reg = new Vue({
		el: '#register',
		data() {
			var validateEmail = (rule, value, callback) => {
			var re = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;				
			if(re.test(value)){
				axios.get('http://localhost:8080/user/checkEmail', {
					params: {
						email: value
					}
				}).then((response)=> {
					var msg = response.data.status;
					if(msg == '邮箱可用'){
						callback();
					}else{
						callback(new Error('该邮箱已注册'));
					}
				}).catch(function(error){
					callback(new Error('网络连接丢失'));
				})
			}else{
				callback(new Error('请输入正确邮箱'));
			}
				
			}
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
	            } else if (value !== this.regForm.password) {
	                callback(new Error('两次输入密码不一致!'));
	            } else {
	                callback();
	            }
	        };
	        return {
	        	regForm: {
	        		email: '',
	        		username: '',
	        		password: '',
	        		Rpassword: ''
	        	},
	        	regFormRules: {
	        		email: [
	        			{required: true, validator: validateEmail, trigger: 'blur'}
	        		],
	        		username: [
	        			{ required: true, message: '请输入昵称', trigger: 'blur'},
	        			{ min: 3, max: 10, message: '昵称长度为3～10个字符', trigger: 'change'}
	        		],
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
			submitForm(formName) {
			    var win = this;
			    var md5 = hex_MD5;
	            this.$refs[formName].validate((valid) => {
	                if (!valid) {
	                    this.$alert('提交失败', '错误', {
	                    	confirmButtonText: '确定'
	                    });
	                    console.log('error submit!!');
	                    return false;
	                }else{
	                    axios({
							url: 'http://localhost:8080/user/doReg',
							method: 'post',
							data: {
								email: this.regForm.email,
								username: this.regForm.username,
								password: md5(this.regForm.password)
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
								    win.$alert(response.data.status, '提示', {
								        confirmButtonText: '确定'
									}).then(() => {
									    if(response.data.status == '注册失败'){
											location.href = 'http://localhost:8080/user/register';
										}else{
											location.href = 'http://localhost:8080/user/login';
										}
									})
								})
	                    console.log(JSON.stringify(this.regForm));
	                	return true;
	                }
	            });
        	}
		}
	})
</script>
</html>