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
    path: "/snsFollow",
    name: "snsFollow",
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
  {
    path: "/moimRegisterIn",
    name: "moimRegisterIn",
    component: () => import("./../views/moim/MoimRegisterInView"),
  },
  // challenge
  {
    path: "/challengemain",
    name: "challengemain",
    component: () => import("./../views/challenge/ChallengeMainView"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
