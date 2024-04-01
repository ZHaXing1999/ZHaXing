<%--
  Created by IntelliJ IDEA.
  User: 14461
  Date: 2024/3/11
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <link rel="stylesheet" href="./index.css">
  </head>
  <body>
  <header>
    <a href="#" id="logo">Logo</a>
    <ul>
      <li><a href="#" class='active'>Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Work</a></li>
      <li><a href="#">Contact</a></li>
    </ul>
  </header>
  <section>
    <img src="./images/stars.png" alt="" id="stars">
    <img src="./images/moon.png" alt="" id="moon">
    <img src="./images/mountains_behind.png" alt="" id="mountain_behind">
    <div id="text">Moon Light</div>
    <a href="IndexServlet" id="btn">欢迎，登录</a>
    <img src="./images/mountains_front.png" alt="" id="mountain_front">
  </section>
  <div class="sec">
    <h2>This is title</h2>
    <p>
      如果一个人爱上浩瀚星辰中独一无二的一朵花，那么这个人在仰望繁星时，就会感到无比幸福。<br>
      ——圣埃克絮佩里《小王子》<br><br>
      让静夜星空 带给你我的怀想吧 也带给你无忧的睡眠 而我，如一个陌生客 默默地走过你窗前我有不安的睡梦 与严寒的隆冬 而我的窗 开向黑夜 开向无言的星空<br>
      ——陈敬容《窗》<br><br>
      我成为某个人，某间 点着油灯的陋室 而这陋室冰凉的屋顶 被群星的亿万只脚踩成祭坛 我像一个领取圣餐的孩子 放大了胆子，但屏住呼吸<br>
      ——西川《在哈尔盖仰望星空》<br><br>
    </p>
  </div>
  </body>
  <script>
    // 辅助函数
    function $(id){
      return document.getElementById(id);
    }
    //获取到一些关键元素
    let stars=$('stars');
    let moon=$('moon');
    let mountain_behind=$('mountain_behind');
    let text=$('text');
    let btn=$('btn');
    let mountain_front=$('mountain_front');

    //给窗口添加鼠标滚动事件
    window.addEventListener('scroll',function(){
      let value=window.scrollY
      stars.style.left=value*0.25+'px';
      moon.style.top=value*1.2+'px';
      mountain_behind.style.top=value*0.5+'px';
      text.style.marginRight=value*4+'px';
      text.style.marginTop=value*0.5+'px';
      btn.style.marginRight=value*1.5+'px';
      btn.style.marginTop=value*0.5+'px';
    })
  </script>
</html>
