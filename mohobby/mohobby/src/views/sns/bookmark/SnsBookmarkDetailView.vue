<template>
    <div>
        <h3 class="heading">{{catgName}} 컬렉션</h3>
        <div class="container" >
            <!-- <div v-if="isBookmark"> -->
                <div class="gallery">
                    <div class="gallery-item" v-for="(bookmark, i) in bookmarks" :key="i">
                        <v-img aspect-ratio="1.2" @click.stop="goFeedDetail(bookmark.postId, bookmark.memberId)" class="gallery-image" :src="require(`@/assets/image/sns/${bookmark.postId}/${bookmark.thumbnail}`)" />
                    </div>
                </div>
            <!-- </div> -->
            <!-- <div v-else> -->
                <!-- <h3 class="heading">아직 저장된 피드가 없습니다!<br>마음에 드는 피드들을 저장해보세요!</h3> -->
            <!-- </div> -->
        </div>
    </div>
</template>
<script>
  export default {
  name: "Detail",
  data: () => ({
      bookmarks: [],
    //   isBookmark:false, //북마크 유무
      catgId: "",//글쓴이 아이디
      catgName: "",
  }),
  created(){
      this.catgId = this.$route.query.catgId;
      this.catgName= this.$route.query.catgName;
      this.getBookmarkList(this.catgId);
      console.log("북마크 디테일뷰 입장" + this.catgId + ", " + this.catgName);
  },
  methods: {
      getBookmarkList(catgId){
          this.axios('/sns/collection/bookmark/' + catgId, {
              params: {
                  catgId: catgId, //post아이디,thumbnail 조회해옴
              }
            }).then(res => {
              console.log("북마크 리스트 호출 성공!");
              console.log(res);
              if(res.data.length == 0){//북마크 리스트가 없으면
                  this.isBookmark=!this.isBookmark
              }else{
                  this.bookmarks = res.data;
              }
          }).catch(err => {
              alert(err);
          });
      },
      //피드 디테일로 이동
      goFeedDetail(postId,writer) {
        console.log(postId,writer);
        this.$router.push({ path: '/snsFeedDetail', query: {postId : postId, writer: writer} });
      }
    }
  }
  </script>
<style scoped lang="css" src="@/assets/css/sns/BookmarkDetail.css">
</style>