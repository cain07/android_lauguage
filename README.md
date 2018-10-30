# android_lauguage

Android APP 国际化 脚本  

#需求 

1.提供 的 翻译文档 Excel 表格 有 id列 中文 列 英文 列 法文 列 
  id 就是 生成 xml 里的 键 中文 英文 就是 值

2.每一个 sheet 工作表 生成 android 里的 一个 xml 文件 


#实现 

用 myeclipse 建立一个 java工程 

1.首先 读取 excel 表格 这里 引入了 读取 excel 的一些jar包 


2.每一个 sheet 生成一个 xml 文件 并 放入 规定的 文件夹里 values-en

3.根据 config.json 配置文件 生成 xml 文件 每次都可以改成 excel 文件里的

4.代码写完以后 打成jar 包 引用了 三方jar 所以生成jar包 麻烦一些 可以根据这个 

   (https://blog.csdn.net/wrfccl/article/details/73614194)

5.把 lauguage_jar 包 放入 android工程 中 

6.可以执行 命令行  



# contribute 
如果本 工具 对你有较大的帮助，欢迎捐赠 


![image](https://github.com/cain07/atools/blob/master/WX20181029110830.png)

# 欢迎交流

wx zhenai907
