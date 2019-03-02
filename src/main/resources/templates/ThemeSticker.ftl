<!DOCTYPE html>
<html>
<head>
	<title>主题贴</title>
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
		
		.content {
			background-color: #FFFBFB;
			width: 75%;
			
		}
		.post {
			border: 1px solid #E9967A;
			min-height: 100px;
			width: 100%;
		}
		.userinfo {
			width: 25%;
			background-color: #F4FBFF;
		}
	</style>
</head>
<body>
	<div id="post">
		<el-row type="flex" justify="center">
			<el-col :span="20">
				<el-container>
					<el-header>
						帖子详情
					</el-header>
					<el-main>
						<div class="post_list">
							<table class="post" v-for="p in posts">
								<tbody>
									<tr>
										<td class="userinfo">
											<div class=info>
												用户:{{ p.user.username }}
												注册时间:{{ new Date(parseInt(p.user.reg_time)).pattern("yyyy-MM-dd hh:mm:ss") }}
												积分:{{ p.user.integral }}
											</div>
										</td>
										<td class="content">
											<div v-html="p.post.content"></div>
											{{ new Date(parseInt(p.post.time)).pattern("yyyy-MM-dd hh:mm:ss") }}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<el-row type="flex" justify="center">
                			<el-pagination layout="prev, pager, next" :page-size="20" v-bind:current-page="currentPage" v-bind:page-count="endPage" @current-change="handleCurrentChange"></el-pagination>
            			</el-row>
					</el-main>
					<el-footer>
						<div ref="editor"></div>
						<el-button type="primary" @click="handleSubmit">发布</el-button>
					</el-footer>
				</el-container>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var post = new Vue({
		el: '#post',
		data: {
			theme_sticker: '',
			posts: '',
			currentPage: 0,
			endPage: 0,
			content: ''
		},
		mounted(){
			var win = this;
			axios.get('http://localhost:8080/forum/getThemeSticker')
				.then((response) => {
					this.theme_sticker = response.data;
					axios.get('http://localhost:8080/forum/getPosts',{
					    params:{
                            theme_id: this.theme_sticker.theme_id
						}
					}).then((response) => {
					    this.posts = response.data.data;
						this.currentPage = response.data.currentPage;
						this.endPage = response.data.endPage;
					})
				})
				.catch(function(error){
					console.log("lost connection.");
                    	win.$alert('网络连接丢失', '错误', {
                        	confirmButtonText: '确定'
                    	});
				})


			var E = window.wangEditor;
			var editor = new E(this.$refs.editor);
        	editor.customConfig.uploadImgShowBase64 = true;
		    editor.customConfig.menus = [
		    	'head',  // 标题
			    'bold',  // 粗体
			    'fontSize',  // 字号
			    'fontName',  // 字体
			    'italic',  // 斜体
			    'underline',  // 下划线
			    'strikeThrough',  // 删除线
			    'foreColor',  // 文字颜色
			    'backColor',  // 背景颜色
			    'list',  // 列表
			    'justify',  // 对齐方式
			    'emoticon',  // 表情
			    'image',  // 插入图片
			    'undo',  // 撤销
			    'redo'  // 重复
		    ];
		    editor.customConfig.onchange = (html) => {
          		this.content = html;
        	};
		    editor.create();
		},
		methods: {
			handleSubmit(){
				axios({
					url: 'http://localhost:8080/forum/addPost',
                    method: "post",
                    data: {
                        theme_id: this.theme_sticker.theme_id,
                        content: this.content
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
                        if(response.data.status == '发布成功'){
                            win.$alert(response.data.status, '提示', {
                                confirmButtonText: '确定'
                            }).then(() => {
                                location.reload();
                        	});
                       	}else{
							win.$alert(response.data.status, '错误', {
								confirmButtonText: '确定'
							});
                        	}
						})
				.catch(function (error) {
                            console.log('submit error');
                            win.$alert('网络连接丢失', '错误', {
                                confirmButtonText: '确定'
                            });
                        });
			},
			handlePage(page){
				var win = this;
                axios.get("http://localhost:8080/forum/getPosts",{
                    theme_id: this.theme_sticker.theme_id,
                    page: page
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

		}
	})
		
</script>
</html>