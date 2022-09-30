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
  // sns
  {
    path: "/snsmain",
    name: "snsmain",
    component: () => import("./../views/sns/SnsMainView"),
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
