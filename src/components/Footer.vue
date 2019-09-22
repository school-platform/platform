<template>
    <div class="footer">
        <div class="footer-content">
            <div class="container">
                <div class="left">
                    <ul class="footer-detail">
                        <li>关于我们</li>
                        <li>用户协议</li>
                        <li>联系我们</li>
                        <li>合作伙伴</li>
                    </ul>
                    <div class="left detail">
                        <p>公司地址：安徽师范大学</p>
                        <p>邮箱：1763420434@qq.com</p>
                    </div>
                    
                </div>
                <div class="right">
                    <img src="../assets/logo1.png" style="width:300px;height:100px;">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>
<style scoped>
.footer{
    background: url(../assets/footer-Bg-1px.png) repeat;
    box-sizing: border-box;
    color: aliceblue;
}
.footer-content{
    height: 200px;
    background: url(../assets/footer.svg) no-repeat 50%;
    background-size: cover;
    background-position: 100% 100%;
}
.container::after{
    content: "";
    clear: both;
}

.left{
    float:left;
}
.right{
    float: right;
    margin:40px auto;
}
.footer-detail{
    height: 50px;
    border-bottom: 1px solid #959595;
    margin-bottom: 10px;
    color: aliceblue;
}

.footer-detail li{
    float: left;
    margin-top: 20px;
    margin-bottom: 0px;
    margin-right: 40px;
}
.detail{
    text-align: left;
}

</style>
