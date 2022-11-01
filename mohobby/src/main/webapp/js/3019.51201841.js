"use strict";(self["webpackChunkmohobby"]=self["webpackChunkmohobby"]||[]).push([[3019],{3019:function(e,t,o){o.r(t),o.d(t,{default:function(){return b}});var s=o(40998),a=o(64562),i=o(34145),r=o(69256),n=o(31267),l=o(43201),c=o(3059),d=o(17808),m=(o(57658),function(){var e=this,t=e._self._c;return t("div",{staticClass:"container-fluid"},[t("div",{staticClass:"row no-gutter"},[t("div",{staticClass:"col-md-6 d-none d-md-flex bg-image"}),t("div",{staticClass:"col-md-6 bg-light"},[t("div",{staticClass:"login d-flex align-items-center py-5"},[t("div",{staticClass:"container"},[t(s.Z,{attrs:{id:"inspire"}},[t(c.Z,{staticClass:"lighten-4"},[t(r.Z,{staticStyle:{"max-width":"450px"},attrs:{"fill-height":""}},[t(l.Z,{attrs:{"align-center":"",row:"",wrap:""}},[t(n.Z,{attrs:{xs12:""}},[t(i.Z,[t("div",{staticClass:"pa-10"},[t("h1",{staticClass:"mb-10",staticStyle:{"text-align":"center"}},[e._v(" Login ")]),t("form",{on:{submit:function(t){return t.preventDefault(),e.checkMember.apply(null,arguments)}}},[t(d.Z,{attrs:{label:"아이디","prepend-inner-icon":"mdi-account",color:"#2ac187"},model:{value:e.memberId,callback:function(t){e.memberId=t},expression:"memberId"}}),t(d.Z,{attrs:{color:"#2ac187","prepend-inner-icon":"mdi-lock",type:"password",label:"비밀번호"},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}}),t(a.Z,{staticClass:"mb-3",attrs:{type:"submit",color:"#2ac187",depressed:"",large:"",block:"",dark:""}},[e._v(" Login ")]),t(a.Z,{attrs:{color:"#2ac187",depressed:"",large:"",block:"",dark:""},on:{click:function(t){return e.$router.push("/register")}}},[e._v(" Sign Up ")]),t("br"),t("span",{staticStyle:{"margin-left":"70px","font-weight":"bold"},on:{click:function(t){return e.findMemberId()}}},[e._v(" 아이디찾기")]),t("span",{staticStyle:{"margin-left":"70px","font-weight":"bold"},on:{click:function(t){return e.findMemberPassword()}}},[e._v(" 비밀번호 찾기")]),t("br"),t("br"),t("a",{attrs:{id:"custom-login-btn"},on:{click:function(t){return e.kakaoLogin()}}},[t("img",{staticStyle:{height:"40px",width:"170px","margin-right":"30px"},attrs:{src:"https://www.gb.go.kr/Main/Images/ko/member/certi_kakao_login.png"}})]),t("div",{attrs:{id:"naverIdLogin"}})],1)])])],1)],1)],1)],1)],1)],1)])])])])}),u=[],h=(o(1120),{data:()=>({step:1,naverLogin:null,memberId:"",password:"",loginInfo:null,email:"",token:""}),props:{source:String},beforeCreate(){},created(){},beforeMount(){},mounted(){this.$store.commit("setIsLoginFalse"),this.$store.commit("logout"),this.$store.commit("setUserData",null),this.naverLogin=new window.naver.LoginWithNaverId({clientId:"qtK4gDKw7gcdHhTwYZpV",callbackUrl:"http://localhost:8080/login",isPopup:!1,loginButton:{color:"green",type:3,height:40,width:150}}),this.naverLogin.init(),this.naverLogin.getLoginStatus((e=>{const t=this;if(e){console.log(e),console.log(this.naverLogin.user);var o=this.naverLogin.user.getEmail();if("undifined"==o||null==o)return alert("이메일은 필수 정보입니다. 정보 제공을 동의해주세요."),void this.naverLogin.reprompt();t.email=o,t.checkMemberByEmail()}else console.log("callback 처리에 실패하였습니다.")}))},beforeUpdate(){},updated(){},beforeUnmount(){},unmounted(){},methods:{kakaoLogin(){const e=this;window.Kakao.Auth.loginForm({scope:"profile_nickname, account_email, gender, birthday",success:function(t){console.log(t),window.Kakao.API.request({url:"/v2/user/me",success:t=>{const o=t.kakao_account;e.token=o.access_token,e.email=o.email,e.checkMemberByEmail()}})},fail:function(e){console.log(e)}})},kakaoLogout(){Kakao.Auth.getAccessToken()?Kakao.Auth.logout((function(e){alert(e+" logout")})):console.log("Not logged in.")},checkMember(){console.log(this.memberId,this.password);const e=this;this.axios({url:"/membercheck",method:"post",data:{memberId:this.memberId,password:this.password}}).then((function(t){if(""!=t.data&&"admin"!=t.data.memberId){if(console.log("if문 안에"+t.data),null!=t.data.delDate)return void e.$swal.fire("탈퇴한 회원입니다.");e.$store.state.id=e.memberId,e.$store.commit("setUserData",t.data),e.$router.push("/")}else""!=t.data&&"admin"==t.data.memberId?(console.log("else if문 안에"+t.data),e.$store.state.id=e.memberId,e.$store.commit("setUserData",t.data),e.$router.push("/adminuser")):e.$swal.fire({icon:"error",title:"아이디, 비밀번호가 일치하지 않습니다."})})).catch((function(e){console.log(e)}))},checkMemberByEmail(){console.log(this.email);const e=this;this.axios({url:"/memberEmail/"+this.email,method:"get"}).then((function(t){if(console.log(t),""!==t.data&&t.data.constructor===Object){if(console.log(t.data),null!=t.data.delDate)return void e.$swal.fire("탈퇴한 회원입니다.");if(4==t.data.role)return void e.$swal.fire("관리자에 의해 접근 금지된 유저입니다.");e.$store.state.id=e.memberId,e.$store.commit("setIsLoginTrue"),e.$store.commit("setUserData",t.data),this.$router.push("/")}else e.$router.push({name:"register",params:{email:e.email}})})).catch((function(e){console.log(e)}))},findMemberId(){const e=this;this.$swal.fire({title:"가입 시 입력한 이메일을 입력하세요",html:'<input type="text" id="email" class="swal2-input" placeholder="email">',confirmButtonText:"제출",cancelButtonText:"취소",focusConfirm:!1,preConfirm:()=>{const e=this.$swal.getPopup().querySelector("#email").value;return e||this.$swal.showValidationMessage("이메일을 입력해 주세요"),{email:e}}}).then((t=>{e.email=t.value.email,this.axios({url:"/memberEmail/"+e.email,method:"get"}).then((function(t){e.$swal.fire("가입하신 아이디는"+t.data.memberId+"입니다.")})).catch((function(e){console.log(e)}))}))},findMemberPassword(){const e=this;this.$swal.fire({title:"아이디를 입력하세요",html:'<input type="text" id="memberId" class="swal2-input" placeholder="memberId">',confirmButtonText:"제출",cancelButtonText:"취소",focusConfirm:!1,preConfirm:()=>{const e=this.$swal.getPopup().querySelector("#memberId").value;return e||this.$swal.showValidationMessage("아이디를 입력해 주세요"),{memberId:e}}}).then((t=>{e.memberId=t.value.memberId,this.axios({url:"/member/"+e.memberId,method:"get"}).then((function(t){e.$swal.fire("찾으시는 비밀번호는"+t.data.password+"입니다.")})).catch((function(e){console.log(e)}))}))}}}),g=h,p=o(1001),f=(0,p.Z)(g,m,u,!1,null,null,null),b=f.exports},4245:function(){},34145:function(e,t,o){o(4245);var s=o(83434),a=o(88126),i=o(58860),r=o(67678);t["Z"]=(0,r.Z)(a.Z,i.Z,s.Z).extend({name:"v-card",props:{flat:Boolean,hover:Boolean,img:String,link:Boolean,loaderHeight:{type:[Number,String],default:4},raised:Boolean},computed:{classes(){return{"v-card":!0,...i.Z.options.computed.classes.call(this),"v-card--flat":this.flat,"v-card--hover":this.hover,"v-card--link":this.isClickable,"v-card--loading":this.loading,"v-card--disabled":this.disabled,"v-card--raised":this.raised,...s.Z.options.computed.classes.call(this)}},styles(){const e={...s.Z.options.computed.styles.call(this)};return this.img&&(e.background=`url("${this.img}") center center / cover no-repeat`),e}},methods:{genProgress(){const e=a.Z.options.methods.genProgress.call(this);return e?this.$createElement("div",{staticClass:"v-card__progress",key:"progress"},[e]):null}},render(e){const{tag:t,data:o}=this.generateRouteLink();return o.style=this.styles,this.isClickable&&(o.attrs=o.attrs||{},o.attrs.tabindex=0),e(t,this.setBackgroundColor(this.color,o),[this.genProgress(),this.$slots.default])}})},31267:function(e,t,o){o(19027);var s=o(27549);t["Z"]=(0,s.Z)("flex")},43201:function(e,t,o){o(19027);var s=o(27549);t["Z"]=(0,s.Z)("layout")}}]);
//# sourceMappingURL=3019.51201841.js.map