import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // admin
  {
    path: "/adminuser",
    name: "adminuser",
    component: () => import("./../views/admin/AdminUserView"),
  },
  {
    path: "/adminmoim",
    name: "adminmoim",
    component: () => import("./../views/admin/AdminMoimView"),
  },
  {
    path: "/adminclass",
    name: "adminClass",
    component: () => import("./../views/admin/AdminClassView"),
  },
  {
    path: "/adminchallenge",
    name: "adminChallenge",
    component: () => import("./../views/admin/AdminChallengeView"),
  },
  {
    path: "/challengepay",
    name: "challengePay",
    component: () => import("./../views/admin/challengePayView"),
  },
  {
    path: "/adminsns",
    name: "adminsns",
    component: () => import("./../views/admin/AdminSnsView"),
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
    component: () => import("./../views/user/LoginView"),
  },
  {
    path: "/register",
    name: "register",
    props: true,
    component: () => import("./../views/user/RegisterView"),
  },
  {
    path: "/mypagemain",
    name: "mypage",
    props: true,
    component: () => import("./../views/user/MypageMainView"),
  },
  // sns
  {
    path: "/snsmain",
    name: "snsmain",
    component: () => import("@/views/sns/SnsMainView"),
  },
  {
    path: "/snsFeedDetail",
    name: "snsFeedDetail",
    component: () => import("@/views/sns/SnsFeedDetailView"),
    props:true,
  },
  {
    path: "/snsFeedRegister",
    name: "snsFeedRegister",
    component: () => import("@/views/sns/SnsFeedRegister"),
  },
  {
    path: "/snsUserFeed",
    name: "snsUserFeed",
    component: () => import("@/views/sns/SnsUserFeed"),
  },
  {
    path: "/snsManagement",
    name: "snsManagement",
    component: () => import("@/views/sns/management/SnsManagementView"),
  },
  {
    path: "/snsBookmark",
    name: "snsBookmark",
    component: () => import("@/views/sns/bookmark/SnsBookmarkView"),
  },
  {
    path: "/snsBookmarkDetail",
    name: "snsBookmarkDetail",
    component: () => import("@/views/sns/bookmark/SnsBookmarkDetailView"),
  },
  // lecture
  {
    path: "/class",
    name: "classmain",
    component: () => import("./../views/lecture/ClassMainView"),
    children: [
      {
        path: "test",
        name: "ClassTest",
        component: () => import("./../views/lecture/test/ClassTestView"),
        children: [
          {
            path: "OpenBankingTest",
            name: "OpenBankingTest",
            component: () => import("./../views/lecture/test/OpenBankingTest"),
          },
          {
            path: "QuillEditorTest",
            name: "QuillEditorTest",
            component: () => import("./../views/lecture/test/QuillEditorTest"),
          },
          {
            path: "iamportTest",
            name: "iamportTest",
            component: () => import("./../views/lecture/test/iamportTest"),
          },
          {
            path: "AccountRealNameTest",
            name: "AccountRealNameTest",
            component: () =>
              import("./../views/lecture/test/AccountRealNameTest"),
          },
          {
            path: "AttdQRTest",
            name: "AttdQRTest",
            component: () => import("./../views/lecture/test/AttdQRTest"),
          },
          {
            path: "KakaoMapTest",
            name: "KakaoMapTest",
            component: () => import("./../views/lecture/test/KakaoMapTest"),
          },
        ],
      },
      {
        path: "list/:catg",
        name: "ClassList",
        component: () => import("./../views/lecture/list/listAll"),
        props: true,
      },
      {
        path: ":classId",
        name: "classDetail",
        component: () => import("./../views/lecture/detail/classDetail"),
        props: true,
      },
    ],
  },

  // moim
  {
    path: "/moimmain",
    name: "moimmain",
    component: () => import("./../views/moim/MoimMainView"),
  },
  {
    //소모임 생성
    path: "/moimRegisterIn",
    name: "moimRegisterIn",
    component: () => import("./../views/moim/MoimRegisterInView"),
  },
  {
    //게시글
    path: "/moimDetail",
    component: () => import("@/views/moim/MoimDetailView"),
    props: true,
    children: [
      {
        //게시판
        path: "",
        name: "moimBoard",
        component: () => import("@/views/moim/board/MoimBoardView"),
        props: true,
      },
      {
        //초대하기
        path: "moiminvite",
        name: "moiminvite",
        component: () => import("@/views/moim/setting/MoimInviteView"),
        props: true,
      },
      {
        path: "moimJoin",
        name: "moimJoin",
        component: () => import("@/views/moim/setting/MoimJoinView"),
        props: true,
      },
      {
        //게시물 단건조회
        path: "moimPost",
        name: "moimPost",
        component: () => import("./../views/moim/board/MoimPostView"),
        props: true,
      },
      {
        path: "moimboardwrite",
        name: "moimboardwrite",
        component: () => import("@/views/moim/board/MoimBoardWrite"),
        props: true,
      },
      {
        //사진첩
        path: "moimPhoto",
        name: "moimPhoto",
        component: () => import("@/views/moim/photo/MoimPhotoView"),
        props: true,
      },
      {
        //공지사항
        path: "moimNotice",
        name: "moimNotice",
        component: () => import("./../views/moim/notice/MoimNoticeView"),
        props: true,
      },
      {
        //일정
        path: "moimSchedule",
        name: "moimSchedule",
        component: () => import("./../views/moim/schedule/MoimScheduleView"),
        props: true,
      },
      {
        //투표
        path: "moimVote",
        name: "moimVote",
        component: () => import("./../views/moim/vote/MoimVoteView"),
        props: true,
      },
      {
        //투표 생성
        path: "makeVote",
        name: "makeVote",
        component: () => import("./../views/moim/vote/MakeVoteView"),
      },
      {
        //투표 디테일 메인
        path: "voteDetail",
        name: "voteDetail",
        component: () => import("./../views/moim/vote/VoteDetailView"),
      },
      {
        //N빵 메인
        path: "moimNbbang",
        name: "moimNbbang",
        component: () => import("./../views/moim/Nbbang/MoimNbbangView"),
        props: true,
      },
      {
        //N빵 생성
        path: "makeNbbang",
        name: "makeNbbang",
        component: () => import("./../views/moim/Nbbang/MakeNbbangView"),
        props: true,
      },
      {
        //N빵 디테일
        path: "nBBangDetail",
        name: "nBBangDetail",
        component: () => import("./../views/moim/Nbbang/NbbangDetailView"),
        props: true,
      },
      {
        //소모임 관리하기
        path: "moimSetting",
        name: "moimSetting",
        component: () => import("./../views/moim/setting/MoimSettingView"),
      },
      {
        //소모임 기본설정
        path: "registerSetting",
        name: "registerSetting",
        component: () => import("./../views/moim/setting/RegisterSettingView"),
      },
    ],
  },
  // challenge
  {
    path: "/challengemain",
    name: "challengemain",
    component: () => import("./../views/challenge/ChallengeMainView"),
  },
  {
    path: "/chat",
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
