[![Build Status](https://travis-ci.org/codingapi/simple-mybatis.svg?branch=master)](https://travis-ci.org/codingapi/simple-mybatis) [![codecov](https://codecov.io/gh/codingapi/simple-mybatis/branch/master/graph/badge.svg)](https://codecov.io/gh/codingapi/simple-mybatis)
# simple-mybatis

## 前言
随着springboot开发方式的流行,但是还有好多人在使用mybatis时依旧采用xml的方式配置mapper,该框架出现的主要原因是为了让Mybatis更方便的在springboot项目下上使用。

## 框架说明
* 框架依赖并兼容jpa注解
* 提供了常用操作的Mapper封装
* 基于pagehelper提供了分页的规范支持
* 提供Query方式对查询语句的支持
* 提供Tree数据格式的规范支持

## 仓库地址
```$xslt
<dependency>
    <groupId>com.codingapi</groupId>
    <artifactId>simple-mybatis</artifactId>
    <version>1.0.0</version>
 </dependency>
```

## 使用说明

* jpa注解的使用
```java
@Data
@Table(name = "t_demo")
public class Demo implements ITree<Long>{ //ITree根据业务需要可不实现

  //Id 注解  
  @Id
  private Long id;

  //数据库字段与属于想一致时可忽略@Column注解
  private Long superId;

  private String name;

  //字段名称  
  @Column(name = "s_module")
  private String module;

  private String myName;
  
  //忽略字段 
  @Transient
  private String test;

}

```
* `CommandMapper` 提供了所有常用的写操作

```java

  //保存数据，会返回主键Id
  int save(T t);

  //批量保存  
  int saveAll(@Param("list") List<T> list);

  //修改数据，修改数据只会替换非null对象
  int update(T t);

  //删除数据，删除条件为Id
  int delete(T t);

  //批量删除，删除条件为Id
  int deleteAll(@Param("list") List<T> list);

  //通过Id直接删除
  int deleteById(@Param("id") Object id);

  //通过Id批量删除
  int deleteAllById(@Param("list") List id);
```

* `QueryMapper`提供常用的查询操作
```java

  //通过Id查询
  T getById(@Param("id") Object id);

  //查询属于数据
  List<T> findAll();

  //通过Query查询数据
  List<T> query(@Param("query") Query query);

  //通过Query查询试图数据，返回List Map对象
  List<Map<String,Object>> queryMap(@Param("query") Query query);

  //通过Query查询试图数据，返回List Bean对象
  <V> List<V> queryView(Class<V> clazz, Query query);

```

* `SimpleMapper`提供了`QueryMapper`与`CommandMapper`的功能

* `IPageQuery` 提供基于MybatisHelper的分页支持

```java
    PageList<Demo> pageList =  demoMapper.page(1, 5, demoMapper::findAll);
	log.info("page:{}",pageList);
```

* `ITreeQuery` 提供树形数据结构组装

```java
    //实现ITree接口
    public class Demo implements ITree<Long>{
      //fields get set ...
    }

    List<TreeList<Demo>> treeLists =  demoMapper.tree( demoMapper::findAll,0L);
	log.info("treeLists:{}",treeLists);
```

* 用户根据自己的Mapper来选择集成对应的功能接口

```java
    @Mapper
    public interface DemoMapper extends QueryMapper<Demo>,IPageQuery<Demo>,ITreeQuery<Demo,Long> {
    
    
    }
    
    @Mapper
    public interface DemoMapper extends SimpleMapper<Demo> {
    
    
    }
    @Mapper
    public interface DemoMapper extends CommandMapper<Demo> {
    
    
    }

```


* query使用说明     
query 通过QueryBuilder来创建，当查询返回的是表的数据，则不需要写select语句，直接拼接where()查询条件。   
若查询返回的是视图格式的数据则需要写select语句，select语句中可以写join关联.    
select语句中的字段可以用下划线，也可以直接处理成小驼峰。都可以转成java bean对象。     
```java
    @Test
	void viewList(){
		List<DemoView> list =
				demoMapper.queryView(
						DemoView.class,
						QueryBuilder.Build()
								.select("select d.name,d.super_id from t_demo d join t_test t on t.demo_id = d.id ")
								.where()
								.date("time","2020-04-12")
								.or()
								.equal("id",31)
								.and()
								.in("id",1,2,3,4,5,6,7,8,9,10)
								.and()
								.like("name","2")
								.orderBy("name desc")
								.builder());
		log.info("list:{}",list);
	}

	@Test
	void queryList(){
		//select * from t_demo where name = '123'
		List<Demo> list = demoMapper.query(QueryBuilder.Build().where().equal("name","123").builder());
		log.info("list:{}",list);
	}
```
## 示例

https://github.com/1991wangliang/simple-mybatis-demo

## 支持
[issue](https://github.com/codingapi/simple-mybatis/issues)

## 使用建议

设计数据库时可以先定义类,然后通过jap扫描这些entity来创建数据库，这样做不仅可以完成数据库的设计，也可以直接完成entity的定义，由于框架兼任了jpa注解也不会在框架使用这些entity上出现错误。
