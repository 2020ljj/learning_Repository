### java知识点

#### 1.引用拷贝/浅拷贝/深拷贝![浅拷贝、深拷贝、引用拷贝示意图](https://oss.javaguide.cn/github/javaguide/java/basis/shallow&deep-copy.png)

#### 2.== 和 equals() 的区别

- 对于基本数据类型来说，`==` 比较的是值。

- 对于引用数据类型来说，`==` 比较的是对象的内存地址。（判断两个对象是不是同一个对象。）

- 注：因为java是值传递，所以本质==比对的都是值，只不过引用数据类型存放的是内存地址

  

**`equals()`** 不能用于判断基本数据类型的变量，只能用来判断两个对象是否相等。

注：如果类没有重写 `equals()`方法 ：通过`equals()`比较该类的两个对象时，等价于通过“==”比较这两个对象，使用的默认是 `Object`类`equals()`方法。

3.String：String、StringBuffer、StringBuilder 的区别？

String：对象是不可变的，可以理解为常量，线程安全。（操作少量的数据）

StringBuffer：对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。（多线程操作字符串缓冲区下操作大量数据）

StringBuilder ：并没有对方法进行加同步锁，所以是非线程安全的。（并没有对方法进行加同步锁，所以是非线程安全的。）

#### 3.java集合

List，Set，Queue，Map的区别？



List：存储的元素有序，可重复

Set：存储的元素无需、不可重复

Queue：队列，元素有序，可重复

Map：键值对存储，key无序不可重复，value可重复



####  List

- `ArrayList`： `Object[]` 数组
- `Vector`：`Object[]` 数组
- `LinkedList`： 双向链表

#### Set

- `HashSet`(无序，唯一): 基于 `HashMap` 实现的，底层采用 `HashMap` 来保存元素
- `LinkedHashSet`: `LinkedHashSet` 是 `HashSet` 的子类，并且其内部是通过 `LinkedHashMap` 来实现的。有点类似于我们之前说的 `LinkedHashMap` 其内部是基于 `HashMap` 实现一样，不过还是有一点点区别的
- `TreeSet`(有序，唯一): 红黑树(自平衡的排序二叉树)

#### Queue

- `PriorityQueue`: `Object[]` 数组来实现二叉堆
- `ArrayQueue`: `Object[]` 数组 + 双指针

#### Map

- `HashMap`： JDK1.8 之前 `HashMap` 由数组+链表组成的，数组是 `HashMap` 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）时，将链表转化为红黑树，以减少搜索时间
- `LinkedHashMap`： `LinkedHashMap` 继承自 `HashMap`，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。另外，`LinkedHashMap` 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
- `Hashtable`： 数组+链表组成的，数组是 `Hashtable` 的主体，链表则是主要为了解决哈希冲突而存在的
- `TreeMap`： 红黑树

#### 5.length、length()和size()

length:针对数组，得到数组的长度

length():针对字符串，得到字符串的长度

size():针对泛型集合，查看泛型里有多少元素
