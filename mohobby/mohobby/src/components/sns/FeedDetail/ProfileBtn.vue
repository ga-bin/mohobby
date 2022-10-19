<template>
    <div class="profile-bio">
    
        <ul v-if='memberId != "" && memberId == getUserId'>
            <button class="btn profile-edit-btn">Edit Profile</button>
        </ul>
        
        <ul v-else>
            <button class="btn profile-edit-btn2">Follow</button>
            <button class="btn profile-edit-btn2" @click="send">Message</button>
        </ul>
    </div>
</template>
<script>

export default {
    name: "ProfileBtn",
    props: {
        userId:String,
    },
    data() {
        return {
            memberId : this.$store.state.id,
            getUserId : "",
        }
    },
    created(){
        this.getUserId = this.userId;
    },
    methods: {
        //팔로우
        follow(){
            this.axios.post('/sns/cmt', {
                followerId : this.memberId,
                followingId : this.getUserId,
            }).then(res => {
                console.log("팔로우 성공! "+res);
            }).catch(err => {
                console.log(err)
            });
        },
        //언팔로우
        unfollow(){
            this.axios.delete('/sns/cmt/' + this.memberId + '/' + this.getUserId,
            ).then(res => {
                console.log("언팔로우 성공! "+res);
            }).catch(err => {
                console.log(err)
            });
        }
    }
}
</script>
<style scoped>
  .btn {
      display: inline-block;
      font: inherit;
      background: none;
      border: none;
      color: inherit;
      padding: 0;
      cursor: pointer;
  }
  .profile-bio {
      float: left;
      width: calc(66.666% - 2rem);
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
.profile-bio {
      font-size: 1.6rem;
      font-weight: 400;
      line-height: 1.5;
      margin-top: 2.3rem;
  }

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
  
</style>
