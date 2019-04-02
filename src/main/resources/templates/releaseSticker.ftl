<!DOCTYPE html>
<html>
<head>
	<title>发布主题贴</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	<script src="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.js"></script>

</head>
<body>
	<div id="releaseSticker">
		<el-row type="flex" justify="center">
			<el-col :span="10">
				<el-form :model="releaseForm" :rules="releaseFormRules" ref="releaseForm">
					<el-form-item label="标题" prop="title">
						<el-input v-model="releaseForm.title"></el-input>
					</el-form-item>
					<br>

                    <el-form-item label="帖子内容">
						<br>
						<div ref="editor"></div>
					</el-form-item>
                    <el-form-item label="主题区" :rules="releaseFormRules" ref="releaseForm">
                        <el-select v-model="releaseForm.subject_id" placeholder="请选择主题区">
                            <el-option v-for="p in subject_area" :label="p.name" :value="p.subject_id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="分类" :rules="releaseFormRules" ref="releaseForm">
                        <el-select v-model="releaseForm.classification" placeholder="请选择分类">
                            <el-option v-for="p in classes" :label="p" :value="p"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
						<el-button type="primary" @click="handleSubmit">发布</el-button>
					</el-form-item>
				</el-form>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var releaseSticker = new Vue({
		el: '#releaseSticker',
		data(){
			return{
				releaseForm: {
					title: '',
					subject_id: '',
					classification: '',
					content: ''
				},
				classes: '',
				subject_area: '',
				editor: '',
				releaseFormRules: {
					title: [
						{ required: true, message: "请输入标题", trigger: ["blur", "change"]}
					],
					classification: [
						{ required: true, message: "请选择分类", trigger: ["blur", "change"]}
					],
					content: [
						{ required: true, message: "请输入内容", trigger: ["blur", "change"]}
					]
				}
			}

		},
		mounted(){
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
          		this.releaseForm.content = html;
        	};
		    editor.create();
            axios.get("http://localhost:8080/forum/getClassification")
                    .then((response) => {
                this.classes = response.data
        })
        .catch(function(error){
                console.log('connect error');
                this.$alert('网络连接丢失', '错误', {
                    confirmButtonText: '确定'
                });
            });
            axios.get("http://localhost:8080/forum/getAllSubjectArea")
					.then((response) => {
						this.subject_area = response.data
					})
					.catch(function(error){
                console.log('connect error');
                this.$alert('网络连接丢失', '错误', {
                    confirmButtonText: '确定'
                });
			});
		},
		methods: {
			handleSubmit(){
			    var win = this;
				this.$refs.releaseForm.validate((valid) => {
	                if (valid) {
	                    axios({
                            url: 'http://localhost:8080/forum/addThemeSticker',
                            method: "post",
                            data: {
                            	title: this.releaseForm.title,
								subject_id: this.releaseForm.subject_id,
                            	classification: this.releaseForm.classification,
                            	content: this.releaseForm.content
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
                            win.$alert(response.data.status + ',即将前往个人中心..', '提示', {
                                confirmButtonText: '确定'
                            }).then(() => {
                                location.href = 'http://localhost:8080/user/userinfo';
                        	});
                        	}else{
								win.$alert(response.data.status, '错误', {
									confirmButtonText: '确定'
								});
                        	}
						}).
                        catch(function (error) {
                            console.log('submit error');
                            win.$alert('网络连接丢失', '错误', {
                                confirmButtonText: '确定'
                            });
                        });
	                }
	            });
			}
		}
	})
</script>
</html>