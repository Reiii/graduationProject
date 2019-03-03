<!DOCTYPE html>
<html>
<head>
	<title></title>
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
		.tag {
			width: 100%;
			border: 1px solid #E9967A;
			min-height: 30px;
			background-color: #F4FBFF;
		}
		.sticker {
			width: 100%;
			border: 1px solid #E9967A;
			min-height: 50px;
			background-color: #FFFBFB;
		}
		.classification {
			width: 10%;
		}
		.title {
			width: 60%;
		}
		.user {
			width: 10%;
		}
		.reply_num {
			width: 10%;
		}
		.last_reply_time {
			width: 10%;
		}
	</style>
</head>
<body>
	<div id="sticker">
		<el-row type="flex" justify="center">
			<el-col :span="20">
				<el-container>
					<el-header>
						主题区头部
					</el-header>
					<el-main>
						<div class="sticker_list">
							<table class="tag">
								<tbody>
									<tr>
										<td class="classification"><div>分类</div></td>
										<td class="title"><div>主题贴标题</div></td>
										<td class="user"><div>用户</div></td>
										<td class="reply_num"><div>回复</div></td>
										<td class="last_reply_time"><div>最后发表</div></td>
									</tr>
								</tbody>
							</table>
							<table class="sticker" v-for="s in stickers">
								<tbody>
									<tr>
										<td class="classification"><a @click="handleClassification(s.theme_sticker.classification)">{{ s.theme_sticker.classification }}</a></td>
										<td class="title"><a @click="handleSticker(s.theme_sticker.theme_id)">{{ s.theme_sticker.title }}</a></td>
										<td class="user">{{ s.user.username }}</td>
										<td class="reply_num">{{ s.theme_sticker.replynum }}</td>
										<td class="last_reply_time">{{ new Date(parseInt(s.theme_sticker.last_reply_time)).pattern("yyyy-MM-dd hh:mm:ss") }}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</el-main>
					<el-footer>
						<el-row type="flex" justify="center">
                			<el-pagination layout="prev, pager, next" :page-size="20" v-bind:current-page="currentPage" v-bind:page-count="endPage" @current-change="handleCurrentChange"></el-pagination>
            			</el-row>
					</el-footer>
				</el-container>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var sticker = new Vue({
		el: '#sticker',
		data: {
			subject_area: '',
			stickers: '',
			currentPage: '',
			endPage: ''
		},
		methods: {
			handleClassification(){

			},
			handleSticker(theme_id){
				window.open("http://localhost:8080/forum/getStickerDetail?theme_id=" + theme_id, "blank");
			},
			handlePage(page){
				var win = this;
                axios.get("http://localhost:8080/forum/getStickers",{
                    params: {
                    	subject_id: this.subject_area.subject_id,
                    	page: page
                    }
                }).then((response) => {
                    this.stickers = response.data.data;
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
			axios.get("http://localhost:8080/forum/getAreaDetail")
				.then((response) => {
					this.subject_area = response.data
					axios.get("http://localhost:8080/forum/getStickers",{
						params: {
							subject_id: this.subject_area.subject_id
						}
					}).then((response) => {
                        this.stickers = response.data.data;
            this.currentPage = response.data.currentPage;
            this.endPage = response.data.endPage;
					})
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