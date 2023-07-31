### GIT使用场景

#### 1.解决合并冲突：

##### 	1.1同一文件不同位置

​	$1.git add [filename]
​	$2.git commit -m '.....'
​	$3.git checkout [branch]
​	$4.git pull

##### 	1.2同一文件同一位置

​		**a**:不丢弃本地修改
​		$1~$4不变 

-编辑冲突文件，决定要保留的内容，然后删掉三行分割线。

​		$5 git add .
​		$6 git commit -m ''
​		$7 git push
​		**b**:丢弃全部本地更新，
​		$5 git reset --hard origin/[branch]
​		**c**:回到合并之前的状态
​		$5 git reset --hard HEAD^

#### 2.回滚提交 

##### 	2.1 reset

​	$git log -20 /git reflog 
​	$git reset --hard/mixed/soft 版本号/HEAD^/HEAD~[int]

##### 	2.2 revert

​		**a**:无合并

```shell
git revert [版本号]
git commit -m
git push
```

![image-20230713110049456](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230713110049456.png)

​		**b:**有合并——指定一个 parent number 标识出"主线"，主线的内容将会保留，而另一条分支的内容将被 revert

​		$git revert -m 1/2 [版本号]

#### 3.修复错误的提交

##### 	3.1 --amend：

##### 		a:可以追加忘记提交的文件，也可以修改上次提交的文字描述信息

```shell
git commit --amend -m 'message'
```

##### 		b:将新的文件修改，也提交到上一个commit中

```shell
git add .
git commit --amend --no-edit
```

#### 4.分支指令

##### 	4. 查看分支

```shell
#  列出所有本地分支
git branch [options]
#1 查看详细信息
options： -v
#2 列出所有远程分支
options： -r
#3 列出所有本地分支和远程分支
options： -a
```

##### 	4.2 新建分支

```shell
#1 新建一个分支，但依然停留在当前分支
git branch [branch-name]
#2 新建一个分支，与指定的远程分支建立关联
git branch --track [branch] [remote-branch]
#3 新建一个分支，并推送到远程库
git branch [branch-name]
git add .
git commit -m ‘新增分支’
git push origin [branch-name]
#4 在现有分支和远程分支中建立关联
git branch --set-upstream-to [branch] [remote-branch]
```

##### 4.3 删除分支

```shell
#1 删除本地分支: -D（大写）强制删除
git branch -d [branch-name]
#2 删除远程分支
git push origin --delete [branch-name] || git push origin -d [branch-name]
```

​	**注：**1.error: Cannot delete branch 'test1' checked out at '...'

![image-20230713141318136](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230713141318136.png)

​	原因：无法在当前分支下删除该分支

​	解决：

```shell
git checkouy [other-branch-name]
```

​		2.error: The branch 'test1' is not fully merged.

![image-20230713141457129](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230713141457129.png)

​	原因：如果本地分支没有合并到其他分支，或者没有对应的远程分支，删除时则会提示这个错误，强制删除即可。之所以会需要这样提示，是因为通常创建分支就是为了在这个分支做一些事情，例如修复一些bug，或者添加一个新的模块功能。然后再合并到master。但现在这个分支，既没有合并，也没有远程分支，你删除它的话，它就彻底没了。所以你删除它的时候，就会给你提示。这就像在一些软件中，删除重要的内容时，会弹出弹框，问你是否确定要删除？

​	解决：

```shell
git branch -D [branch-name] #强制删除
```



##### 4.4 重命名分支

```shell
git branch -m [old-branch-name] [new-branch-name]
```

#### 5.代码回退

##### 5.1 撤销工作区的修改，把暂存区恢复到工作区。

```shell
git checkout .
```

##### 5.2 撤销工作区、暂存区的修改，用`HEAD`指向的当前分支最新版本替换

```shell
git checkout HEAD .
```

##### 5.3 暂存工作区、暂存区未提交的修改，等恢复现场后继续工作

```shell
#1 把未提交内容隐藏起来，包括未暂存、已暂存。
git stash
#2 查看所有被隐藏的内容列表
git stash list
#3 恢复被隐藏的内容，同时删除隐藏记录
git stash pop
#4 恢复被隐藏的文件，但是隐藏记录不删除
git stash apply
```

