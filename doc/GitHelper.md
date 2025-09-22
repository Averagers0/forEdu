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


试一下


## 4️⃣如何创造一个自己的github仓库并且把项目塞进去

---

### 一、注册 Git 账户

常用的代码托管平台有 **GitHub**、**GitLab**、**Gitee**（国内快一点）。我以 GitHub 为例（其他平台步骤类似）：

1. **注册账号**

    * 打开 [GitHub 官网](https://github.com/)
    * 点击 **Sign up**
    * 输入邮箱 → 创建用户名 → 设置密码 → 验证邮箱
    * 注册完成后，进入 GitHub 主页

2. **安装 Git**

    * 下载 [Git](https://git-scm.com/)
    * 安装后在终端/命令行输入：

      ```bash
      git --version
      ```

      有版本号说明安装成功。

3. **配置用户信息**（全局配置）
   在命令行执行：

   ```bash
   git config --global user.name "你的GitHub用户名"
   git config --global user.email "你的GitHub注册邮箱"
   ```

   > 注意：这里的 `user.name` 和 `user.email` 必须和 GitHub 账号一致，否则提交不会关联到你的 GitHub 账户。

---

### 二、生成 SSH key 并绑定 GitHub

这样以后就不用每次都输入账号密码。

1. **生成 SSH key**

   ```bash
   ssh-keygen -t rsa -C "你的GitHub邮箱"
   ```

   按回车保存到默认路径 `~/.ssh/id_rsa`

2. **查看公钥**

   ```bash
   cat ~/.ssh/id_rsa.pub
   ```

   复制里面的一长串内容。

3. **绑定到 GitHub**

    * 登录 GitHub → 右上角头像 → Settings
    * 左边栏选 **SSH and GPG keys** → New SSH key
    * 粘贴刚才复制的 key → 保存

4. **测试连接**

   ```bash
   ssh -T git@github.com
   ```

   如果显示 `Hi xxx! You've successfully authenticated...` 就成功了。

---

### 三、在项目里使用 Git 账户

现在可以把项目托管到 GitHub 上。

### 1. 新建一个仓库

* GitHub → **New repository**
* 输入仓库名，比如 `my-project`
* 选择 Public / Private
* 创建完成后，会生成一个远程仓库地址，比如：

  ```
  git@github.com:用户名/my-project.git
  ```

### 2. 本地项目关联 Git

进入你项目的文件夹：

```bash
cd ~/Desktop/my-project
git init               # 初始化本地仓库
git remote add origin git@github.com:用户名/my-project.git
```

### 3. 提交代码到 GitHub

```bash
git add .
git commit -m "第一次提交"
git push -u origin master   # 首次推送
```

推送成功后，你就能在 GitHub 仓库页面看到项目了。



