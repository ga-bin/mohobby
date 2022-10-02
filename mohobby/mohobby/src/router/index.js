import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // user
  {
    path: "/",
    name: "Main",
    component: () => import("./../views/user/MainView"),
  },
  { 
    path: "/login",
    name: "Login",
    component: () => import("./../views/user/LoginView")
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
