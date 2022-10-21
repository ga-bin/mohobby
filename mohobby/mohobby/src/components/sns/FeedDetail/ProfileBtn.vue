<template>
    <div class="profile-bio">
        <ul v-if='!memberId && memberId == getUserId'>
            <button class="btn profile-edit-btn" @click="goMypage(memberId)">Edit Profile</button>
        </ul>     
        <ul v-else>
            <button v-if='followStatus === 0' @click="follow(memberId, getUserId)" class="btn profile-edit-btn2">Follow</button>
            <button v-else style="background-color: #2ac187; color: white;" @click="unfollow(memberId, getUserId)" class="btn profile-edit-btn2">Unfollow</button>
            <button class="btn profile-edit-btn2" @click="send(memberId)">Message</button>
        </ul>
    </div>
</template>
<script>
import router from '@/router';

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
        //세션유무 검증
        confirmMember(memberId){  
            if(memberId){
            console.log("true");
            return true;
            }
            else{
            console.log("false");
            return false;
            }
        },
        //로그인 검증 모달
        loginConfirm(){
            this.$swal({
            title: "로그인하셔야 가능하세요🙏",
            text: "🙏로그인화면으로 이동부탁드립니다🙏",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#2ac187",
            cancelButtonColor: "#d33",
            cancelButtonText: "취소",
            confirmButtonText: "이동",
            }).then((result) => {
            if (result.isConfirmed) {
                this.$router.push({ path: "login" });
            }
            });
        },

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
            if(this.confirmMember(memberId) == false){
                this.loginConfirm();
            }else{
                this.axios.post('/sns/follow', {
                    followerId : memberId, //로그인세션 아이디
                    followingId : getUserId, //피드주인 아이디
                }).then(res => {
                    console.log("팔로우성공 전"+this.followStatus);
                    this.followStatus = 1;
                    // this.followCheck(memberId, getUserId);
                    console.log("팔로우성공 후"+this.followStatus);
                    console.log("팔로우 성공! "+res);
                }).catch(err => {
                    console.log(err)
                });
            }
        },
        
        //언팔로우
        unfollow(memberId, getUserId){
            this.axios.delete('/sns/follow/' + memberId + '/' + getUserId,
            ).then(res => {
                console.log("언팔로우성공 전"+this.followStatus);
                this.followStatus = 0;
                // this.followCheck(memberId, getUserId);
                console.log("언팔로우성공 후"+this.followStatus);
                console.log("언팔로우 성공! "+res);
            }).catch(err => {
                console.log(err)
            });
        },

        //마이페이지로 이동
        goMypage(memberId){
            this.$router.push({path : "/mypagesns", query: { memberId : memberId } });
        },

        //채팅방 이동
        send(memberId){
            let vm = this;
            if(this.confirmMember(memberId) == false){
                this.loginConfirm();
            } else {
                this.axios
                .get("/getSnsChatRoomNo", {
                params: { myId: memberId, targetId: this.getUserId },
                })
                .then(function (res) {
                console.log(res.data.vroomNo);

                vm.$router.push({
                name: "chat",
                params: { getRoomId: res.data.vroomNo },
                });
                })
                .catch(function (err) {
                console.log(err);
                });
            }
        }
    }
}
</script>
<style scoped lang="css" src="@/assets/css/sns/ProfileBtn.css">
</style>
