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
    <script src="/js/md5.js"></script>
	<title>login</title>
<body>
	<div id="login">
		<el-row type="flex" justify="center">
			<el-col :span="8">
				<el-form :model="loginForm" :rules="loginFormRules" ref="loginForm">
					<el-form-item label="电子邮件" prop="email">
						<el-input v-model="loginForm.email"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="password">
						<el-input type="password" v-model="loginForm.password"></el-input>
                        <a href="http://localhost:8080/user/setPassword/forget">忘记密码</a>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
						<el-button onClick="location.href = 'http://localhost:8080/user/register'">注册</el-button>
					</el-form-item>
				</el-form>
			</el-col>
		</el-row>
	</div>
</body>
<script>
    var login = new Vue({
        el: '#login',
        data() {
            return{
                loginForm: {
                    email: '',
                    password: ''
                },
                loginFormRules: {
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur'},
                        { type: 'email', message: '请输入正确邮箱', trigger: 'change'}
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                var win = this;
                var md5 = hex_MD5;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios({
                            url: 'http://localhost:8080/user/doLogin',
                            method: "post",
                            data: {
                                email: this.loginForm.email,
                                password: md5(this.loginForm.password)
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
                            if(response.data.status == '登录成功'){
                                location.href = 'http://localhost:8080/mall/home'
                            }else{
                                win.$alert(response.data.status, '错误', {
                                confirmButtonText: '确定'
                            });
                        }
                    }).catch(function(error){
                            console.log('submit error');
                            this.$alert('网络连接丢失', '错误', {
                                confirmButtonText: '确定'
                            });
                        })

                    }
                });
            }
        }
    })
</script>
</html>