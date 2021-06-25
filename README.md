# Assess
assess

直观、客观

产品定义（product）
```json
{
  "prodId": "产品id",
  "prodName": "产品完整名称",
  "underBrand": "所属品牌",
  "underSeries": "所属系列",
  "prodAlias": ["别名列表"],
  "prodSpec": ["产品规格列表"],
  "ownerId": "所有者id(企业或个人)",
  "ownerName": "所有者名称",
  "createTime": "产品实际创建时间",
  "inputTime": "录入系统时间",
  "updateTime": "最后更新时间",
  "prodTags": ["标签列表"],
  "broadCategory": "产品大类",
  "subCategory": "产品子类",
  "lastCategory": "产品末类",
  "evaluate": ["评价标签列表"],
  "prodParams": {}
}
```

评论定义（discuss）
```json

```

用户定义（user）

一个用户可以有多种身份 
* 管理员：111
* 用户：可以操作(assess,discuss)
* 产品所有者：可以操作(product)
```json
{
  "nikeName": "昵称",
  "userId": "用户id",
  "userType": "(int)用户类型",
  "phoneNum": "手机号",
  "password": "密码",
  "prodNum": "int 产品数量",
  "prodTags": ["产品标签"],
  "introduce": "介绍"
}
```

测评(assess)

```json
{
  "assId": "测评id",
  "assTitle": "标题",
  "ownerId": "所有者id",
  "ownerName": "所有者名称",
  "assType": "类型video/text",
  "originSource": "来源",
  "address": "地址",
  "assentNum": "(int)赞成数",
  "dissentNum": "(int)反对数",
  "prodIdList": ["测评产品id列表"],
  "prodNameList": ["测评产品名称列表"],
  "broadCategory": "产品大类",
  "subCategory": "产品子类",
  "lastCategory": "产品末类",
  "assTags": ["标签列表"],
  "assIntroduce": "介绍",
  "createTime": "创建时间",
  "inputTime": "录入系统时间",
  "inputUserId": "录入者id",
  "updateTime": "最后更新时间",
  "updateUserId": "最后更新者id"
}
```