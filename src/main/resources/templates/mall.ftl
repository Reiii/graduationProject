<!DOCTYPE html>
<html>
<head>
    <title>mall</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
    <style>
        a{
            text-decoration: none;
        }
        .item {
            display: inline-block;
            width: 200px;
            height: 330px;
            margin-right: 10px;
            color: black;
        }
        .item-img {
            width:200px;
            height:200px
        }
        img {
            width: 100%;
            height: 100%;
        }
        .user-info{
            border-top: 1px solid lightgray;
        }

    </style>
</head>
<body>
<div id="mall">
    <el-menu  :default-active="activeIndex" class="el-menu-demo" mode="horizontal" >
        <el-menu-item index="1">二手商城</el-menu-item>
        <el-menu-item index="2"><a href="http://localhost:8080/forum/home">交流论坛</a></el-menu-item>
        <el-menu-item index="3"><a href="http://localhost:8080/activity/home">同城活动</a></el-menu-item>
        <el-menu-item index="4"><a href="http://localhost:8080/user/userinfo">个人中心</a></el-menu-item>
    </el-menu>
    <br>
    <el-container>
        <el-header>
            <el-row type="flex" justify="center">
                <el-col :span="12">
                    <el-input placeholder="请输入关键字.." v-model="keyword">
                        <el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
                    </el-input>
                </el-col>
            </el-row>
            <br>
            <el-row type="flex" justify="center">
                <el-col>
                    <el-menu :default-active="activeIndex" mode="horizontal" background-color="#F0F0F0" @select="handleType">
                        <el-menu-item index="1">新鲜</el-menu-item>
                        <el-menu-item index="2">乐高</el-menu-item>
                        <el-menu-item index="3">高达</el-menu-item>
                        <el-menu-item index="4">海贼王</el-menu-item>
                    </el-menu>
                </el-col>
            </el-row>
        </el-header>
        <el-main>
            <br>
            <br>
            <div class="item-list">
                <span v-for="i in items">
                    <a class="item" @click="handleHrefClick(i.toy.commodity_id)">
                        <div class="item-img" v-if="i.pic">
                            <img :src="i.pic.url">
                        </div>
                        <div class="item-title">{{ i.toy.title }}</div>
                        <div class="item-price"><font color="red" size="4">¥ {{ i.toy.price }}</font></div>
                        <div class="user-info">
                            <div class="username">{{ i.user.username }}</div>
                            <div class="address">{{ i.toy.province }} {{ i.toy.city }}</div>
                        </div>
                </a>
                </span>
            </div>
        </el-main>
        <el-footer>
            <el-row type="flex" justify="center">
                <el-pagination layout="prev, pager, next" :page-size="20" v-bind:current-page="currentPage" v-bind:page-count="endPage" @current-change="handleCurrentChange"></el-pagination>
            </el-row>
        </el-footer>
    </el-container>

</div>
</body>
<script>
    var mall = new Vue({
        el: '#mall',
        data: {
            activeIndex: '1',
            items: [],
			currentPage: '',
			endPage: '',
			keyword: ''
        },
        mounted(){
            axios.get("http://localhost:8080/mall/getItems")
                    .then((response) => {
                this.items = response.data.data;
                this.currentPage = response.data.currentPage;
                this.endPage = response.data.endPage;
        })
        .catch(function(error){
                console.log("lost connection.")
                this.$alert('网络连接丢失', '错误', {
                    confirmButtonText: '确定'
                });
            });
        },
        methods: {
            handleSearch(){
                axios.get("http://localhost:8080/mall/getItems",{
                    params: {
                        keyword: this.keyword,
                    }
                }).then((response) => {
                    this.items = response.data.data;
					this.currentPage = response.data.currentPage;
					this.endPage = response.data.endPage;
				}).catch(function(error){
                    console.log("lost connection.")
                    this.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
                });
			},
			handleType(index){
				switch (index){
					case '1' : this.keyword = '';break;
					case '2' : this.keyword = '乐高';break;
					case '3' : this.keyword = '高达';break;
					case '4' : this.keyword = '海贼王';break;
				}
				this.handleSearch();
			},
			handlePage(page){
                var param;
                if(this.keyword != ''){
					param = {
					    keyword: this.keyword,
						page: page
					}
				}else{
                    param = {
                        page: page
                    }
				}
                axios.get("http://localhost:8080/mall/getItems",{
                    params: param
                }).then((response) => {
                    this.items = response.data.data;
                    console.log(this.items);
                this.currentPage = response.data.currentPage;
                this.endPage = response.data.endPage;
            }).catch(function(error){
                    console.log("lost connection.")
                    this.$alert('网络连接丢失', '错误', {
                        confirmButtonText: '确定'
                    });
                });
			},
			handleCurrentChange(val){
                this.handlePage(val);
			},
			handleHrefClick(id){
                window.open("http://localhost:8080/mall/item?commodity_id=" + id, "_blank");
            }
        }
    })
</script>
</html>