<!DOCTYPE html>
<html>
<head>
	<title>发布活动</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
	<!-- 引入样式 -->
	<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
	<!-- 引入组件库 -->
	<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
	 <script src="https://cdn.bootcss.com/wangEditor/10.0.13/wangEditor.js"></script>
	 <script src="/js/city.js"></script>
</head>
<body>
	<div id="release">
		<el-row type="flex" justify="center">
			<el-col :span="10">
				<el-form :model="releaseForm" :rules="releaseFormRules" ref="releaseForm">
					<el-form-item label="标题" prop="title">
						<el-input v-model="releaseForm.title"></el-input>
					</el-form-item>
					<el-form-item label="所在地" prop="province">
						<el-select v-model="releaseForm.province" placeholder="请选择省份">
							<el-option v-for="p in provinces" :label="p" :value="p"></el-option>
						</el-select>
						<el-select v-model="releaseForm.city" placeholder="请选择城市">
							<el-option v-for="c in cities" :label="c" :value="c"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="详细地址" prop="address">
						<el-input v-model="releaseForm.address"></el-input>
					</el-form-item>
					<el-form-item label="费用" prop="cost">
						<el-input v-model="releaseForm.cost">
							<template slot="append">元</template>
						</el-input>
					</el-form-item>

					<el-form-item label="起始时间" prop="start_time">
						<el-date-picker
					        v-model="releaseForm.start_time"
					      	type="datetime"
					      	value-format="timestamp"
					      	placeholder="选择日期时间">
					    </el-date-picker>
					</el-form-item>
					<el-form-item label="结束时间" prop="end_time">
						<el-date-picker
					        v-model="releaseForm.end_time"
					      	type="datetime"
					      	value-format="timestamp"
					      	placeholder="选择日期时间">
					    </el-date-picker>
					</el-form-item>
					<el-form-item label="封面图片">
						<el-upload
							class="upload-demo"
							action=""
							:on-preview="handlePreview"
							:on-remove="handleRemove"
							:file-list="fileList"
							list-type="picture">
							<el-button size="small" type="primary">点击上传</el-button>
							<div slot="tip" class="el-upload_tip">只能上传jpg/png文件，且不超过500kb</div>
						</el-upload>
					</el-form-item>
					<br>
					<br>
					<br>
					<el-form-item label="活动详情">
						<br>
						<div ref="editor"></div>
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
	var release = new Vue({
		el: "#release",
		data(){
			var validatePrice = (rule, value, callback) => {
				var re = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
				if (!value) {
					callback(new Error('请输入价格'));
				} else if (!re.test(value)) {
					callback(new Error('请输入正确价格'))
				} else {
					callback();
				}
			};
			var validateCityData = (rule, value, callback) => {
				var re = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
				if (!value) {
					callback(new Error('请选择所在地'));
				}
				else {
					callback();
				}
			};
			return {
				releaseForm: {
					title: '',
					province: '',
					city: '',
					address: '',
					start_time: '',
					end_time: '',
					detail: '',
					cost: ''
				},
				releaseFormRules: {
					title: [
						{ required: true, message: "请输入标题", trigger: ["blur", "change"]}
					],
					cost: [
						{ required: true, validator: validatePrice, trigger: ["blur", "change"]}
					],
					address:[
						{required: true, message: "请输入详细地址", trigger: ["blur", "change"]}
					],
					province:[
						{required: true, validator: validateCityData, trigger: ["blur", "change"]}
					],
					city:[
						{required: true, validator: validateCityData, trigger: ["blur", "change"]}
					],
					start_time:[
						{ required: true, message: "请选择时间", trigger: ["blur", "change"] }
					],
					end_time:[
						{ required: true, message: "请选择时间", trigger: ["blur", "change"] }
					]
				},
				editor: '',
				fileList: ''
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
          		this.releaseForm.detail = html;
        	};
		    editor.create();
		},
		computed: {
			provinces() {
                this.releaseForm.city = '';
                return citydata.map(item => item.name);
            },
            cities() {
                if (this.releaseForm.province == '') {
                    this.releaseForm.city = '';
                } else {
                    return citydata.filter(item => item.name == this.releaseForm.province)[0].city.map(item => item.name);
                }
            }
		},
		methods: {
			handleSubmit(){
			    var win = this;
				this.$refs.releaseForm.validate((valid) => {
	                if (valid) {
	                    axios({
                            url: 'http://localhost:8080/activity/doRelease',
                            method: "post",
                            data: {
                            	title: this.releaseForm.title,
                            	cost: this.releaseForm.cost,
                                province: this.releaseForm.province,
                                city: this.releaseForm.city,
                                address: this.releaseForm.address,
                                detail: this.releaseForm.detail,
                                start_time: this.releaseForm.start_time,
                                end_time: this.releaseForm.end_time
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
			},
			handleRemove(file, fileList) {
        		console.log(file, fileList);
      		},
            handlePreview(file) {
        		console.log(file);
      		}
      	}
	})
</script>
</html>