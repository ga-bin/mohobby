"use strict";(self["webpackChunkmohobby"]=self["webpackChunkmohobby"]||[]).push([[1389],{41612:function(e,t,r){r.d(t,{Z:function(){return I}});var o=r(43423),n=r(99223),i=r(4324),s=r(65495),m=r(65808),a=r(54525),c=r(79500),u=r(74611),l=r(99709),d=(r(57658),function(){var e=this,t=e._self._c;return t(l.Z,{attrs:{app:""}},[t("div",{staticClass:"text-center pa-10"},[t(o.Z,{staticClass:"mb-4",attrs:{color:"grey darken-1",size:"64"}},[t(s.Z,{attrs:{"aspect-ratio":"30",src:r(6935)(`./${e.profileImg}`)}})],1),t("h4",{staticStyle:{color:"#2b2b2b","font-weight":"bolder"}},[e._v(e._s(e.memberId))])],1),t(n.Z),t(m.Z,e._l(e.links,(function(r){return t(a.Z,{key:r.text,attrs:{link:""},on:{click:function(t){return e.$router.push({path:r.route})}}},[t(u.Z,[t(i.Z,[e._v(e._s(r.icon))])],1),t(c.km,[t(c.V9,[e._v(e._s(r.text))])],1)],1)})),1)],1)}),h=[],f={data(){return{memberId:"",profileImg:"comfuck.jpg",links:[{icon:"mdi-account",text:"나의 프로필",route:"/mypageprofile"},{icon:"mdi-instagram",text:"나의 SNS",route:"/mypagesns"},{icon:"mdi-human-male-board",text:"나의 강의",route:"/mypageclass"},{icon:"mdi-account-group",text:"나의 소모임",route:"/mypagemoim"},{icon:"mdi-stairs-up",text:"나의 챌린지",route:"/mypagechallenge"}]}},created(){this.setMemberInfo()},methods:{setMemberInfo(){if(this.memberId=this.$store.state.id,!this.memberId)return this.memberId="비회원",void(this.profileImg="comfuck.jpg");"admin"==this.memberId?this.profileImg="logo-color.png":""!=this.memberId&&"admin"!=this.memberId&&this.getMemberInfo()},getMemberInfo(){const e=this;this.axios({url:"/member/"+this.memberId,method:"get"}).then((function(t){""!=t.data&&(console.log(t.data),e.memberId=t.data.memberId,e.profileImg=t.data.profileImg)})).catch((function(e){console.log(e)}))}}},p=f,b=r(1001),g=(0,b.Z)(p,d,h,!1,null,"f8cd13c2",null),I=g.exports},4257:function(e,t,r){r.r(t),r.d(t,{default:function(){return u}});var o=function(){var e=this,t=e._self._c;return t("div",[t("UserSidebar"),t("h1",[e._v("마이페이지챌린지")])],1)},n=[],i=r(41612),s={name:"",components:{UserSidebar:i.Z},data(){return{sampleData:""}},setup(){},created(){},mounted(){},unmounted(){},methods:{}},m=s,a=r(1001),c=(0,a.Z)(m,o,n,!1,null,null,null),u=c.exports}}]);
//# sourceMappingURL=1389.c6cd3554.js.map