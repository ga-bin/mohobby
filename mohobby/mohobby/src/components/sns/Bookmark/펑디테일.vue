<template>
    <div class="container">
        <div v-if="isBookmark == true">
            <h3 class="heading">전달받은 catgName</h3>
            <div class="gallery" v-for="(bookmark, i) in bookmarks" :key="i">
                <div class="gallery-item">
                    <img class="gallery-image" :src="require(`@/assets/image/sns/${bookmark.postId}/${bookmark.thumbnail}`)">
                </div>
            </div>
        </div>
        <div v-else>
            <h3 class="heading">아직 저장된 피드가 없습니다!<br>마음에 드는 피드들을 저장해보세요!</h3>
        </div>
    </div>
</template>
<script>
    export default {
    name: "Detail",
    data: () => ({
        bookmarks: [],
        isBookmark: false, //북마크 유무
    }),
    created(){
        this.getBookmarkList(catgId);
    },
    methods: {
        getBookmarkList(catgId){
            this.axios('/sns/collection/bookmark/' + catgId, {
                params: {
                    catgId: catgId, //post아이디,thumbnail 조회해옴
                }
            }).then(res => {
                if(res.data.length == 0){//북마크 리스트가 없으면
                    this.isBookmark = 
                }else{
                    this.bookmarks = res.data;
                }
            }).catch(err => {
                alert(err);
            });
        },
    }
    }
    </script>
<style scoped lang="css" src="@/assets/css/sns/BookmarkDetail.css">
</style>