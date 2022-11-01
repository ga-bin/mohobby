<!-- none_user random postlist component in the list -->
<template>
  <div>
    <v-container class="gallery" fluid max-width="1400">
      <v-row>
        <v-col class="gallery-item" v-for="(feed,i) in feeds" :key="i" cols="3">
          <div @click="goFeedDetail(feed.postId, feed.memberId)"> 
            <v-img :src="require(`@/assets/image/sns/${feed.postId}/${feed.thumbnail}`)"
            class="white--text align-end rounded-sm" aspect-ratio="1.2"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)">
            <!-- class="d-flex child-flex" -->
            <v-icon v-show="feed.imgs > 1" class="image_icon">mdi-checkbox-multiple-blank</v-icon>
                <span id="user_id" class="ml-3 mb-2">{{ feed.memberId }}</span>
                <div class="gallery-item-info">
                  <ul>
                    <li class="gallery-item-likes"><v-icon color="#bdbdbd" class="mr-2">mdi-heart</v-icon>{{feed.likes}}</li>
                    <li class="gallery-item-comments"><v-icon color="#e1e1e1" class="mr-2">mdi-chat-outline</v-icon>{{feed.cmts}}</li>
                  </ul>
                </div>

              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular indeterminate color="grey lighten-5" />
                </v-row>
              </template>
            </v-img>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  name: "NoneUser",
  props: {
    feeds : [],
    keyword : String,
  },
  data() {
      return {
      };
    },
    methods : {

      //디테일피드 테스트버튼
      goFeedDetail(postId, memberId) {
        console.log(memberId);
        this.$router.push({ path: "/snsFeedDetail", query: {postId : postId, writer : memberId} });
      },

      //유저피드로 이동
      goUserFeed(userId) {
        this.$router.push({ path: "/snsUserFeed", query: { userId: userId } });
      }
  }
}
</script>
<style scoped>
#user_id{
  font-size:smaller;
  color:#c9c9c9;
}


.gallery {
    display: flex;
    flex-wrap: wrap;
    margin: -1rem -1rem;
    padding-bottom: 3rem;
}

.gallery-item {
    position: relative;
    flex: 1 0 22rem;
    margin: 1rem;
    color: rgb(192, 192, 192);
    cursor: pointer;
}

.image_icon {
    position: absolute;
    top: 5px;
    left: 5px;
    z-index: 1;
    color: white;
  }

  .icon_secret{
    position: absolute;
    top: 5px;
    left: 30px;
    z-index: 1;
  }

.gallery-item:hover .gallery-item-info,
.gallery-item:focus .gallery-item-info {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.3);
}
.gallery-item-info {
    display: none;
}

.gallery-item-info li {
    display: inline-block;
    font-size: 1rem;
    font-weight: 400;
    color:#c9c9c9;
}

.gallery-item-likes {
    margin-right: 0.3rem;
}

.gallery-item-type {
    position: absolute;
    top: 1rem;
    /* right: 1rem; */
    font-size: 1.2rem;
    text-shadow: 0.2rem 0.2rem 0.2rem rgba(0, 0, 0, 0.1);
}

.fa-clone,
.fa-comment {
    transform: rotateY(180deg);
}

.gallery-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}



</style>