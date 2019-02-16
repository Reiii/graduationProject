<!DOCTYPE html>
<html>
<head>
    <title>确认订单</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.22/dist/vue.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
    <script src="/js/city.js"></script>
    <style>
        .item {
            display: inline-block;
            width: 200px;
            height: 330px;
            margin-right: 10px;
            text-decoration: none;
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
    </style>
</head>
<body>
<div id="order">
    <el-row type="flex" justify="center">
        <el-col :span="20">
            <el-container>
                <el-header>
                    <el-row type="flex" justify="center">
                        <el-col :span="4">
                            <font size="6">订单详情</font>
                        </el-col>
                    </el-row>
                </el-header>
                <el-container>
                    <el-aside width="400px">
                        <div class="item" >
                            <div class="item-img">
                                <img id="pic" src="">
                            </div>
                            <div class="item-title">{{ item.title }}</div>
                            <div class="item-price"><font color="red" size="4">¥ {{ item.price }}</font></div>
                        </div>
                    </el-aside>
                    <el-main>
                        <el-col :span="12">
                            <label>收货地址</label>
                            <el-form ref="orderForm" :model="orderForm" :rules="orderFormRules">
                                <el-form-item prop="province">
                                    <el-select v-model="orderForm.province" placeholder="请选择省份">
                                        <el-option v-for="p in provinces" :value="p" :key="p">{{ p }}</el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item prop="city">
                                    <el-select v-model="orderForm.city" placeholder="请选择城市">
                                        <el-option v-for="c in cities" :value="c" :key="c">{{ c }}</el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item prop="district">
                                    <el-select v-model="orderForm.district" placeholder="请选择区" >
                                        <el-option v-for="d in districts" :value="d" :key="d">{{ d }}</el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="详细地址" prop="address">
                                    <el-input v-model="orderForm.address" ></el-input>
                                </el-form-item>
                                <el-form-item label="联系电话"  prop="buyer_phone">
                                    <el-input v-model="orderForm.buyer_phone"></el-input>
                                </el-form-item>
                                <el-form-item><el-button type="primary" @click="submitForm('orderForm')">提交订单</el-button></el-form-item>
                            </el-form>
                        </el-col>
                    </el-main>
                </el-container>
            </el-container>
        </el-col>
    </el-row>

</div>
</body>
<script>
    var order = new Vue({
        el: '#order',
        data() {
            return {
                orderForm: {
                    province: '',
                    city: '',
                    district: '',
                    address: '',
                    buyer_phone: ''
                },
                orderFormRules: {
                    province: [
                        {required: true, message: '未选择省份', trigger: 'blur'}
                    ],
                    city: [
                        {required: true, message: '未选择城市', trigger: 'blur'}
                    ],
                    district: [
                        {required: true, message: '未选择区', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请输入详细地址', trigger: 'blur'}
                    ],
                    buyer_phone: [
                        {required: true, message: '请输入联系电话', trigger: 'blur'}
                    ]

                },
                item: ''
            }
        },
        mounted() {
            axios.get("http://localhost:8080/mall/getBuyItem")
                    .then((response) => {
                this.item = response.data;
            axios.get("http://localhost:8080/mall/cover", {
                params: {
                    commodity_id: this.item.commodity_id
                }
            }).then((resp) => {
                document.getElementById('pic').src = resp.data.url;
        }).catch(function (error) {
                console.log(error)
            });
        }).catch(function (error) {
                console.log("get toy error");
            });
        },
        computed: {
            provinces() {
                this.orderForm.city = '';
                this.orderForm.district = '';
                return citydata.map(item => item.name
            )
                ;
            },
            cities() {
                if (this.orderForm.province == '') {
                    this.orderForm.city = '';
                    this.orderForm.district = '';
                } else {

                    this.orderForm.district = '';
                    return citydata.filter(item => item.name == this.orderForm.province
                )
                    [0].city.map(item => item.name
                )
                    ;
                }
            },
            districts() {
                if (this.orderForm.city == '') {
                    this.orderForm.district = ''
                } else {
                    var thecity = citydata.filter(item => item.name == this.orderForm.province
                )
                    [0].city.filter(item => item.name == this.orderForm.city
                )
                    [0];
                    if (thecity) {
                        return thecity.area;
                    }
                    return [];
                }
            }
        },
        methods: {
            submitForm(formName) {
                var win = this;
                this.$refs[formName].validate((valid) => {
                    if(valid) {
                        axios({
                            url: 'http://localhost:8080/mall/submitOrder',
                            method: "post",
                            data: {
                                province: this.orderForm.province,
                                city: this.orderForm.city,
                                district: this.orderForm.district,
                                address: this.orderForm.address,
                                buyer_phone: this.orderForm.buyer_phone
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
                            if(response.data.status == '提交成功'){
                            win.$alert(response.data.status, '提示', {
                                confirmButtonText: '确定'
                            }).then(() => {
                                location.href = 'http://localhost:8080/mall/home';
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
    });
</script>
</html>