### 自定义mybatis框架简答题

#### 1. Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？

1. 动态sql是指在进行sql操作的时候,传入的参数对象或者参数值,根据匹配的条件,有可能需要动态的去判断是否为空,循环,拼接等情况

2. if 和 where 、include、foreach 、prefix、suffix等常用标签

3. 动态sql的执行原理

   ~~~xml
      首先在解析xml配置文件的时候,会有一个SqlSource sqlSource = langDriver.createSqlSource(configuration, context, parameterTypeClass) 的操作 createSqlSource底层使用了XMLScriptBuilder来对xml中的标签进行解析
     XMLScriptBuilder调用了parseScriptNode()的方法,在parseScriptNode()的方法中有一个parseDynamicTags()方法,会对nodeHandlers里的标签根据不同的handler来处理不同的标签
      然后把DynamicContext结果放回SqlSource中DynamicSqlSource获取BoundSql在Executor执行的时候,调用DynamicSqlSource的解析方法,并返回解析好的BoundSql,和已经排好序,需要替换的参数
   ~~~

   

#### 2.Mybatis是否支持延迟加载？如果支持,它的实现原理是什么？

~~~xml
MyBatis仅支持association关联对象和collection关联集合对象的延迟加载,association指的就是一对一,collection指的就是一对多查询。
在Mybatis配置文件中,可以配置是否启用延迟加载lazyLoadingEnabled=true|false。
它的原理是,使用CGLIB创建目标对象的代理对象,当调用目标方法时,进入拦截器方法,比如调用a.getB().getName(),拦截器invoke()方法发现a.getB()是null值,
那么就会单独发送事先保存好的查询关联B对象的sql,把B查询上来,然后调用a.setB(b),于是a的对象b属性就有值了,接着完成a.getB().getName()方法的调用。这就是延迟加载的基本原理。
~~~

#### 3.Mybatis都有哪些Executor执行器？它们之间的区别是什么？

 1. SimpleExecutor、ReuseExecutor、BatchExecutor

 2. 区别

    ~~~xml
    	SimpleExecutor：每执行一次update或select,就开启一个Statement对象,用完立刻关闭Statement对象。
    	ReuseExecutor：执行update或select,以sql作为key查找Statement对象,存在就使用,不存在就创建,用完后,不关闭Statement对象,而是放置于Map内,供下一次使用。简言之,就是重复使用Statement对象		BatchExecutor：执行update（没有select,JDBC批处理不支持select）,将所有sql都添加到批处理中(addBatch()),等待统一执行（executeBatch()）,它缓存了多个Statement对象,每个Statement对象都是addBatch()完毕后,等待逐一执行executeBatch()批处理。与JDBC批处理相同。
    ~~~

    

#### 4.简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？

~~~xml
 一级缓存：
		存储结构： hashMap
		范围 ： MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象，SqlSession对象中会有一				个新的Executor对象。Executor对象中持有一个新的PerpetualCache对象；当会话结束时，				   SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。
		失效场景 ： 数据库做commit的时候(增删改的时候)
 二级缓存：
		存储结构： hashMap
		范围 ：多个sqlSession可以共享一个一级的二级缓存区域。二级缓存区域按namespace区分
		失效场景 ： 数据库做commit的时候(增删改的时候)
~~~

#### 5.简述Mybatis的插件运行原理，以及如何编写一个插件

~~~xml
Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4种接口的插件，Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可
~~~

