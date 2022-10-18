<template>
    <div>
        <h1 class="main-title">북마크 페이지입니다</h1>
        <!-- 모달 시작 -->
        <div>
            <v-row justify="center">
                <v-dialog v-model="dialog" scrollable max-width="350px">
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="#2ac187" dark v-bind="attrs" v-on="on">컬렉션 만들기</v-btn>
            </template>
            <v-card class="mx-auto">
                <v-card-title>
                    <span class="text-h8">컬렉션 만들기</span>
                </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field  v-model="catgName" label="*컬렉션이름을 입력해주세요!" required />
                                    <!-- @change="inputE()" -->
                                  </v-col>
                            </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="createCollection(memberId)">저장</v-btn>
                            <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </div>
        <!-- 모달 끝 -->
        <!-- 컬렉션 시작 -->
        <div class="container" v-for="(collection,i) in collections" :key="i">
            <div class="content">
                <div class="content-overlay"></div>
                <!-- 북마크 저장시 저장 포스트의 첫번째 썸네일을 부모컴포넌트로 보내서 그걸 전달받기<img v-if="v-if='tnPostId != "" && thumbnail != ""'(전달받은 썸네일 있으면)" class="content-image" :src="require(`@/assets/image/sns/${img.postId}/${img.fileName}`)">  -->
                <!-- 없으면 디폴트 이미지 -->
                <img class="content-image" :src="require(`@/assets/image/sns/1/0.png`)">
                <div class="content-details fadeIn-bottom">
                <v-btn color="#2ac187" v-if='collection.catgName!= "default"' class="text--white" @click="swal(collection.memberId,collection.catgId)">삭제</v-btn>
                <h3 class="content-title">{{collection.catgName}}</h3>
                <!-- <p class="content-text">This is a short description</p> -->
                </div>
            </div>
        </div>
        <!-- 컬렉션 끝 -->
    </div>
</template>
<script>
    export default {
        name: "Collection",
        data() {
            return {
                collections:[],
                dialog: false,
                catgName: "",
                memberId : this.$store.state.id,
                tnPostId:Number,
                thumbnail:"",
                dummy:"",//해당 유저에게 생성된 컬렉션이 없을 시 자동생성되도록 도와주는 더미
            }
        },
        setup() {
            
        },
        created() {
            this.getCollectionList(this.memberId);
        },
        mounted() {
        
        },
        unmounted() {
        
        },
        watch: {
          // inputE(val) { //입력한 값 받아와서
          //   if (!val) { //입력한 값이 없으면 return
          //       return
          //   }
          //   this.createCollection() //있으면
          // },
        },
        methods: {
            //컬렉션리스트
            getCollectionList(memberId){
                this.axios('/sns/collection/' + memberId, {
                    params: {
                        memberId: memberId,
                    }
                }).then(res => {
                    if(res.data.length == 0){//리스트 불러올 데이터가 없으면 디폴트 컬렉션 생성
                      this.createDefaultCollection(memberId);
                    }else{
                      this.collections = res.data;//or not 생성되어있는 리스트 호출
                      console.log("컬렉션리스트 호출 성공!");
                    }

                }).catch(err => {
                    alert(err);
                });
            },
            //디폴트 컬렉션 생성
            createDefaultCollection(memberId){
              const thumbnail = '기도.png'//*************기본 사진 바꿀것
              this.axios.post('/sns/collection', {
                  memberId : memberId,
                  catgName : 'default',
                  thumbnail : thumbnail,
              }).then(res => {
                  console.log("디폴트컬렉션생성 성공!"+res);
                  this.getCollectionList(memberId);
              }).catch(err => {
                  alert(err);
              });
            },
            //컬렉션생성
            createCollection(memberId){
              //해당 유저 아이디에 생성된 컬렉션이 없으면 기본으로 한개 생성할 것
              if (this.catgName == "" || this.catgName == undefined){
                  this.$swal('컬렉션 이름을 입력해주세요🙏')
                  return;
              }
              if(this.catgName.length>11){
                this.$swal('이름은 10글자 이내로 입력해주세요🙏')
                  return;
              }
              const thumbnail = '기도.png'//*************기본 사진 바꿀것
              this.dialog = false;
              this.axios.post('/sns/collection', {
                  memberId : memberId,
                  catgName : this.catgName,
                  thumbnail : thumbnail,
              }).then(res => { 
                  console.log("컬렉션생성 성공!"+res);
                  this.getCollectionList(memberId);
              }).catch(err => {
                  alert(err);
              });
            },
            //컬렉션 삭제
            deleteClctn(memberId,catgId) {
              this.swal();
              this.axios
                .delete("/sns/collection/" + catgId)
                .then((res) => {
                  console.log("컬렉션삭제 성공! " + res);
                  this.getCollectionList(memberId);
                })
                .catch((err) => {
                  alert(err);
                });
            },
            swal(memberId,catgId) {
              this.$swal({
                title: "정말 삭제할까요?",
                text: "삭제된 컬렉션은 복구가 불가능합니다.🙏",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#2ac187",
                cancelButtonColor: "#d33",
                cancelButtonText: "취소",
                confirmButtonText: "네, 삭제할게요!",
              }).then((result) => {
                if (result.isConfirmed) {
                  this.deleteClctn(memberId,catgId);
                  this.$swal("삭제 완료!", "게시글이 삭제되었습니다.", "success");
                }
              });
          },
       },
    };
