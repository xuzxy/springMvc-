
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<html>
<head>
    <title>springmvc 之ajax和 mvc收参数非常重要</title>
</head>
<body>
  <div>
      <input type="button" value="ajax的01中传参 k-结构或者对象" id="ajax01">
      <input type="button" value="ajax的02中传参 数对象" id="ajax02">
      <input type="button" value="ajax的03中传参 传数组或集合" id="ajax03">
      <input type="button" value="ajax的04中传参  传json对象" id="ajax04">
      <input type="button" value="ajax的05中传参 传多个对象" id="ajax05">
      <input type="button" value="ajax的06中传参  传批量对象传参" id="ajax06">
      <input type="button" value="ajax的07中传参  Map传参" id="ajax07">
      <input type="button" value="ajax的08中对象+常用类型 混合，常用于 带参数的分页查询" id="ajax08">
  </div>

<script>
    $(function () {
        $("ajax01").click(function () {
            $.ajax({
                url: "/api/admin/regByBean",
                type: "POST",
                data: {
                    'adminName': 'zhangsan',
                    'adminPwd': '123456789',
                    'adminTime': '2010-09-08 23:09:08',
                },
                dataType: 'JSON',
                success: function (res) {
                    console.log(res)
                }
            });
        });


        $("ajax02").click(function () {
            var adminInfo = {};
            adminInfo.adminName = 'zhangsan';
            adminInfo.adminPwd = '123456789';
            adminInfo.adminTime = '2010-09-08 23:09:08';
            $.ajax({
                url: "/api/admin/regByBean",
                type: "POST",
                data: {
                    'adminName': 'zhangsan',
                    'adminPwd': '123456789',
                    'adminTime': '2010-09-08 23:09:08'
                },
                dataType: 'JSON',
                success: function (res) {
                    console.log(res)
                }
            });
        });

        $("ajax03").click(function () {
         var ids = new Array();
            ids.push(3);
            ids.push(6);
            ids.push(8);
            ids.push(10);
            //
            $.ajax({
                url:"/api/admin/ajax03",
                type:'POST',
                data:{'ids':ids},
                dataType:'JSON',
                success: function (res) {
                    console.log(res)
                }
            })



        });
        $("ajax04").click(function () {
  //json对象 浏览器中看到请求有颜色
            var adminInfo={
/*
                private String adminName;
            //因为前端传来参数是字符串，所以需要做类型转换,之前小区所学的是xml配置
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            private Date adminTime;
            private  String adminPwd ;
            private List<Lover> loverList;
            private Integer[] aiHao; //1.写代码  2.看书  3.追剧
        }*/

            adminName:"杨洋",
            adminTime:'2010-09-08 23:09:08',
            adminPwd:"98760",
            loverList:[
                {
                    name:"小花",
                    age:18
                },
                {
                    name:"小草",
                    age:19
                },

                {
                    name:"小书",
                    age:20
                },
            ]


            };
            //var 出来的对象，他是个对象，如果直接传输，www.urlxxx 传递的是黑色的
          //因为普通的请求 是很多个 k -v结构，后台收取特别麻烦，所以就需要把负责的对象转换成本json对象
            //然后 后台接受就方便多了，前后端项目，一般都是用json传递
            $.ajax({
                url:"/api/admin/ajax04",
                type:'POST',
             //   data:adminInfo,  //普通的 k-v 结构 请求方式是 Content-Type: application/x-www-form-urlencoded; charset=UTF-8
                data :JSON.stringify(adminInfo), //变为json 就需要 Content-Type 改为 application/json;charset=UTF-8
                contentType:"application/json;charset=UTF-8",
                dataType:'JSON',
                success: function (res) {
                    console.log(res)
                }
            })


        });
        $("ajax05").click(function () {  //不常见，因为发货单，上面收货人的对象，下面是产品对象
              $.ajax({
                  url:"/api/admin/ajax05",
                  type:'post',
                  data:{
                      'lover.name':'小杨',
                      'lover.age':18,
                      'dog.dogId':1002,
                      'dog.dogSex':'男',
                  },
                  success: function (res) {
                      console.log(res)
                  }
              });
       })

        $("ajax06").click(function () {
            var loverList=[];
            for (var i = 0; i < 5; i++) {
                var lover={
                    name:"小花"+i,
                    age:18
                }
                loverList.push(lover)
            }
               $.ajax({
                   url:"/api/admin/ajax06",
                 // type:'GET',    //get请求是无法 传输 json 协议的 和 json数据的！！！
                   //
                   type:'post',
                   data:JSON.stringify(loverList),
                   dataType:'json',
                   contenType:"application/json;charset=utf-8",

                   success: function (r) {
                       console.log(r)
                   }
               });
        })

        $("ajax07").click(function () {
                var map ={
                    'adminName': 'zhangsan',
                    'adminPwd': '123456789',
                    'adminTime': '2010-09-08 23:09:08',
                }
                $.ajax({
                    url:'api/admin/ajax07',
                    type:'post',
                    dataType:'json',
                    data:JSON.stringify(map),

                    success: function (res) {
                        console.log(res)
                    },
                    contentType: "application/json;charset=utf-8"
                })
        })

        $("ajax08").click(function () {
             $.ajax({
                 url:"/api/admin/ajax08",
                 type:'post',
                 data:{    //4个kv
                     'name':'李易峰',
                     'pageSize':5,
                     'page':2
                 },
                 dataType:'json',
                 success: function (r) {
                     console.log(r)
                 }
             });
        });
        });





</script>

</body>
</html>
