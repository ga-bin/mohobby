<template>
    <div>
        <h1 class="main-title">북마크 페이지입니다</h1>
        <!-- 모달 시작 -->
        <div>
          <v-row justify="center">
              <v-dialog v-model="dialog" scrollable max-width="350px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn color="#2ac187" dark v-bind="attrs" v-on="on">컬렉션 만들기</v-btn>
                  <div>
                    <v-btn @click="manager()">북마크관리</v-btn>
                    <v-btn color="grey" @click="goBookmarkDetail()">북마크디테일</v-btn>
                  </div>
                </template>
              <v-card class="mx-auto">
                <v-card-title>
                  <span v-if="editMode" class="mx-auto text-h8">컬렉션 만들기</span>
                    <span v-else class="mx-auto text-h8">컬렉션 수정</span>
                </v-card-title>
                    <v-card-text>
                      <v-container>
                          <v-row>
                            <v-col cols="12" v-if="editMode">
                              <v-text-field  v-model="catgName" label="*컬렉션이름을 입력해주세요!" required />
                            </v-col>
                            <v-col cols="12" v-else>
                              <v-text-field  v-model="editedCatgName" label="*수정할 컬렉션이름을 입력해주세요!" required />
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>
                        <v-card-actions v-if="editMode">
                          <v-spacer></v-spacer>
                          <v-btn color="blue darken-1" text @click="createCollection(memberId)">저장</v-btn>
                          <v-btn color="blue darken-1" text @click="dialog = false">취소</v-btn>
                        </v-card-actions>
                        <v-card-actions v-else>
                          <v-btn color="blue darken-1" text @click="editCollection(catgId, memberId)">저장</v-btn>
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
                <!-- 북마크 저장시 저장 포스트의 첫번째 썸네일을 부모컴포넌트로 보내서 그걸 전달받기<img v-if="v-if='tnPostId != "" (저장된 포스트가 있으면)" class="content-image" :src="require(`@/assets/image/sns/${img.postId}/${img.fileName}`)">  -->
                <!-- 없으면 디폴트 이미지 -->
                <v-img class="white--text align-end rounded-sm dark" aspect-ratio="1.2" v-if ='collection.postId == ""' :src="require('@/assets/image/sns/bookmark/bookmark_defalt.png')" />
                <v-img class="white--text align-end rounded-sm dark" aspect-ratio="1.2" v-else  :src="require(`@/assets/image/sns/${collection.postId}/${collection.thumbnail}`)"
                      @click="goBookmark(collection.catgId, collection.catgName)" />
                <!-- 관리버튼을 누르면 삭제버튼이 뜨도록 -->
                <div v-if="management" class="content-details fadeIn-bottom">
                  <v-btn color="#2ac187" v-if='collection.catgName!= "default" && editMode' class="text--white" dark v-bind="attrs" v-on="on" @click="showEditForm(collection.catgId)">수정</v-btn>
                  <v-btn color="#2ac187" v-if='collection.catgName!= "default" && editMode' class="text--white" @click="swal(collection.memberId,collection.catgId)">삭제</v-btn>
                  <!-- <h3 class="content-title">{{collection.catgName}}</h3> -->
                </div>
                <div v-else class="content-details">
                  <h3 class="content-title">{{collection.catgName}}</h3>
                </div>
                <!-- <p class="content-text">This is a short description</p> -->
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
              catgId:"",
              memberId : this.$store.state.id,
              tnPostId:Number,
              thumbnail:"",
              dummy:"",//해당 유저에게 생성된 컬렉션이 없을 시 자동생성되도록 도와주는 더미
              management: false, //북마크관리
              editMode:false, //수정모드
              editedCatgName: "", //수정할 컬렉션 이름
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
        //컬렉션 관리
        manager(){
          this.management = !this.management;
          this.editMode = !this.editMode;
        },
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
                  console.log(res.data);
                  this.collections = res.data;//or not 생성되어있는 리스트 호출
                  console.log(this.collections);
                  console.log("컬렉션리스트 호출 성공!");
                }

            }).catch(err => {
                alert(err);
            });
        },
        //디폴트 컬렉션 생성 - 해당 유저 아이디에 생성된 컬렉션이 없으면 기본컬렉션 생성됨
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
        //댓글 수정폼 호출
        showEditForm(catgId) {
          this.dialog =! this.dialog;
          this.editMode = !this.editMode;
          this.catgId = catgId; //수정클릭한 컬렉션 Id 바인딩
        },
        //컬렉션수정(이름)
        editCollection(catgId, memberId){
          if (this.editedCatgName == "" || this.editedCatgName == undefined) {
            this.$swal("이름을 입력해주세요🙏");
            return this.editMode = !this.editMode;
          }
          if(this.catgName.length>11){
              this.$swal('이름은 10글자 이내로 입력해주세요🙏')
                return;
          }
          this.axios
            .put("/sns/collection", {
              catgId:catgId,
              catgName: this.editedCatgName,
            })
            .then((res) => {
              console.log("컬렉션이름수정 성공! " + res);
              this.$swal('컬렉션 이름이 수정되었습니다🙏');
              this.dialog =! this.dialog;
              this.editMode = !this.editMode;
              this.management = !this.management;
              this.getCollectionList(memberId);
            })
            .catch((err) => {
              console.log(err);
            });
          },
          //삭제swal
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
          //컬렉션 삭제
          deleteClctn(memberId,catgId) {
            this.swal();
            this.axios
              .delete("/sns/collection/" + catgId)
              .then((res) => {
                console.log("컬렉션삭제 성공! " + res);
                this.management = !this.management;
                this.getCollectionList(memberId);
              })
              .catch((err) => {
                alert(err);
              });
          },
          //해당 컬렉션으로 이동
          goBookmark(catgId,catgName){
            console.log(catgId);
            console.log(catgName);
            this.$router.push({ path: '/snsBookmarkDetail', query: {catgId : catgId, catgName : catgName} });
          }
       },
    };
</script>

<style scoped lang="css" src="@/assets/css/sns/Collection.css" />
    