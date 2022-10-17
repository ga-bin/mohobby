<template>
  <v-navigation-drawer 
  app>
   <div class="text-center mt-4 pa-10">
        <v-avatar class="mb-4" color="grey darken-1" size="64">
        <v-img
          aspect-ratio="30"
          :src="require(`@/assets/image/user/${profileImg}`)"
        ></v-img>
      </v-avatar>
      <h4 class="white--text">{{ memberId }}</h4>

     <!-- 소모임 회원 -->
     <div class="right" v-if="right==1">
       <v-btn text @click="invite()">
         <v-icon small color="white">mdi-plus-circle-outline</v-icon>
         <div style="color:white">초대하기</div>
       </v-btn>
     </div>

     <!-- 설정 안되어있는 모하비 회원 -->
     <div class="right" v-if="right==0 && member==0">
       <div class="text-center">
       <v-dialog
         v-model="dialog"
         width="500"
       >
         <template v-slot:activator="{ on, attrs }">
           <v-btn
             text
             v-bind="attrs"
             v-on="on"
           >
           <v-icon small color="white">mdi-plus-circle-outline</v-icon>
           <div style="color:white">가입하기</div>
           </v-btn>
         </template>

         <v-card>
           <v-card-title class="text-h5 grey lighten-2">
             가입하기
           </v-card-title>

           <v-card-text>
             <br>
             이 소모임은 가입조건이 설정되어 있습니다.
             <br>
             마이페이지에서 성별과 나이를 설정해주십시오.
           </v-card-text>

           <v-divider></v-divider>

           <v-card-actions>
             <v-spacer></v-spacer>
             <v-btn
               color="primary"
               text
               @click="goMypage()"
             >
               확인
             </v-btn>

             <v-btn
               class="ml-3"
               color="primary"
               text
               @click="dialog=false"
             >
               취소
             </v-btn>
           </v-card-actions>
         </v-card>
       </v-dialog>
     </div>
     </div>

     <!-- 설정 되어있는 모하비 회원 -->
     <div class="right" v-if="right==0 && member==1">
       <div class="text-center">
       <v-dialog
         v-model="dialog"
         width="500"
       >
         <template v-slot:activator="{ on, attrs }">
           <v-btn
             text
             v-bind="attrs"
             v-on="on"
           >
           <v-icon small color="white">mdi-plus-circle-outline</v-icon>
           <div style="color:white">가입하기</div>
           </v-btn>
         </template>

         <v-card>
           <v-card-title class="text-h5 grey lighten-2">
             가입하기
           </v-card-title>

           <v-card-text>
             <br>
             이 소모임에 가입하기를 원하시면 아래 가입버튼을 눌러주십시오!
           </v-card-text>

           <v-divider></v-divider>

           <v-card-actions>
             <v-spacer></v-spacer>
             <v-btn
               color="primary"
               text
               @click="goMain()"
             >
               가입
             </v-btn>

             <v-btn
               class="ml-3"
               color="primary"
               text
               @click="dialog=false"
             >
               취소
             </v-btn>

           </v-card-actions>
         </v-card>
       </v-dialog>
       </div>
       </div>
</div>

   <v-divider></v-divider>

   <v-list>
     <v-list-item
       v-for="link in links"
       :key="link.text"
       @click="$router.push({ name: link.route })"
       link
     >
       <v-list-item-icon>
         <v-icon>{{ link.icon }}</v-icon>
       </v-list-item-icon>

       <v-list-item-content>
         <v-list-item-title>{{ link.text }}</v-list-item-title>
       </v-list-item-content>
     </v-list-item>
   </v-list>
 </v-navigation-drawer>
</template>
<script>
export default {
 data() {
   return {
     memberId : this.$store.state.id,
     profileImg : this.$store.state.user.profileImg,
     dialog: false,
     member: 0,
     right: 0,
     links: [
       { icon: "mdi-plus-box", text: "관리하기", route: "/" },
       { icon: "mdi-note-text", text: "게시글", route: "moimBoard" },
       { icon: "mdi-image", text: "사진첩", route: "moimPhoto" },
       { icon: "mdi-clipboard-check", text: "공지사항", route: "moimNotice" },
       { icon: "mdi-calendar-text", text: "일정", route: "moimSchedule" },
       { icon: "mdi-chart-gantt", text: "투표", route: "moimVote" },
       { icon: "mdi-chart-pie", text: "N빵", route: "moimNbbang" },
   ]};
 },
  created() {
    this.setMemberInfo();
  },
 methods: {
   setMemberInfo() {
      if (this.memberId == "") {
        this.memberId = "비회원";
        this.profileImg = "comfuck.jpg";
        return;
      } else if (this.memberId == "admin") {
        this.profileImg = "female.png";
      }
    },
   goMypage(){
     //this.$route.push(name:"my")
   },
   goMain(){
     this.$router.push({name:"moimBoard"})
   }
 }
};
</script>
<style scoped>
.text-center {
 background-color: #2ac187;
}

.mb-4{
 margin-top: 0;  
}

.right {
 float: right;
 margin-right: -30px;
}

.dialogBox{
 width: 300px;
 margin: 0;
}

.title{
 text-align: center;
 padding: 3%
}
</style>