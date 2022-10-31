<template>
    <div class="container">
        <!-- 피드 갤러리 -->
        <div class="gallery">
            <div class="gallery-item" @click.stop="goFeedDetail(feed.memberId, feed.postId)" tabindex="0" v-for="(feed,i) in feeds" :key="i">
                <v-icon v-show="feed.secPost === 1" color="red" class="icon_secret">mdi-lock</v-icon>
                <v-icon v-show="feed.imgs > 1" class="image_icon">mdi-checkbox-multiple-blank</v-icon>
                <v-img class="gallery-image" :src="require(`@/assets/image/sns/${feed.postId}/${feed.thumbnail}`)" alt="thumbnail_img" />
                <div class="gallery-item-info">
                    <ul>
                    <li class="gallery-item-likes"><v-icon color="#e1e1e1" class="mr-2">mdi-heart</v-icon>{{ feed.likes }}</li>
                    <li class="gallery-item-comments"><v-icon color="#e1e1e1" class="mr-2">mdi-chat-outline</v-icon>{{ feed.cmts }}</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="no_post" v-show="feeds.length < 1">
            <v-img id="no_post_img" :src="require(`@/assets/image/sns/default/no_post.png`)" />
            <h2>아직 업로드된 게시글이 없습니다!</h2>
        </div>
        <!-- 피드 갤러리 끝 -->
        <!-- <div class="loader"></div> -->
    </div>
</template>
<script>
export default {
    name: "Feeds",
    props:{
        userId:String,
    },
    data(){
        return{
            feeds:[],
            getUserId:"", //부모에서 받은 userId바인딩
        }
    },
    created() {
        this.getUserId = this.userId;
        this.loadUserFeedList(this.getUserId);
    },
    methods: {
        loadUserFeedList(getUserId) {
            this.axios('sns/user/user_feeds/' + getUserId)
            .then(res => {
              this.feeds = res.data;
            }).catch(err => {
              console.log(err);
            });  
          },
        //피드 디테일로 이동
        goFeedDetail(memberId, postId) {
            console.log(postId);
            this.$router.push({ path: '/snsFeedDetail', query: {writer : memberId, postId : postId} }).$router.go(0);
        },
    },
    setup() {
    },
}
</script>
<style scoped lang="css" src="@/assets/css/sns/Feeds.css">
</style>