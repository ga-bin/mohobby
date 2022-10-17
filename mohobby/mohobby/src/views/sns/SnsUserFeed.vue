<template>
    <div id="wrap_box">
      <div class="container">
  
        <div class="profile">
  
          <div class="profile-image">
            <v-avatar class="ml-10 my-10 mr-4" color="grey darken-1" size="150">
                  <v-img aspect-ratio="30" :src="require(`@/assets/image/user/${infoes.profileImg}`)" alt="profile_img" />
            </v-avatar>
          </div>
  
          <div class="profile-user-settings">
  
            <h1 class="profile-user-name">{{ infoes.memberId }}</h1>
  
            
  
            <button class="btn profile-settings-btn" aria-label="profile settings"><v-icon>mdi-check-decagram</v-icon><i class="fas fa-cog" aria-hidden="true"></i></button>
  
          </div>
  
          <div class="profile-stats">
            <ul>
              <li><span class="profile-real-name">{{ infoes.nickname }}</span></li>
            </ul>
  
            <ul>
              <li><span class="profile-stat-count">{{ infoes.postCnt }}</span> posts</li>
              <li><span class="profile-stat-count">{{ infoes.followerCnt }}</span> followers</li>
              <li><span class="profile-stat-count">{{ infoes.followingCnt }}</span> following</li>
            </ul>
            <ul>
                <li><p class="profile-bio">{{ infoes.intro }} 📷✈️🏕️</p></li>
                <li></li>
            </ul>
            
          </div>
          
          <ProfileBtn :postid="postId" />
          <!-- <div class="profile-bio">
            
            <ul v-if='this.sessionId != "" && this.sessionId == this.postId'>
                <button class="btn profile-edit-btn">Edit Profile</button>
            </ul>
            
            <ul v-else>
                <button class="btn profile-edit-btn2">Follow</button>
                <button class="btn profile-edit-btn2" @click="send">Message</button>
            </ul>
          </div> -->

  
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
  import Feeds from "@/components/sns/FeedDetail/Feeds.vue";
  import SnsSidebar from "@/components/sns/Common/SnsSidebar.vue";
  import ProfileBtn from "@/components/sns/FeedDetail/ProfileBtn.vue";
  export default {
    name: "UserProfile",
    components: { SnsSidebar, Feeds, ProfileBtn },
    data() {
        return{     
            infoes:[],
            sessionId:"",
            postId:"",
        }
    },
    setup() { },
    created() {
        console.log("유저 프로필로 이동!");
        console.log(this.$route.query.memId); //라우터에서 넘겨받은 memId
        this.postId == this.$route.query.memId; //변수 대입
          this.loadUserProfile(this.postId);
        //   if(this$store.state.id != ""){
        //     this.sessionId == this.$store.state.id; //세션에 저장된 memId 변수대입
        //     console.log(this.$store.state.id +","+ this.sessionId); 
        //   }

      },
      methods: {
        //프로필 업로드
        loadUserProfile(postId) {
            this.axios('/sns/user/profile/' + 'user1')
            .then(res => {
              this.infoes = res.data;
            }).catch(err => {
              console.log(err);
            });  
          },
        //채팅방 이동
        send() {
        this.$router.push({ name: "chat", params: { roomId: this.roomId } });
    },
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
  
  #wrap_box {
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
      margin-left: 20px;
  }
  
  .profile-edit-btn {
      font-size: 1.4rem;
      line-height: 1.8;
      border: 0.1rem solid #dbdbdb;
      border-radius: 0.3rem;
      padding: 0 2.4rem;
      margin-left: 2rem;
  }
  .profile-edit-btn2 {
      font-size: 1.4rem;
      line-height: 1.8;
      border: 0.1rem solid #dbdbdb;
      border-radius: 0.3rem;
      padding: 0 2.4rem;
      margin-left: 2rem;
  }

  .profile-edit-btn2:hover {
    background-color: #2ac187;
    box-shadow: 0 2px 4px 0 rgba(13, 164, 101, 0.5);
    color: white;
}

.profile-edit-btn2:active {
    outline: 0.5rem auto #2ac187;
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
      text-align:center;
      font-weight: 600;
      width: 600px;
      margin: 20px 0;
  }
  .profile-edit-btn2 {
      text-align:center;
      font-weight: 600;
      width: 300px;
      margin: 20px 0 10px;
      padding: 8px;
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