## 毕设idea

登陆部分

用shiro整合ssm项目，做角色管理和身份验证

用户	证件号码（一般身份证号）	对身份证进行正则校验

医生 管理员	证件号码（为职工证件号16115073003）	进行正则校验 





## todolist补充

v-bind属性绑定

登陆部分前端验证：

可以把两种角色的登陆验证放在一起。医生界面？

登录部分后端验证（角色管理和身份验证）







## 踩坑挖坑填坑日志

##### vue表单提交报requesturl404：

提交了两次表单，表单提交部分的的type应该为button而不是submit。submit会直接出发form表单的事件成为一个同步提交，也就是说提交了两次表单，一次同步，一次异步，不知道哪一个才是正确的提交。因此就会报错。



##### anxios提交表单后台获取为null：

因为anxios和ajax提交数据的格式不一样，

解决办法：	

1.@resquestBody  User user 绑定接受数据的该对象。

2.在vue前端装qs，在进行anxios传参的时候先对data进行格式转换，this.$qs.stringify(data)



##### 用get（1）get不到想要的集合里面的1位置的数据

因为return findlist返回的是一个集合数组，get（0）返回的是整个集合，所以 get（1）拿到的数据为null，故应该调用格式为：findlist.get(0).username



##### 跨域问题

调用数据的跨域问题：@CrossOrigin使用注解解决跨域问题

request跨域问题：

在前端vue的index.js里设置允许跨域

```js
  proxyTable: {
      '/login.action':{
        target:'http://localhost:8080/login.action',
        changeOrigin:true,
        pathRewrite:{'^/login.action':''}
      }
```



##### 请求自动加端口号

其实并不是vue给请求自动加上端口号，而是浏览器没有识别的端口，就自动给请求加上当前请求处的端口号。

在设置了proxyTable的情况下请求后端的时候requesturl还是前端端口号+请求路径。

原因是，proxyTable是一个虚拟代理，实际上vue的真实请求路径确实是前端端口号+请求路径。只不过node.js会根据虚拟代理对该请求进行转发，从而请求到正确的后端接口去获取数据。

若在检查了前端代理以及请求路径无问题的情况下出现requestur404，则是因为后端的接收有问题。尝试重写。（目前具体原因也还没有查出来，但是重写就对了）

##### vue封装需要的数据

```vue
var data={
                   inputusername : this.formMess.inputusername,
                   inputpassword : this.formMess.inputpassword
                }
```



##### 多级菜单问题

点击的时候获取当前点击的内容即name，然后把name返回给后台 获取departmenttypeid，

根据改id获取该id的所有department。

最终所需数据：

```vue 
 headerData: [{
            name:  "",
            list: [],
            show: false
          }, {
            name: "",
            list: [],
            show: false
          }, {
            name: '',
            list: [],
            show: false
          }, {
            name: '',
            list: ['子集', '子集', '子集', '子集', '子集'],
            show: false
          }, {
            name: '',
            list: ['子集', '子集', '子集', '子集', '子集'],
            show: false
          },{
            name: '',
            list: ['子集', '子集', '子集', '子集', '子集'],
            show: false
          },{
            name: '',
            list: ['子集', '子集', '子集', '子集', '子集'],
            show: false
        }]
```

 

已获得所有的departmenttype，如何放进去？

```js
for(var i;i<detypenamelist.length;i++){
    headerData.name=this.dptypelist[i].departmentname;
     list.put
}
```



正确思路：

第一步：直接在后端拼接json数据，前端仅调用一次接口就可以获得想要的数据。

第二步：分析该json数据。一层为list，二层为多个map，三层为list。一层嵌套二层，二层嵌套三层，依次类推。

第三步：上代码

``` java

@ResponseBody
    @CrossOrigin
    @RequestMapping("/findDepartmentByDetypeId.action")
//    public List<Departmenttype> findAllDepartment() {
    public List findAllDepartment() {

        //Map topFloorMap=new HashMap();
        List secondFloorList=new ArrayList();
        //topFloorMap.put("headerData",secondFloorList);
        int a;
        List<Departmenttype> findlist = findAll();

        for (int i = 0; i < findlist.size(); i++) {
            System.out.println(findlist.get(i).getDepartypementname());
            a = findlist.get(i).getDepartmenttypeid();

            List<Departmenttype> tempDepartmenttypeList=departmenttypeService.findDepartmentBytypeId(a);
            System.out.println( departmenttypeService.findDepartmentBytypeId(a));

            Map thirdFloorMap=new HashMap();
            thirdFloorMap.put("name","");
            thirdFloorMap.put("list","");
            thirdFloorMap.put("show",false);
            secondFloorList.add(thirdFloorMap);

            List fourthList=new ArrayList();
            for (Departmenttype d:tempDepartmenttypeList)
                for (int j=0;j<d.getDepartments().size();j++)
                    fourthList.add(d.getDepartments().get(j).getDepartmentname());

            thirdFloorMap.put("list",fourthList);
            thirdFloorMap.put("name",findlist.get(i).getDepartypementname());

        }
      //  System.out.println(topFloorMap);
        return secondFloorList;
    }
```

##### ssm下的mybatis的联表查询问题

分为一对多和多对一的关系。

比如部门和部门类型就是，多对一的关系，在department中加入departmenttype的实体，在查询的时候将查询到的departmenttype信息封装到该实体。

```java
@Data
public class Department {
    @Id
    @Column(name = "id")
    private Integer departmentid;
    @Column(name = "department_name")
    private String departmentname;
    @Column(name = "detype_id")
    private Integer dtypeid;
    @Column(name = "department_profile")
    private String deprofile;
    @Transient
    private Departmenttype department_type;
}
```

比如根据部门类型id查询部门就是一对多的关系。同样的，多的一方需要用一个list封装起来。指定类型为Department。

``` java

@Data
public class Departmenttype {
    @Id
    @Column(name = "id")
    private Integer departmenttypeid;
    @Column(name = "detype_name")
    private String departypementname;
    @Transient
    private List<Department> departments;
}
```

在mapper里面写查询的时候注意的点是，需要在多的一方写上@Many注解。

```java
@Repository
public interface DepartmenttypeMapper extends Mapper<Departmenttype> {

    @Select("select * from departmenttype where id = #{departmenttypeId}")
    @Results({
            @Result(property="departmenttypeid", column="id"),
            @Result(property="departypementname", column="detype_name"),
            @Result(property="departments", column="id", many=@Many(
                    select="com.seventeen.hospital.mapper.DepartmentMapper.selectDepartmentByDeptypeId"
            ))
    })
    List<Departmenttype> selectDepartmenttypeById(int departmenttypeId);

}
```

```java
@Repository
public interface DepartmentMapper extends Mapper<Department> {

   @Select("select * FROM department,departmenttype WHERE department.detype_id=departmenttype.id")
   @Results({
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "department_type.departmenttypeid",column = "id"),
           @Result(property = "department_type.departmentname",column = "detype_name")
   })
   List<Department> findAllDepartment();

   @Select("select * from department where detype_id = #{deptypeid}")
   @Results(value={
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "dtypeid",column = "detype_id"),
   })
   List<Department> selectDepartmentByDeptypeId(int deptypeid);


}
```



##### 后台模板（前端）与后台的数据交互问题

登录问题：

登录成功之后需要跳转到管理员首页，失败则需要返回失败，前端提示根据失败提示管理员输入错误。

思路：不在后端做页面的跳转，要在前端页面的相互跳转。后端给前端发送数据，直接return，并且加上@ResponseBody的注解。在前端则是用ajax对后端的数据进行请求。