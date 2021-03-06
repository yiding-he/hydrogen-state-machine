# hydrogen-state-machine

hydrogen-state-machine 是一个简单的状态机。

### 状态机的基本概念

- 状态机用来帮助处理对象的**状态**变化。当对象持久化到数据库时，也就是处理数据库记录的状态变化。
- **状态**是属于某个对象的。我们会直观的将状态看作是一个对象的属性。这里的对象可以是指数据库中的一条记录，或者已经加载到内存中的对象。
- **事件**是触发状态变化的缘由。一个事件会影响一个或多个对象。当一个对象受到一个事件的影响时，状态机就要负责计算_这个影响过程中的状态变化_。
- 但是状态机不负责判断一个事件会影响哪些对象，这部分由具体的业务逻辑来决定。
- 当事件发生时，对象当前的状态称为**前置状态**，状态机通过计算，得到这个对象的**后置状态**。
- 一个状态机包含一组**规则**，状态机通过查询**规则**来得到后置状态的值。
- 状态机只负责输出计算结果，不负责将对象的状态从前置状态变为后置状态。
- 状态机在这个基础上再加入**侦听**（Listener）和 **拦截**（Interceptor）机制，以满足更复杂的场景需要。

### 使用状态机的好处

状态机的作用就是将处理状态变化的代码提取成结构化的表达，也就是将 `if-else` 指令提取成一组相同结构的规则记录，使得：

- 业务方能够更加容易看懂并核查这些规则；
- 简化代码的编写；
- 方便日志打印、调试、测试和故障排查。

### hydrogen-state-machine 的特性

- 线程安全
- 处理方式是同步的，因此可以在数据库事务中使用


