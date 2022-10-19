<template>
    <div class="container">
    <!-- 피드 갤러리 -->
    <div class="gallery">
        <div @click.stop="goFeedDetail(feed.memberId, feed.postId)" class="gallery-item" tabindex="0" v-for="(feed,i) in feeds" :key="i">
            <v-img :src="require(`@/assets/image/sns/${feed.postId}/${feed.thumbnail}`)" class="gallery-image" alt="thumbnail_img" />
            <div class="gallery-item-info">
                <ul>
                <li class="gallery-item-likes"><span class="visually-hidden"><v-icon>mdi-heart-outline</v-icon></span><i class="fas fa-heart" aria-hidden="true"></i>{{ feed.likes }}</li>
                <li class="gallery-item-comments"><span class="visually-hidden"><v-icon>mdi-chat-outline</v-icon></span><i class="fas fa-comment" aria-hidden="true"></i>{{ feed.cmts }}</li>
                </ul>
            </div>
        </div>
        <!-- 더미사진 -->
        <div class="gallery-item" tabindex="0">
            <img src="https://images.unsplash.com/photo-1423012373122-fff0a5d28cc9?w=500&h=500&fit=crop" class="gallery-image" alt="">
            <div class="gallery-item-type">
                <span class="visually-hidden">Video</span><i class="fas fa-video" aria-hidden="true"></i>
            </div>
        </div>
    </div>
    <!-- End of gallery -->

    <div class="loader"></div>

    </div>
    <!-- End of container -->
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
            this.$router.push({ path: '/snsFeedDetail', query: {writer : memberId, postId : postId} });
        },

    },

    setup() {
        
    },
}
</script>
<style scoped lang="css" src="@/assets/css/sns/Feeds.css">
</style>