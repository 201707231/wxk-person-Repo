webpackJsonp([8],{mqJ6:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=a("0xDb"),r={data:function(){return{devID:"",list:[],listLoading:!1}},mounted:function(){console.log("devParts Mounted")},created:function(){console.log("devParts created"),this.devID=this.$route.query.devID,console.log("devID:",this.devID),this.devID&&this.getDevParts()},methods:{getDevParts:function(){var e=this;console.log("getDevParts"),this.listLoading=!0,this.api({url:"/dev/listDevParts",method:"get",params:{devID:this.devID}}).then(function(t){console.log(t),e.list=t.list,e.listLoading=!1})},TimeFormatter:function(e,t,a,r){return Object(l.a)(a)}}},n=a("XyMi"),i=Object(n.a)(r,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",[a("el-form-item",[a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:"part:add",expression:"'part:add'"}],attrs:{type:"primary",icon:"plus"}},[e._v("为设备添加配件 ")])],1)],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{data:e.list,border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"配件编号",prop:"partID"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"配件类型",prop:"partType"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"配件状态",prop:"partStatus"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"批次",prop:"partBatchNum"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"备注",prop:"partNote"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"创建时间",prop:"createTime",formatter:e.TimeFormatter}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"管理"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:"device:delete",expression:"'device:delete'"}],attrs:{type:"danger",icon:"delete"}},[e._v(" 删除 ")])]}}])})],1)],1)},[],!1,null,null,null);t.default=i.exports}});