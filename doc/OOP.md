# 面向对象编程思想

面向对象编程思想（**OOP, Object-Oriented Programming**）是一种编程范式，它的核心理念是**把现实世界中的事物抽象成程序中的对象**，通过对象之间的交互来完成系统的功能。

相比传统的“面向过程编程”（以函数和过程为中心），面向对象更强调**数据与行为的结合**，以便提高程序的可维护性、可扩展性和复用性。

---

## 核心概念

1. **对象（Object）**

    * 程序中的基本单元，既包含数据（属性），也包含行为（方法）。
    * 例如：一个 `Student`类可以创造一个student对象，可以有属性 `name`、`age`，以及方法 `study()`、`introduce()`。
      ```java
        Student student = new Student(name,age);
      ```

2. **类（Class）**

    * 对象的“模板”或“蓝图”，规定了对象应该有哪些属性和方法。
    * 例如：`class Student { String name; int age; void study() {...} }`。

3. **封装（Encapsulation）**

    * 把数据（属性）和操作（方法）捆绑在一起，隐藏内部实现，只对外提供必要的接口。
    * 好处：保护数据安全、降低耦合。
    * 例：私有变量 `private int age;`，对外提供 `getAge()`、`setAge()` 方法。

4. **继承（Inheritance）**

    * 子类可以继承父类的属性和方法，并在需要时扩展或重写。
    * 好处：代码复用、结构清晰。
    * 例：`class Teacher extends Person { ... }`。

5. **多态（Polymorphism）**

    * 同一个接口，不同对象可以有不同实现。
    * 好处：提高灵活性和可扩展性。
    * 例：`Animal a = new Dog(); a.speak();` 和 `Animal a = new Cat(); a.speak();`，虽然都是 `speak()`，但结果不同。

当然多态的含义远不止这些，还包括泛型等等

---

## 面向对象编程思想的优势

* **贴近现实世界**：代码更符合人类的思维习惯。
* **提高复用性**：通过继承、抽象类、接口实现复用。
* **更易维护**：模块化设计，修改影响更小。
* **可扩展性强**：新需求往往可以通过新增类或重写方法来实现。

---

🔎 打个比方：

* **面向过程**像是写菜谱，把做菜的步骤一条条写出来。
* **面向对象**像是创建一个“厨师对象”，他知道“洗菜()”、“炒菜()”、“上菜()”，然后我们只要让厨师去做就行。

---

以上都是概念，懂意思就行，下面才是重头戏

---

## 面向对象编程原则


面向对象编程不仅有 **思想**（封装、继承、多态），在工程实践中还总结出了一些 **设计原则**，指导我们写出更清晰、可维护、可扩展的代码。

这些原则通常被称为 **面向对象设计原则（OOD Principles）**，最著名的就是 **SOLID 五大原则**。

为什么学这个？学这个对代码的理解一定会有质的飞跃，软件工程讲究高内聚，低耦合，了解这些可以初步实现以上要求。
并且可以大大提供可读性，以免自己写的代码自己都嫌弃（bushi）

---

### 🌟 SOLID 五大原则

1. **单一职责原则（SRP, Single Responsibility Principle）**

    * **定义**：一个类只负责一件事（一个变化的原因）。
    * **好处**：降低类的复杂度，提高可读性和可维护性。
    * **例子**：

        * ❌ `UserService` 既负责用户登录，又负责数据库操作。
        * ✅ 拆成 `UserService`（业务逻辑）和 `UserRepository/userMapper`（数据库持久化）。

请参考本代码的controller、service、mapper三层，分别处理不同的逻辑。
- controller只负责接口，和前端交互，业务不参与设计
- service只负责设计业务逻辑，不关心谁能用，也不关心底层如何实现
- mapper只负责和数据库交互，提供统一的增删改查方法。

---

2. **开闭原则（OCP, Open-Closed Principle）**

    * **定义**：软件实体应该对扩展开放，对修改关闭。
    * **好处**：通过扩展而不是修改来应对需求变化。
    * **例子**：

        * ❌ 在 `Shape` 类里加 `if (type == "circle") ...`，以后每加一种图形都要改代码。
        * ✅ 定义抽象 `Shape`，具体类 `Circle`、`Rectangle` 各自实现 `draw()`，以后只需新增类，不改旧代码。

参考本代码例子：

orderService里的order123(int a)代码是这样的：

```java
public String order123(int a){
    if(a == 1){
        return "Pizza";
    } else if (a == 2) {
        return "noodles";
    }
    else{
        return "not";
    }
}
```

从 **功能上**没问题，但从 **面向对象设计原则**（特别是开闭原则 OCP）来看，确实存在缺陷：

* **为什么不符合 OCP？**

    * 因为每当你要新增一种食物（比如 "Burger"），就要 **修改这个方法的源码**，加一条 `else if (a == 3)`。
    * 这样导致类/方法 **对修改开放**，违背了“对修改关闭，对扩展开放”的思想。