</script>
    
<style scoped>
    @import url(https://fonts.googleapis.com/css?family=Raleway);
  
    *, *:before, *:after{
  
      margin: 0;
      padding: 0;
      -webkit-box-sizing: border-box;
      -moz-box-sizing:border-box;
      box-sizing: border-box;
    }
  
    body{
      background: #f9f9f9;
      font-size: 16px;
      font-family: 'Raleway', sans-serif;
    }
  
    .main-title{
      color: #2d2d2d;
      text-align: center;
      text-transform: capitalize;
      padding: 0.7em 0;
    }
  
    .container{
      padding: 1em 0;
      float: left;
      width: 50%;
    }
    @media screen and (max-width: 640px){
      .container{
        display: block;
        width: 100%;
      }
    }
  
    @media screen and (min-width: 900px){
      .container{
        width: 33.33333%;
      }
    }
  
    .container .title{
      color: #1a1a1a;
      text-align: center;
      margin-bottom: 10px;
    }
  
    .content {
      position: relative;
      width: 90%;
      max-width: 400px;
      margin: auto;
      overflow: hidden;
    }
  
    .content .content-overlay {
      background: rgba(0,0,0,0.7);
      position: absolute;
      height: 99%;
      width: 100%;
      left: 0;
      top: 0;
      bottom: 0;
      right: 0;
      opacity: 0;
      -webkit-transition: all 0.4s ease-in-out 0s;
      -moz-transition: all 0.4s ease-in-out 0s;
      transition: all 0.4s ease-in-out 0s;
    }
  
    .content:hover .content-overlay{
      opacity: 1;
    }
  
    .content-image{
      width: 100%;
    }
  
    .content-details {
      position: absolute;
      text-align: center;
      padding-left: 1em;
      padding-right: 1em;
      width: 100%;
      top: 50%;
      left: 50%;
      opacity: 0;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      -webkit-transition: all 0.3s ease-in-out 0s;
      -moz-transition: all 0.3s ease-in-out 0s;
      transition: all 0.3s ease-in-out 0s;
    }
  
    .content:hover .content-details{
      top: 50%;
      left: 50%;
      opacity: 1;
    }
  
    .content-details h3{
      color: #fff;
      font-weight: 500;
      letter-spacing: 0.15em;
      margin-bottom: 0.5em;
      text-transform: uppercase;
    }
  
    .content-details p{
      color: #fff;
      font-size: 0.8em;
    }
  
    .fadeIn-bottom{
      top: 80%;
    }
  
    .fadeIn-top{
      top: 20%;
    }
  
    .fadeIn-left{
      left: 20%;
    }
  
    .fadeIn-right{
      left: 80%;
    }  
</style>
    