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



##### vue封装需要的数据

```vue
var data={
                   inputusername : this.formMess.inputusername,
                   inputpassword : this.formMess.inputpassword
                }
```



菜单引入：

点击的时候获取当前点击的内容即name，然后把name返回给后台 获取departmenttypeid，

根据改id获取该id的所有department。



已获得所有的departmenttype，如何放进去？

```js
for(var i;i<detypenamelist.length;i++){
    headerData.name=this.dptypelist[i].departmentname;
     list
}
```

