<!DOCTYPE html>
<html>

<head>
	<title>item</title>
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
        .time {
            float: right;
            clear: both;
        }
        .content {
            word-break: break-all;
            word-wrap: break-word;
        }
        .comment, .reply_comment {
            border-bottom: 1px solid lightgray;
            padding: 5px;
			overflow: hidden;
        }
		.relpy {
			float: right;
			clear: both;
		}
	</style>
</head>

<body>
	<div id="item">
		<el-row type="flex" justify="center">
			<el-col :span="20">
				<el-container>
					<el-header>
						<el-row type="flex" justify="center">
							<el-col :span="4">
								<font size="6">商品详情</font>
							</el-col>
						</el-row>
					</el-header>
					<el-container>
						<el-aside width="730px">
							<el-carousel class="item-img" height="490px">
								<template v-for="i in imgs">
                                    <el-carousel-item>
                                        <img v-bind:src="i.url">
                                    </el-carousel-item>
								</template>
							</el-carousel>
						</el-aside>
						<el-main>
							<div class="item-info">
								<h1 class="item-title">{{ title }}</h1>
								<ul class="item-price">价格&nbsp;&nbsp;<b>¥</b><font size="5" color="red">{{ price }}</font></ul>
								<ul class="item-detail">
									<li>卖家&nbsp;&nbsp;&nbsp;&nbsp;{{ username }}</li>
									<li>信用分&nbsp;&nbsp;&nbsp;{{ credit }}</li>
									<li>所在地&nbsp;&nbsp;&nbsp;{{ province }}&nbsp;{{ city }}</li>
									<li>交易方式&nbsp;&nbsp;{{ transction }}</li>
								</ul>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<el-button type="warning" @click="handleBuy">立即购买</el-button>
							</div>
						</el-main>
					</el-container>
					<el-footer>
						<el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
							<el-menu-item index="1">商品详情</el-menu-item>
							<el-menu-item index="2">留言</el-menu-item>
						</el-menu>
						<div class="detail" v-bind:style="detailStyle">
							<span v-html="detail"></span>
						</div>
						<div class="message" v-bind:style="messageStyle">
							<div v-for="c in messages">
                                <div class="comment" v-if="c.type == 0">
                                    <div class="comment_info">
                                        <span class="name">{{ c.username }}</span>
                                        <span class="time">{{ new Date(parseInt(c.time)).pattern("yyyy-MM-dd hh:mm:ss") }}</span>
                                    </div>
                                    <div class="content" v-html="c.content">
                                    </div>
                                    <el-button class="relpy" @click="reply_comment(c.uid)">回复</el-button>
                                </div>
                                <div class="reply_comment" v-if="c.type == 1">
                                    <div class="comment_info">
                                        <span class="name">{{ c.username }}</span>
                                        <span>回复</span>
                                        <span class="name">{{ c.reply_username }}</span>
                                        <span class="time">{{ new Date(parseInt(c.time)).pattern("yyyy-MM-dd hh:mm:ss") }}</span>
                                    </div>
                                    <div class="content" v-html="c.content">
                                    </div>
                                    <el-button class="relpy" @click="reply_comment(c.uid)">回复</el-button>
                                </div>
							</div>
                            <div ref="editor"></div>
							<el-button @click="reply_comment">发表</el-button>
						</div>
					</el-footer>
				</el-container>
			</el-col>
		</el-row>
	</div>
</body>
<script>
	var item = new Vue({
		el: '#item',
		data: {
			activeIndex: "1",
			detailStyle: {
				display: "block"
			},
			messageStyle:{
				display: "none"
			},
			imgs: [],
			title: '',
			price: '',
			username: '',
			credit: '',
			province: '',
			city: '',
			transction: '',
			detail: '',
			messages: [],
			commodity_id: '',
			content: ''
		},
		mounted(){
			axios.get("http://localhost:8080/mall/getToyInfo")
				.then((response) => {
				    this.commodity_id = response.data.commodity_id;
					this.title = response.data.title;
					this.price = response.data.price;
					this.province = response.data.province;
					this.city = response.data.city;
					this.transction = response.data.means_of_transction;
					this.detail = response.data.description
				}).catch(function(error){
					console.log("toyinfo error");
				});
			axios.get("http://localhost:8080/mall/getSeller")
				.then((response) => {
					this.username = response.data.username;
					this.credit = response.data.credit;
				}).catch(function(error){
					console.log("get seller error");
				});
			axios.get("http://localhost:8080/mall/getToyPic")
				.then((response) => {
					this.imgs = response.data.pictures;
				}).catch(function(error){
					console.log(error);
				});
			axios.get("http://localhost:8080/mall/getComment")
					.then((response) => {
					    this.messages = response.data;
			}).catch(function(error){
			    console.log(error);
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
			handleSelect(key){
				console.log(key);
				if(key == "1"){
					this.detailStyle.display = "block";
					this.messageStyle.display = "none";
				}else {
					this.detailStyle.display = "none";
					this.messageStyle.display = "block";
				}
			},
			handleBuy(){
			    location.href = 'http://localhost:8080/mall/doBuy?commodity_id=' + this.commodity_id;
			},
			reply_comment(uid){
			    var win = this;
			    var param;
			    if(uid != null){
			        param = {
                        content: this.content,
						commodity_id: this.commodity_id,
                        reply_id: uid
					}
				}else{
			        param = {
                        content: this.content,
                        commodity_id: this.commodity_id,

                    }
				}
                axios({
                    url: 'http://localhost:8080/mall/addComment',
                    method: 'post',
                    data: param,
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
                    })
                })
			}
		}
	})
</script>
</html>