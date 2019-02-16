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
							message
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
			commodity_id: ''
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
			}
		}
	})
</script>
</html>