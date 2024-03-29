# JVM——Java内存区域详解

### Java内存区域详解（重点）

1.运行时数据区域

​	—线程共享：

​		—堆

​			—字符串常量池

​	—线程私有：

​		—虚拟机栈、本地方法栈、程序计数器

2.本地内存

​	—元空间

​		—运行时常量池

​	—直接内存

####  程序计数器：

1.过改变程序计数器来依次读取指令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理。

2.多线程情况下，用于记录当前线程执行的位置，线程切换时可以找到上次线程运行的位置

注：不会出现OutOfMemoryError，生命周期随线程创建而创建，结束而死亡

#### Java 虚拟机栈:

虚拟机栈由一个个栈帧组成：栈帧包含**局部变量表**、**操作数栈**、**动态链接和** **方法返回地址**

1.局部变量表：存放了编译期可知的各种数据类型

2.操作数栈：存放方法执行 过程中产生的中间计算结果和计算过程中产生的临时变量

3.动态链接：服务于一个方法调用其他方法的场景，将常量池中指向方法的符号引用转化为其在内存地址中的直接引用

####  堆：存放对象实例

####  方法区：

方法区属于是 JVM 运行时数据区域的一块逻辑区域，是各个线程共享的内存区域。方法区会存储已被虚拟机加载的 **类信息、字段信息、方法信息、常量、静态变量、即时编译器编译后的代码缓存等数据**。

方法区和永久代以及元空间的关系：永久代以及元空间是 HotSpot 虚拟机对虚拟机规范中方法区的两种实现方式。

####  对象的创建：

##### step1.类加载检查

虚拟机遇到一条new指令时，首先去检查这个指令的参数能否在常量池中定位到这个类的符号引用，并且检查这个符号代表的类是否被加载过、解析和初始化过。如果没有，那必须先执行相应的类加载过程。

##### Step2:分配内存

在**类加载检查**通过后，接下来虚拟机将为新生对象**分配内存**。类加载完成后就可以确认对象所需的内存大小，为对象分配空间具体就是从Java堆中划分一块确定大小的内存，**分配方式**有

**指针碰撞**（堆内存规整（即没有内存碎片））和

**空闲列表**（堆内存不规整）两种方式，

**选择哪种分配方式由 Java 堆是否规整决定，而 Java 堆是否规整又由所采用的垃圾收集器是否带有压缩整理功能决定**。

##### Step3:初始化零值

内存分配完成后，虚拟机需要将分配到的内存空间都初始化为零值（不含对象头），这样，这些对象的实例字段在Java代码中可以不赋初值就直接使用

##### Step4:设置对象头

初始化零值完成后，虚拟机要对对象做必要的设置，比如这个对象是哪个类的实例、如何才能找到类的元数据信息、对象的哈希码，**这些信息都放在对象头中**

##### Step5:执行 init 方法

钱四步完成后，从虚拟机的视角，一个新对象已经产生了，但对Java来说，对象创建才刚开始，执行 new 指令之后会接着执行 `<init>` 方法，把对象按照程序员的意愿进行初始化，这样一个真正可用的对象才算完全产生出来。