<template>
    <div>
        <SnsSidebar />
        <h3 class="heading">{{catgName}} 컬렉션</h3>
        <div class="container" >
            <!-- <div v-if="isBookmark"> -->
                <!-- bookmark가 있을 때 -->
                <div class="gallery" v-if="isBookmark == true">
                    <div class="gallery-item" v-for="(bookmark, i) in bookmarks" :key="i">
                        <v-img aspect-ratio="1.2" @click.stop="goFeedDetail(bookmark.postId, bookmark.memberId)" class="gallery-image" :src="require(`@/assets/image/sns/${bookmark.postId}/${bookmark.thumbnail}`)" />
                    </div>
                </div>

                <!-- 북마크가 없을 때 -->
                <div class="wrap_box" v-else>
                <v-img class="mx-auto mt-10 mb-10" width="100" src="@/assets/image/sns/default/warn.jpg" alt="no_result"></v-img>
                <div class="text_box">
                    <p>
                        저장된 피드가 없습니다!<br>마음에 드시는 피드 저장부터 부탁드립니다🙏
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

  import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";

  export default {

  name: "Detail",

  components:{ SnsSidebar },

  data: () => ({
      bookmarks: [], //북마크 피드들

      //컬렉션리스트에서 받은 값
      catgId: "",   
      catgName: "",

      isBookmark: false,//북마크 있을때
  }),
  created(){
      this.catgId = this.$route.query.catgId;
      this.catgName= this.$route.query.catgName;

      if(this.catgName != "default"){
        getBookmarkList();
      }else{
        getAllBookmarks();
      }

      console.log("북마크 디테일뷰 입장" + this.catgId + ", " + this.catgName);
  },

  methods: {
       //북마크전체
       getAllBookmarks(){
            this.axios('/sns/collection/allBookmarks',{

                    params: {
                        memberId : this.bookmark.memberId, //post아이디,thumbnail 조회해옴
                    }

                }).then(res => {
                    console.log("북마크 리스트 호출 성공!");

                    if(res.data.length < 1){  //북마크 리스트가 없으면
                        this.isBookmark = false; //북마크 저장 권유

                    }else{    //있으면 목록보여주기
                        this.bookmarks = res.data;
                        this.isBookmark = true;
                    }

                }).catch(err => {
                    alert(err);
            });
      },

      //컬렉션별 북마크
      getBookmarkList(catgId){
            this.axios('/sns/collection/bookmark/' + catgId, {

              params: {
                  catgId: catgId, //post아이디,thumbnail 조회해옴
              }

            }).then(res => {
              console.log("북마크 리스트 호출 성공!");
              this.bookmarks = res.data;
              if(res.data.length < 1){  //북마크 리스트가 없으면
                  this.isBookmark = false; //북마크 저장 권유

              }else{    //있으면 목록보여주기
                this.bookmarks = res.data;
                this.isBookmark = true;
              }

          }).catch(err => {
              alert(err);
          });
        },

      //피드 디테일로 이동
      goFeedDetail(postId,writer) {
        console.log(postId,writer);
        this.$router.push({ path: '/snsFeedDetail', query: { postId : postId, writer: writer } });
      }
    }
  }
  </script>
<style scoped lang="css" src="@/assets/css/sns/BookmarkDetail.css">
</style>