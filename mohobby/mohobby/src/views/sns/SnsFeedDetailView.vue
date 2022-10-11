<template>
  <div>
    <div class="container">

      <div class="profile">

        <div class="profile-image">

          <img src="https://images.unsplash.com/photo-1513721032312-6a18a42c8763?w=152&h=152&fit=crop&crop=faces" alt="">

  <div id="container">
    <SnsSidebar></SnsSidebar>
    <h1>피드디테일</h1>
    <v-container>
      <v-card class="mx-auto" max-width="1000">
        <v-row>
          <v-col col="12">

            <!-- 프로필 -->
            <div>
              <div class="flex">
                <v-avatar class="ml-10 my-10 mr-4" color="grey darken-1" size="64">
                  <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${items.profileImg}`)" />
                </v-avatar>
                <div class="user text-overline">{{items.memberId}}<br>{{
                this.$moment(items.writeDate).format('YYYY.MM.DD') }}</div>
              </div>
            </div>
            <div id="mdi-dots-vertical">
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </div>
          </v-col>
        </v-row>
        <v-row>
          <v-col col="12">
            <div id="image_box">
              <!-- <div class="d-flex flex-column justify-space-between align-center">
            <v-slider
              v-model="width"
              class="align-self-stretch"
              min="300"
              max="600"
              step="1"
            ></v-slider> -->
              <!-- <v-img v-for="(item, i) in imagelist" :key="i" :src="require(`../../../back/uploads/${item}`)"
       contain height="150px" width="200px" style="border: 2px solid black; margin-left:100px;"/> -->
              <v-carousel ref="myCarousel" hide-delimiters :touchless="ture">
                <v-carousel-item :aspect-ratio="4 / 3" :width="width"
                  :src="require(`@/assets/image/sns/${items.thumbnail}`)"></v-carousel-item>
              </v-carousel>
              <!-- </div> -->
            </div>
          </v-col>
        </v-row>
        <v-col col="12">
          <div>{{ items.content }}</div>
        </v-col>
        <v-col col="12" v-for="hashtag in hashtags" :key="hashtag">
          <div @click="search($event)">#{{ hashtag }}</div>
        </v-col>
        <div style="display=flex;">
          <div id="like_box">
            <v-btn icon id="full_heart" text @click="likeBtn">
              <v-icon color="red lighten-2">mdi-heart</v-icon>
            </v-btn>
            <v-btn icon id="empty_heart" text @click="likeBtn">
              <v-icon>mdi-heart-outline</v-icon>
            </v-btn>
            {{ items.likes }}
          </div>
          <div>
            <v-icon>mdi-chat-outline</v-icon>
            {{ items.cmts }}
          </div>
          <div>
            <div v-if="this.$store.state.id !== items.memberId">
              <v-icon @click="send">mdi-send</v-icon>
          </div>
          </div>
        </div>

        <div class="profile-user-settings">

          <h1 class="profile-user-name">janedoe_</h1>

          <button class="btn profile-edit-btn">Edit Profile</button>

          <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>

        </div>

        <div class="profile-stats">

          <ul>
            <li><span class="profile-stat-count">164</span> posts</li>
            <li><span class="profile-stat-count">188</span> followers</li>
            <li><span class="profile-stat-count">206</span> following</li>
          </ul>

        </div>

        <div class="profile-bio">

          <p><span class="profile-real-name">Jane Doe</span> Lorem ipsum dolor sit, amet consectetur adipisicing elit 📷✈️🏕️</p>

        </div>

      </div>
      <!-- End of profile section -->

    </div>
    <!-- End of container -->
    <!-- 피드 컴포넌트 -->
    <div>
      <Feeds></Feeds>
    </div>
  </div>
</template>
<script>
import Feeds from "@/components/sns/FeedDetail/Feeds.vue"
import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
export default {
  name: "UserProfile",
  components: { SnsSidebar, Feeds },
  data: () => ({
    infoes:[],
    memberId:'user1',//더미아이디
  }),
  setup() { },
  created() {
        this.loadUserProfile();
    console.log(this.$route.query.id);
    console.log(this.$store.state.id);
    this.showDetail();
  },
  mounted() {
    // For touch devices
    // this.$refs.myCarousel.$el.addEventListener("touchmove", (e) => {
    //   this.drag = false;
    //   this.touch = true;
    //   this.logic(e);
    // });
    // window.addEventListener("touchend", (e) => {
    //   this.move = [];
    // });

    // // For non-touch devices
    // this.$refs.myCarousel.$el.addEventListener("mousedown", (e) => {
    //   this.drag = true;
    //   this.touch = false;
    //   this.logic(e);
    // });
    // this.$refs.myCarousel.$el.addEventListener("mousemove", (e) => {
    //   this.drag ? this.logic(e) : null;
    // });
    // window.addEventListener("mouseup", (e) => {
    //   this.drag = false;
    //   this.touch = false;
    //   this.move = [];
    // });
  },
  unmounted() { },
  methods: {
    //Detail조회
    showDetail() {
      let postId = this.$route.query.id;
      this.axios('/sns/user/feed_detail', {
        params: {
          postId: postId
        }
      }).then(res => {
        this.items = res.data;
        let str = this.items.hashtag;
        let hashtag = str.split(',');
        console.log(hashtag);
        this.hashtags = hashtag;
        console.log(this.items);
        console.log(this.items.cmts);
      }).catch(err => {
        console.log(err);
      });
    },

    search(e){
            //유저 아이디 or 닉네임 조회
            let getHashtag = e.target.innerText;
            let hashtag = getHashtag.slice(1);
            console.log(hashtag);
            this.axios('/sns/search/hashtag', {
                params : {
                    hashtag : hashtag
                }
            }).then(res => {
                console.log(res);
                this.feeds = res.data;
                this.goSearchPage(this.feeds);
                console.log("검색성공:"+this.feeds);

            }).catch(err =>{
                console.log(err);
            });
       
    },
    goSearchPage(feeds){
      console.log("goSearchPage실행"+feeds);
      

      this.$router.push({ name: "snsmain", params: {  sfeeds: feeds } })

    },

    send() {
      this.$router.push({ name: "chat", params: { roomId: this.roomId } })
      let vm = this;
      this.axios.post('http://localhost:8088/java/CreateRoom/', {
        myId: vm.$store.state.id,
        tarId: vm.items.memberId
      })
        .then(function (res) {
          console.log(res);
        })
        .catch(function (error) {
          console.log(error);
        })
      //this.$router.push({name:"chat",params:{roomId:this.roomId}})
    },
    methods: {
      loadUserProfile() {
          this.axios('/sns/user/profile/' + this.memberId)
          .then(res => {
            this.infoes = res.data;
            console.log(infoes);
          }).catch(err => {
            console.log(err);
          });  
        }
    },
  mounted() {},
  unmounted() { },
};
</script>
<style scoped>
/*

All grid code is placed in a 'supports' rule (feature query) at the bottom of the CSS (Line 310). 
        
The 'supports' rule will only run if your browser supports CSS grid.

Flexbox and floats are used as a fallback so that browsers which don't support grid will still recieve a similar layout.

*/

/* Base Styles */

:root {
    font-size: 10px;
}

*,
*::before,
*::after {
    box-sizing: border-box;
}

body {
    font-family: "Open Sans", Arial, sans-serif;
    min-height: 100vh;
    background-color: #fafafa;
    color: #262626;
    padding-bottom: 3rem;
}

img {
    display: block;
}

.container {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
}

.btn {
    display: inline-block;
    font: inherit;
    background: none;
    border: none;
    color: inherit;
    padding: 0;
    cursor: pointer;
}

.btn:focus {
    outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
    position: absolute !important;
    height: 1px;
    width: 1px;
    overflow: hidden;
    clip: rect(1px, 1px, 1px, 1px);
}

/* Profile Section */

.profile {
    padding: 5rem 0;
}

.profile::after {
    content: "";
    display: block;
    clear: both;
}

.profile-image {
    float: left;
    width: calc(33.333% - 1rem);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
}

.profile-image img {
    border-radius: 50%;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
    float: left;
    width: calc(66.666% - 2rem);
}

.profile-user-settings {
    margin-top: 1.1rem;
}

.profile-user-name {
    display: inline-block;
    font-size: 3.2rem;
    font-weight: 300;
}

.profile-edit-btn {
    font-size: 1.4rem;
    line-height: 1.8;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.3rem;
    padding: 0 2.4rem;
    margin-left: 2rem;
}

.profile-settings-btn {
    font-size: 2rem;
    margin-left: 1rem;
}

.profile-stats {
    margin-top: 2.3rem;
}

.profile-stats li {
    display: inline-block;
    font-size: 1.6rem;
    line-height: 1.5;
    margin-right: 4rem;
    cursor: pointer;
}

.profile-stats li:last-of-type {
    margin-right: 0;
}

.profile-bio {
    font-size: 1.6rem;
    font-weight: 400;
    line-height: 1.5;
    margin-top: 2.3rem;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
    font-weight: 600;
}

/* Gallery Section */

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
    color: #fff;
    cursor: pointer;
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
    font-size: 1.7rem;
    font-weight: 600;
}

.gallery-item-likes {
    margin-right: 2.2rem;
}

.gallery-item-type {
    position: absolute;
    top: 1rem;
    right: 1rem;
    font-size: 2.5rem;
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

/* Loader */

.loader {
    width: 5rem;
    height: 5rem;
    border: 0.6rem solid #999;
    border-bottom-color: transparent;
    border-radius: 50%;
    margin: 0 auto;
    animation: loader 500ms linear infinite;
}

/* Media Query */

@media screen and (max-width: 40rem) {
    .profile {
        display: flex;
        flex-wrap: wrap;
        padding: 4rem 0;
    }

    .profile::after {
        display: none;
    }

    .profile-image,
    .profile-user-settings,
    .profile-bio,
    .profile-stats {
        float: none;
        width: auto;
    }

    .profile-image img {
        width: 7.7rem;
    }

    .profile-user-settings {
        flex-basis: calc(100% - 10.7rem);
        display: flex;
        flex-wrap: wrap;
        margin-top: 1rem;
    }

    .profile-user-name {
        font-size: 2.2rem;
    }

    .profile-edit-btn {
        order: 1;
        padding: 0;
        text-align: center;
        margin-top: 1rem;
    }

    .profile-edit-btn {
        margin-left: 0;
    }

    .profile-bio {
        font-size: 1.4rem;
        margin-top: 1.5rem;
    }

    .profile-edit-btn,
    .profile-bio,
    .profile-stats {
        flex-basis: 100%;
    }

    .profile-stats {
        order: 1;
        margin-top: 1.5rem;
    }

    .profile-stats ul {
        display: flex;
        text-align: center;
        padding: 1.2rem 0;
        border-top: 0.1rem solid #dadada;
        border-bottom: 0.1rem solid #dadada;
    }

    .profile-stats li {
        font-size: 1.4rem;
        flex: 1;
        margin: 0;
    }

    .profile-stat-count {
        display: block;
    }
}

/* Spinner Animation */

@keyframes loader {
    to {
        transform: rotate(360deg);
    }
}

/*

The following code will only run if your browser supports CSS grid.

Remove or comment-out the code block below to see how the browser will fall-back to flexbox & floated styling. 

*/

@supports (display: grid) {
    .profile {
        display: grid;
        grid-template-columns: 1fr 2fr;
        grid-template-rows: repeat(3, auto);
        grid-column-gap: 3rem;
        align-items: center;
    }

    .profile-image {
        grid-row: 1 / -1;
    }

    .gallery {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
        grid-gap: 2rem;
    }

    .profile-image,
    .profile-user-settings,
    .profile-stats,
    .profile-bio,
    .gallery-item,
    .gallery {
        width: auto;
        margin: 0;
    }

    @media (max-width: 40rem) {
        .profile {
            grid-template-columns: auto 1fr;
            grid-row-gap: 1.5rem;
        }

        .profile-image {
            grid-row: 1 / 2;
        }

        .profile-user-settings {
            display: grid;
            grid-template-columns: auto 1fr;
            grid-gap: 1rem;
        }

        .profile-edit-btn,
        .profile-stats,
        .profile-bio {
            grid-column: 1 / -1;
        }

        .profile-user-settings,
        .profile-edit-btn,
        .profile-settings-btn,
        .profile-bio,
        .profile-stats {
            margin: 0;
        }
    }
}

</style>