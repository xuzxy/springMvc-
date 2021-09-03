
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<html>
<head>
    <title>我的农场</title>
</head>
<body>
<%--<div>
    <input type="button" value="ajax的001" id="ajax001">

<script>
    $(function () {
        $("ajax001").click(function () {
            $.ajax({
                url: "/api/admin/ajax001",
                type: 'post',
                data: {
                    'farmer.id': 1,
                    'farmer.name': '张三',
                    'farmer.jinbi': 1000,

                    'buyrecord.id':1,
                    'buyrecord.fid':1,
                    'buyrecord.sname':'水稻',
                    'buyrecord.num':10,
                    'buyrecord.buydate':'2021-08-27',
                },
                dataType:'json',
                success: function (res) {
                    console.log(res)
                }
            });
        })



    });

</script>

</div>--%>

<form class="layui-form"  onsubmit="return false" >
    <div class="layui-form-item" >
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="zzname"  placeholder="请输入种子名称" class="layui-input" >
        </div>
    </div>

    <div class="layui-form-item" action="" >
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="text" name="jg"  placeholder="请输入价格" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item" action="" >
        <label class="layui-form-label">数量</label>
        <div class="layui-input-inline">
            <input type="text" name="sl"  placeholder="请输入数量" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">购买</button>
        </div>
    </div>
</form>
<script>
    layui.use(["form","jquery",'layer'],function () {

        var form=layui.form;
        var layer=layui.layer;
        var $=layui.jquery;
        form.on("submit(formDemo)",function (data) {
            var map={
                "jg":data.field.jg,
                "sl":data.field.sl
            };
            $.ajax({
                url:"/api/admin/ajax002",
                type:'POST',
                data:JSON.stringify(map),
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                }
                , contentType:"application/json;charset=UTF-8",
            })

        })
    })
</script>


</body>
</html>
