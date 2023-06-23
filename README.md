# haidnorVM
一个用 Java 编写的元循环虚拟机

# 编译方式
打开窗口:
```cmd
C:\ProgramData\Microsoft\Windows\Start Menu\Programs\Visual Studio 2022\Visual Studio Tools\VC\x64 Native Tools Command Prompt for VS 2022
```
进入项目文件夹
```
cd \java_project\haidnorVM
```
执行编译
```cmd
mvn clean package
```
字节码规范文档
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html

# 开发分支说明
## main
元循环虚拟机主要分支
## dev
开发分支
## helloWorld
实现JVM最简单的功能,仅能解析字节码文件然后打印 hello,world
## bytecode
编码解析字节码文件 demo