---

### 更符合 OCP 的设计

思路：把食物抽象成类，通过扩展（新增类）来增加功能，而不是改现有代码。

#### 方式一：使用 **多态**

```java
import java.util.HashMap;
import java.util.Map;

interface Food {
    String getName();
}

class Pizza implements Food {
    @Override
    public String getName() {
        return "Pizza";
    }
}

class Noodles implements Food {
    @Override
    public String getName() {
        return "Noodles";
    }
}

// 点单逻辑
class OrderService {
    private Map<Integer, Food> menu = new HashMap<>();

    // 注册菜品
    public void addFood(int id, Food food) {
        menu.put(id, food);
    }

    // 点单
    public String order(int id) {
        Food food = menu.get(id);
        if (food == null) {
            return "Not Found";
        }
        return food.getName();
    }
}

public class Main {
    public static void main(String[] args) {
        OrderService service = new OrderService();

        // 初始化菜单
        service.addFood(1, new Pizza());
        service.addFood(2, new Noodles());

        // 点单
        System.out.println(service.order(1)); // Pizza
        System.out.println(service.order(2)); // Noodles
        System.out.println(service.order(3)); // Not Found

        // 新增食物 —— 只需要加一行，不改 OrderService
        service.addFood(3, new Food() {
            @Override
            public String getName() {
                return "Burger";
            }
        });

        System.out.println(service.order(3)); // Burger
    }
}



```

* 以后新增 `Burger` 只需要写 `class Burger implements Food`，不用动原有 `Food` 和 `OrderService`。

* 当然以上写法不是spring的规范，addFood的操作一般由更下层的实现。这里写个main函数只是举例子罢了。反正核心就是
尽量的少改动，而是多“加”

只要设计的好，所有的改都可以被加代替


---

#### 方式二：使用 **工厂 + Map 注册表**

更灵活的做法是用一个注册表：

```java
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class OrderService {
    private final Map<Integer, Supplier<Food>> menu = new HashMap<>();

    // 构造时注册食物
    public OrderService() {
        menu.put(1, Pizza::new);
        menu.put(2, Noodles::new);
    }

    public Food order(int a) {
        return menu.getOrDefault(a, () -> () -> "Not Found").get();
    }
}
```

以后新增 `Burger` 只需要：

```java
menu.put(3, Burger::new);
```

而不用修改 `order()` 方法。

---

🔑 **结论**
你的写法逻辑上没错，但不符合 **开闭原则**。

* 在 OOP 思想里，我们希望“新增功能 = 新增类/对象”，而不是“修改旧方法”。
* 用接口、多态、工厂模式等手段，可以让系统更符合开闭原则。

---

要不要我帮你对比写一个 **“不符合 OCP vs 符合 OCP”** 的完整 Java 示例，展示当新增食物时，两种写法的差异？


---

3. **里氏替换原则（LSP, Liskov Substitution Principle）**

    * **定义**：子类对象可以替换父类对象，并且程序行为保持正确。
    * **好处**：保证继承结构的正确性。
    * **例子**：

        * ❌ `Penguin extends Bird`，但 `Penguin.fly()` 不合理。
        * ✅ 重新抽象：`Bird` 分为 `FlyingBird` 和 `NonFlyingBird`。

---

4. **接口隔离原则（ISP, Interface Segregation Principle）**

    * **定义**：客户端不应该依赖它不需要的方法。
    * **好处**：避免“胖接口”，让接口更专一。
    * **例子**：

        * ❌ 一个 `Animal` 接口有 `fly()`、`swim()`、`run()`，结果 `Dog` 也要实现 `fly()`。
        * ✅ 拆成 `Flyable`、`Swimmable`、`Runnable` 接口，按需实现。

---

5. **依赖倒置原则（DIP, Dependency Inversion Principle）**

    * **定义**：高层模块不应该依赖低层模块，二者都应该依赖抽象。
    * **好处**：降低耦合，方便替换实现。
    * **例子**：

        * ❌ `OrderService` 直接依赖 `MySQLDatabase`。
        * ✅ `OrderService` 依赖 `Database` 接口，具体使用 `MySQLDatabase` 或 `MongoDBDatabase`。

---

### 其他常见的 OOP 原则

除了 SOLID，还有一些常见的补充原则：

* **迪米特法则（LoD, Law of Demeter）**：一个对象应当尽可能少地了解其他对象（“只和朋友说话，不和陌生人说话”）。
* **组合优于继承**：在能用组合（has-a）解决问题时，尽量不要用继承（is-a），避免类层次过深。
* **最少惊讶原则**：代码的行为要符合直觉，减少意外。




## 面向对象编程设计模式

还在更新。。。。
