<template>
    <div class="profile-bio">
        <ul v-if='memberId != "" && memberId == getUserId'>
            <button class="btn profile-edit-btn">Edit Profile</button>
        </ul>     
        <ul v-else>
            <button v-if="followStatus == 0" @click="follow(memberId, getUserId)" class="btn profile-edit-btn2">Follow</button>
            <button v-else style="background-color: #2ac187; color: white;" @click="unfollow(memberId, getUserId)" class="btn profile-edit-btn2">Unfollow</button>
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
            followStatus: Number,
        }
    },
    created(){
        this.getUserId = this.userId;
        this.followCheck(this.memberId, this.getUserId) ;
    },
    methods: {
        //팔로우 상태 조회
        followCheck(memberId, getUserId){
            this.axios('/sns/follow/check', {
                params: {
                    myId: memberId,
                    targetId: getUserId,
                }
            }).then(res => {
                this.followStatus = res.data;
                console.log("팔로우상태: "+this.followStatus);
            }).catch(err => {
                alert(err);
            });
        },
        //팔로우
        follow(memberId, getUserId){
            this.axios.post('/sns/follow', {
                followerId : memberId, //로그인세션 아이디
                followingId : getUserId, //피드주인 아이디
            }).then(res => {
                console.log("팔로우성공 전"+this.followStatus);
                this.followStatus = 1;
                console.log("팔로우성공 후"+this.followStatus);
                console.log("팔로우 성공! "+res);
            }).catch(err => {
                console.log(err)
            });
        },
        
        //언팔로우
        unfollow(memberId, getUserId){
            this.axios.delete('/sns/follow/' + memberId + '/' + getUserId,
            ).then(res => {
                console.log("언팔로우성공 전"+this.followStatus);
                this.followStatus = 0;
                console.log("언팔로우성공 후"+this.followStatus);
                console.log("언팔로우 성공! "+res);
            }).catch(err => {
                console.log(err)
            });
        }
    }
}
</script>
<style scoped lang="css" src="@/assets/css/sns/ProfileBtn.css">
</style>
