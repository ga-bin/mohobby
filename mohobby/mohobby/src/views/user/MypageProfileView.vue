<template>
  <div>
    <UserSidebar></UserSidebar>
    <h1>마이페이지프로필</h1>
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
  
            
  
            <button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>
  
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
  
          <div class="profile-bio">
            
            <ul v-if='this.sessionId != "" && this.sessionId == this.postId'>
                <button class="btn profile-edit-btn"  @click="$router.push('/edituser')">내정보 수정하기</button>
            </ul>
            
            <ul v-else>
                <button class="btn profile-edit-btn2">Edit Profile</button>
                <button class="btn profile-edit-btn2">Edit Profile</button>
            </ul>
          </div>

  
        </div>
        <!-- End of profile section -->
  
      </div>
      </div>
      </div>
</template>
<script>
import UserSidebar from "../../components/user/UserSidebar.vue";

export default {
  name: "",
  components: { UserSidebar },
   data() {
        return{     
            infoes:[],
            memberId: "",
        }
    },
    setup() { },
    created() {
          
      },
      methods: {
        loadUserProfile() {
            const vm = this;
            this.memberId = this.$store.state.id;
            if (!this.memberId) {
                this.memberId = 'user11';
            }
            this.axios('/sns/user/profile/' + vm.memberId)
            .then(res => {
              this.infoes = res.data;
              console.log(this.infoes);
            }).catch(err => {
              console.log(err);
            });  
          }
      },
  mounted() {
    this.loadUserProfile();
  },
  unmounted() {},
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
      height : 30rem;
      background-color: #fafafa;
      color: #262626;
      padding-bottom: 3rem;
  }
  
  img {
      display: block;
  }
  
  .container {
      max-width: 70rem;
      max-height: 30rem;
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
      padding: 2rem 0;
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
  }
</style>
