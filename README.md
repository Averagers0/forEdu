梳理一下 **Git 常用指令 + 本地/远程原理 + origin 的概念**，一次讲清楚，方便新人理解。

---

## 1️⃣ Git 常用指令

### 基础操作

| 功能     | 命令                             | 说明               |
| ------ | ------------------------------ | ---------------- |
| 初始化仓库  | `git init`                     | 在当前文件夹初始化 Git 仓库 |
| 查看状态   | `git status`                   | 查看修改/新增/未跟踪文件    |
| 添加文件   | `git add <file>` / `git add .` | 把修改加入暂存区（Stage）  |
| 提交     | `git commit -m "信息"`           | 提交暂存区的内容到本地仓库    |
| 查看提交历史 | `git log`                      | 查看提交记录           |

### 分支操作

| 功能    | 命令                         | 说明           |
| ----- | -------------------------- | ------------ |
| 新建分支  | `git branch <name>`        | 新建分支，但不切换    |
| 切换分支  | `git checkout <branch>`    | 切换到指定分支      |
| 创建并切换 | `git checkout -b <branch>` | 创建并切换分支      |
| 查看分支  | `git branch`               | 当前本地分支列表     |
| 合并分支  | `git merge <branch>`       | 把指定分支合并到当前分支 |

### 远程操作

| 功能   | 命令                            | 说明           |
| ---- | ----------------------------- | ------------ |
| 查看远程 | `git remote -v`               | 查看远程仓库地址     |
| 添加远程 | `git remote add origin <url>` | 关联远程仓库       |
| 拉取   | `git pull`                    | 拉取远程修改并合并到本地 |
| 推送   | `git push -u origin main`     | 推送本地分支到远程仓库  |

### 其他常用

| 功能   | 命令                         | 说明           |
| ---- | -------------------------- | ------------ |
| 克隆仓库 | `git clone <url>`          | 从远程复制整个仓库到本地 |
| 删除远程 | `git remote remove origin` | 删除远程仓库       |
| 查看差异 | `git diff`                 | 查看文件改动       |

---

## 2️⃣ Git 本地 + 远程的原理

* **本地仓库（Local Repo）**

  * 就是在你电脑上的 `.git` 文件夹里存的版本历史
  * 提交 (`git commit`) 只影响本地仓库

* **暂存区（Stage/Index）**

  * `git add` 把文件改动放到暂存区，准备提交
  * 可以理解为“准备打包的文件列表”

* **远程仓库（Remote Repo）**

  * GitHub/GitLab/Gitee 上的仓库
  * 远程仓库和本地仓库是分开的
  * 推送 (`git push`) → 把本地提交同步到远程
  * 拉取 (`git pull`) → 把远程更新同步到本地

* **数据流**

  ```
  工作区 → 暂存区 → 本地仓库 → 远程仓库
  ```

---

## 3️⃣ origin 是什么

* `origin` 是 Git 默认给远程仓库取的名字
* 可以理解为 **远程仓库的别名**，你可以用别名操作远程仓库

  ```bash
  git remote add origin <url>  # 关联远程仓库
  git push origin main          # 推送到 origin 仓库的 main 分支
  ```
* 你可以改名字或加多个远程仓库：

  ```bash
  git remote rename origin upstream
  git remote add github https://github.com/xxx/yyy.git
  ```

> 小结：
>
> * **本地仓库** 存历史，**暂存区** 准备提交，**远程仓库** 用于协作
> * **origin** 是远程仓库的默认别名
> * 常用操作就是 `add → commit → push → pull`

---

this is a new info

