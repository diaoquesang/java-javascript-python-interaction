# java-javascript-python-interaction
## Created on 2023/8/25
Collaborators: Yifei Sun, Zhihao Wu, Linfeng Luo, Yuyun Xiang, Ruihan Yang❤️❤️❤️

# 😍任务目标：建设一个网站，用户上传图片，图片经python算法处理后返回新图片，在网站展示新图片
😎1.	具体流程：数据流从前端传到后端，后端以用户名+时间戳等形式命名，将图片存储到original文件夹，将文件名，用户id，时间，是否处理完毕等信息输入到excel（process），并将相关信息通过http请求传给python

😎2.	Python通过http请求获取相关信息以读取图片，经模型处理得到新图片，以同名存入processed文件夹，并通过http请求向java返回处理完成的信息

😎3.	Java通过http请求获取到python处理完成的信息，来修改excel处理完成度等相关信息

😎4.	Js未获取到处理完成的图片则显示加载中，获取到图片则显示图片


# 更正日志
2023.8.27 罗淋峰代码初始化
2023.8.30 service项目前端初始化完成，由于上传限制，只能上传部分V3代码，今后将会采取直接上传文件方式
2023.8.31 service项目前端重构，删除冗余代码，重新上传src文件夹，其余文件暂不上传，完成axios初步搭建
