import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // admin
  {
    path: "/adminuser",
    name: "adminuser",
    component: () => import("./../views/admin/AdminUserView")
  },
  {
    path: "/adminmoim",
    name: "adminmoim",
    component: () => import("./../views/admin/AdminMoimView")
  },
  {
    path: "/adminclass",
    name: "adminClass",
    component: () => import("./../views/admin/AdminClassView")
  },
  {
    path: "/adminchallenge",
    name: "adminChallenge",
    component: () => import("./../views/admin/AdminChallengeView")
  },
  {
    path: "/challengepay",
    name: "challengePay",
    component: () => import("./../views/admin/challengePayView")
  },
  {
    path: "/adminsns",
    name: "adminsns",
    component: () => import("./../views/admin/AdminSnsView")
  },
  // user
  {
    path: "/",
    name: "main",
    component: () => import("./../views/user/MainView"),
  },
  { 
    path: "/login",
    name: "login",
    component: () => import("./../views/user/LoginView")
  },
  { 
    path: "/register",
    name: "register",
    component: () => import("./../views/user/RegisterView")
  },
  // sns
  {
    path: "/snsmain",
    name: "snsmain",
    component: () => import("./../views/sns/SnsMainView"),
  },
  {
    path: "/snsFeed",
    name: "snsFeed",
    component: () => import("./../views/sns/SnsFeedView"),
  },
  {
    path: "/snsFeedDetail",
    name: "snsFeedDetail",
    component: () => import("./../views/sns/SnsFeedDetailView"),
  },
  {
    path: "/SnsFeedInsert",
    name: "SnsFeedInsert",
    component: () => import("./../views/sns/SnsFeedInsertView"),
  },
  {
    path: "/snsLecture",
    name: "snsLecture",
    component: () => import("./../views/sns/SnsLectureView"),
  },
  {
    path: "/snsMoim",
    name: "snsMoim",
    component: () => import("./../views/sns/SnsMoimView"),
  },
  {
    path: "/snsChall",
    name: "snsChall",
    component: () => import("./../views/sns/SnsChallView"),
  },
  {
    path: "/snsBookmark",
    name: "snsBookmark",
    component: () => import("./../views/sns/SnsBookmarkView"),
  },
  {
    path: "/snsBookmarkDetail",
    name: "snsBookmarkDetail",
    component: () => import("./../views/sns/SnsBookmarkDetailView"),
  },
  {
    path: "/snsFollower",
    name: "snsFollower",
    component: () => import("./../views/sns/SnsFollowerView"),
  },
  {
    path: "/snsFollowing",
    name: "snsFollowing",
    component: () => import("./../views/sns/SnsFollowingView"),
  },
  // lecture
  {
    path: "/classmain",
    name: "classmain",
    component: () => import("./../views/lecture/ClassMainView"),
  },
  // moim
  { 
    path: "/moimmain",
    name: "moimmain",
    component: () => import("./../views/moim/MoimMainView"),
  },
  { //소모임 생성
    path: "/moimRegisterIn",
    name: "moimRegisterIn",
    component: () => import("./../views/moim/MoimRegisterInView"),
  },
  { //게시글
    path: "/moimDetail",
    name: "moimDetail",
    component: () => import("./../views/moim/MoimDetailView"),
  },
  {
    path : "/moimboardwrite",
    name : "moimboardwrite",
    component : () => import ("@/views/moim/board/MoimBoardWrite"),
  },
  { //게시물 단건조회
    path: "/moimPost",
    name: "moimPost",
    component: () => import("./../views/moim/board/MoimPostView"),
  },
  { //사진첩
    path: "/moimPhoto", 
    name: "moimPhoto",
    component: () => import("./../views/moim/photo/MoimPhotoView"),
  },
  { //공지사항
    path: "/moimNotice",
    name: "moimNotice",
    component: () => import("./../views/moim/notice/MoimNoticeView"),
  },
  { //공지사항
    path: "/moimSchedule",
    name: "moimSchedule",
    component: () => import("./../views/moim/schedule/MoimScheduleView"),
  },
  { //투표
    path: "/moimVote",
    name: "moimVote",
    component: () => import("./../views/moim/vote/MoimVoteView"),
  },
  { //N빵
    path: "/moimNbbang",
    name: "moimNbbang",
    component: () => import("./../views/moim/Nbbang/MoimNbbangView"),
  },
  // challenge
  {
    path: "/challengemain",
    name: "challengemain",
    component: () => import("./../views/challenge/ChallengeMainView"),
  },
  {
    path: "/chat/:id",
    name: "chat",
    component: () => import("./../views/chat/ChatView"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
